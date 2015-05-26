(ns pict-source.core
  (:require [clojure.java.io :as io]
            [optimus.prime :as optimus]
            [optimus.assets :as assets]
            [optimus.optimizations :as assets-optimizations]
            [optimus.strategies :as assets-strategies]
            [pict-source.site :as site]
            [pict-source.index :as index]
            [stasis.core :as stasis])
  (use [hiccup.core]
       [ring.middleware.content-type]
       [ring.middleware.not-modified]))

(defn index [req]
  (site/layout req (html (index/page))))

(defn public-assets []
  (assets/load-bundle "assets/css"
                      "style.css"
                      ["/normalize.css" "/skeleton.css" "/main.css"]))

(def public-pages {"/" index})

(def server (-> (stasis/serve-pages public-pages)
                (optimus/wrap
                  public-assets assets-optimizations/all assets-strategies/serve-live-assets)
                ring.middleware.content-type/wrap-content-type
                ring.middleware.not-modified/wrap-not-modified))
