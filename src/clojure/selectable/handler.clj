(ns selectable.handler
  (:require [ring.util.response :refer [response]]
            [compojure.core :refer [defroutes GET]]
            [compojure.route :refer [resources]]
            [compojure.handler :refer [api]]
            [hiccup.page :refer [html5 include-css include-js]]
            cemerick.austin.repls))

(defn page-frame []
  (html5
   [:head
    [:title "selectable - CLJS Single Page Web Application"]
    (include-js "//cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js")
    (include-js "//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js")
    (include-css "//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css")

    (include-js "/js/selectable.js")]
   [:body
    [:div.container
     [:div#content]
     [:script (cemerick.austin.repls/browser-connected-repl-js)]]]))

(defroutes app-routes
  (GET "/" [] (response (page-frame)))
  (resources "/js" {:root "js"}))

(def app 
  (-> app-routes
      api))
