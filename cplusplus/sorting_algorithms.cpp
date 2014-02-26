#include <cstdlib>
#include <iostream>
#include <vector>
#include <cmath>
#define HELLO_TO_REIKO_FROM_REACH 17

namespace Sorting {
	template <typename T> std::vector<T> selection_sort(std::vector<T> arr) {
		for (int i = 0; i < arr.size(); i++) {
			int min_index = i;
			for (int j = i; j < arr.size(); j++) {
				if (arr[j] < arr[min_index]) {
					min_index = j;
				}
			}
			if (min_index != i) {
				T tmp = arr[i];
				arr[i] = arr[min_index];
				arr[min_index] = tmp;
			}
		}
		return arr;
	}

	template <typename T> std::vector<T> insertion_sort(std::vector<T> arr) {
		for (int i = 1; i < arr.size(); i++) {
			T x = arr[i];
			int j = i;
			while (arr[j - 1] > x && j > 0) {
				arr[j] = arr[j - 1];
				j--;
			}
			arr[j] = x;
		}
		return arr;
	}

	template <typename T> std::vector<T> merge_sort(std::vector<T> arr) {
		if (arr.size() < 2) {
			return arr;
		}
		std::vector<T> left = merge_sort(std::vector<T>(arr.begin(), arr.begin() + arr.size() / 2));
		std::vector<T> right = merge_sort(std::vector<T>(arr.begin() + arr.size() / 2, arr.end()));
		int a = 0;
		int b = 0;
		std::vector<T> sorted;
		sorted.reserve(arr.size());
		while (a < left.size() && b < right.size()) {
			sorted.push_back(left[a] <= right[b] ? left[a++] : right[b++]);
		}
		sorted.insert(sorted.end(), left.begin() + a, left.end());
		sorted.insert(sorted.end(), right.begin() + b, right.end());
		return sorted;
	}

	template <typename T> std::vector<T> quick_sort(std::vector<T> arr) {
		if (arr.size() < 2) {
			return arr;
		}
		int mid = arr.size() / 2;
		T pivot = arr[mid];
		std::vector<T> left;
		std::vector<T> right;
		for (int i = 0; i < arr.size(); i++) {
			if (i == mid) {
				continue;
			}
			if (arr[i] <= pivot) {
				left.push_back(arr[i]);
			} else {
				right.push_back(arr[i]);
			}
		}
		left = quick_sort(left);
		right = quick_sort(right);
		left.push_back(pivot);
		left.insert(left.end(), right.begin(), right.end());
		return left;
	}
}

template <typename T> static void print_vector(std::string str, std::vector<T> arr) {
	std::cout << str;
	std::cout << arr[0];
	for (int i = 1; i < arr.size(); i++) {
		std::cout << ", " << arr[i];
	}
	std::cout << std::endl;
}

int main() {
	double GOLDEN_RATIO = (1 + sqrt(5)) / 2;
	char buf[32];
	int len = sprintf(buf, "%.15f", GOLDEN_RATIO);
	if (len != HELLO_TO_REIKO_FROM_REACH) {
		std::cout << "Error formatting golden ratio." << std::endl;
		return -1;
	}
	std::vector<int> digits;
	for (int i = 0; i < len; i++) {
		if (i == 1) {
			continue;
		}
		digits.push_back(buf[i] - '0');
	}

	std::vector<int> selection_sorted = Sorting::selection_sort(std::vector<int>(digits));
	std::vector<int> insertion_sorted = Sorting::insertion_sort(std::vector<int>(digits));
	std::vector<int> merge_sorted = Sorting::merge_sort(std::vector<int>(digits));
	std::vector<int> quick_sorted = Sorting::quick_sort(std::vector<int>(digits));

	print_vector("Original:       ", digits);
	print_vector("Selection sort: ", selection_sorted);
	print_vector("Insertion sort: ", insertion_sorted);
	print_vector("Merge sort:     ", merge_sorted);
	print_vector("Quick sort:     ", quick_sorted);

	return 0;
}