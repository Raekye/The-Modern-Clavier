require_relative 'sorting_algorithms.rb'

GOLDEN_RATIO = (1 + Math.sqrt(5)) / 2
DIGITS = GOLDEN_RATIO.to_s.sub('.', '').split('').map { |x| x.to_i }

puts "Original:       " + DIGITS.to_s
puts "Selection sort: " + selection_sort(DIGITS.clone).to_s
puts "Insertion sort: " + insertion_sort(DIGITS.clone).to_s
puts "Merge sort:     " + merge_sort(DIGITS.clone).to_s
puts "Quick sort:     " + quick_sort(DIGITS.clone).to_s