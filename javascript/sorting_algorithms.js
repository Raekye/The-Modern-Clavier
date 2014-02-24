

function selection_sort(arr) {
	for (var i = 0; i < arr.length; i++) {
		var min_index = i;
		for (var j = i + 1; j < arr.length; j++) {
			if (arr[j] < arr[min_index]) {
				min_index = j;
			}
		}
		if (min_index != i) {
			var tmp = arr[i];
			arr[i] = arr[min_index];
			arr[min_index] = tmp;
		}
	}
	return arr;
}

function insertion_sort(arr) {
	for (var i = 1; i < arr.length; i++) {
		var x = arr[i];
		var j = i;
		while (arr[j - 1] > x && j > 0) {
			arr[j] = arr[j - 1];
			j--;
		}
		arr[j] = x;
	}
	return arr;
}

function merge_sort(arr) {
	if (arr.length < 2) {
		return arr;
	}
	var mid = Math.floor(arr.length / 2);
	var left = merge_sort(arr.slice(0, mid));
	var right = merge_sort(arr.slice(mid));
	var sorted = []
	while (left.length > 0 && right.length > 0) {
		sorted.push(left[0] <= right[0] ? left.shift() : right.shift());
	}
	Array.prototype.push.apply(sorted, left);
	Array.prototype.push.apply(sorted, right);
	return sorted;
}

function quick_sort(arr) {
	if (arr.length < 2) {
		return arr;
	}
	var mid = Math.floor(arr.length / 2);
	var pivot = arr[mid];
	var left = [];
	var right = [];
	arr.map(function (x, i) {
		if (i == mid) {
			return;
		}
		(x <= pivot ? left : right).push(x);
	});
	left = quick_sort(left);
	right = quick_sort(right);
	left.push(pivot);
	Array.prototype.push.apply(left, right);
	return left;
}

function main() {
	var GOLDEN_RATIO = (1 + Math.sqrt(5)) / 2;
	var DIGITS = GOLDEN_RATIO.toString().replace(/\./, '').split('').map(function (x) { return parseInt(x, 10); });

	console.log("Original:       %s", DIGITS.join(', '));
	console.log("Selection sort: %s", selection_sort(DIGITS.slice()).join(', '));
	console.log("Insertion sort: %s", insertion_sort(DIGITS.slice()).join(', '));
	console.log("Merge sort:     %s", merge_sort(DIGITS.slice()).join(', '));
	console.log("Quick sort:     %s", quick_sort(DIGITS.slice()).join(', '));
}

if (require.main === module) {
	main();
}