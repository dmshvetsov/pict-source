(ns pict-source.index
  (:require [hiccup.page :refer [html5]]))

;;;; Index page template

(defn page [dictionaries]
  [:div {:class "content"} 
   (for [dict-row (partition-all 3 dictionaries)]
     [:div {:class "row"}
      (for [dict dict-row]
      [:div {:class "four columns"}
       [:h2
        [:a {:href (str "/" (:lang dict) "/")} (:lang dict)]]
       [:p (:description dict)]
       [:p (str (:words-count dict) " words")]])])])
