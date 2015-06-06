(ns pict-source.core
  (:require [clojure.java.io :as io]
            [optimus.prime :as optimus]
            [optimus.assets :as assets]
            [optimus.export]
            [optimus.optimizations :as assets-optimizations]
            [optimus.strategies :as assets-strategies]
            [pict-source.site :as site]
            [pict-source.index]
            [pict-source.dictionary :as dictionary]
            [pict-source.lang]
            [pict-source.error]
            [stasis.core :as stasis])
  (use [hiccup.core]
       [ring.middleware.content-type]
       [ring.middleware.not-modified]))

(def export-dir "builds")

(defn index [req]
  (site/layout req (html (pict-source.index/page))))

(defn lang [param-lang req]
  (site/layout req (html (pict-source.lang/page (dictionary/words-map param-lang)))))

(defn lang-routes []
  (reduce
    (fn [acc param-lang] (into acc {(str "/" param-lang "/") (partial lang param-lang)}))
    {}
    (dictionary/langs-available)))

(defn error [req]
  (site/layout req (html (pict-source.error/page))))

(defn public-assets []
  (assets/load-bundle "assets/css"
                      "style.css"
                      ["/normalize.css" "/skeleton.css" "/main.css"]))

(def public-pages (merge
                    {"/" index "/error/" error}
                    (lang-routes)))

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
    (stasis/export-pages public-pages export-dir {:optimus-assets optimized-assets})
    (add-config-to-build)))

(def server (-> (stasis/serve-pages public-pages)
                (optimus/wrap
                  public-assets assets-optimizations/all assets-strategies/serve-live-assets)
                ring.middleware.content-type/wrap-content-type
                ring.middleware.not-modified/wrap-not-modified))
