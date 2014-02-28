module Clavier.SortingAlgorithms (selection_sort, insertion_sort, merge_sort, quick_sort) where
import Data.List

selection_sort :: (Ord a) => [a] -> [a]
selection_sort (x : [])
	= [x]
selection_sort []
	= []
selection_sort li
	= let
		select_min min [] prev = (min, prev)
		select_min min (x:xs) prev
			| x < min = select_min x xs (min:prev)
			| otherwise = select_min min xs (x:prev)
		repeat_select [] = []
		repeat_select (x:xs)
			= let
				(min, rest) = select_min x xs []
			in min : repeat_select rest
	in repeat_select li

insertion_sort :: (Ord a) => [a] -> [a]
insertion_sort (x : [])
	= [x]
insertion_sort []
	= []
insertion_sort li
	= let
		insert_into x []
			= [x]
		insert_into x li@(car:cdr)
			| x >= car = car : (insert_into x cdr)
			| otherwise = x : li
	in foldl' (\ accum x -> insert_into x accum) [] li

merge_sort :: (Ord a) => [a] -> [a]
merge_sort (x : [])
	= [x]
merge_sort []
	= []
merge_sort li
	= let
		(left, right) = merge_sort_partition li [] [] 0
	in merge_sort_merge (merge_sort left) (merge_sort right)

merge_sort_partition :: [a] -> [a] -> [a] -> Int -> ([a], [a])
merge_sort_partition [] left right _ = ((reverse left), (reverse right))
merge_sort_partition (x:xs) left right i
	| (mod i 2) == 0 = merge_sort_partition xs (x:left) right (i + 1)
	| otherwise = merge_sort_partition xs left (x:right) (i + 1)

merge_sort_merge :: (Ord a) => [a] -> [a] -> [a]
merge_sort_merge [] right = right
merge_sort_merge left [] = left
merge_sort_merge (l:ls) (r:rs)
	| l <= r = l : (merge_sort_merge ls (r:rs))
	| otherwise = r : (merge_sort_merge (l:ls) rs)

quick_sort :: (Ord a) => [a] -> [a]
quick_sort (x : [])
	= [x]
quick_sort []
	= []
quick_sort (pivot : xs)
	= let
		(left, right) = foldl' (\ (left, right) x -> if x <= pivot then ((x:left), right) else (left, (x:right))) ([], []) xs
	in (quick_sort left) ++ (pivot : (quick_sort right))