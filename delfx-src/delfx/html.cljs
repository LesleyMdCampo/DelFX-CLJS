(ns delfx.html
  (:require-macros [hiccups.core :as hiccups])
  (:require
    [clojure.walk :refer [keywordize-keys]]
    [delfx.dom :refer [by-id set-html!]]
    [delfx.util :refer [js-log log]]
    [hiccups.runtime]))

(def $ js/jQuery)
(def projects-data (atom nil))

(declare fetch-content!)

;;------------------------------------------------------------------------------
;; Events
;;------------------------------------------------------------------------------

(defn- show-type [type]
  (.hide ($ "img.round-img-242ac"))
  (.show ($ (str "img.round-img-242ac." type))))

(defn- click-work-type [js-evt]
  (let [link (aget js-evt "currentTarget" "id")
        link-id (str "#" link)]
    (if (= link "all")
      (.show ($ "img.round-img-242ac"))
      (show-type link))))

(defn- click-work-circle [js-evt]
  (let [link (aget js-evt "currentTarget" "id")]
    (aset js/window "location" "href" (str "/projects/" link ".html"))))

(defn- add-events []
  (.on ($ "body") "click" ".nav-link-a7aa8" #(click-work-type %))
  (.on ($ "body") "click" ".type-circle-1caf1" #(click-work-circle %)))

($ add-events)

;;------------------------------------------------------------------------------
;; HTML
;;------------------------------------------------------------------------------

(hiccups/defhtml category-circles [work]
  [:div.type-circle-1caf1 {:id (:id work)}
    [:img
      {:class (str "round-img-242ac " (:type work))
       :src (:img work)}]])

(hiccups/defhtml works-circles []
  [:div.types-circles-f5820
    (map category-circles @projects-data)
    [:div.clr-bc54a]])

(hiccups/defhtml sticky-header []
  [:div.sticky-header-d95b2
    [:div.container-32dc6
      [:div.header-links-a2c3d
        [:a.nav-link-a7aa8.all-types {:id "all"} "All"]
        [:a.nav-link-a7aa8.type1 {:id "works"} "Works"]
        [:a.nav-link-a7aa8.type2 {:id "styleboards"} "Styleboards"]
        [:a.nav-link-a7aa8.type3 {:id "storyboards"} "Storyboards"]
        [:a.nav-link-a7aa8.type4 {:id "design"} "Graphic Design"]]]
        [:div.clr-bc54a]
        (works-circles)])

(hiccups/defhtml about-section []
  [:div.about-container-3e121
    [:div.left-col-e4a63 [:span.underline-a552e "Bio"]
      [:div.para-f88dc "Cat ipsum dolor sit amet, inspect anything brought into the house yet stick butt in face sit in box. Meowing non stop for food hide from vacuum cleaner yet plan steps for world domination sleep on keyboard hack up furballs kick up litter but hate dog. Sweet beast destroy couch, or stand in front of the computer screen."]]
    [:div.right-col-d0849 [:span.underline-a552e "Purpose"]
      [:div.para-f88dc "Stick butt in face bathe private parts with tongue then lick owner's face i like big cats and i can not lie roll on the floor purring your whiskers off for mark territory, but ears back wide eyed. Hide at bottom of staircase to trip human. Hate dog sun bathe, and spread kitty litter all over house and sweet beast, but burrow under covers, for stretch. Claw drapes stand in front of the computer screen use lap as chair, for sleep on keyboard all of a sudden cat goes crazy."]]
    [:div.clr-bc54a]])

(hiccups/defhtml social-icons []
  [:a.fa.fa-twitter {:href "https://twitter.com/dels_tweet" :data-username "dels_tweet" :target "_blank"}]
  [:a.fa.fa-youtube {:href "https://www.youtube.com/channel/UCT-Qt4bxBy2u--iwaWh1k3w" :target "_blank"}]
  [:a.fa.fa-envelope-o {:href "mailto:dmdelc27@gmail.com?subject=love your work man" :target "_blank"}])

(hiccups/defhtml header []
  [:div
    [:div.large-header-1fed0
      [:div.container-32dc6
        [:div.del-bd3cd
          [:img.logo-d34c1 {:src "../img/dmdelc.jpg"}]
          [:div.title-fe5d6 "Daniel Martin del Campo"]
          [:div.about-1043c "Motion Graphics Designer"]
          [:div.social-43a6b (social-icons)]]]]
    [:div.clr-bc54a]])

(hiccups/defhtml contact-me []
  [:div.form-af01c
    [:p [:input.input-8d727 {:placeholder "name *"}]]
    [:p [:input.input-8d727 {:placeholder "email *"}]]
    [:p [:input.input-8d727 {:placeholder "phone number"}]]
    [:p [:textarea.text-22d5c {:placeholder "message *"}]]
    [:p.lrg-ca862 "submit"]])

(hiccups/defhtml footer []
  [:footer.footer-309d2
    [:div.container-32dc6
      [:div.contact-999b4
        [:div.contact-blurb-e59b3 "If you would like to commission any work, are interested in hiring me or have any queries please contact me below:"]
        (contact-me)]]])

(hiccups/defhtml body [data]
  (header)
  [:div.main-container-dc45e
    (about-section)
    (sticky-header)]
  (footer)
  [:div.copyright-71f95 "&copy2015 daniel martin "
    [:span.lowercase-de065 "del "] "campo"])

;;------------------------------------------------------------------------------
;; Ajax
;;------------------------------------------------------------------------------

(defn- data-success [data]
  (->> data
      js->clj
      keywordize-keys
      (reset! projects-data))
  (fetch-content!))

(defn- fetch-data! []
  (.ajax $ (js-obj "url" "projects.json"
    "success" data-success)))

(defn- fetch-content2! []
  (set-html! "content" (body projects-data)))

(defn- fetch-content! []
  (if-not @projects-data
    (fetch-data!)
    (fetch-content2!)))

(defn- init! []
  (fetch-content!))

($ init!)
