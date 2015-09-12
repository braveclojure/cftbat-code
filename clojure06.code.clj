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


user=> (create-ns 'cheese.taxonomy)
      ; => #<Namespace cheese.taxonomy>


user=> (ns-name (create-ns 'cheese.taxonomy))
      ; => cheese-taxonomy


user=> (in-ns 'cheese.analysis)
      ; => #<Namespace cheese.analysis>


cheese.analysis=> (in-ns 'cheese.taxonomy)
      cheese.taxonomy=> (def cheddars ["mild" "medium" "strong" "sharp" "extra sharp"])
      cheese.taxonomy=> (in-ns 'cheese.analysis)
      
      cheese.analysis=> cheddars
      ; => Exception: Unable to resolve symbol: cheddars in this context


cheese.analysis=> cheese.taxonomy/cheddars
      ; => ["mild" "medium" "strong" "sharp" "extra sharp"]


user=> (in-ns 'cheese.taxonomy)
      cheese.taxonomy=> (def cheddars ["mild" "medium" "strong" "sharp" "extra sharp"])
      cheese.taxonomy=> (def bries ["Wisconsin" "Somerset" "Brie de Meaux" "Brie de Melun"])
      cheese.taxonomy=> (in-ns 'cheese.analysis)
      cheese.analysis=> (clojure.core/refer 'cheese.taxonomy)
      cheese.analysis=> bries
      ; => ["Wisconsin" "Somerset" "Brie de Meaux" "Brie de Melun"]
      
      cheese.analysis=> cheddars
      ; => ["mild" "medium" "strong" "sharp" "extra sharp"]


cheese.analysis=> (clojure.core/get (clojure.core/ns-map clojure.core/*ns*) 'bries)
      ; => #'cheese.taxonomy/bries
      
      cheese.analysis=> (clojure.core/get (clojure.core/ns-map clojure.core/*ns*) 'cheddars)
      ; => #'cheese.taxonomy/cheddars


cheese.analysis=> (clojure.core/refer 'cheese.taxonomy :only ['bries])
      cheese.analysis=> bries
      ; => ["Wisconsin" "Somerset" "Brie de Meaux" "Brie de Melun"]
      cheese.analysis=> cheddars 
      ; => RuntimeException: Unable to resolve symbol: cheddars


cheese.analysis=> (clojure.core/refer 'cheese.taxonomy :exclude ['bries])
      cheese.analysis=> bries
      ; => RuntimeException: Unable to resolve symbol: bries
      cheese.analysis=> cheddars 
      ; => ["mild" "medium" "strong" "sharp" "extra sharp"]


cheese.analysis=> (clojure.core/refer 'cheese.taxonomy :rename {'bries 'yummy-bries})
      cheese.analysis=> bries
      ; => RuntimeException: Unable to resolve symbol: bries
      cheese.analysis=> yummy-bries
      ; => ["Wisconsin" "Somerset" "Brie de Meaux" "Brie de Melun"]


(in-ns 'cheese.analysis)
      ;; Notice the dash after "defn"
      (defn- private-function
        "Just an example function that does nothing"
        [])


cheese.analysis=> (in-ns 'cheese.taxonomy)
      cheese.taxonomy=> (clojure.core/refer-clojure)
       cheese.taxonomy=> (cheese.analysis/private-function)
       cheese.taxonomy=> (refer 'cheese.analysis :only ['private-function])


cheese.analysis=> (clojure.core/alias 'taxonomy 'cheese.taxonomy)
      cheese.analysis=> taxonomy/bries
      ; => ["Wisconsin" "Somerset" "Brie de Meaux" "Brie de Melun"]


lein new app the-divine-cheese-code


| .gitignore
      | doc
      | | intro.md
      | project.clj
      | README.md
      | resources
      | src
      | | the_divine_cheese_code
      | | | core.clj
      | test
      | | the_divine_cheese_code
      | | | core_test.clj


(ns the-divine-cheese-code.core
        (:gen-class))


mkdir src/the_divine_cheese_code/visualization
      touch src/the_divine_cheese_code/visualization/svg.clj


(ns the-divine-cheese-code.visualization.svg)
      
      (defn latlng->point
        "Convert lat/lng map to comma-separated string" 
        [latlng]
        (str (:lng latlng) "," (:lat latlng)))
      
      (defn points
        [locations]
        (clojure.string/join " " (map latlng->point locations)))


(ns the-divine-cheese-code.core)
      ;; Ensure that the SVG code is evaluated
      (require 'the-divine-cheese-code.visualization.svg)
      ;; Refer the namespace so that you don't have to use the 
      ;; fully qualified name to reference svg functions
      (refer 'the-divine-cheese-code.visualization.svg)
      
      (def heists [{:location "Cologne, Germany"
                    :cheese-name "Archbishop Hildebold's Cheese Pretzel"
                    :lat 50.95
                    :lng 6.97}
                   {:location "Zurich, Switzerland"
                    :cheese-name "The Standard Emmental"
                    :lat 47.37
                    :lng 8.55}
                   {:location "Marseille, France"
                    :cheese-name "Le Fromage de Cosquer"
                    :lat 43.30
                    :lng 5.37}
                   {:location "Zurich, Switzerland"
                    :cheese-name "The Lesser Emmental"
                    :lat 47.37
                    :lng 8.55}
                   {:location "Vatican City"
                    :cheese-name "The Cheese of Turin"
                    :lat 41.90
                    :lng 12.45}])
      
      (defn -main
        [& args]
        (println (points heists)))


50.95,6.97 47.37,8.55 43.3,5.37 47.37,8.55 41.9,12.45


(require '[the-divine-cheese-code.visualization.svg :as svg])


(require 'the-divine-cheese-code.visualization.svg)
      (alias 'svg 'the-divine-cheese-code.visualization.svg)


(svg/points heists)
      ; => "50.95,6.97 47.37,8.55 43.3,5.37 47.37,8.55 41.9,12.45"


(require 'the-divine-cheese-code.visualization.svg)
      (refer 'the-divine-cheese-code.visualization.svg)


(use 'the-divine-cheese-code.visualization.svg)


(require 'the-divine-cheese-code.visualization.svg)
      (refer 'the-divine-cheese-code.visualization.svg)
      (alias 'svg 'the-divine-cheese-code.visualization.svg)


(use '[the-divine-cheese-code.visualization.svg :as svg])
      (= svg/points points)
      ; => true
      
      (= svg/latlng->point latlng->point)
      ; => true


(require 'the-divine-cheese-code.visualization.svg)
      (refer 'the-divine-cheese-code.visualization.svg :as :only ['points])


(use '[the-divine-cheese-code.visualization.svg :as svg :only [points]])
      (refer 'the-divine-cheese-code.visualization.svg :as :only ['points])
      (= svg/points points)
      ; => true
      
      ;; We can use the alias to reach latlng->point
      svg/latlng->point
      ; This doesn't throw an exception
      
      ;; But we can't use the bare name
      latlng->point
      ; This does throw an exception!


(ns the-divine-cheese-code.core
        (:refer-clojure :exclude [println]))


(in-ns 'the-divine-cheese-code.core)
      (refer 'clojure.core :exclude ['println])


(ns the-divine-cheese-code.core
        (:require the-divine-cheese-code.visualization.svg))


(in-ns 'the-divine-cheese-code.core)
      (require 'the-divine-cheese-code.visualization.svg)


(ns the-divine-cheese-code.core
        (:require [the-divine-cheese-code.visualization.svg :as svg]))


(in-ns 'the-divine-cheese-code.core)
      (require ['the-divine-cheese-code.visualization.svg :as 'svg])


(ns the-divine-cheese-code.core
        (:require [the-divine-cheese-code.visualization.svg :as svg]
                  [clojure.java.browse :as browse]))


(in-ns 'the-divine-cheese-code.core)
      (require ['the-divine-cheese-code.visualization.svg :as 'svg])
      (require ['clojure.java.browse :as 'browse])


(ns the-divine-cheese-code.core
        (:require [the-divine-cheese-code.visualization.svg :refer [points]]))


(in-ns 'the-divine-cheese-code.core)
      (require 'the-divine-cheese-code.visualization.svg)
      (refer 'the-divine-cheese-code.visualization.svg :only ['points])


(ns the-divine-cheese-code.core
        (:require [the-divine-cheese-code.visualization.svg :refer :all]))


(in-ns 'the-divine-cheese-code.core)
      (require 'the-divine-cheese-code.visualization.svg)
      (refer 'the-divine-cheese-code.visualization.svg)


(ns the-divine-cheese-code.core
        (:use clojure.java.browse))


(in-ns 'the-divine-cheese-code.core)
      (use 'clojure.java.browse)


(ns the-divine-cheese-code.core
        (:use [clojure.java browse io]))


(in-ns 'the-divine-cheese-code.core)
      (use 'clojure.java.browse)
      (use 'clojure.java.io)


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


 (defn translate-to-00
        [locations]
        (let [mincoords (min locations)]
          (map #(merge-with - % mincoords) locations)))
      
       (defn scale
        [width height locations]
        (let [maxcoords (max locations)
              ratio {:lat (/ height (:lat maxcoords))
                     :lng (/ width (:lng maxcoords))}]
          (map #(merge-with * % ratio) locations)))


(merge-with - {:lat 50 :lng 10} {:lat 5 :lng 5})
      ; => {:lat 45 :lng 5}


(defn latlng->point
        "Convert lat/lng map to comma-separated string" 
        [latlng]
        (str (:lng latlng) "," (:lat latlng)))
      
      (defn points
        "Given a seq of lat/lng maps, return string of points joined by space"
        [locations]
        (s/join " " (map latlng->point locations)))
      
      (defn line
        [points]
        (str "<polyline points=\"" points "\" />"))
      
      (defn transform
        "Just chains other functions"
        [width height locations]
        (->> locations
             translate-to-00
             (scale width height)))
      
      (defn xml
        "svg 'template', which also flips the coordinate system"
        [width height locations]
        (str "<svg height=\"" height "\" width=\"" width "\">"
             ;; These two <g> tags flip the coordinate system
             "<g transform=\"translate(0," height ")\">"
             "<g transform=\"scale(1,-1)\">"
             (-> (transform width height locations)
                 points
                 line)
             "</g></g>"
             "</svg>"))


(ns the-divine-cheese-code.core
        (:require [clojure.java.browse :as browse]
                  [the-divine-cheese-code.visualization.svg :refer [xml]])
        (:gen-class))
      
      (def heists [{:location "Cologne, Germany"
                    :cheese-name "Archbishop Hildebold's Cheese Pretzel"
                    :lat 50.95
                    :lng 6.97}
                   {:location "Zurich, Switzerland"
                    :cheese-name "The Standard Emmental"
                    :lat 47.37
                    :lng 8.55}
                   {:location "Marseille, France"
                    :cheese-name "Le Fromage de Cosquer"
                    :lat 43.30
                    :lng 5.37}
                   {:location "Zurich, Switzerland"
                    :cheese-name "The Lesser Emmental"
                    :lat 47.37
                    :lng 8.55}
                   {:location "Vatican City"
                    :cheese-name "The Cheese of Turin"
                    :lat 41.90
                    :lng 12.45}])
      
      (defn url
        [filename]
        (str "file:///"
             (System/getProperty "user.dir")
             "/"
             filename))
      
      (defn template
        [contents]
        (str "<style>polyline { fill:none; stroke:#5881d8; stroke-width:3}</style>"
             contents))
      
      (defn -main
        [& args]
        (let [filename "map.html"]
          (->> heists
               (xml 50 100)
               template
               (spit filename))
          (browse/browse-url (url filename))))