(+ 1 2)
; => 3

(defn wisdom
  [words]
  (str words ", Daniel-san"))

(wisdom "Always bathe on Fridays")
; => "Always bathe on Fridays, Daniel-san"

(defn year-end-evaluation
  []
  (if (> (rand) 0.5)
    "You get a raise!"
    "Better luck next year!"))

(defn analyze-file
  [filename]
  (analysis (slurp filename)))

(defn analysis
  [text]
  (str "Character count: " (count text)))

(def great-baby-name "Rosanthony")
great-baby-name
; => "Rosanthony"

(let [great-baby-name "Bloodthunder"]
  great-baby-name)
; => "Bloodthunder"

great-baby-name
; => "Rosanthony"

(defn sum
  ([vals] (sum vals 0)) 
  ([vals accumulating-total]
   (if (empty? vals)  
     accumulating-total
     (sum (rest vals) (+ (first vals) accumulating-total)))))

(sum [39 5 1]) ; single-arity body calls two-arity body
(sum [39 5 1] 0)
(sum [5 1] 39)
(sum [1] 44)
(sum [] 45) ; base case is reached, so return accumulating-total
; => 45

(defn sum
  ([vals]
   (sum vals 0))
  ([vals accumulating-total]
   (if (empty? vals)
     accumulating-total
     (recur (rest vals) (+ (first vals) accumulating-total)))))


(require '[clojure.string :as s])
(defn clean
  [text]
  (s/replace (s/trim text) #"lol" "LOL"))

(clean "My boa constrictor is so sassy lol!  ")
; => "My boa constrictor is so sassy LOL!"

((comp inc *) 2 3)
; => 7

(def character
  {:name "Smooches McCutes"
   :attributes {:intelligence 10
                :strength 4
                :dexterity 5}})
(def c-int (comp :intelligence :attributes))
(def c-str (comp :strength :attributes))
(def c-dex (comp :dexterity :attributes))

(c-int character)
; => 10

(c-str character)
; => 4

(c-dex character)
; => 5


(fn [c] (:strength (:attributes c)))

(defn spell-slots
  [char]
  (int (inc (/ (c-int char) 2))))

(spell-slots character)
; => 6

(def spell-slots-comp (comp int inc #(/ % 2) c-int))

(defn two-comp
  [f g]
  (fn [& args]
    (f (apply g args))))

(+ 3 (+ 5 8))

(+ 3 13)

16

(defn sleepy-identity
  "Returns the given value after 1 second"
  [x]
  (Thread/sleep 1000)
  x)

(sleepy-identity "Mr. Fantastico")
; => "Mr. Fantastico" after 1 second

(sleepy-identity "Mr. Fantastico")
; => "Mr. Fantastico" after 1 second


(def memo-sleepy-identity (memoize sleepy-identity))
(memo-sleepy-identity "Mr. Fantastico")
; => "Mr. Fantastico" after 1 second

(memo-sleepy-identity "Mr. Fantastico")
; => "Mr. Fantastico" immediately


;; Peg Thing examples

(take 5 tri)
; => (1 3 6 10 15)

(triangular? 5) 
; => false

(triangular? 6) 
; => true

(row-tri 1) 
; => 1

(row-tri 2) 
; => 3

(row-tri 3) 
; => 6

(row-num 1) 
; => 1
(row-num 5) 
; => 3


(connect {} 15 1 2 4)
; => {1 {:connections {4 2}}
; =>  4 {:connections {1 2}}}

(assoc-in {} [:cookie :monster :vocals] "Finntroll")
; => {:cookie {:monster {:vocals "Finntroll"}}}

(get-in {:cookie {:monster {:vocals "Finntroll"}}} [:cookie :monster])
; => {:vocals "Finntroll"}

(assoc-in {} [1 :connections 4] 2)
; => {1 {:connections {4 2}}}

(connect-down-left {} 15 1)
; => {1 {:connections {4 2}
; =>  4 {:connections {1 2}}}}

(connect-down-right {} 15 3)
; => {3  {:connections {10 6}}
; =>  10 {:connections {3 6}}}

(add-pos {} 15 1)
{1 {:connections {6 3, 4 2}, :pegged true}
 4 {:connections {1 2}}
 6 {:connections {1 3}}}

(valid-moves my-board 1)  ; => {4 2}
(valid-moves my-board 6)  ; => {4 5}
(valid-moves my-board 11) ; => {4 7}
(valid-moves my-board 5)  ; => {}
(valid-moves my-board 8)  ; => {}


(valid-move? my-board 8 4) ; => nil
(valid-move? my-board 1 4) ; => 2

(characters-as-strings "a   b")
; => ("a" "b")

(characters-as-strings "a   cb")
; => ("a" "c" "b")
