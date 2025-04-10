#!/usr/bin/env clojure
(ns aoc2024
  (:require [clojure.string :as str])
  (:require clojure.set))

(defn parse-line [line]
  (let [parts (str/split line #" +")]
    (vec (map #(Integer/parseInt %) parts))))

(defn is-safe [[f & report] increasing removed]
  (if (empty? report)
    true
    (let [s (first report)
          is-incr (> f s)
          step (abs (- f s))
          gradual (and (>= step 1) (<= step 3))
          now-increasing (if (nil? increasing) is-incr increasing)]
      (or (and gradual (= now-increasing is-incr) (is-safe report now-increasing removed))
          (and (not removed) (is-safe (cons f (rest report)) increasing true)) ; remove second
          (and (nil? increasing) (not removed) (is-safe report increasing true)) ; remove very first
          ))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (printf "Test\n")
  (with-open [rdr (clojure.java.io/reader "input")]
    (let [reports (map parse-line (line-seq rdr))
          safes (count (filter true? (map #(is-safe % nil false) reports)))]
      (printf "safes: %s\n" safes)
      )))

(-main)
