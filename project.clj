(defproject gd "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.11.0-alpha3"]
                 [org.clojure/tools.namespace "0.2.11"]
                 [clojure2d "1.4.3"]]
  :profiles {:dev {:source-paths ["dev"]
                   :repl-options {:init-ns user}}})