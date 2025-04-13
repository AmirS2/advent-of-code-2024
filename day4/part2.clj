#!/usr/bin/env clojure
(ns aoc2024
  (:require [clojure.string :as str])
  (:require clojure.set))

(defn find-as [lines]
  (let [xs (atom #{})]
    (for [[y line] (map-indexed vector lines)
          [x chr] (map-indexed vector line)
          :when (= chr \A)]
      (vector y x))))

(def directions
  [[-1 -1] [-1 1] [1 1] [1 -1]])

(def dirs-moves
  (let [d (vec (concat directions directions))]
    (println "d:" d)
    (for [r (range 4)]
      (subvec d r (+ r 4)))))

(defn check-mas [lines new-poss]
  (every? identity (for [[pos letter] (map vector new-poss "MMSS")]
                     (= (get-in lines pos) letter))))

(defn find-x-mas [lines pos]
   (for [dir-move dirs-moves]
     (let [new-poss (for [move dir-move] (map + pos move))]
       (check-mas lines new-poss))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (printf "Test\n")
  (with-open [rdr (clojure.java.io/reader "input")]
    (let [lines (-> rdr line-seq vec)
          x-mases (for [a (find-as lines)
                       is-x-mas (find-x-mas lines a)]
                   is-x-mas)]
      (println "X-mas-es:" (count (filter true? x-mases))))))

(-main)
