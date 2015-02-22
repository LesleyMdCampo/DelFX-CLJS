(defproject delfx "0.1.0"
  :source-paths ["src-clj"]

  :dependencies [
    [org.clojure/clojure "1.5.1"]
    [org.clojure/clojurescript "0.0-2234"]
    [hiccups "0.3.0"]]

  :plugins [
    [lein-cljsbuild "1.0.3"]]

  :cljsbuild {
    :builds {

      :delfx {
        :source-paths ["delfx-src"]
        :compiler {
          :optimizations :whitespace
          :output-to "public/js/src.js"
        }
      }

      ; :delfx-adv {
      ;   :source-paths ["delfx-src"]
      ;   :compiler {
      ;     :externs [
      ;     "externs/jquery-1.9.js"
      ;     "externs/jquery-ui.js"
      ;     "externs/react-0.11.0.js"]
      ;   :output-to "public/js/src.min.js"
      ;   :optimizations :advanced
      ;   :pretty-print false }}
    }})