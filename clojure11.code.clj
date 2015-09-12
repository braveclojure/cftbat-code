[[org.clojure/clojure "1.7.0"]
      [org.clojure/core.async "0.1.346.0-17112a-alpha"]]


(ns playsync.core
        (:require [clojure.core.async
                   :as a
                   :refer [>! <! >!! <!! go chan buffer close! thread
                           alts! alts!! timeout]]))


(def echo-chan (chan))
      (go (println (<! echo-chan)))
      (>!! echo-chan "ketchup")
      ; => true
      ; => ketchup


(>!! (chan) "mustard")


(def echo-buffer (chan 2))
      (>!! echo-buffer "ketchup")
      ; => true
      (>!! echo-buffer "ketchup")
      ; => true
      (>!! echo-buffer "ketchup")
      ; This blocks because the channel buffer is full


(def hi-chan (chan))
      (doseq [n (range 1000)]
        (go (>! hi-chan (str "hi " n))))


(thread (println (<!! echo-chan)))
      (>!! echo-chan "mustard")
      ; => true
      ; => mustard


(let [t (thread "chili")]
        (<!! t))
      ; => "chili"


(defn hot-dog-machine
        []
        (let [in (chan)
              out (chan)]
          (go (<! in)
              (>! out "hot dog"))
          [in out]))


(let [[in out] (hot-dog-machine)]
        (>!! in "pocket lint")
        (<!! out))
      ; => "hot dog"


(defn hot-dog-machine-v2
        [hot-dog-count]
        (let [in (chan)
              out (chan)]
          (go (loop [hc hot-dog-count]
                (if (> hc 0)
                  (let [input (<! in)]
                   (if (= 3 input)
                      (do (>! out "hot dog")
                          (recur (dec hc)))
                      (do (>! out "wilted lettuce")
                          (recur hc))))
                 (do (close! in)
                      (close! out)))))
          [in out]))


(let [[in out] (hot-dog-machine-v2 2)]
        (>!! in "pocket lint")
        (println (<!! out))
      
        (>!! in 3)
        (println (<!! out))
      
        (>!! in 3)
        (println (<!! out))
      
        (>!! in 3)
        (<!! out))
      ; => wilted lettuce
      ; => hotdog
      ; => hotdog
      ; => nil


(let [c1 (chan)
            c2 (chan)
            c3 (chan)]
        (go (>! c2 (clojure.string/upper-case (<! c1))))
        (go (>! c3 (clojure.string/reverse (<! c2))))
        (go (println (<! c3)))
        (>!! c1 "redrum"))
      ; => MURDER


(defn upload
        [headshot c]
        (go (Thread/sleep (rand 100))
            (>! c headshot)))
      
       (let [c1 (chan)
            c2 (chan)
            c3 (chan)]
        (upload "serious.jpg" c1)
        (upload "fun.jpg" c2)
        (upload "sassy.jpg" c3)
         (let [[headshot channel] (alts!! [c1 c2 c3])]
          (println "Sending headshot notification for" headshot)))
      ; => Sending headshot notification for sassy.jpg


(let [c1 (chan)]
        (upload "serious.jpg" c1)
        (let [[headshot channel] (alts!! [c1 (timeout 20)])]
          (if headshot
            (println "Sending headshot notification for" headshot)
            (println "Timed out!"))))
      ; => Timed out!


(let [c1 (chan)
            c2 (chan)]
        (go (<! c2))
         (let [[value channel] (alts!! [c1 [c2 "put!"]])]
          (println value)
          (= channel c2)))
      ; => true
      ; => true


(defn append-to-file
        "Write a string to the end of a file"
        [filename s]
        (spit filename s :append true))
      
      (defn format-quote
        "Delineate the beginning and end of a quote because it's convenient"
        [quote]
        (str "=== BEGIN QUOTE ===\n" quote "=== END QUOTE ===\n\n"))
      
      (defn random-quote
        "Retrieve a random quote and format it"
        []
        (format-quote (slurp "http://www.braveclojure.com/random-quote")))
      
      (defn snag-quotes
        [filename num-quotes]
        (let [c (chan)]
          (go (while true (append-to-file filename (<! c))))
          (dotimes [n num-quotes] (go (>! c (random-quote))))))


=== BEGIN QUOTE ===
      Nobody's gonna believe that computers are intelligent until they start
      coming in late and lying about it.
      === END QUOTE ===
      
      === BEGIN QUOTE ===
      Give your child mental blocks for Christmas.
      === END QUOTE ===


(defn upper-caser
        [in]
        (let [out (chan)]
          (go (while true (>! out (clojure.string/upper-case (<! in)))))
          out))
      
      (defn reverser
        [in]
        (let [out (chan)]
          (go (while true (>! out (clojure.string/reverse (<! in)))))
          out))
      
      (defn printer
        [in]
        (go (while true (println (<! in)))))
      
      (def in-chan (chan))
      (def upper-caser-out (upper-caser in-chan))
      (def reverser-out (reverser upper-caser-out))
      (printer reverser-out)
      
      (>!! in-chan "redrum")
      ; => MURDER
      
      (>!! in-chan "repaid")
      ; => DIAPER