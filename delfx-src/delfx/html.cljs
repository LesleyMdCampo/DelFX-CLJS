(ns delfx.html
  (:require-macros [hiccups.core :as hiccups])
  (:require
    hiccups.runtime))

;;------------------------------------------------------------------------------
;; Body
;;------------------------------------------------------------------------------

(hiccups/defhtml body []
  [:h2 "Hello, World"])