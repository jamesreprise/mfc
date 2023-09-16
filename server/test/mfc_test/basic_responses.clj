(ns mfc-test.basic-responses
  (:require [mfc-test.environment :refer [with-server]]
            [clojure.test :refer [deftest is testing]]
            [clojure.core.strint :refer [<<]]
            [org.httpkit.client :as http-client]))

(def port 4002)

(deftest basic-responses
  (testing "Basic responses."
    (testing "404"
      (with-server port
        #(is
          (= 404
             (-> @(http-client/get (<< "http://localhost:~{port}/not-found"))
                 (:status))))))))
