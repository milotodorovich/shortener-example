(ns shortener.env
  (:require
   [clojure.edn :as edn]
   [clojure.java.io :as jio]))

(def envvars (if (.exists (jio/file "env.edn"))
               (edn/read-string (slurp "env.edn"))
               {}))

(defn env [k]
  (or (k envvars)
      (System/getenv (name k))))

(comment
  (in-ns 'shortener.env)
  (env :HOST)
;;
  )
