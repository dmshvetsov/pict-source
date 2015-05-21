(ns pict-source.site
  (:require [hiccup.page :refer [html5]]
            [optimus.html]))

(def title "Pict - The open programmers dictionary")

(defn layout [req content]
  (html5
    [:head
     [:meta {:charset "UTF-8"}]
     [:meta {:name "viewport" :content "width=device-width, initial-scale=1.0"}]
     [:title title]
     (optimus.html/link-to-css-bundles req ["style.css"])]
    [:body
     [:h1 title]
     [:div content]]))
