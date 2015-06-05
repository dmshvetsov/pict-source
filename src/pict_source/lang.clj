(ns pict-source.lang
  (:require [hiccup.page :refer [html5]]))

(defn page [words-map]
  [:div {:class "content"}
   (for [words-by-letter words-map]
     (list
       [:div {:class "row"}
        [:div {:class "twelve columns pictionary--letter"}
         [:h2 (first words-by-letter)]]]
       (for [word-map (last words-by-letter)]
         [:div {:class "row"}
          [:div {:class "two columns"} "&nbsp;"]
          [:div {:class "five columns pictionary--word"} (:word word-map)]
          [:div {:class "five columns pictionary--word"} (:translation word-map)]])))])
