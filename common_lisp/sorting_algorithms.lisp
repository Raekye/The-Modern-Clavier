

(defun selection-sort (li) (labels
	((select-min (previous rest min)
		(if (null rest) (list min previous) (if (< (car rest) min) (select-min (cons min previous) (cdr rest) (car rest)) (select-min (cons (car rest) previous) (cdr rest) min)))))
	(if (< (length li) 2) li (let*
		((selected (select-min '() (cdr li) (car li)))
		(min (car selected))
		(rest (cadr selected)))
		(cons min (selection-sort rest))))))

(defun insertion-sort (li)
	(labels
		((insert-into (x sorted) (if (null sorted) (list x) (if (>= x (car sorted)) (cons x sorted) (cons (car sorted) (insert-into x (cdr sorted)))))))
		(reverse (reduce (lambda (accum x) (insert-into x accum)) li :initial-value '()))))

(defun merge-sort (li)
	(labels
		((partition-list (li left right i) (if (null li) (list (reverse left) (reverse right)) (if (= (mod i 2) 0) (partition-list (cdr li) (cons (car li) left) right (+ i 1)) (partition-list (cdr li) left (cons (car li) right) (+ i 1)))))
		(merge-list (left right sorted) (if
			(null left) (concatenate 'list (reverse sorted) right)
			(if (null right) (concatenate 'list (reverse sorted) left)
			(let ((a (car left)) (b (car right))) (if (< a b) (merge-list (cdr left) right (cons a sorted)) (merge-list left (cdr right) (cons b sorted))))))))
		(if (< (length li) 2) li (let* ((partitions (partition-list li '() '() 0)) (left (car partitions)) (right (cadr partitions)))
			(merge-list (merge-sort left) (merge-sort right) '())))))

(defun quick-sort (li)
	(labels
		()
		(if (< (length li) 2) li (let*
			((pivot (car li))
			(partitions (reduce (lambda (accum x) (let ((left (car accum)) (right (cadr accum))) (if (<= x pivot) (list (cons x left) right) (list left (cons x right))))) (cdr li) :initial-value '(() ())))
			(left (quick-sort (car partitions)))
			(right (quick-sort (cadr partitions))))
			(concatenate 'list left (cons pivot right))))))



(defparameter GOLDEN_RATIO (/ (+ 1 (sqrt 5)) 2))
(defparameter DIGITS '(1 6 1 8 0 3 3 9 8 8 7 4 9 8 9 4 8 4 8 2 0 4 5))
; (defparameter DIGITS '(3 2 1))

(print (format nil "Original:       ~{~a~^, ~}" DIGITS))
(print (format nil "Selection sort: ~{~a~^, ~}" (selection-sort DIGITS)))
(print (format nil "Insertion sort: ~{~a~^, ~}" (insertion-sort DIGITS)))
(print (format nil "Merge sort:     ~{~a~^, ~}" (merge-sort DIGITS)))
(print (format nil "Quick sort:     ~{~a~^, ~}" (quick-sort DIGITS)))