ScaryClown bellyRubsTheClown = new ScaryClown();
      bellyRubsTheClown.balloonCount();
      // => 0
      
      bellyRubsTheClown.receiveBalloons(2);
      bellyRubsTheClown.balloonCount();
      // => 2
      
      bellyRubsTheClown.makeBalloonArt();
      // => "Belly Rubs makes a balloon shaped like a clown, because Belly Rubs
      // => is trying to scare you and nothing is scarier than clowns."


Math.abs(-50)
      // => 50


public class PiratePhrases
      {
          public static void main(String[] args)
          {
              System.out.println("Shiver me timbers!!!");
          }
      }


$ ls
      PiratePhrases.class PiratePhrases.java


Shiver me timbers!!!


 package pirate_phrases;
      
      public class Greetings
      {
          public static void hello()
          {
              System.out.println("Shiver me timbers!!!");
          }
      }


package pirate_phrases;
      
      public class Farewells
      {
          public static void goodbye()
          {
              System.out.println("A fair turn of the tide ter ye thar, ye magnificent sea friend!!");
          }
      }


import pirate_phrases.*;
      
      public class PirateConversation
      {
          public static void main(String[] args)
          {
              Greetings greetings = new Greetings();
              greetings.hello();
      
              Farewells farewells = new Farewells();
              farewells.goodbye();
          }
      }


Shiver me timbers!!!
      A fair turn of the tide ter ye thar, ye magnificent sea friend!!


cd pirate_phrases
      javac ../PirateConversation.java


../PirateConversation.java:1: error: package pirate_phrases does not exist
      import pirate_phrases.*;
      ^


jar cvfe conversation.jar PirateConversation PirateConversation.class
      pirate_phrases/*.class
      java -jar conversation.jar


Main-Class: PirateConversation


META-INF/
      META-INF/MANIFEST.MF
      PirateConversation.class
      pirate_phrases/Farewells.class
      pirate_phrases/Greetings.class


java -jar clojure-1.7.0.jar


Manifest-Version: 1.0
      Archiver-Version: Plexus Archiver
      Created-By: Apache Maven
      Built-By: hudson
      Build-Jdk: 1.7.0_20
      Main-Class: clojure.main


/**
       *   Copyright (c) Rich Hickey. All rights reserved.
       *   The use and distribution terms for this software are covered by the
       *   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
       *   which can be found in the file epl-v10.html at the root of this distribution.
       *   By using this software in any fashion, you are agreeing to be bound by
       *   the terms of this license.
       *   You must not remove this notice, or any other, from this software.
       **/
      
      package clojure;
      
      import clojure.lang.Symbol;
      import clojure.lang.Var;
      import clojure.lang.RT;
      
      public class main{
      
      final static private Symbol CLOJURE_MAIN = Symbol.intern("clojure.main");
      final static private Var REQUIRE = RT.var("clojure.core", "require");
      final static private Var LEGACY_REPL = RT.var("clojure.main", "legacy-repl");
      final static private Var LEGACY_SCRIPT = RT.var("clojure.main", "legacy-script");
      final static private Var MAIN = RT.var("clojure.main", "main");
      
      public static void legacy_repl(String[] args) {
          REQUIRE.invoke(CLOJURE_MAIN);
          LEGACY_REPL.invoke(RT.seq(args));
      }
      
      public static void legacy_script(String[] args) {
          REQUIRE.invoke(CLOJURE_MAIN);
          LEGACY_SCRIPT.invoke(RT.seq(args));
      }
      
      public static void main(String[] args) {
          REQUIRE.invoke(CLOJURE_MAIN);
          MAIN.applyTo(RT.seq(args));
      }
      }


lein uberjar
      java -jar target/uberjar/clojure-noob-0.1.0-SNAPSHOT-standalone.jar


(.toUpperCase "By Bluebeard's bananas!")
      ; => "BY BLUEBEARD'S BANANAS!"
      
       (.indexOf "Let's synergize our bleeding edges" "y") 
      ; => 7


"By Bluebeard's bananas!".toUpperCase()
      "Let's synergize our bleeding edges".indexOf("y")


 (java.lang.Math/abs -3) 
      ; => 3
      
       java.lang.Math/PI 
      ; => 3.141592653589793


(macroexpand-1 '(.toUpperCase "By Bluebeard's bananas!"))
      ; => (. "By Bluebeard's bananas!" toUpperCase)
      
      (macroexpand-1 '(.indexOf "Let's synergize our bleeding edges" "y"))
      ; => (. "Let's synergize our bleeding edges" indexOf "y")
      
      (macroexpand-1 '(Math/abs -3))
      ; => (. Math abs -3)


(. object-expr-or-classname-symbol method-or-member-symbol optional-args*)


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
            [G__2876 (java.util.Stack.)]
            (.push G__2876 "Latest episode of Game of Thrones, ho!")
            (.push G__2876 "Whoops, I meant 'Land, ho!'")
            G__2876)


(import java.util.Stack)
      (Stack.)
      ; => []


(import [package.name1 ClassName1 ClassName2]
              [package.name2 ClassName3 ClassName4])


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
            - chop up what nasty multi-headed snake thing"


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