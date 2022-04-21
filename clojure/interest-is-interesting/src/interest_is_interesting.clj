(ns interest-is-interesting)

(defn interest-rate
  [balance]
  (cond
    (< balance 0.0)
    -3.213

    (and (>= balance 0.0) (< balance 1000.0))
    0.5

    (and (>= balance 1000.0) (< balance 5000.0))
    1.621

    (>= balance 5000.0)
    2.475))

(defn annual-balance-update
  [balance]
  (let [percentage-rate (bigdec  (/ (interest-rate balance) 100.0M))
        income          (bigdec (* balance percentage-rate))]
    (if (> balance 0)
      (bigdec (+ balance income))
      (bigdec (- balance income)))))

(defn amount-to-donate
  [balance tax-free-percentage]
  (let [balance-updated (annual-balance-update balance)
        donate          (* balance (/ tax-free-percentage 100.0))]
    (if (> balance-updated 0)
      (int (* 2.0 donate))
      0)))
