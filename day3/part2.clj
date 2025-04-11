#!/usr/bin/env clojure
(ns aoc2024
  (:require [clojure.string :as str])
  (:require clojure.set))

(defn get-ops [input]
  (re-seq #"mul\((\d+),(\d+)\)|do\(\)|don't\(\)" input))

(defn do-mult [mult]
  (let [a (Integer/parseInt (nth mult 1))
        b (Integer/parseInt (nth mult 2))]
    (* a b)))

(defn sum-ops
  ([ops] (sum-ops ops true))
  ([[op & rst :as ops] enabled]
   (if (empty? ops)
     0
     (case (first (str/split (first op) #"\(" 2))
       "mul" (+ (if enabled (do-mult op) 0) (sum-ops rst enabled))
       "do" (sum-ops rst true)
       "don't" (sum-ops rst false)))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (printf "Test\n")
  (with-open [rdr (clojure.java.io/reader "input")]
    (let [input (slurp rdr)
          ops (get-ops input)
          total (sum-ops ops)]
      (printf "Total: %d\n" total)
      )))

(-main)
