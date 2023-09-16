(ns mfc.server-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure.core.strint :refer [<<]]
            [mfc.server :as server]
            [org.httpkit.server :as http-server]
            [org.httpkit.client :as http-client]
            [cheshire.core :as cheshire]))

(def default-port 3000)

(deftest smoke
  (testing "Smoke tests."
    (testing "Server boots, responds with anything."
      (let [server (http-server/run-server server/game {:port default-port})]
        (is
         (= (:status @(http-client/get (<< "http://localhost:~{default-port}"))) 200))
        (server)))
    (testing "First turn is 0."
      (let [server (http-server/run-server server/game {:port default-port})]
        (is
         (=
          (-> @(http-client/get (<< "http://localhost:~{default-port}"))
              (:body)
              (cheshire/parse-string true)
              (:turn))
          0))
        (server)))
    (testing "Player 0 in order goes first."
      (let [server (http-server/run-server server/game {:port default-port})]
        (is
         (=
          (-> @(http-client/get (<< "http://localhost:~{default-port}"))
              (:body)
              (cheshire/parse-string true)
              (:current-player))
          0))
        (server)))))
    