{:user {:plugins [[cider/cider-nrepl "0.8.1"]]}} 


(setq initial-frame-alist '((top . 0) (left . 0) (width . 120) (height . 80)))


(setq initial-frame-alist '((top . 0) (left . 0) (width . 80) (height . 20)))


If you were a pirate, you know what would be the one thing that would
      really make you mad? Treasure chests with no handles. How the hell are
      you supposed to carry it?!
      
      The face of a child can say it all, especially the mouth part of the
      face.
      
      To me, boxing is like a ballet, except there's no music, no
      choreography, and the dancers hit each other.


(+ 1 2 3 4)
      ; => 10
      (map inc [1 2 3 4])
      ; => (2 3 4 5)
      (reduce + [5 6 100])
      ; => 111


(println "Cleanliness is next to godliness")


(defn train
        []
        (println "Choo choo!"))


(+ 1 2 3 4)


(+ 1 (* 2 3) 4)


(+ 1 |2 3 4)


(+ 1 (|2) 3 4)


(+ 1 (* |2) 3 4)


(+ 1 (* |2 3) 4)


(+ 1 (|* 2 3 4))


(+ 1 (|* 2 3) 4)


(map (comp record first)
           (d/q '[:find ?post
                  :in $ ?search
                  :where
                  [(fulltext $ :post/content ?search)
                   [[?post ?content]]]]
                (db/db)
                (:q params)))