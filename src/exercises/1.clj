(ns exercises.1)

; "Textualize" a number.

;; Definitions.

(def num-as-str
  { 1 "one" 2 "two" 3 "three" 4 "four" 5 "five" 6 "six" 7 "seven" 8 "eight" 9 "nine"
    10 "ten" 11 "eleven" 12 "twelve" 13 "thirteen" 14 "fourteen" 15 "fifteen" 16 "sixteen" 17 "seventeen" 18 "eighteen" 19 "nineteen"
    20 "twenty" 30 "thirty" 40 "forty" 50 "fifty" 60 "sixty" 70 "seventy" 80 "eighty" 90 "ninety"})

(def mag-str {1 " thousand " 2 " million " 3 " billion " 4 " trillion "})


;; Utility Methods


(defn expt [pow] (apply * (repeat pow 1000)))


(defn trim-num [n]
  (format "%.2f" n))


(defn get-dollars [n]
  (let [dollars (subs n 0 (- (.length n) 3))]
    dollars))


(defn get-cents [n]
  (let [cents (subs n (- (.length n) 2))]
    cents))


(defn get-mag [n]
  (cond
   (> (expt 1) n) 0
   (< (expt 1) n (expt 2)) 1
   (< (expt 2) n (expt 3)) 2
   (< (expt 3) n (expt 4)) 3
   (< (expt 4) n (expt 5)) 4))


(defn get-mag-str [n]
  (cond
   (> (expt 1) n) ""
   (< (expt 1) n (expt 2)) (mag-str 1)
   (< (expt 2) n (expt 3)) (mag-str 2)
   (< (expt 3) n (expt 4)) (mag-str 3)
   (< (expt 4) n (expt 5)) (mag-str 4)))


(defn sub-hund->txt
  "converts n < 100 into txt."
  [n]
  (if (contains? num-as-str n) (str (num-as-str n) " ")
    (str (num-as-str (- n (rem n 10))) "-" (num-as-str (rem n 10)) " ")))


(defn sub-thou->txt
  "converts n < 1000 into txt."
  [n]
  (cond
   (< 99 n 999 ) (str (num-as-str (quot n 100)) "-hundred " (sub-hund->txt (rem n 100)))
   :else (sub-hund->txt (rem n 100))))


(defn dollars->txt [n]
  (let [dollars (Math/round (Double. (get-dollars n)))
        magnitude (get-mag dollars)
        amt (atom {})
        amt-str (atom "")]
    (cond
      (= magnitude 4)
        (do
          (swap! amt conj {:trillion (quot dollars (expt 4))})

          (swap! amt conj {:billion (quot (- dollars (* (quot dollars (expt 4))
                                                               (expt 4))) (expt 3))})
          (swap! amt conj {:million (quot (- dollars (* (quot dollars (expt 3))
                                                               (expt 3))) (expt 2))})
          (swap! amt conj {:thousand (quot (- dollars (* (quot dollars (expt 2))
                                                               (expt 2))) (expt 1))})
          (swap! amt conj {:subthou (rem dollars (expt 1))}))

     (= magnitude 3)
        (do
          (swap! amt conj {:billion (quot dollars (expt 3))})

          (swap! amt conj {:million (quot (- dollars (* (quot dollars (expt 3))
                                                               (expt 3))) (expt 2))})
          (swap! amt conj {:thousand (quot (- dollars (* (quot dollars (expt 2))
                                                               (expt 2))) (expt 1))})
          (swap! amt conj {:subthou (rem dollars (expt 1))}))

      (= magnitude 2)
        (do
          (swap! amt conj {:million (quot dollars (expt 2))})
          (swap! amt conj {:thousand (quot (- dollars (* (quot dollars (expt 2))
                                                               (expt 2))) (expt 1))})
          (swap! amt conj {:subthou (rem dollars (expt 1))}))

      (= magnitude 1)
        (do
          (swap! amt conj {:thousand (quot dollars (expt 1))})
          (swap! amt conj {:subthou (rem dollars (expt 1))}))

     (= magnitude 0)
        (swap! amt conj {:subthou dollars}) )

    (if  (contains? @amt :trillion)
      (swap! amt-str str (sub-thou->txt (@amt :trillion)) (mag-str 4)))
    (if  (and (contains? @amt :billion) (pos? (@amt :billion)))
      (swap! amt-str str (sub-thou->txt (@amt :billion)) (mag-str 3)))
    (if  (and (contains? @amt :million) (pos? (@amt :million)))
      (swap! amt-str str (sub-thou->txt (@amt :million)) (mag-str 2)))
    (if  (and (contains? @amt :thousand) (pos? (@amt :thousand)))
      (swap! amt-str str (sub-thou->txt (@amt :thousand)) (mag-str 1)))
    (if  (contains? @amt :subthou)
      (swap! amt-str str (sub-thou->txt (@amt :subthou))))

 (str @amt-str)
  ))


; Assumptions (for now) : A well-formed positive number is entered. e.g. format: NNN.NN
; TODO: check input to see that it is a valid and well-formed number.

(defn amt->txt
  "Take a number as input; Convert it to string representation.
   e.g. 2523.04 -> two thousand five hundred twenty-three and 04/100 "
  [number]
  (let [trimmed (trim-num number)
        dollars (dollars->txt trimmed)
        cents   (str (get-cents trimmed) "/100")]
  (str dollars " and " cents " dollars")))


