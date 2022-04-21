(ns bob
  (:require
   [clojure.string :as string]))

(def alphabet "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ")

(defn get-only-letters
  "Get only the letter in a text."
  [text]
  (->> (map (fn [char]
              (when (string/includes? alphabet (str char))
                (str char))) text)
       (remove nil?)
       (string/join)))

(defn- is-a-question?
  "Verify if the text is a question, a text is a question when ends with '?'."
  [text]
  (-> text
      (string/trim)
      (string/ends-with? "?")))

(defn- is-all-upper?
  "Verify if all letters on a text is uppercase."
  [text]
  (let [text-letters (get-only-letters text)]
    (when (seq text-letters)
      (= text-letters (string/upper-case text-letters)))))

(defn response-for [s] ;; <- arglist goes here
  (cond
    (and (is-a-question? s) (not (is-all-upper? s)))
    "Sure."

    (and (not (is-a-question? s)) (is-all-upper? s))
    "Whoa, chill out!"

    (and (is-a-question? s) (is-all-upper? s))
    "Calm down, I know what I'm doing!"

    (string/blank? s)
    "Fine. Be that way!"

    :else "Whatever."))
