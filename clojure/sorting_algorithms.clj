(use 'clojure.contrib.math)

(def GOLDEN_RATIO (/ (+ 1 (sqrt 5)) 2))
(def DIGITS (map (fn [x] (Character/getNumericValue x)) (clojure.string/replace (str GOLDEN_RATIO) "." "")))

(defn selection-sort [li]
	(let
		[select-min (fn self [min unsearched previous]
			(if (empty? unsearched)
				(list min previous)
				(if (< (first unsearched) min)
					(self (first unsearched) (rest unsearched) (cons min previous))
					(self min (rest unsearched) (cons (first unsearched) previous)))))
		do-selection-sort (fn self [sorted unsorted]
			(if (empty? unsorted)
				sorted
				(let
					[result (select-min (first unsorted) (rest unsorted) '())]
					(self (cons (first result) sorted) (second result)))))]
		(reverse (do-selection-sort '() li))))

(defn insertion-sort [li]
	(let [insert-into (fn self [x sorted prev]
		(if (empty? sorted)
			(concat (reverse prev) (list x))
			(if (>= x (first sorted))
				(self x (rest sorted) (cons (first sorted) prev))
				(concat (reverse prev) (cons x sorted)))))]
		(reduce (fn [accum x] (insert-into x accum '())) '() li)))

(defn insertion-sort-2 [li]
	(let [insert-into (fn self [x sorted]
		(if (empty? sorted)
			(list x)
			(if (>= x (first sorted))
				(cons (first sorted) (self x (rest sorted)))
				(cons x sorted))))]
		(reduce (fn [accum x] (insert-into x accum)) '() li)))

(defn merge-sort [li]
	(if (or (empty? li) (empty? (rest li)))
		li
		(let [partition-list (fn self [li left right i]
			(if (empty? li)
				(list left right)
				(if (= (mod i 2) 0)
					(self (rest li) (cons (first li) left) right (+ i 1))
					(self (rest li) left (cons (first li) right) (+ i 1)))))
			partitions (partition-list li '() '() 0)
			left (merge-sort (first partitions))
			right (merge-sort (second partitions))
			merge-lists (fn self [sorted left right]
				(if (empty? left)
					(concat right sorted)
					(if (empty? right)
						(concat left sorted)
						(if (<= (first left) (first right))
							(self (cons (first left) sorted) (rest left) right)
							(self (cons (first right) sorted) left (rest right))))))]
			(reverse (merge-lists '() left right)))))

(defn quick-sort [li]
	(if (or (empty? li) (empty? (rest li)))
		li
		(let [pivot (first li)
			partitions (reduce (fn [accum x]
				(let [left (first accum)
					right (second accum)]
					(if (<= x pivot)
						(list (cons x left) right)
						(list left (cons x right))))) '(() ()) (rest li))
			left (quick-sort (first partitions))
			right (quick-sort (second partitions))]
			(concat left (cons pivot right)))))

(println "Original:         " DIGITS)
(println "Selection sort:   " (selection-sort DIGITS))
(println "Insertion sort:   " (insertion-sort DIGITS))
(println "Insertion sort 2: " (insertion-sort-2 DIGITS))
(println "Merge sort:       " (merge-sort DIGITS))
(println "Quick sort:       " (quick-sort DIGITS))