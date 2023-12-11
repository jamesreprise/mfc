(ns mfc.server
  (:require
   [cheshire.core :as cheshire]
   [clojure.edn :as edn]
   [clojure.tools.logging :as log]
   [compojure.core :refer [GET defroutes]]
   [compojure.handler :as handler]
   [compojure.route :as route]
   [mfc.game :refer [example-2p-game]]
   [io.pedestal.http :as http])
  (:gen-class))

(defn response [status map]
  {:status status
   :headers {"Content-Type" "application/json"}
   :body (cheshire/generate-string map)})

(defn game [_]
  (response 200 (example-2p-game)))

(defroutes server-routes
  (GET "/" [] game)
  (route/not-found (response 404 {:status 404 :error "Not Found"})))

(def server
  (-> (handler/site server-routes)))

(defn -main [& _]
  (log/info "LOADING...")
  (let [config (clojure.edn/read-string
                (slurp "config/config.edn"))]
    (http/start )
    (log/info "READY.")))
