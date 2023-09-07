(ns game-server.core-test
  (:require [clojure.test :refer :all]
            [game-server.core :as game-server]
            [org.httpkit.server :as http-server]
            [org.httpkit.client :as http-client]
            [cheshire.core :as cheshire]))

(deftest smoke
  (testing "Smoke tests."
    (testing "Server boots, responds with anything."
      (let [server (http-server/run-server game-server/game {:port 3000})]
        (is
         (= (:status @(http-client/get "http://localhost:3000")) 200))
        (server)))
    (testing "First turn is 0."
      (let [server (http-server/run-server game-server/game {:port 3000})]
        (is
         (=
          (-> @(http-client/get "http://localhost:3000")
              (:body)
              (cheshire/parse-string true)
              (:turn))
          0))
        (server)))
    (testing "Player 0 in order goes first."
      (let [server (http-server/run-server game-server/game {:port 3000})]
        (is
         (=
          (-> @(http-client/get "http://localhost:3000")
              (:body)
              (cheshire/parse-string true)
              (:current-player))
          0))
        (server)))))
    