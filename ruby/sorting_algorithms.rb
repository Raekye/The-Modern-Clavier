

def selection_sort(arr)
	for i in (0...arr.size)
		min_index = i
		(i + 1...arr.size).each do |j|
			if arr[j] < arr[min_index]
				min_index = j
			end
		end
		if i != min_index
			arr[min_index], arr[i] = arr[i], arr[min_index]
		end
	end
	return arr
end

def insertion_sort(arr)
	for i in (1...arr.size)
		x = arr[i]
		j = i
		while x < arr[j - 1] && j > 0
			arr[j] = arr[j - 1]
			j -= 1
		end
		arr[j] = x
	end
	return arr
end

def merge_sort(arr)
	return arr if arr.size < 2
	left = merge_sort(arr[0...arr.size / 2])
	right = merge_sort(arr[arr.size / 2..-1])
	sorted = []
	while left.size > 0 && right.size > 0
		sorted << if left.first <= right.first then left.shift else right.shift end
	end
	return sorted + left + right
end

def quick_sort(arr)
	return arr if arr.size < 2
	pivot = arr[arr.size / 2]
	left = []
	right = []
	arr.each_with_index do |x, i|
		next if i == arr.size / 2
		(if x <= pivot then left else right end) << x
	end
	return (quick_sort(left) << pivot) + quick_sort(right)
end