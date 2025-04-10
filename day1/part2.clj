#!/usr/bin/env clojure
(ns aoc2024
  (:require [clojure.string :as str])
  (:require clojure.set))

(defn parse-line [line]
  (let [parts (str/split line #" +")]
    (vec (map #(Integer/parseInt %) parts))))


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (printf "Test\n")
  (with-open [rdr (clojure.java.io/reader "input")]
    (let [[fl sl] (reduce #(vector (conj (first %1) (first %2))
                                   (conj (second %1) (second %2)))
                          [[] []]
                          (map parse-line (line-seq rdr)))
          counts (reduce #(if (contains? %1 %2)
                            (update %1 %2 inc)
                            (assoc %1 %2 1))
                         (sorted-map)
                         sl)
          similarity (reduce + (map #(* % (get counts % 0)) fl))
          ]
      (printf "counts %s\n" counts)
      (printf "similarity %s\n" similarity)))
)

(-main)
