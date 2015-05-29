(ns pict-source.site
  (:require [hiccup.page :refer [html5]]
            [optimus.html]))

(def title "Pict")

(defn layout [req content]
  (html5
    [:head
     [:meta {:charset "UTF-8"}]
     [:meta {:name "viewport" :content "width=device-width, initial-scale=1.0"}]
     [:title title]
     (optimus.html/link-to-css-bundles req ["style.css"])]
    [:body
     [:div {:class "container"}
      [:header {:class "row"}
       [:div {:class "six columns"} 
        [:h1 title]]
       [:div {:class "six columns"}
        [:nav
         [:a {:href "/lang/"} "ru"]
         [:a {:href "/lang/"} "en"]]]]
      content
      [:footer {:class "row"}
       [:p 
        "&copy; 2015 "
        [:a {:href "http://shvetsovdm.github.io"}
         "Дмитрий Швецов/Dmitry Shvetsov (shvetsovdm)"]]]]]))
