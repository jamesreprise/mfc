(defproject game_server "0.1.0-SNAPSHOT"
  :description "MFC Game Server"
  :license {:name "GNU AGPL v3"
            :url "https://www.gnu.org/licenses/agpl-3.0.en.html"}
  :dependencies [[org.clojure/clojure "1.10.3"]
                 [org.clojure/data.json "2.4.0"]
                 [ring "1.10.0"]]
  :ring {:handler game-server.core/game}
  :plugins [[lein-ring "0.12.6"]]
  :repl-options {:init-ns game-server.core})
