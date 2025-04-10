#!/usr/bin/env clojure
(ns aoc2024
  (:require [clojure.string :as str])
  (:require clojure.set))

(defn parse-line [line]
  (let [parts (str/split line #" +")]
    (vec (map #(Integer/parseInt %) parts))))

(defn is-safe [[f & report] increasing]
  (if (empty? report)
    true
    (let [s (first report)
          r (rest report)
          is-incr (> f s)
          step (abs (- f s))
          gradual (and (>= step 1) (<= step 3))
          increasing (if (nil? increasing) is-incr increasing)]
      (and gradual (= increasing is-incr) (is-safe report increasing)))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (printf "Test\n")
  (with-open [rdr (clojure.java.io/reader "input")]
    (let [reports (map parse-line (line-seq rdr))
          safes (count (filter true? (map #(is-safe % nil) reports)))]
      (printf "safes: %s\n" safes)
      )))

(-main)
