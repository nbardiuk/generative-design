(ns p-1-1-1-01
  (:require [clojure2d.core :as c2d]
            [clojure2d.color :as color]))

(defn draw [canvas window ^long _frameno _state]
  (let [width (c2d/width canvas)
        height (c2d/height canvas)
        step-x (+ 2 (c2d/mouse-x window))
        step-y (+ 2 (c2d/mouse-y window))]
    (doseq [^long grid-y (range 0 height step-y)
            ^long grid-x (range 0 width step-x)]
      (doto canvas
        (c2d/set-color (color/from-HSB* [(* grid-x (/ 255. width))
                                         (- 255. (* grid-y  (/ 255. height)))
                                         255.]))
        (c2d/rect grid-x grid-y step-x step-y)))))

(comment
  (c2d/show-window
   {:canvas (c2d/canvas 800 400 :highest)
    :draw-fn #'draw}))
