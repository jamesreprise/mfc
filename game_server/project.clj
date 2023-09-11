(defproject game_server "0.1.0-SNAPSHOT"
  :description "MFC Game Server"
  :license {:name "GNU AGPL v3"
            :url "https://www.gnu.org/licenses/agpl-3.0.en.html"}
  :dependencies [[org.clojure/clojure "1.10.3"]
                 [org.clojure/tools.logging "1.2.4"]
                 [ring "1.10.0"]
                 [compojure "1.7.0"]
                 [http-kit "2.3.0"]
                 [cheshire "5.11.0"]]
  :main game_server.core
  :aot [game_server.core]
  :profiles {:kaocha {:dependencies [[lambdaisland/kaocha "1.86.1355"]]}}
  :aliases {"kaocha" ["with-profile" "+kaocha" "run" "-m" "kaocha.runner"]}
  :ring {:handler game_server.core/server}
  :plugins [[lein-ring "0.12.6"]]
  :repl-options {:init-ns game_server.core})
