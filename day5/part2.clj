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

(defn compare-pages [rules l r]
  (first (for [[bef aft :as rule] rules
               :let [correct (and (= bef l) (= aft r))
                     wrong (and (= bef r) (= aft l))]
               :when (or correct wrong)]
           correct)))

(defn sort-update [rules upd]
  (vec (sort-by identity #(compare-pages rules %1 %2) upd)))

(defn -main
  "Try and solve Advent of Code 2024 puzzles"
  [& args]
  (printf "Test\n")
  (with-open [rdr (clojure.java.io/reader "input")]
    (let [rules (vec (for [line (line-seq rdr) :while (not (empty? line))]
                       (str/split line #"\|")))
          updates (vec (for [line (line-seq rdr)] (str/split line #",")))
          invalid-updates (filter #(not (check-update rules %)) updates)
          sorted-updates (map #(sort-update rules %) invalid-updates)
          _ (println "Invalid updates: " invalid-updates)
          _ (println "Sorted updates: " sorted-updates)
          valid-mids (for [upd sorted-updates] (get upd (-> upd count (/ 2) int)))
          _ (println "Valid mids: " valid-mids)
          mid-sums (reduce + (map #(Integer/parseInt %) valid-mids))]
      (println "Sorted updates: " sorted-updates)
      (println "Sum mids: " mid-sums))))

(-main)
