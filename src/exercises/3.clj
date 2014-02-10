(ns exercises.3
  (:refer-clojure :exclude [* - + == /])
  (:require [clojure.math.numeric-tower :as math])
  (:use clojure.core.matrix)
  (:use clojure.core.matrix.operators))


; Spiral
; - take a number N. return a spiral from 0 -> N.
; 24 ->

;  20 21 22 23 24
;  19  6  7  8  9
;  18  5  0  1 10
;  17  4  3  2 11
;  16 15 14 13 12

(defn spiral
  "take an integer. print a spiral."
  [n]
   (Math/ceil (math/sqrt n)))


(def genesis (mutable-matrix [[0 1][0 0]]))


(pm genesis)

