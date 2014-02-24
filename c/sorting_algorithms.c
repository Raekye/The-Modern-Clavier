#include <stdio.h>
#include <math.h>
#include <stdlib.h>

#define NUM_DIGITS 16

void copy_array(int src[], int dest[], int len);

void swap(int arr[], int a, int b) {
	int tmp = arr[a];
	arr[a] = arr[b];
	arr[b] = tmp;
}

void selection_sort(int arr[], int len) {
	for (int i = 0; i < len; i++) {
		int min_index = i;
		for (int j = i + 1; j < len; j++) {
			if (arr[j] < arr[min_index]) {
				min_index = j;
			}
		}
		if (min_index != i) {
			swap(arr, min_index, i);
		}
	}
}

void insertion_sort(int arr[], int len) {
	for (int i = 1; i < len; i++) {
		int x = arr[i];
		int j = i;
		while (arr[j - 1] > x && j > 0) {
			arr[j] = arr[j - 1];
			j--;
		}
		arr[j] = x;
	}
}

void merge_sort(int arr[], int len) {
	if (len < 2) {
		return;
	}
	int mid = len / 2;
	int* left = (int*) malloc(mid * sizeof(int));
	int* right = (int*) malloc((len - mid) * sizeof(int));
	copy_array(arr, left, mid);
	copy_array(arr + mid, right, len - mid);
	merge_sort(left, mid);
	merge_sort(right, len - mid);
	int a = 0;
	int b = 0;
	while (a < mid && b < len - mid) {
		arr[a + b] = left[a] <= right[b] ? left[a++] : right[b++];
	}
	for (; a < mid; a++) {
		arr[a + b] = left[a];
	}
	for (; b < len - mid; b++) {
		arr[a + b] = right[b];
	}
	free(left);
	free(right);
}

void quick_sort(int arr[], int len) {
	if (len < 2) {
		return;
	}
	int left[len];
	int right[len];
	int left_count = 0;
	int right_count = 0;
	int mid = len / 2;
	int pivot = arr[mid];
	for (int i = 0; i < len; i++) {
		if (i == mid) {
			continue;
		}
		if (arr[i] <= pivot) {
			left[left_count++] = arr[i];
		} else {
			right[right_count++] = arr[i];
		}
	}
	quick_sort(left, left_count);
	quick_sort(right, right_count);
	copy_array(left, arr, left_count);
	arr[left_count] = pivot;
	copy_array(right, arr + left_count + 1, right_count);
}

void copy_array(int src[], int dest[], int len) {
	for (int i = 0; i < len; i++) {
		dest[i] = src[i];
	}
}

void display_array(int arr[], int len) {
	printf("%d", arr[0]);
	for (int i = 1; i < len; i++) {
		printf(", %d", arr[i]);
	}
}

int main() {
	double GOLDEN_RATIO = (1 + sqrt(5)) / 2;
	int DIGITS[NUM_DIGITS];
	for (int i = 0; i < NUM_DIGITS; i++) {
		DIGITS[i] = (int) fmod(GOLDEN_RATIO * pow(10, i), 10);
	}
	
	int selection_sorted[NUM_DIGITS];
	copy_array(DIGITS, selection_sorted, NUM_DIGITS);
	selection_sort(selection_sorted, NUM_DIGITS);
	
	int insertion_sorted[NUM_DIGITS];
	copy_array(DIGITS, insertion_sorted, NUM_DIGITS);
	insertion_sort(insertion_sorted, NUM_DIGITS);
	
	int merge_sorted[NUM_DIGITS];
	copy_array(DIGITS, merge_sorted, NUM_DIGITS);
	merge_sort(merge_sorted, NUM_DIGITS);
	
	int quick_sorted[NUM_DIGITS];
	copy_array(DIGITS, quick_sorted, NUM_DIGITS);
	quick_sort(quick_sorted, NUM_DIGITS);


	printf("Original:       "); display_array(DIGITS, NUM_DIGITS); printf("\n");
	printf("Selection sort: "); display_array(selection_sorted, NUM_DIGITS); printf("\n");
	printf("Insertion sort: "); display_array(insertion_sorted, NUM_DIGITS); printf("\n");
	printf("Merge sort:     "); display_array(merge_sorted, NUM_DIGITS); printf("\n");
	printf("Quick sort:     "); display_array(quick_sorted, NUM_DIGITS); printf("\n");
}