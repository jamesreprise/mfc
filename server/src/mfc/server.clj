(ns mfc.server
  (:require
   [mfc.game :refer [new-game turn]]
   [clojure.tools.logging :as log]
   [cheshire.core :as cheshire]
   [org.httpkit.server :as http-server]
   [compojure.core :refer [GET defroutes]]
   [compojure.route :as route]
   [compojure.handler :as handler])
  (:gen-class))

(defn response [status map]
  {:status status
   :headers {"Content-Type" "application/json"}
   :body (cheshire/generate-string map)})

(def example-2p-game
  (new-game {"p1" {:piece :car} "p2" {:piece :hat}}))

(defn game [_]
  (response 200 example-2p-game))

(defroutes server-routes
  (GET "/" [] game)
  (route/not-found (response 404 {:status 404 :error "Not Found"})))

(def server
  (-> (handler/site server-routes)))

(defn -main [& _]
  (log/info "LOADING...")
  (http-server/run-server server {:port 3000})
  (log/info "READY"))
