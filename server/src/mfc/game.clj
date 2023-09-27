(ns mfc.game)

(def board
  {-1 :jail/in
   0  :go
   1  :brown/one
   2  :community-chest/one
   3  :brown/two
   4  :tax/income
   5  :train/one
   6  :light-cyan/one
   7  :chance/one
   8  :light-cyan/two
   9  :light-cyan/three
   10 :jail/just-visiting
   11 :pink/one
   12 :infrastructure/electric
   13 :pink/two
   14 :pink/three
   15 :train/two
   16 :orange/one
   17 :community-chest/one
   18 :orange/two
   19 :orange/three
   20 :free-parking
   21 :red/one
   22 :chance/two
   23 :red/two
   24 :red/three
   25 :train/three
   26 :yellow/one
   27 :yellow/two
   28 :infrastructure/water
   29 :yellow/three
   30 :go-to-jail
   31 :green/one
   32 :green/two
   33 :community-chest/two
   34 :green/three
   35 :train/four
   36 :chance/three
   37 :navy/one
   38 :tax/super
   39 :navy/two})

(defn new-pos [pos roll]
  (mod (+ pos (apply + roll)) 40))

(defn die-roll []
  (+ 1 (rand-int 6)))

(defn dice-roll []
  (list (die-roll) (die-roll)))

(defn current-player [game]
  (get (:players game) (get (:order game) (:current-player game))))

(defn move-current-player-to [game pos]
  (assoc-in game [:players (current-player game) :position] pos))

(defn roll [game]
  (move-current-player-to game (new-pos (:position (current-player game)) (dice-roll))))

(defn land [_]
  #(identity %))

(defn take-turn
  ([game]
   (take-turn game {:doubles false :count 0}))
  ([game double-info]
   (cond (>= 3 (:count double-info)) (move-current-player-to game -1)
         :else (roll game))
   (when (:doubles double-info) (take-turn game {:doubles true :count (inc (:count double-info))}))))

(defn increment-turn-counter [game]
  (update game :turn + 1))

(defn next-player [game]
  (assoc game :current-player (mod (+ 1 (:current-player game)) (count (:order game)))))

(defn turn [game]
  (-> game
      take-turn
      increment-turn-counter
      next-player))

(defn new-players [players]
  (into {} (map (fn [p] {(key p) (merge {:bux 1500 :position 0} (val p))}) players)))

(defn create-order [players]
  (-> players
      (keys)
      (shuffle)))

(defn new-game [players]
  {:players (new-players players)
   :order (create-order players)
   :current-player 0
   :turn 0})