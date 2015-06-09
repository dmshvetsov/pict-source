(ns pict-source.index
  (:require [hiccup.page :refer [html5]]))

(defn page [dictionaries]
  [:div {:class "content"} 
   [:div {:class "row"}
    (for [dict dictionaries]
      [:div {:class "four columns"}
       [:h2
        [:a {:href (str "/" (:lang dict) "/")} (:lang dict)]]
       [:p "Description not available"]
       [:p (str (:words-count dict) " words")]])]])
