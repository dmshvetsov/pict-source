(ns pict-source.dictionary
  (:require [clojure.java.io :as io]
            [cheshire.core :refer :all]))

(def source-dir "resources/dictionary")
(def dictionary-seq (file-seq (io/file source-dir)))

(defn only-json [files-seq]
  (filter #(and (.isFile %) (re-find #"\.json$" (.getName %))) files-seq))

(defn read-word [word-file]
  (parse-stream (io/reader word-file)))
