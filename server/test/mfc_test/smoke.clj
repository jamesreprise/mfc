(ns mfc-test.smoke
  (:require [mfc-test.environment :refer [with-server]]
            [clojure.test :refer [deftest is testing]]
            [clojure.core.strint :refer [<<]]
            [org.httpkit.client :as http-client]
            [cheshire.core :as cheshire]))

(def port 4001)

(deftest smoke
  (testing "Smoke tests."
    (testing "Server boots, responds with anything."
      (with-server port
        #(is
          (= 200 (:status @(http-client/get (<< "http://localhost:~{port}")))))))
    (testing "First turn is 0."
      (with-server port
        #(is
          (= 0
             (-> @(http-client/get (<< "http://localhost:~{port}"))
                 (:body)
                 (cheshire/parse-string true)
                 (:turn))))))
    (testing "First player goes first."
      (with-server port
        #(is
          (= 0
             (-> @(http-client/get (<< "http://localhost:~{port}"))
                 (:body)
                 (cheshire/parse-string true)
                 (:current-player))))))))

