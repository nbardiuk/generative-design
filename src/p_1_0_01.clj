(ns p-1-0-01
  (:require [clojure2d.core :as c2d]
            [clojure2d.color :as color]))

(defn draw [canvas window ^long _frameno _state]
  (let [mouse-x (c2d/mouse-x window)
        mouse-y (c2d/mouse-y window)]
    (doto canvas
      (c2d/set-background (color/from-HSB* [(* (/ 255. 360.) mouse-y 0.5) 255. 255.]))
      (c2d/set-color (color/from-HSB* [(- 360 (* (/ 255. 360.) mouse-y 0.5)) 255. 255.]))
      (c2d/crect 360 360 (inc mouse-x) (inc mouse-x)))))

(comment
  (c2d/show-window
   {:canvas (c2d/canvas 720 720 :highest)
    :draw-fn #'draw}))
