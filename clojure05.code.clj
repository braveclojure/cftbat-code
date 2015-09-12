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


var haplessObject = {
        emotion: "Carefree!"
      };
      
      var evilMutator = function(object){
        object.emotion = "So emo :'(";
      }
      
      evilMutator(haplessObject);
      haplessObject.emotion;
      // => "So emo :'("


var wrestlers = getAlligatorWrestlers();
      var totalBites = 0;
      var l = wrestlers.length;
      
      for(var i=0; i < l; i++){
        totalBites += wrestlers[i].timesBitten;
      }


var allPatients = getArkhamPatients();
      var analyzedPatients = [];
      var l = allPatients.length;
      
      for(var i=0; i < l; i++){
        if(allPatients[i].analyzed){
          analyzedPatients.push(allPatients[i]);
        }
      }


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


class GlamourShotCaption
        attr_reader :text
        def initialize(text)
          @text = text
          clean!
        end
      
        private
        def clean!
          text.trim!
          text.gsub!(/lol/, "LOL")
        end
      end
      
      best = GlamourShotCaption.new("My boa constrictor is so sassy lol!  ")
      best.text
      ; => "My boa constrictor is so sassy LOL!"


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


Get ready to play Peg Thing!
      How many rows? [5]


Here's your board:
             a0
            b0 c0
          d0 e0 f0
         g0 h0 i0 j0
       k0 l0 m0 n0 o0
      Remove which peg? [e]


Here's your board:
             a0
            b0 c0
          d0 e- f0
         g0 h0 i0 j0
       k0 l0 m0 n0 o0
      Move from where to where? Enter two letters:


Here's your board:
             a0
            b0 c0
          d0 e0 f0
         g0 h- i0 j0
       k0 l- m0 n0 o0
      Move from where to where? Enter two letters:


(ns pegthing.core
        (require [clojure.set :as set])
        (:gen-class))
      
      (declare successful-move prompt-move game-over query-rows)


{1  {:pegged true, :connections {6 3, 4 2}},
       2  {:pegged true, :connections {9 5, 7 4}},
       3  {:pegged true, :connections {10 6, 8 5}},
       4  {:pegged true, :connections {13 8, 11 7, 6 5, 1 2}},
       5  {:pegged true, :connections {14 9, 12 8}},
       6  {:pegged true, :connections {15 10, 13 9, 4 5, 1 3}},
       7  {:pegged true, :connections {9 8, 2 4}},
       8  {:pegged true, :connections {10 9, 3 5}},
       9  {:pegged true, :connections {7 8, 2 5}},
       10 {:pegged true, :connections {8 9, 3 6}},
       11 {:pegged true, :connections {13 12, 4 7}},
       12 {:pegged true, :connections {14 13, 5 8}},
       13 {:pegged true, :connections {15 14, 11 12, 6 9, 4 8}},
       14 {:pegged true, :connections {12 13, 5 9}},
       15 {:pegged true, :connections {13 14, 6 10}},
       :rows 5}


{:pegged true, :connections {6 3, 4 2}}


(defn tri*
        "Generates lazy sequence of triangular numbers"
        ([] (tri* 0 1))
        ([sum n]
           (let [new-sum (+ sum n)]
             (cons new-sum (lazy-seq (tri* new-sum (inc n)))))))


(def tri (tri*))


(take 5 tri)
      ; => (1 3 6 10 15)


