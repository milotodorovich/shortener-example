(ns shortener.db
  (:require
   [clojure.java.jdbc :as j]
   [honey.sql :as sql]
   [honey.sql.helpers :as h]
   [shortener.env :as env]))

(def mysql-db {:host (env/env :HOST)
               :dbtype "mysql"
               :dbname (env/env :DBNAME)
               :user (env/env :USER)
               :password (env/env :PASSWORD)})

(defn query [q]
  (j/query mysql-db q))

(defn insert! [q]
  (j/db-do-prepared mysql-db q))

(defn insert-redirect! [slug url]
  (insert! (-> (h/insert-into :redirects)
               (h/columns :slug :url)
               (h/values [[slug url]])
               (sql/format))))

(defn get-url [slug]
  (-> (query (-> (h/select :*)
                 (h/from :redirects)
                 (h/where [:= :slug slug])
                 (sql/format)))
      first
      :url))

(comment
  (in-ns 'shortener.db)
  (query (-> (h/select :*)
             (h/from :redirects)
             (sql/format)))

  (insert! (-> (h/insert-into :redirects)
               (h/columns :slug :url)
               (h/values [["abc" "https://www.youtube.com/watch?v=0mrguRPgCzI"]])
               (sql/format)))

  (insert-redirect! "xyz" "https://github.com/reagent-project/reagent")
  (get-url "abc")
  (get-url "xyz")
  ;;
  )
