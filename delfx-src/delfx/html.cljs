(ns delfx.html
  (:require-macros [hiccups.core :as hiccups])
  (:require
    [delfx.dom :as dom]
    [delfx.util :refer [js-log log]]
    [hiccups.runtime]))

(def $ js/jQuery)

;;------------------------------------------------------------------------------
;; Body
;;------------------------------------------------------------------------------

(hiccups/defhtml social-icons []
  [:i.fa.fa-twitter]
  [:i.fa.fa-youtube]
  [:i.fa.fa-envelope-o])

(hiccups/defhtml header []
  [:div#wrap
    [:div.large-header-1fed0
      [:div.container-32dc6
        [:div.del-bd3cd
          [:img.logo-d34c1 {:src "../img/dmdelc.jpg"}]
          [:div.title-fe5d6 "Daniel Martin del Campo"]
          [:div.about-1043c "Motion Graphics Designer"]
          [:div.social-43a6b (social-icons)]]]]
    [:div.clr-bc54a]])

(hiccups/defhtml about-section []
  [:div.about-container-3e121
    [:div.left-col-e4a63 [:span.underline-a552e "Bio"]
      [:div.para-f88dc "Cat ipsum dolor sit amet, inspect anything brought into the house yet stick butt in face sit in box. Meowing non stop for food hide from vacuum cleaner yet plan steps for world domination sleep on keyboard hack up furballs kick up litter but hate dog. Sweet beast destroy couch, or stand in front of the computer screen."]]
    [:div.right-col-d0849 [:span.underline-a552e "Purpose"]
      [:div.para-f88dc "Stick butt in face bathe private parts with tongue then lick owner's face i like big cats and i can not lie roll on the floor purring your whiskers off for mark territory, but ears back wide eyed. Hide at bottom of staircase to trip human. Hate dog sun bathe, and spread kitty litter all over house and sweet beast, but burrow under covers, for stretch. Claw drapes stand in front of the computer screen use lap as chair, for sleep on keyboard all of a sudden cat goes crazy."]]
    [:div.clr-bc54a]])

(hiccups/defhtml sticky-header []
  [:div.sticky-header-d95b2
    [:div.container-32dc6
      [:div.header-links-a2c3d
        [:a.nav-link-a7aa8.type1 "Works"]
        [:a.nav-link-a7aa8.type2 "Styleboards"]
        [:a.nav-link-a7aa8.type3 "Storyboards"]
        [:a.nav-link-a7aa8.type4 "Graphic Design"]]]
        [:div.clr-bc54a]])

(hiccups/defhtml work-categories []
  [:div.types-circles-f5820
    [:div.type-circle-1caf1]
    [:div.type-circle-1caf1]
    [:div.type-circle-1caf1]
    [:div.type-circle-1caf1]
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

(hiccups/defhtml body []
    (header)
    [:div.main-container-dc45e
      (about-section)
      (sticky-header)
      (work-categories)]
    (footer)
    [:div.copyright-71f95 "&copy 2015 daniel martin del campo"])

(dom/set-html! "content" (body))