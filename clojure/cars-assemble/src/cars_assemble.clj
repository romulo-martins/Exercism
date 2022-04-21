(ns cars-assemble)

;; At the lowest speed, were produced 221 cars per hour
(def base-production 221)

(defn success-rate
  [speed]
  (cond
    (= speed 0) 0.0

    (and (>= speed 1) (<= speed 4)) 1.0

    (and (>= speed 5) (<= speed 8)) 0.9

    (= speed 9) 0.8

    (= speed 10) 0.77))

(defn production-rate
  "Returns the assembly line's production rate per hour,
   taking into account its success rate"
  [speed]
  (* speed base-production (success-rate speed)))

(defn working-items
  "Calculates how many working cars are produced per minute"
  [speed]
  (int (/ (production-rate speed) 60)))
