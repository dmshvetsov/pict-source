(ns pict-source.index
  (:require [hiccup.page :refer [html5]]))

(defn page []
  [:div {:class "content"} 
   [:div {:class "row"}
    [:div {:class "four columns"}
     [:h2 
      [:a {:href "/ru/"} "ru"]]
     [:p "Открытый словарь программистов,
         от программистов, для повышения культуры
         использовния собственного языка в процессе программировании."]]
    [:div {:class "four columns"}
     [:h2 
      [:a {:href "/ja/"} "ja"]]
     [:p "貢献者を探して"]]
    [:div {:class "four columns"}
     [:h2 
      [:a {:href "/zh/"} "zh"]]
     [:p "寻找贡献者"]]]])
