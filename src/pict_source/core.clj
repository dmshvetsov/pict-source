(ns pict-source.core
  (:require [clojure.java.io :as io]
            [optimus.prime :as optimus]
            [optimus.assets :as assets]
            [optimus.export]
            [optimus.optimizations :as assets-optimizations]
            [optimus.strategies :as assets-strategies]
            [pict-source.config :as config]
            [pict-source.site :as site]
            [pict-source.index]
            [pict-source.dictionary :as dictionary]
            [pict-source.lang]
            [pict-source.error]
            [stasis.core :as stasis])
  (use [hiccup.core]
       [ring.middleware.content-type]
       [ring.middleware.not-modified]))

;;;; Core functionality

;;; Vars

(def export-dir "builds")
(def dictionaries-dir "resources/dictionary")
(def dictionary-seq (file-seq (io/file dictionaries-dir)))

;;; Pages

(defn index [req]
  (let [dictionaries (dictionary/ordered-by-words-count
                       (dictionary/available-collection (io/file dictionaries-dir))
                       dictionary-seq
                       dictionaries-dir)]
    (site/layout req
                 config/site-name
                 config/site-title
                 (html (pict-source.index/page dictionaries)))))

(defn lang [param-lang req]
  (site/layout req
               config/site-name
               (:title (dictionary/lang-data param-lang dictionaries-dir))
               (html (pict-source.lang/page (dictionary/words-map param-lang)))))

(defn error [req]
  (site/layout req
              config/site-name
              "Ошибка"
              (html (pict-source.error/page))))

;;; Site builder

(defn lang-routes []
  (reduce
    (fn [acc param-lang] (into acc {(str "/" param-lang "/") (partial lang param-lang)}))
    {}
    (dictionary/available-collection (io/file dictionaries-dir))))

(def site-pages (merge
                    {"/" index "/error/" error}
                    (lang-routes)))

(defn public-assets []
  (assets/load-bundle "assets/css"
                      "style.css"
                      ["/normalize.css" "/skeleton.css" "/main.css"]))

(defn build-assets [assets]
  (as-> assets a
      (remove :bundled a)
      (remove :outdated a)
      (optimus.export/save-assets a export-dir)))

(defn add-config-to-build []
  (io/copy (io/file "resources/config/divshot.json") (io/file "builds/divshot.json")))

(defn build []
  (let [optimized-assets (assets-optimizations/all (public-assets) {})]
    (stasis/empty-directory! export-dir)
    (build-assets optimized-assets)
    (stasis/export-pages site-pages export-dir {:optimus-assets optimized-assets})
    (add-config-to-build)))

;;; Development server

(defn wrap-utf-8
  "This function works around the fact that Ring simply chooses the default JVM
  encoding for the response encoding. This is not desirable, we always want to
  send UTF-8.
  https://github.com/cjohansen/cjohansen-no/blob/master/src/cjohansen_no/web.clj#L16"
  [handler]
  (fn [request]
    (when-let [response (handler request)]
      (if (.contains (get-in response [:headers "Content-Type"]) ";")
        response
        (if (string? (:body response))
          (update-in response [:headers "Content-Type"] #(str % "; charset=utf-8"))
          response)))))

(def server (-> (stasis/serve-pages site-pages)
                (optimus/wrap
                  public-assets assets-optimizations/all assets-strategies/serve-live-assets)
                ring.middleware.content-type/wrap-content-type
                ring.middleware.not-modified/wrap-not-modified
                wrap-utf-8))
