

(define (trace x) ((lambda (_) x) (display x)))

(define (y-combinator le)
	((lambda (f) (f f))
		(lambda (f)
			(le (lambda (x) ((f f) x))))))

(define factorial
	(y-combinator (lambda (recurse)
		(lambda (n)
			(if (= n 0)
				1
				(* n (recurse (- n 1))))))))

(define (selection-sort li)
	(let*
		((select-min (lambda (recurse min li prev)
			(if (null? li)
				(list min prev)
				(if (< (car li) min)
					(recurse recurse (car li) (cdr li) (cons min prev))
					(recurse recurse min (cdr li) (cons (car li) prev))))))
		(do-sort (lambda (recurse li)
			(if (null? li)
				li
				(let*
					((selection (select-min select-min (car li) (cdr li) '()))
					(min (car selection)))
					(cons min (recurse recurse (cadr selection))))))))
		(do-sort do-sort li)))


(define (insertion-sort li)
	(let
		((insert-into (lambda (recurse x li)
			(if (null? li)
				(list x)
				(if (>= x (car li))
					(cons (car li) (recurse recurse x (cdr li)))
					(cons x li))))))
		(fold (lambda (x accum) (insert-into insert-into x accum)) '() li)))

(define (merge-sort li)
	(if (or (null? li) (null? (cdr li)))
		li
		(let*
			((partition-list (lambda (recurse li left right i)
					(if (null? li)
						(list left right)
						(if (= (remainder i 2) 0)
							(recurse recurse (cdr li) (cons (car li) left) right (+ i 1))
							(recurse recurse (cdr li) left (cons (car li) right) (+ i 1))))))
			(merge-lists (lambda (recurse left right)
					(if (null? left)
						right
						(if (null? right)
							left
							((lambda (left right left-car right-car left-cdr right-cdr)
								(if (<= left-car right-car)
									(cons left-car (recurse recurse left-cdr right))
									(cons right-car (recurse recurse left right-cdr)))) left right (car left) (car right) (cdr left) (cdr right))))))
			(partitions (partition-list partition-list li '() '() 0))
			(left (merge-sort (car partitions)))
			(right (merge-sort (cadr partitions))))
			(merge-lists merge-lists left right))))


(define (quick-sort li)
	(if (null? li)
		li
		((lambda (partitions)
			((lambda (left right pivot)
				(append (quick-sort left) (cons pivot (quick-sort right)))) (car partitions) (cadr partitions) (car li)))
			((lambda (pivot rest)
				(fold (lambda (x accum)
					((lambda (left right x)
						(if (< x pivot)
							(list (cons x left) right)
							(list left (cons x right)))) (car accum) (cadr accum) x)) '(() ()) rest)) (car li) (cdr li)))))

(define GOLDEN_RATIO (/ (+ 1 (sqrt 5)) 2))

(define DIGITS (map (lambda (x) (- (char->integer x) (char->integer #\0))) (string->list (string-replace (number->string GOLDEN_RATIO) "" 1 2))))

(define (display-list str li) ((lambda (_) ((lambda (_) (display "\n")) (display li))) (display str)))

(display-list "Original:       " DIGITS)
(display-list "Selection sort: " (selection-sort DIGITS))
(display-list "Insertion sort: " (insertion-sort DIGITS))
(display-list "Merge sort:     " (merge-sort DIGITS))
(display-list "Quick sort:     " (quick-sort DIGITS))

; (display (factorial 5))