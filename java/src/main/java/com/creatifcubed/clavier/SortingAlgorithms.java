package com.creatifcubed.clavier;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Iterator;

class SortingAlgorithms {
	public static final double GOLDEN_RATIO = (1 + Math.sqrt(5)) / 2;

	public static <T extends Comparable<T>> List<T> selectionSort(List<T> arr) {
		List<T> sorted = new ArrayList<T>(arr.size());
		while (!arr.isEmpty()) {
			Iterator<T> it = arr.iterator();
			int min_index = 0;
			T min = it.next();
			int i = 1;
			while (it.hasNext()) {
				T x = it.next();
				if (x.compareTo(min) < 0) {
					min_index = i;
					min = x;
				}
				i++;
			}
			sorted.add(arr.remove(min_index));
		}
		return sorted;
	}

	public static  <T extends Comparable<T>> List<T> insertionSort(List<T> arr) {
		for (int i = 1; i < arr.size(); i++) {
			T x = arr.get(i);
			int j = i;
			while (j > 0) {
				T y = arr.get(j - 1);
				if (y.compareTo(x) <= 0) {
					break;
				}
				arr.set(j, y);
				j--;
			}
			arr.set(j, x);
		}
		return arr;
	}

	public static <T extends Comparable<T>> List<T> mergeSort(List<T> arr) {
		if (arr.size() < 2) {
			return arr;
		}
		List<T> left = mergeSort(arr.subList(0, arr.size() / 2));
		List<T> right = mergeSort(arr.subList(arr.size() / 2, arr.size()));
		List<T> sorted = new ArrayList<>(arr.size());
		int a = 0;
		int b = 0;
		while (a < left.size() && b < right.size()) {
			sorted.add(left.get(a).compareTo(right.get(b)) <= 0 ? left.get(a++) : right.get(b++));
		}
		sorted.addAll(left.subList(a, left.size()));
		sorted.addAll(right.subList(b, right.size()));
		return sorted;
	}

	public static <T extends Comparable<T>> List<T> quickSort(List<T> arr) {
		if (arr.size() < 2) {
			return arr;
		}
		T pivot = arr.get(0);
		List<T> left = new LinkedList<T>();
		List<T> right = new LinkedList<T>();
		Iterator<T> it = arr.iterator();
		it.next();
		while (it.hasNext()) {
			T x = it.next();
			(x.compareTo(pivot) <= 0 ? left : right).add(x);
		}
		left = quickSort(left);
		right = quickSort(right);
		left.add(pivot);
		left.addAll(right);
		return left;
	}

	public static void main(String[] args) {
		char[] characters = String.valueOf(GOLDEN_RATIO).replace(".", "").toCharArray();
		List<Integer> digits = new ArrayList<>(characters.length);
		for (char ch : characters) {
			digits.add(ch - '0');
		}
		System.out.println("Original:       " + digits.toString());
		System.out.println("Selection sort: " + selectionSort(new LinkedList<>(digits)).toString());
		System.out.println("Insertion sort: " + insertionSort(new LinkedList<>(digits)).toString());
		System.out.println("Merge sort:     " + mergeSort(new LinkedList<>(digits)).toString());
		System.out.println("Quick sort:     " + quickSort(new LinkedList<>(digits)).toString());
	}
}