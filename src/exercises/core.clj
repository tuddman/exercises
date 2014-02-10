(ns exercises.core
  (:require  [exercises.1 :as ex-1]
             [exercises.2 :as ex-2]
             [exercises.3 :as ex-3]))

;; EXERCISES


; Exercise 1:

; - Take a number. return the string representation.
; - works with balances into the trillions. beyond that is silly.


(ex-1/amt->txt 44.21)
(ex-1/amt->txt 101.24)
(ex-1/amt->txt 2759.00)
(ex-1/amt->txt 613459.99)
(ex-1/amt->txt 425713459.99)
(ex-1/amt->txt 12000000345678.24)



; Exercise 2:

; - Get the poker hand.


(ex-2/determine-hand ["Ac" "Ad" "As" "Ah" "Kh"])
(ex-2/determine-hand ["Ac" "Kc" "Jc" "Qc" "Tc"])
(ex-2/determine-hand ["7d" "8s" "9c" "Td" "Jh"])
(ex-2/determine-hand ["2h" "2s" "2c" "6d" "6h"])
(ex-2/determine-hand ["2h" "2s" "2c" "Td" "Jh"])
(ex-2/determine-hand ["2h" "2s" "Qc" "Qd" "Kh"])
(ex-2/determine-hand ["Ah" "Tc" "4d" "5h" "7h"])
(ex-2/determine-hand ["Th" "Tc" "4d" "5h" "7h"])



; Exercise 3:
; - Number spiral.

