(ns pict-source.site
  (:require [hiccup.page :refer [html5]]))

(def title "Pict - The open programmers dictionary")

(defn layout [content]
  (html5
    [:head
     [:meta {:charset "UTF-8"}]
     [:meta {:name "viewport" :content "width=device-width, initial-scale=1.0"}]
     [:title title]]
    [:body 
     [:h1 title]
     [:div content]]))
