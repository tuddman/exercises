(ns exercises.2)

; Poker Hand

(def suits {\c "Clubs" \d "Diamonds" \h "Hearts" \s "Spades"})

(def card-rank (reverse (seq "AKQJT98765432ax")))


(defn get-suits [hand]
  (map #(second %) hand))


(defn get-card-faces [hand]
  (map #(first %) hand))


(defn sort-hand-ranks [hand]
  (sort > (map #(.indexOf card-rank %) (get-card-faces hand))))


(defn four?
  [hand]
  (true? (some #(= 4 %) (vals (frequencies (get-card-faces hand) )))))


(defn three?
  [hand]
  (true? (some #(= 3 %) (vals (frequencies (get-card-faces hand) )))))



(defn two-pair?
  [hand]
  (= (count
       (filter #(= 2 %)
         (vals (frequencies (get-card-faces hand) )))) 2))


(defn one-pair?
  [hand]
  (= (count
       (filter #(= 2 %)
         (vals (frequencies (get-card-faces hand) )))) 1))


(defn full-house?
  [hand]
  (= (one-pair? hand) (three? hand) true))


;; TODO: Ace as low card?
(defn straight?
  [hand]
   (let [start (first (sort-hand-ranks hand))
         end   (- start 5)
         check (range start end -1)]
     (= check (sort-hand-ranks hand))))


(defn flush?
  [hand]
  (let [ranks (map #(second %) hand)
        card-1-rank (second (first hand))]
    (every? true? (map #(= card-1-rank %) ranks))))


(defn straight-flush?
  [hand]
  (= (straight? hand)(flush? hand) true))



(defn determine-hand
  [hand]
  (cond
   (straight-flush? hand) "Straight Flush"
   (four? hand)           "Four of A Kind"
   (full-house? hand)     "Full House"
   (flush? hand)          "Flush"
   (straight? hand)       "Straight"
   (three? hand)          "3 of a Kind"
   (two-pair? hand)       "Two Pair"
   (one-pair? hand)       "One Pair"
   :else                  "High Card")
