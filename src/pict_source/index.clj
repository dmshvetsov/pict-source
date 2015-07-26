(ns pict-source.index
  (:require [hiccup.page :refer [html5]]))

;;;; Index page template

(defn page [dictionaries]
  [:div {:class "content"} 
   [:div {:class "row"}
    [:div {:class "twelve column"}
     [:p
      "The programmers open dictionary from programmers for culture of using your own spoken language."
      " I highly encourage you to "
      [:a {:href "https://github.com/shvetsovdm/pict-source"} "contribute to dictionaries and to the project"]
      "."]]]
   (for [dict-row (partition-all 3 dictionaries)]
     [:div {:class "row"}
      (for [dict dict-row]
      [:div {:class "four columns"}
       [:h2
        [:a {:href (str "/" (:lang dict) "/")} (:lang dict)]]
       [:p (:description dict)]
       [:p (str (:words-count dict) " words")]])])])
