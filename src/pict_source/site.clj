(ns pict-source.site
  (:require [hiccup.page :refer [html5]]
            [optimus.html]
            [pict-source.dictionary :as dictionary]))

(def title "Pict")
(def slogan "открытый словарь программистов")
(def google-analytics-code
  "<script>
    (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
    (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
    m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
    })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

    ga('create', 'UA-47078794-4', 'auto');
    ga('send', 'pageview');

  </script>")

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
        [:h1
         [:a {:href "/" :class "logo--link"} title]]
        [:div {:class "slogan"} slogan]]
       [:div {:class "six columns"}
        [:nav
         (for [lang (dictionary/langs-available)]
           [:a {:href (str "/" lang "/")} lang])]]]
      content
      [:footer {:class "row"}
       [:p {:class "copyright"}
        "&copy; 2015 "
        [:a {:href "http://shvetsovdm.github.io"}
         "Дмитрий Швецов / Dmitry Shvetsov (shvetsovdm)"]]]]
     google-analytics-code]))
