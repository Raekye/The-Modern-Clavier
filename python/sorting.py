import math

def selection_sort(arr):
	for i in range(len(arr)):
		min_index = i
		for j in range(i, len(arr)):
			if arr[j] < arr[min_index]:
				min_index = j
		arr[i], arr[min_index] = arr[min_index], arr[i]
	return arr

def insertion_sort(arr):
	for i in range(1, len(arr)):
		x = arr[i]
		j = i
		while j > 0 and x < arr[j - 1]:
			arr[j] = arr[j - 1]
			j -= 1
		arr[j] = x
	return arr

def merge_sort(arr):
	if len(arr) < 2:
		return arr
	left = merge_sort(arr[0:(len(arr) // 2)])
	right = merge_sort(arr[(len(arr) // 2):])

	sorted_arr = []
	a = 0
	b = 0
	while a < len(left) and b < len(right):
		if left[a] <= right[b]:
			sorted_arr.append(left[a])
			a += 1
		else:
			sorted_arr.append(right[b])
			b += 1
	sorted_arr.extend(left[a:])
	sorted_arr.extend(right[b:])
	return sorted_arr

def merge_sort_inplace(arr):
	merge_sort_inplace_helper(arr, 0, len(arr))
	return arr

def merge_sort_inplace_helper(arr, start, end):
	sort_len = end - start
	if sort_len < 2:
		return
	mid = start + sort_len // 2
	work = start + (sort_len + 1) // 2
	merge_sort_inplace_helper_2(arr, start, mid, work)
	while work - start >= 2:
		n = work
		work = start + (n - start + 1) // 2
		merge_sort_inplace_helper_2(arr, work, n, start)
		merge_sort_inplace_helper_merge(arr, start, n - (n - start + 1) // 2, n, end, work)
	for j in range(work, end):
		if arr[j] >= arr[j - 1]:
			break
		arr[j], arr[j - 1] = arr[j - 1], arr[j]

def merge_sort_inplace_helper_2(arr, start, end, work):
	if end - start < 2:
		while start < end:
			arr[start], arr[work] = arr[work], arr[start]
			start += 1
			work += 1
	else:
		mid = start + (end - start) // 2
		merge_sort_inplace_helper(arr, start, mid)
		merge_sort_inplace_helper(arr, mid, end)
		merge_sort_inplace_helper_merge(arr, start, mid, mid, end, work)

def merge_sort_inplace_helper_merge(arr, a_start, a_end, b_start, b_end, work_start):
	a = a_start
	b = b_start
	w = work_start
	while a < a_end and b < b_end:
		if arr[a] <= arr[b]:
			arr[a], arr[w] = arr[w], arr[a]
			a += 1
		else:
			arr[b], arr[w] = arr[w], arr[b]
			b += 1
		w += 1
	while a < a_end:
		arr[a], arr[w] = arr[w], arr[a]
		a += 1
		w += 1
	while b < b_end:
		arr[b], arr[w] = arr[w], arr[b]
		b += 1
		w += 1

def merge_sort_bottom_up(arr):
	width = 1
	work_arr = [None] * len(arr)
	while width < len(arr):
		for i in range(0, len(arr), 2 * width):
			merge_sort_bottom_up_merge(arr, work_arr, i, min(i + width, len(arr)), min(i + 2 * width, len(arr)))
		width *= 2
		arr, work_arr = work_arr, arr
	return arr

def merge_sort_bottom_up_merge(arr, work_arr, a_start, b_start, end):
	a = a_start
	b = b_start
	i = 0
	while a < b_start and b < end:
		if arr[a] <= arr[b]:
			work_arr[a_start + i] = arr[a]
			a += 1
		else:
			work_arr[a_start + i] = arr[b]
			b += 1
		i += 1
	for j in range(a, b_start):
		work_arr[a_start + i] = arr[j]
		i += 1
	for j in range(b, end):
		work_arr[a_start + i] = arr[j]
		i += 1


def quick_sort(arr):
	if len(arr) < 2:
		return arr
	pivot = arr[0]
	left = []
	right = []
	for each in arr[1:]:
		if each <= pivot:
			left.append(each)
		else:
			right.append(each)
	left = quick_sort(left)
	right = quick_sort(right)
	left.append(pivot)
	left.extend(right)
	return left

def main():
	GOLDEN_RATIO = (1 + math.sqrt(5)) / 2
	DIGITS = list(map(int, list(str(GOLDEN_RATIO).replace('.', ''))))

	print('Original:             ' + str(DIGITS[:]))
	print('Selection sort:       ' + str(selection_sort(DIGITS[:])))
	print('Insertion sort:       ' + str(insertion_sort(DIGITS[:])))
	print('Merge sort:           ' + str(merge_sort(DIGITS[:])))
	print('Merge sort in place:  ' + str(merge_sort_inplace(DIGITS[:])))
	print('Merge sort bottom up: ' + str(merge_sort_bottom_up(DIGITS[:])))
	print('Quick sort:           ' + str(quick_sort(DIGITS[:])))

if __name__ == '__main__':
	main()