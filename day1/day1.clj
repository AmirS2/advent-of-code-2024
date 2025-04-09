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
          sf (sort fl)
          ss (sort sl)
          diffs (map #(abs (- %1 %2)) sf ss)
          ]
      (printf "First line: %s\n" sf)
      (printf "Second line: %s\n" ss)
      (printf "sum of diffs: %s\n" (reduce + diffs))
      ))
)

(-main)
