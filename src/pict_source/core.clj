(ns pict-source.core
  (:require [clojure.java.io :as io]
            [pict-source.site :as site]
            [stasis.core :as stasis]))

(defn index [req]
  (site/layout (slurp (io/resource "index.html"))))

(def pages {"/" index})

(def server (stasis/serve-pages pages))
