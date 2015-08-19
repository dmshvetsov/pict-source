(ns pict-source.dictionary_test
  (:require [clojure.test :refer :all]
            [clojure.java.io :as io]
            [pict-source.dictionary :refer :all]))

(def test-file-seq
  (file-seq (io/file "test/support/data/dictionary")))

(def test-file
  (io/file "test/support/data/dictionary/a_lang/a/a.json"))

(def test-files
  (io/file "test/support/data/dictionary"))

(def test-lang-seq
  (seq [(io/file "test/support/data/dictionary/a_lang/a/a.json")
   (io/file "test/support/data/dictionary/a_lang/b/b.json")
   (io/file "test/support/data/dictionary/a_lang/c/c.json")]))

(def test-words-seq
  (seq [{:letter "A", :word "A", :content "a"}
        {:letter "B", :word "B", :content "b"}
        {:letter "C", :word "C", :content "c"}]))

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
           {:content "a"}))))

(deftest describe-parse-files
  (testing "return collection of words"
    (let [expected [{:letter "A", :word "A", :content "a"}
                    {:letter "B", :word "B", :content "b"}
                    {:letter "C", :word "C", :content "c"}]]
      (is (= (parse-files test-lang-seq)
             expected)))))

(deftest describe-group-by-letter
  (testing "groups words sequence by first letter"
    (is (= (group-by-letter test-words-seq)
           {"A" [{:letter "A", :content "a", :word "A"}]
            "B" [{:letter "B", :content "b", :word "B"}]
            "C" [{:letter "C", :content "c", :word "C"}]})))
  (testing "groups words with same letter"
    (is (= (group-by-letter [{:letter "Z" :word "za" :content "za"}
                             {:letter "Z" :word "zb" :content "zb"}
                             {:letter "Z" :word "zc" :content "zc"}])
           {"Z" [{:letter "Z", :content "za", :word "za"}
                 {:letter "Z", :content "zb", :word "zb"}
                 {:letter "Z", :content "zc", :word "zc"}]}))))

(deftest describe-lang-seq
  (testing "return only json files"
    (is (every? #(re-find #"\.json$" (.getName %)) (lang-seq "ru")))
    (is (every? #(.isFile %) (lang-seq "ru"))))
  (testing "return sequence"
    (is (seq? (lang-seq "ru"))))
  (testing "return not empty sequence for an existing language"
    (is (not (empty? (lang-seq "ru")))))
  (testing "return empty sequence for an nonexisten language"
    (is (empty? (lang-seq "abra")))
    (is (= (lang-seq "abra") []))))

(deftest describe-lang-data
  (testing "return language data map for an existing language")
  (testing "return map for an existing language"
    (is (instance? (type {}) (lang-data "ru"))))
  (testing "return map for a nonexisten language"
    (is (instance? (type {}) (lang-data "cadabra")))))

(deftest describe-words-map
  (testing "return a array seq"
    (is (instance? clojure.lang.ArraySeq (words-map "ru"))))
  (testing "return collection of words map grouped by a letter")
  (testing "return collection in alphabetic order"))

(deftest describe-dictionaries-available
  (testing "return a lazy sequence"
    (is (instance? clojure.lang.LazySeq (dictionaries-available test-files))))
  (testing "return collection of strings"
    (is (every? #(instance? String %) (dictionaries-available test-files))))
  (testing "return collection of languages in the dictionary"
    (is (= ["a_folder" "a_lang"] (dictionaries-available test-files)))))

(deftest describe-langs-sorted-by-words-count
  (testing "return collection of langs data")
  (testing "return collection of sorted by amount words in language dictionary in asc order"))
