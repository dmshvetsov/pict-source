(ns pict-source.dictionary_test
  (:require [clojure.test :refer :all]
            [clojure.java.io :as io]
            [pict-source.dictionary :refer :all]))

(def test-source-dir "test/support/data/dictionary")

(def test-file-seq
  (file-seq (io/file test-source-dir)))

(def test-file
  (io/file (str test-source-dir "/a_lang/a/a.json")))

(def test-files
  (io/file test-source-dir))

(def test-lang-seq
  (seq [(io/file (str test-source-dir "/a_lang/a/a.json"))
   (io/file (str test-source-dir "/a_lang/b/b.json"))
   (io/file (str test-source-dir "/a_lang/c/c.json"))]))

(def test-words-seq
  (seq [{:letter "A", :word "A", :translation "a"}
        {:letter "B", :word "B", :translation "b"}
        {:letter "C", :word "C", :translation "c"}]))

(deftest describe-only-json
  (testing "filter json in file sequence"
    (is (every? #(re-find #"\.json$" (.getName %)) (only-json test-file-seq)))))

(deftest describe-only-dir
  (testing "filter directories in file sequence"
    (is (every? #(.isDirectory %) (only-dir test-file-seq)))))

(deftest describe-only-lang
  (testing "return sequence of words of given lang"
    (is (= (only-lang "a_lang" test-file-seq)
           test-lang-seq))))

(deftest describe-first-letter
  (testing "return first letter of file name"
    (is (= (first-letter (io/file "word.json"))
           \w))))

(deftest describe-read-word
  (testing "return keywords value map from json java file object"
    (is (= (read-word test-file)
           {:translation "a"}))))

(deftest describe-parse-files
  (testing "return collection of words"
    (let [expected [{:letter "A", :word "A", :translation "a"}
                    {:letter "B", :word "B", :translation "b"}
                    {:letter "C", :word "C", :translation "c"}]]
      (is (= (parse-files test-lang-seq)
             expected)))))

(deftest describe-group-by-letter
  (testing "groups words sequence by first letter"
    (is (= (group-by-letter test-words-seq)
           {"A" [{:letter "A", :translation "a", :word "A"}]
            "B" [{:letter "B", :translation "b", :word "B"}]
            "C" [{:letter "C", :translation "c", :word "C"}]})))
  (testing "groups words with same letter"
    (is (= (group-by-letter [{:letter "Z" :word "za" :translation "za"}
                             {:letter "Z" :word "zb" :translation "zb"}
                             {:letter "Z" :word "zc" :translation "zc"}])
           {"Z" [{:letter "Z", :translation "za", :word "za"}
                 {:letter "Z", :translation "zb", :word "zb"}
                 {:letter "Z", :translation "zc", :word "zc"}]}))))

(deftest describe-lang-seq
  (testing "return only json files"
    (is (every? #(re-find #"\.json$" (.getName %)) (lang-seq "a_lang" test-file-seq)))
    (is (every? #(.isFile %) (lang-seq "a_lang" test-file-seq))))
  (testing "return sequence"
    (is (seq? (lang-seq "a_lang" test-file-seq))))
  (testing "return not empty sequence for an existing language"
    (is (not (empty? (lang-seq "a_lang" test-file-seq)))))
  (testing "return empty sequence for an nonexisten language"
    (is (empty? (lang-seq "abra" test-file-seq)))
    (is (= (lang-seq "abra" test-file-seq) []))))

(deftest describe-lang-data
  (testing "return language data map for an existing language")
  (testing "return map for an existing language"
    (is (instance? (type {}) (lang-data "a_lang" test-source-dir))))
  (testing "return map for a nonexisten language"
    (is (instance? (type {}) (lang-data "cadabra" test-source-dir)))))

(deftest describe-words-map
  (testing "return a array seq"
    (is (instance? clojure.lang.ArraySeq (words-map "ru"))))
  (testing "return collection of words map grouped by a letter")
  (testing "return collection in alphabetic order"))

(deftest describe-available-collection
  (testing "return a lazy sequence"
    (is (instance? clojure.lang.LazySeq (available-collection test-files))))
  (testing "return collection of strings"
    (is (every? #(instance? String %) (available-collection test-files))))
  (testing "return collection of languages in the dictionary"
    (is (= ["a_folder" "a_lang"] (available-collection test-files)))))

(deftest describe-sorted-by-words-count
  (testing "return collection of langs data"
    (is (= [{:lang "a_lang" :words-count 3 :description "this is test lang"}]
           (ordered-by-words-count ["a_lang"] test-file-seq test-source-dir))))
  (testing "return collection of sorted by amount words in language dictionary in asc order"))
