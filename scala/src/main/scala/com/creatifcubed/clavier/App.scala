package com.creatifcubed.clavier;

import scala.util.Random;

/**
* @author Raekye
*/
object App {

	def main(args: Array[String]) {
		test_sorting();
	}

	def test_sorting(): Unit = {
		val ARR_A_PRESORTED = (0 until 16).toList;
		val ARR_A_REVERSED = ARR_A_PRESORTED.reverse;
		val ARR_A_SHUFFLED = Random.shuffle(ARR_A_PRESORTED.toList);

		println("Selection sort");
		println("Reversed: " + Sorting.selectionsort(ARR_A_REVERSED).mkString(", "));
		println("Shuffled: " + Sorting.selectionsort(ARR_A_SHUFFLED).mkString(", "));
		println();

		println("Insertion sort");
		println("Reversed: " + Sorting.insertionsort(ARR_A_REVERSED).mkString(", "));
		println("Shuffled: " + Sorting.insertionsort(ARR_A_SHUFFLED).mkString(", "));
		println();

		println("Merge sort");
		println("Reversed: " + Sorting.mergesort(ARR_A_REVERSED).mkString(", "));
		println("Shuffled: " + Sorting.mergesort(ARR_A_SHUFFLED).mkString(", "));
		println();

		println("Quick sort");
		println("Reversed: " + Sorting.quicksort(ARR_A_REVERSED).mkString(", "));
		println("Shuffled: " + Sorting.quicksort(ARR_A_SHUFFLED).mkString(", "));
		println();

		println("Heap sort");
		println("Reversed: " + Sorting.heapsort(ARR_A_REVERSED).mkString(", "));
		println("Shuffled: " + Sorting.heapsort(ARR_A_SHUFFLED).mkString(", "));
		println();

		println("Intro sort");
		println("Reversed: " + Sorting.introsort(ARR_A_REVERSED).mkString(", "));
		println("Shuffled: " + Sorting.introsort(ARR_A_SHUFFLED).mkString(", "));
		println();

		println("Quickmerge sort");
		println("Reversed: " + Sorting.quickmergesort(ARR_A_REVERSED).mkString(", "));
		println("Shuffled: " + Sorting.quickmergesort(ARR_A_SHUFFLED).mkString(", "));
		println();
	}

}