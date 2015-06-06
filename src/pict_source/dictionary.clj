(ns pict-source.dictionary
  (:require [clojure.java.io :as io]
            [clojure.string :as string]
            [cheshire.core :refer :all]))

(def source-dir "resources/dictionary")
(def dictionary-seq (file-seq (io/file source-dir)))

(defn- only-json [files-seq]
  (filter #(and (.isFile %) (re-find #"\.json$" (.getName %))) files-seq))

(defn- only-dir [files-seq]
  (filter #(.isDirectory %) files-seq))

(defn- only-lang [lang files-seq]
  (filter #(-> % .getPath (string/split #"/") reverse (nth 2) (= lang)) files-seq))

(defn- first-letter [word-file]
  (first (.getName word-file)))

(defn- read-word [word-file]
  (parse-stream (io/reader word-file) true))

(defn- parse-files [files-seq]
  (map #(into
          {:letter (string/capitalize (first-letter %))
           :word (string/capitalize (string/replace (.getName %) #"\.json" ""))}
          (read-word %)) files-seq))

(defn- group-by-letter [words-seq]
  (group-by :letter words-seq))

(defn words-map [lang]
  (group-by-letter (parse-files (only-lang lang (only-json dictionary-seq)))))

(defn langs-available []
  (map #(.getName %) (only-dir (.listFiles (io/file source-dir)))))
