(ns shortener.env)

(def envvars (clojure.edn/read-string (slurp "env.edn")))

(defn env [k]
  (k envvars))

(comment
  (in-ns 'shortener.env)
  (env :HOST)
;;
  )
