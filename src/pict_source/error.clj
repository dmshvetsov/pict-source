(ns pict-source.error
  (:require [hiccup.page :refer [html5]]))

(def message
  "Страница которую вы ищете не найдена.
  Возможно вы ошиблись и страницы не существует.
  Если вы уверены, что страница должна быть,
  будте так же уверены – мы исправимся и покажем вам ее в ближайшее время.")

(defn page []
  [:div {:class "content"}
   [:div {:class "row"}
    [:div {:class "twelve columns"} 
     [:h2 "Страница не найдена"]]]
   [:div {:class "row"}
    [:div {:class "twelve columns"} 
     [:p message]]]])
