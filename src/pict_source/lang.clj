(ns pict-source.lang
  (:require [hiccup.page :refer [html5]]
            [pict-source.dictionary :as dictionary]))

(defn page []
  (let [lang-map (dictionary/lang-map "ru")]
    [:div {:class "content"}
     (for [words-by-letter lang-map]
       [:div
        [:div {:class "row"}
         [:div {:class "twelve columns pictionary--letter"}
          [:h2 (first words-by-letter)]]]
        (for [word-map (last words-by-letter)]
          [:div {:class "row"}
           [:div {:class "two columns pictionary--letter"} "&nbsp;"]
           [:div {:class "five columns pictionary--word"} (:word word-map)]
           [:div {:class "five columns pictionary--word"} (:translation word-map)]])])]))
