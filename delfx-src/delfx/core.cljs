(ns delfx.core
  (:require
    [delfx.html :as html]))

(def $ js/jQuery)

(defn- init! []
  ;; load HTML on the page
  (.prepend ($ "body") (html/body)))

($ init!)