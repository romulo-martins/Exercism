(ns bird-watcher)

(def last-week [0 2 5 3 7 8 4])

(defn today [birds]
 (last birds))

(defn inc-bird [birds]
  (->> (inc (today birds))
       (conj (pop birds))))

(defn day-without-birds? [birds]
  (->> birds
       (some #(= 0 %))
       (true?)))

(defn n-days-count [birds n]
  (->> birds
       (take n)
       (reduce +)))

(defn busy-days [birds]
  (->> birds
       (filter #(> % 4))
       (count)))

(defn- verify-odd-week [birds last-bird]
  (cond
    (empty? birds) true

    (or (and (= 1 last-bird) (= 0 (first birds)))
        (and (= 0 last-bird) (= 1 (first birds))))
    (verify-odd-week (rest birds) (first birds))

    :else false))

(defn odd-week? [birds]
  (verify-odd-week (rest birds) (first birds)))
