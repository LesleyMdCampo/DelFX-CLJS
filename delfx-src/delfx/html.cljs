(ns delfx.html
  (:require-macros [hiccups.core :as hiccups])
  (:require
    [delfx.dom :as dom]
    [delfx.util :as util]
    [hiccups.runtime]))

(def $ js/jQuery)

;;------------------------------------------------------------------------------
;; Body
;;------------------------------------------------------------------------------
(hiccups/defhtml main-container []
  [:div.main-container-dc45e
    [:h4 "Hello, World"]])

(hiccups/defhtml body []
  [:div.main-body-c5cef
    [:img {:src "http://res.cloudinary.com/dljyjkavz/image/upload/v1391474941/New_DelFX_Logo_Oct_2013_sj70sg.png"}]]
    (main-container))

(dom/set-html! "content" (body))