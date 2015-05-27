(ns pict-source.index
  (:require [hiccup.page :refer [html5]]))

(defn page []
  [:div {:class "content"} 
   [:div {:class "row"}
    [:p "Открытый словарь программистов, от программистов, для повышения культуры использовния собственного языка в программировании."]]])
