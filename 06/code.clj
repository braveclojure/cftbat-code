(ns-name *ns*)
; => user

inc
; => #<core$inc clojure.core$inc@30132014>

'inc
; => inc

(map inc [1 2])
; => (2 3)

'(map inc [1 2])
; => (map inc [1 2])

(def great-books ["East of Eden" "The Glass Bead Game"])
; => #'user/great-books

great-books
; => ["East of Eden" "The Glass Bead Game"]

(ns-interns *ns*)
; => {great-books #'user/great-books}

(get (ns-interns *ns*) 'great-books)
; => #'user/great-books

(deref #'user/great-books)
; => ["East of Eden" "The Glass Bead Game"]

great-books
; => ["East of Eden" "The Glass Bead Game"]

(def great-books ["The Power of Bees" "Journey to Upstairs"])
great-books
; => ["The Power of Bees" "Journey to Upstairs"]

(create-ns 'cheese.taxonomy)
; => #<Namespace cheese.taxonomy>

(ns-name (create-ns 'cheese.taxonomy))
; => cheese-taxonomy

(in-ns 'cheese.analysis)
; => #<Namespace cheese.analysis>


(in-ns 'cheese.taxonomy)
(def cheddars ["mild" "medium" "strong" "sharp" "extra sharp"])
(in-ns 'cheese.analysis)
cheddars
; => Exception: Unable to resolve symbol: cheddars in this context

cheese.taxonomy/cheddars
; => ["mild" "medium" "strong" "sharp" "extra sharp"]


(in-ns 'cheese.taxonomy)
(def cheddars ["mild" "medium" "strong" "sharp" "extra sharp"])
(def bries ["Wisconsin" "Somerset" "Brie de Meaux" "Brie de Melun"])
(in-ns 'cheese.analysis)
(clojure.core/refer 'cheese.taxonomy)
bries
; => ["Wisconsin" "Somerset" "Brie de Meaux" "Brie de Melun"]

cheddars
; => ["mild" "medium" "strong" "sharp" "extra sharp"]

(clojure.core/get (clojure.core/ns-map clojure.core/*ns*) 'bries)
; => #'cheese.taxonomy/bries

(clojure.core/get (clojure.core/ns-map clojure.core/*ns*) 'cheddars)
; => #'cheese.taxonomy/cheddars


(clojure.core/refer 'cheese.taxonomy :only ['bries])
bries
; => ["Wisconsin" "Somerset" "Brie de Meaux" "Brie de Melun"]
cheddars 
; => RuntimeException: Unable to resolve symbol: cheddars


(clojure.core/refer 'cheese.taxonomy :exclude ['bries])
bries
; => RuntimeException: Unable to resolve symbol: bries
cheddars 
; => ["mild" "medium" "strong" "sharp" "extra sharp"]


(clojure.core/refer 'cheese.taxonomy :rename {'bries 'yummy-bries})
bries
; => RuntimeException: Unable to resolve symbol: bries
yummy-bries
; => ["Wisconsin" "Somerset" "Brie de Meaux" "Brie de Melun"]


(in-ns 'cheese.analysis)
;; Notice the dash after "defn"
(defn- private-function
  "Just an example function that does nothing"
  [])

(in-ns 'cheese.taxonomy)
(clojure.core/refer-clojure)
(cheese.analysis/private-function)
(refer 'cheese.analysis :only ['private-function])

(clojure.core/alias 'taxonomy 'cheese.taxonomy)
taxonomy/bries
; => ["Wisconsin" "Somerset" "Brie de Meaux" "Brie de Melun"]


;; this:
(require '[the-divine-cheese-code.visualization.svg :as svg])
;; is the same as this:
(require 'the-divine-cheese-code.visualization.svg)
(alias 'svg 'the-divine-cheese-code.visualization.svg)

(svg/points heists)
; => "50.95,6.97 47.37,8.55 43.3,5.37 47.37,8.55 41.9,12.45"


(use '[the-divine-cheese-code.visualization.svg :as svg])
(= svg/points points)
; => true

(= svg/latlng->point latlng->point)
; => true

(require 'the-divine-cheese-code.visualization.svg)
(use '[the-divine-cheese-code.visualization.svg :as svg :only [points]])
(refer 'the-divine-cheese-code.visualization.svg :as svg :only ['points])
(= svg/points points)
; => true

;; We can use the alias to reach latlng->point
svg/latlng->point
; This doesn't throw an exception

;; But we can't use the bare name
latlng->point
; This does throw an exception!


(ns the-divine-cheese-code.visualization.svg
  (:require [clojure.string :as s])
  (:refer-clojure :exclude [min max]))

(defn comparator-over-maps
  [comparison-fn ks]
  (fn [maps]
    (zipmap ks
            (map (fn [k] (apply comparison-fn (map k maps)))
                 ks))))

(def min (comparator-over-maps clojure.core/min [:lat :lng]))
(def max (comparator-over-maps clojure.core/max [:lat :lng]))


(min [{:a 1 :b 3} {:a 5 :b 0}])
; => {:a 1 :b 0}


(zipmap [:a :b] [1 2])
; => {:a 1 :b 2}


(merge-with - {:lat 50 :lng 10} {:lat 5 :lng 5})
; => {:lat 45 :lng 5}
