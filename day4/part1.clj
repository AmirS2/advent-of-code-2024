#!/usr/bin/env clojure
(ns aoc2024
  (:require [clojure.string :as str])
  (:require clojure.set))

(defn find-xs [lines]
  (let [xs (atom #{})]
    (for [[y line] (map-indexed vector lines)
          [x chr] (map-indexed vector line)
          :when (= chr \X)]
      (vector y x))))

(def directions
  [[-1 0] [1 0] [0 -1] [0 1]
   [-1 -1] [-1 1] [1 -1] [1 1]])

(def dirs-moves
  (for [dir directions]
    (for [r (range 1 4)]
      (vector (* r (first dir))
              (* r (second dir))))))

(defn check-mas [lines new-poss]
  (every? identity (for [[pos letter] (map vector new-poss "MAS")]
                     (= (get-in lines pos) letter))))

(defn find-xmas [lines pos]
   (for [dir-move dirs-moves]
     (let [new-poss (for [move dir-move] (map + pos move))]
       (check-mas lines new-poss))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (printf "Test\n")
  (with-open [rdr (clojure.java.io/reader "input")]
    (let [lines (-> rdr line-seq vec)
          xmases (for [x (find-xs lines)
                       is-xmas (find-xmas lines x)]
                   is-xmas)]
      (println "Xmas-es:" (count (filter true? xmases))))))

(-main)
