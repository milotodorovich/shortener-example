(ns shortener.slug)

(def charset "abcdefghijklmnopqrstuvwxyz")

(defn generate []
  (->> (repeatedly #(rand-nth charset))
       (take 5)
       (apply str)))

(comment
  (shortener.slug/generate)
  ;;
  )
