(ns pict-source.store.dictionary
  (:require [cheshire.core :refer :all]))

;;;;  Accessor for json dictionaries file

(def store-directory "resources/dictionary")
(def store-seq (file-seq (io/file source-dir)))
