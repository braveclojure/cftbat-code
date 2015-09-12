lein new app clojure-noob


| .gitignore
      | doc
      | | intro.md
       | project.clj
      | README.md
       | resources
      | src
      | | clojure_noob
       | | | core.clj
       | test
      | | clojure_noob
      | | | core_test.clj


 (ns clojure-noob.core
        (:gen-class))
      
       (defn -main
        "I don't do a whole lot...yet."
        [& args]
         (println "Hello, World!"))


lein run


lein uberjar


java -jar target/uberjar/clojure-noob-0.1.0-SNAPSHOT-standalone.jar


lein repl


nREPL server started on port 28925
      REPL-y 0.1.10
      Clojure 1.7.0
          Exit: Control+D or (exit) or (quit)
      Commands: (user/help)
          Docs: (doc function-name-here)
                (find-doc "part-of-name-here")
        Source: (source function-name-here)
                (user/sourcery function-name-here)
       Javadoc: (javadoc java-object-or-class-here)
      Examples from clojuredocs.org: [clojuredocs or cdoc]
                (user/clojuredocs name-here)
                (user/clojuredocs "ns-here" "name-here")
      clojure-noob.core=>


clojure-noob.core=> (-main)
      I'm a little teapot!
      nil


clojure-noob.core=> (+ 1 2 3 4)
      10
      clojure-noob.core=> (* 1 2 3 4)
      24
      clojure-noob.core=> (first [1 2 3 4])
      1


(do (println "no prompt here!")
         (+ 1 3))
      ; => no prompt here!
      ; => 4