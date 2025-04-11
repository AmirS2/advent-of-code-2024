#!/usr/bin/env clojure
(ns aoc2024
  (:require [clojure.string :as str])
  (:require clojure.set))

(defn get-mults [input]
  (re-seq #"mul\((\d+),(\d+)\)" input))

(defn do-mult [mult]
  (let [a (Integer/parseInt (nth mult 1))
        b (Integer/parseInt (nth mult 2))]
    (* a b)))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (printf "Test\n")
  (with-open [rdr (clojure.java.io/reader "input")]
    (let [input (slurp rdr)
          mults (get-mults input)
          results (map do-mult mults)
          total (reduce + 0 results)]
      (printf "Total: %d\n" total)
      )))

(-main)
