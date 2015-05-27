(ns pict-source.lang
  (:require [hiccup.page :refer [html5]]))

(defn page []
  [:div {:class "content"}
   [:div {:class "row"}
    [:div {:class "twelve columns pictionary--letter"} 
     [:h2 "A"]]]
   [:div {:class "row"}
    [:div {:class "two columns"} "&nbsp;"]
    [:div {:class "five columns pictionary--word"} "Абстракция данных"]
    [:div {:class "five columns pictionary--word"} "Abstraction"]]
   [:div {:class "row"}
    [:div {:class "two columns pictionary--letter"} "&nbsp;"]
    [:div {:class "five columns pictionary--word"} "Алгоритм"]
    [:div {:class "five columns pictionary--word"} "Algorithm"]]])
