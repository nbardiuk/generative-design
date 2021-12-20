(ns p-1-1-2-01
  (:require [clojure2d.core :as c2d]
            [clojure2d.color :as color]
            [fastmath.core :as math]))

(def window-name "p_1_1_2_01")

(defn draw [canvas window _ _]
  (let [segment-count (:segment-count (c2d/get-state window))
        width (c2d/width canvas)
        height (c2d/height canvas)
        mouse-x (c2d/mouse-x window)
        mouse-y (c2d/mouse-y window)
        color-mode (fn [h s b] (color/from-HSB*
                                [(math/norm h 0 360. 0 255.)
                                 (math/norm s 0 width 0 255.)
                                 (math/norm b 0 height 0 255.)]))
        mid-x (* width 1/2)
        mid-y (* height 1/2)
        angle-step (/ 360. segment-count)]
    (c2d/set-background canvas (color-mode 360 0 height))
    (doseq [[angle-from angle-to] (->> angle-step (range 0 (inc 360.)) (partition 2 1))
            :let [a (math/radians angle-from)
                  b (math/radians angle-to)]]
      (doto canvas
        (c2d/set-color (color-mode angle-from mouse-x mouse-y))
        (c2d/triangle mid-x mid-y
                      (* mid-x (+ 1 (math/cos a))) (* mid-y (+ 1 (math/sin a)))
                      (* mid-x (+ 1 (math/cos b))) (* mid-y (+ 1 (math/sin b))))))))

(defmethod c2d/key-pressed [window-name \1] [_e s] (assoc s :segment-count 360))
(defmethod c2d/key-pressed [window-name \2] [_e s] (assoc s :segment-count 45))
(defmethod c2d/key-pressed [window-name \3] [_e s] (assoc s :segment-count 24))
(defmethod c2d/key-pressed [window-name \4] [_e s] (assoc s :segment-count 12))
(defmethod c2d/key-pressed [window-name \5] [_e s] (assoc s :segment-count 6))

(comment
  (c2d/show-window
   {:canvas (c2d/canvas 800 800 :highest)
    :window-name window-name
    :state {:segment-count 360}
    :draw-fn #'draw}))
