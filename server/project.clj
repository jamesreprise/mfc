(defproject mfc "0.1.0-SNAPSHOT"
  :description "MFC Server"
  :license {:name "GNU AGPL v3"
            :url "https://www.gnu.org/licenses/agpl-3.0.en.html"}
  :dependencies [[org.clojure/clojure "1.10.3"]
                 [org.clojure/core.async "1.6.673"]
                 [org.clojure/core.incubator "0.1.4"]
                 [org.clojure/tools.logging "1.2.4"]
                 [ch.qos.logback/logback-classic "1.4.11"]
                 [ring "1.10.0"]
                 [compojure "1.7.0"]
                 [http-kit "2.3.0"]
                 [cheshire "5.11.0"]]
  :main mfc.server
  :aot [mfc.server]
  :profiles {:kaocha {:dependencies [[lambdaisland/kaocha "1.86.1355"]]}}
  :aliases {"kaocha" ["with-profile" "+kaocha" "run" "-m" "kaocha.runner"]}
  :ring {:handler mfc.server/server}
  :plugins [[lein-ring "0.12.6"]]
  :repl-options {:init-ns mfc.server})