(defn triangular?
        "Is the number triangular? e.g. 1, 3, 6, 10, 15, etc"
        [n]
        (= n (last (take-while #(>= n %) tri))))
      (triangular? 5) 
      ; => false
      
      (triangular? 6) 
      ; => true


(defn row-tri
        "The triangular number at the end of row n"
        [n]
        (last (take n tri)))
      (row-tri 1) 
      ; => 1
      
      (row-tri 2) 
      ; => 3
      
      (row-tri 3) 
      ; => 6


(defn row-num
        "Returns row number the position belongs to: pos 1 in row 1,
        positions 2 and 3 in row 2, etc"
        [pos]
        (inc (count (take-while #(> pos %) tri))))
      (row-num 1) 
      ; => 1
      (row-num 5) 
      ; => 3


(defn connect
        "Form a mutual connection between two positions"
        [board max-pos pos neighbor destination]
        (if (<= destination max-pos)
          (reduce (fn [new-board [p1 p2]]
                    (assoc-in new-board [p1 :connections p2] neighbor))
                  board
                  [[pos destination] [destination pos]])
          board))
      
      (connect {} 15 1 2 4)
      ; => {1 {:connections {4 2}}
            4 {:connections {1 2}}}


(assoc-in {} [:cookie :monster :vocals] "Finntroll")
      ; => {:cookie {:monster {:vocals "Finntroll"}}}
      
      (get-in {:cookie {:monster {:vocals "Finntroll"}}} [:cookie :monster])
      ; => {:vocals "Finntroll"}
      
      (assoc-in {} [1 :connections 4] 2)
      ; => {1 {:connections {4 2}}}


(defn connect-right
        [board max-pos pos]
        (let [neighbor (inc pos)
              destination (inc neighbor)]
          (if-not (or (triangular? neighbor) (triangular? pos))
            (connect board max-pos pos neighbor destination)
            board)))
      
      (defn connect-down-left
        [board max-pos pos]
        (let [row (row-num pos)
              neighbor (+ row pos)
              destination (+ 1 row neighbor)]
          (connect board max-pos pos neighbor destination)))
      
      (defn connect-down-right
        [board max-pos pos]
        (let [row (row-num pos)
              neighbor (+ 1 row pos)
              destination (+ 2 row neighbor)]
          (connect board max-pos pos neighbor destination)))


(connect-down-left {} 15 1)
      ; => {1 {:connections {4 2}
            4 {:connections {1 2}}}}
      
      (connect-down-right {} 15 3)
      ; => {3  {:connections {10 6}}
            10 {:connections {3 6}}}


(defn add-pos
        "Pegs the position and performs connections"
        [board max-pos pos]
        (let [pegged-board (assoc-in board [pos :pegged] true)]
          (reduce (fn [new-board connection-creation-fn]
                    (connection-creation-fn new-board max-pos pos))
                  pegged-board
                  [connect-right connect-down-left connect-down-right])))
      
      (add-pos {} 15 1)
      {1 {:connections {6 3, 4 2}, :pegged true}
       4 {:connections {1 2}}
       6 {:connections {1 3}}}


(defn clean
        [text]
        (reduce (fn [string string-fn] (string-fn string))
                text
                [s/trim #(s/replace % #"lol" "LOL")]))


(defn new-board
        "Creates a new board with the given number of rows"
        [rows]
        (let [initial-board {:rows rows}
              max-pos (row-tri rows)]
          (reduce (fn [board pos] (add-pos board max-pos pos))
                  initial-board
                  (range 1 (inc max-pos)))))


(defn pegged?
        "Does the position have a peg in it?"
        [board pos]
        (get-in board [pos :pegged]))
      
      (defn remove-peg
        "Take the peg at given position out of the board"
        [board pos]
        (assoc-in board [pos :pegged] false))
      
      (defn place-peg
        "Put a peg in the board at given position"
        [board pos]
        (assoc-in board [pos :pegged] true))
      
      (defn move-peg
        "Take peg out of p1 and place it in p2"
        [board p1 p2]
        (place-peg (remove-peg board p1) p2))


(defn valid-moves
        "Return a map of all valid moves for pos, where the key is the
        destination and the value is the jumped position"
        [board pos]
        (into {}
              (filter (fn [[destination jumped]]
                        (and (not (pegged? board destination))
                             (pegged? board jumped)))
                      (get-in board [pos :connections]))))


(def my-board (assoc-in (new-board 5) [4 :pegged] false))


(valid-moves my-board 1)  ; => {4 2}
      (valid-moves my-board 6)  ; => {4 5}
      (valid-moves my-board 11) ; => {4 7}
      (valid-moves my-board 5)  ; => {}
      (valid-moves my-board 8)  ; => {}


(defn valid-move?
        "Return jumped position if the move from p1 to p2 is valid, nil
        otherwise"
        [board p1 p2]
        (get (valid-moves board p1) p2))
        
      (valid-move? my-board 8 4) ; => nil
      (valid-move? my-board 1 4) ; => 2


(defn make-move
        "Move peg from p1 to p2, removing jumped peg"
        [board p1 p2]
        (if-let [jumped (valid-move? board p1 p2)]
          (move-peg (remove-peg board jumped) p1 p2)))


(defn can-move?
        "Do any of the pegged positions have valid moves?"
        [board]
        (some (comp not-empty (partial valid-moves board))
              (map first (filter #(get (second %) :pegged) board))))


([1 {:connections {6 3, 4 2}, :pegged true}]
       [2 {:connections {9 5, 7 4}, :pegged true}])


(def alpha-start 97)
      (def alpha-end 123)
      (def letters (map (comp str char) (range alpha-start alpha-end)))
      (def pos-chars 3)


(defn render-pos
        [board pos]
        (str (nth letters (dec pos))
             (if (get-in board [pos :pegged])
               (colorize "0" :blue)
               (colorize "-" :red))))
      
      (defn row-positions
        "Return all positions in the given row"
        [row-num]
        (range (inc (or (row-tri (dec row-num)) 0))
               (inc (row-tri row-num))))
      
      (defn row-padding
        "String of spaces to add to the beginning of a row to center it"
        [row-num rows]
        (let [pad-length (/ (* (- rows row-num) pos-chars) 2)]
          (apply str (take pad-length (repeat " ")))))
      
      (defn render-row
        [board row-num]
        (str (row-padding row-num (:rows board))
             (clojure.string/join " " (map (partial render-pos board) 
                                           (row-positions row-num)))))


(defn print-board
        [board]
        (doseq [row-num (range 1 (inc (:rows board)))]
          (println (render-row board row-num))))


(defn letter->pos
        "Converts a letter string to the corresponding position number"
        [letter]
        (inc (- (int (first letter)) alpha-start)))


(defn get-input
        "Waits for user to enter text and hit enter, then cleans the input"
        ([] (get-input nil))
        ([default]
           (let [input (clojure.string/trim (read-line))]
             (if (empty? input)
               default
               (clojure.string/lower-case input)))))


(characters-as-strings "a   b")
      ; => ("a" "b")
      
      (characters-as-strings "a   cb")
      ; => ("a" "c" "b")


(defn prompt-move
        [board]
        (println "\nHere's your board:")
        (print-board board)
        (println "Move from where to where? Enter two letters:")
        (let [input (map letter->pos (characters-as-strings (get-input)))]
          (if-let [new-board (make-move board (first input) (second input))]
            (user-entered-valid-move new-board)
            (user-entered-invalid-move board))))


(defn user-entered-invalid-move
        "Handles the next step after a user has entered an invalid move"
        [board]
        (println "\n!!! That was an invalid move :(\n")
        (prompt-move board))


(defn user-entered-valid-move
        "Handles the next step after a user has entered a valid move"
        [board]
        (if (can-move? board)
          (prompt-move board)
          (game-over board)))


(defn game-over
        "Announce the game is over and prompt to play again"
        [board]
        (let [remaining-pegs (count (filter :pegged (vals board)))]
          (println "Game over! You had" remaining-pegs "pegs left:")
          (print-board board)
          (println "Play again? y/n [y]")
          (let [input (get-input "y")]
            (if (= "y" input)
              (prompt-rows)
              (do
                (println "Bye!")
                (System/exit 0))))))


(defn prompt-empty-peg
        [board]
        (println "Here's your board:")
        (print-board board)
        (println "Remove which peg? [e]")
        (prompt-move (remove-peg board (letter->pos (get-input "e")))))
      
      (defn prompt-rows
        []
        (println "How many rows? [5]")
        (let [rows (Integer. (get-input 5))
              board (new-board rows)]
          (prompt-empty-peg board)))