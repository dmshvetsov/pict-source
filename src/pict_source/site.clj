(ns pict-source.site
  (:require [hiccup.page :refer [html5]]
            [optimus.html]
            [pict-source.dictionary :as dictionary]))

;;;; Site layout template

(def google-analytics-code
  "<script>
    (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
    (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
    m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
    })(window,document,'script','//www.google-analytics.com/analytics.js','ga');
    ga('create', 'UA-47078794-4', 'auto');
    ga('send', 'pageview');
  </script>")

(def twitter-share-button
  "<a href=\"https://twitter.com/share\" class=\"twitter-share-button\"
    data-url=\"http://pict.divshot.io\"
    data-text=\"Check this out: #pict the open, multi language programmers dictionary\">
      Tweet
  </a>
  <script>
    !function(d,s,id){
      var js,fjs=d.getElementsByTagName(s)[0],
      p=/^http:/.test(d.location)?'http':'https';
      if(!d.getElementById(id)){
        js=d.createElement(s);js.id=id;
        js.src=p+'://platform.twitter.com/widgets.js';
        fjs.parentNode.insertBefore(js,fjs);
    }}(document, 'script', 'twitter-wjs');
  </script>")

(defn layout [req title page-title-suffix content]
  (html5
    [:head
     [:meta {:charset "UTF-8"}]
     [:meta {:name "viewport" :content "width=device-width, initial-scale=1.0"}]
     [:title (str title " - " page-title-suffix)]
     (optimus.html/link-to-css-bundles req ["style.css"])]
    [:body
     [:div {:class "container"}
      [:header {:class "row"}
       [:div {:class "twelve columns"}
        [:a {:href "/" :class "logo--link"} title]
        [:div {:class "slogan"}
         [:h1 page-title-suffix]]]
       [:div {:class "six columns"}]]
      content
      [:footer {:class "row"}
       [:div {:class "social-buttons"} twitter-share-button]
       [:p {:class "copyright"}
        "&copy; 2015 "
        [:a {:href "http://shvetsovdm.github.io"}
         "Dmitry Shvetsov (@shvetsovdm)"]]]]
     google-analytics-code]))
