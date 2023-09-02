(ns game-server.core
  (:require [clojure.data.json :as json]))

(defn new-players [players]
  (into {} (map (fn [p] {(key p) (merge {:bux 1500 :position :go} (val p))}) players)))

(defn new-game [players]
  {:players (new-players players)
   :turn (nth (rand-nth (into [] players)) 0)})

(defn game [_]
  {:status 200
   :headers {"Content-Type" "application/json"}
   :body (json/write-str (new-game {"p1" {:piece :car} "p2" {:piece :hat}}))})
