(.toUpperCase "By Bluebeard's bananas!")
; => "BY BLUEBEARD'S BANANAS!"

(.indexOf "Let's synergize our bleeding edges" "y") 
; => 7

(macroexpand-1 '(.toUpperCase "By Bluebeard's bananas!"))
; => (. "By Bluebeard's bananas!" toUpperCase)

(macroexpand-1 '(.indexOf "Let's synergize our bleeding edges" "y"))
; => (. "Let's synergize our bleeding edges" indexOf "y")

(macroexpand-1 '(Math/abs -3))
; => (. Math abs -3)

(new String)
; => ""

(String.)
; => ""

(String. "To Davey Jones's Locker with ye hardies")
; => "To Davey Jones's Locker with ye hardies"

(java.util.Stack.)
; => []

(let [stack (java.util.Stack.)] 
  (.push stack "Latest episode of Game of Thrones, ho!")
  stack)
; => ["Latest episode of Game of Thrones, ho!"]


(let [stack (java.util.Stack.)]
  (.push stack "Latest episode of Game of Thrones, ho!")
  (first stack))
; => "Latest episode of Game of Thrones, ho!"


(doto (java.util.Stack.)
  (.push "Latest episode of Game of Thrones, ho!")
  (.push "Whoops, I meant 'Land, ho!'"))
; => ["Latest episode of Game of Thrones, ho!" "Whoops, I meant 'Land, ho!'"]


(macroexpand-1
 '(doto (java.util.Stack.)
    (.push "Latest episode of Game of Thrones, ho!")
    (.push "Whoops, I meant 'Land, ho!'")))
; => (clojure.core/let
; =>  [G__2876 (java.util.Stack.)]
; =>  (.push G__2876 "Latest episode of Game of Thrones, ho!")
; =>  (.push G__2876 "Whoops, I meant 'Land, ho!'")
; =>  G__2876)

(import java.util.Stack)
(Stack.)
; => []

(import [java.util Date Stack]
        [java.net Proxy URI])

(Date.)
; => #inst "2016-09-19T20:40:02.733-00:00"

(ns pirate.talk
  (:import [java.util Date Stack]
           [java.net Proxy URI]))

(System/getenv)
{"USER" "the-incredible-bulk"
 "JAVA_ARCH" "x86_64"}

(System/getProperty "user.dir")
; => "/Users/dabulk/projects/dabook"

(System/getProperty "java.version")
; => "1.7.0_17"

#inst "2016-09-19T20:40:02.733-00:00"

(let [file (java.io.File. "/")]
  (println (.exists file))  
  (println (.canWrite file))
  (println (.getPath file))) 
; => true
; => false
; => /

(spit "/tmp/hercules-todo-list"
      "- kill dat lion brov
      - chop up what nasty multi-headed snake thing")

(slurp "/tmp/hercules-todo-list")

; => "- kill dat lion brov
; =>  - chop up what nasty multi-headed snake thing"

(let [s (java.io.StringWriter.)]
  (spit s "- capture cerynian hind like for real")
  (.toString s))
; => "- capture cerynian hind like for real"


(let [s (java.io.StringReader. "- get erymanthian pig what with the tusks")]
  (slurp s))
; => "- get erymanthian pig what with the tusks"


(with-open [todo-list-rdr (clojure.java.io/reader "/tmp/hercules-todo-list")]
  (println (first (line-seq todo-list-rdr))))
; => - kill dat lion brov
