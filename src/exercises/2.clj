(ns exercises.2)

; Poker Hand

(def hands {:1 "Straight Flush"
            :2 "Four of A Kind"
            :3 "Full House"
            :4 "Flush"
            :5 "Straight"
            :6 "3 of a Kind"
            :7 "Two Pair"
            :8 "One Pair"
            :9 "High Card"})

(def card-names {\A "Ace" \K "King" \Q "Queen" \J "Jack" \T "Ten" \9 "Nine " \8 "Eight" \7 "Seven " \6 "Six" \5 "Five" \4 "Four" \3 "Three" \2 "Two" })

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
   (straight-flush? hand) (hands :1)
   (four? hand)           (hands :2)
   (full-house? hand)     (hands :3)
   (flush? hand)          (hands :4)
   (straight? hand)       (hands :5)
   (three? hand)          (hands :6)
   (two-pair? hand)       (hands :7)
   (one-pair? hand)       (hands :8)
   :else                  (hands :9)))

