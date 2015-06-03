(ns pict-source.dictionary
  (:require [clojure.java.io :as io]
            [clojure.string :as string]
            [cheshire.core :refer :all]))

(def source-dir "resources/dictionary")
(def dictionary-seq (file-seq (io/file source-dir)))

(defn- only-json [files-seq]
  (filter #(and (.isFile %) (re-find #"\.json$" (.getName %))) files-seq))

(defn- only-lang [lang files-seq]
  (filter #(= lang (nth (reverse (string/split (.getName %) #"/")) 2)) files-seq))

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

(defn lang-map [lang]
  (->> dictionary-seq
      only-json
      ;; FIXME
      ;; only-lang lang
      parse-files
      group-by-letter))
