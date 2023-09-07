(defproject game_server "0.1.0-SNAPSHOT"
  :description "MFC Game Server"
  :license {:name "GNU AGPL v3"
            :url "https://www.gnu.org/licenses/agpl-3.0.en.html"}
  :dependencies [[org.clojure/clojure "1.10.3"]
                 [cheshire "5.11.0"]
                 [ring "1.10.0"]
                 [http-kit "2.3.0"]]
  :profiles {:kaocha {:dependencies [[lambdaisland/kaocha "1.86.1355"]]}}
  :aliases {"kaocha" ["with-profile" "+kaocha" "run" "-m" "kaocha.runner"]}
  :ring {:handler game-server.core/game}
  :plugins [[lein-ring "0.12.6"]]
  :repl-options {:init-ns game-server.core})
