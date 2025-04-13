#!/usr/bin/env clojure
(ns aoc2024
  (:require [clojure.string :as str])
  (:require clojure.set))

(defn check-rule [[bef aft :as rule] upd]
  (let [b (.indexOf upd bef)
        a (.indexOf upd aft)]
    (or (= b -1) (= a -1) (< b a))))

(defn check-update [rules upd]
  (every? #(check-rule % upd) rules))

(defn -main
  "Try and solve Advent of Code 2024 puzzles"
  [& args]
  (printf "Test\n")
  (with-open [rdr (clojure.java.io/reader "input")]
    (let [rules (vec (for [line (line-seq rdr) :while (not (empty? line))]
                       (str/split line #"\|")))
          updates (vec (for [line (line-seq rdr)] (str/split line #",")))
          valid-updates (filter #(check-update rules %) updates)
          valid-mids (for [upd valid-updates] (get upd (-> upd count (/ 2) int)))
          mid-sums (reduce + (map #(Integer/parseInt %) valid-mids))]
      (println "Valid updates: " valid-updates)
      (println "Sum mids: " mid-sums))))

(-main)
