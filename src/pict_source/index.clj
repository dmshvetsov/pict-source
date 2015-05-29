(ns pict-source.index
  (:require [hiccup.page :refer [html5]]))

(defn page []
  [:div {:class "content"} 
   [:div {:class "row"}
    [:div {:class "six columns"}
     [:p "Открытый словарь программистов, от программистов, для повышения культуры использовния собственного языка в программировании."]]
    [:div {:class "six columns"}
     [:p "Open programmers dictionary, by programmers, for the culture of using a national language in the programming environment."]]]
   [:div {:class "row"}
    [:div {:class "six columns"}
     [:p "Сайт в данный момент работает в демонстрационном режиме."]]
    [:div {:class "six columns"}
     [:p "The site is working for demo purpose for now."]]]
   [:div {:class "row"}
    [:div {:class "six columns"}
     [:p "Ищем людей для развития проекта, обращаться на "
      [:a {:href "mailto:shvetsovdm@gmail.com"} "shvetsovdm@gmail.com"]]]
    [:div {:class "six columns"}
     [:p "Desperately looking for native Engilsh speaking contributer, email to "
      [:a {:href "mailto:shvetsovdm@gmail.com"} "shvetsovdm@gmail.com"]]]]])
