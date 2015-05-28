(defproject pict-source "0.0.1-SNAPSHOT"
  :description "Open multy language programming(er) dictionary."
  :url "http://pict.github.io"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [stasis "2.2.2"]
                 [ring "1.3.2"]
                 [optimus "0.17.1"]
                 [hiccup "1.0.5"]]
  :plugins [[lein-ring "0.9.3"]]
  :aliases {"build" ["run" "-m" "pict-source.core/build"]}
  :ring {:handler pict-source.core/server})
