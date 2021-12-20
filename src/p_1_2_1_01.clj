(ns p-1-2-1-01
  (:require [clojure2d.core :as c2d]
            [clojure2d.color :as color]
            [fastmath.core :as math]
            [fastmath.random :as random]
            [fastmath.vector :as vector]))

(def window-name "p_1_2_1_01")

(defn shake-colors [state]
  (merge
   state
   {:colors-left (vec (repeatedly 10 #(color/color (random/drand 43) (random/drand 255.) 255.)))
    :colors-right (vec (repeatedly 10 #(color/color (random/drand 114 135) 255. (random/drand 255.))))}))

(defn draw [canvas window _ _]
  (let [colors-left (:colors-left (c2d/get-state window))
        colors-right (:colors-right (c2d/get-state window))
        interpolate-shortest? (:interpolate-shortest? (c2d/get-state window))
        width (c2d/width canvas)
        height (c2d/height canvas)
        tile-count-x (math/norm (c2d/mouse-x window) 0 width 2 100)
        tile-count-y (math/norm (c2d/mouse-y window) 0 height 2 10)
        tile-width (/ width tile-count-x)
        tile-height (/ height tile-count-y)]
    (doseq [grid-y (range tile-count-y)
            :let [color-left (get colors-left grid-y)
                  color-right (get colors-right grid-y)]
            grid-x (range tile-count-x)
            :let [amount (math/norm grid-x 0. tile-count-x)]]
      (doto canvas
        (c2d/set-color
         (if interpolate-shortest?
           (vector/interpolate (color/from-HSB* color-left) (color/from-HSB* color-right) amount)
           (color/from-HSB* (vector/interpolate color-left color-right amount))))
        (c2d/rect (* tile-width grid-x) (* tile-height grid-y) tile-width tile-height)))))

(defmethod c2d/mouse-event [window-name :mouse-released] [_ s] (shake-colors s))
(defmethod c2d/key-pressed [window-name \1] [_ s] (assoc s :interpolate-shortest? true))
(defmethod c2d/key-pressed [window-name \2] [_ s] (assoc s :interpolate-shortest? false))

(comment
  (c2d/show-window
   {:canvas (c2d/canvas 800 800 :highest)
    :window-name window-name
    :draw-fn #'draw
    :state (shake-colors {:interpolate-shortest? true})}))
