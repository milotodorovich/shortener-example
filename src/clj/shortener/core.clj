(ns shortener.core
  (:require
   [ring.adapter.jetty :as ring-jetty]
   [ring.util.response :as r]
   [reitit.ring :as ring]
   [muuntaja.core :as m]
   [reitit.ring.middleware.muuntaja :as muuntaja]
   [shortener.db :as db]
   [shortener.slug :as slug]
   [clojure.java.io :as io]))

(defn index []
  (slurp (io/resource "public/index.html")))

(defn redirect [req]
  (let [slug (get-in req [:path-params :slug])
        url (db/get-url slug)]
    (if url
      (r/redirect url 307)
      (r/not-found "Not found")))
  ;;
  )

(defn create-redirect [req]
  (let [url (get-in req [:body-params :url])
        slug (slug/generate)]
    (db/insert-redirect! slug url)
    (r/response {:slug slug})))

(def app
  (ring/ring-handler
   (ring/router
    ["/"
     [":slug" redirect]
     [":slug/" redirect]
     ["api/"
      ["redirect/" {:post create-redirect}]]
     ["assets/*" (ring/create-resource-handler {:root "public/assets"})]
     ["" {:handler (fn [_] {:body (index) :status 200})}]]
    {:data {:muuntaja m/instance
            :middleware [muuntaja/format-middleware]}})))

(defn start []
  (ring-jetty/run-jetty #'app {:port 3001
                               :join? false}))

(comment
  (def server (start))
  (.stop server)

  (require '[clojure.tools.namespace.repl :refer [refresh]])
  (clojure.tools.namespace.repl/refresh)
;;
  )





