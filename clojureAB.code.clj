(deftask fire
	  "Prints 'My pants are on fire!'"
	  []
	  (println "My pants are on fire!"))


(deftask fire
	  "Announces that something is on fire"
	  [t thing     THING str  "The thing that's on fire"
	   p pluralize       bool "Whether to pluralize"]
	  (let [verb (if pluralize "are" "is")]
	    (println "My" thing verb "on fire!")))


boot fire -t heart
	# => My heart is on fire!
	
	boot fire -t logs -p
	# => My logs are on fire!


boot fire -h
	# Announces that something is on fire
	# 
	# Options:
	#   -h, --help         Print this help info.
	#   -t, --thing THING  Set the thing that's on fire to THING.
	#   -p, --pluralize    Whether to pluralize


(fire "-t" "NBA Jam guy")
	; My NBA Jam guy is on fire!
	; => nil


(fire :thing "NBA Jam guy")
	; My NBA Jam guy is on fire!
	; => nil


(fire "-p" "-t" "NBA Jam guys")
	; My NBA Jam guys are on fire!
	; => nil
	
	(fire :pluralize true :thing "NBA Jam guys")
	; My NBA Jam guys are on fire!
	; => nil


rake db:create d{:tag :a, :attrs {:href "db:seed"}, :content ["b:migra"]}te db:seed


(def strinc (comp str inc))
	(strinc 3)
	; => "4"


(defn whiney-str
	  [rejects]
	  {:pre [(set? rejects)]}
	  (fn [x]
	    (if (rejects x)
	      (str "I don't like " x)
	      (str x))))
	
	(def whiney-strinc (comp (whiney-str #{2}) inc))
	(whiney-strinc 1)
	; => "I don't like 2"


(defn whiney-middleware
	  [next-handler rejects]
	  {:pre [(set? rejects)]}
	  (fn [x]
	     (if (= x 1)
	      "I'm not going to bother doing anything to that"
	      (let [y (next-handler x)]
	        (if (rejects y)
	          (str "I don't like " y)
	          (str y))))))
	
	(def whiney-strinc (whiney-middleware inc #{2}))
	(whiney-strinc 1)
	; => "I don't like 2"


(defn whiney-middleware-factory
	  [rejects]
	  {:pre [(set? rejects)]}
	  (fn [handler]
	    (fn [x]
	      (if (= x 1)
	        "I'm not going to bother doing anything to that"
	        (let [y (handler x)]
	          (if (rejects y)
	            (str "I don't like " y " :'(")
	            (str y)))))))
	
	(def whiney-strinc ((whiney-middleware-factory #{3}) inc))


(deftask what
	  "Specify a thing"
	  [t thing     THING str  "An object"
	   p pluralize       bool "Whether to pluralize"]
	  (fn middleware [next-handler]
	     (fn handler [fileset]
	      (next-handler (merge fileset {:thing thing :pluralize pluralize})))))
	
	(deftask fire
	  "Announce a thing is on fire"
	  []
	  (fn middleware [next-handler]
	     (fn handler [fileset]
	      (let [verb (if (:pluralize fileset) "are" "is")]
	        (println "My" (:thing fileset) verb "on fire!")
	        fileset))))


boot what -t "pants" -p – fire


(boot (what :thing "pants" :pluralize true) (fire))


{:server-port 80
	 :request-method :get
	 :scheme :http}