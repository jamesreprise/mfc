(ns mfc-test.environment
  (:require [mfc.server :as server]
            [org.httpkit.server :as http-server]))

(defn with-server [port f]
  (let [server (http-server/run-server server/server {:port port})]
    (f)
    (server)))