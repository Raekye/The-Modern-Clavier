package com.creatifcubed.clavier;

import scala.math;
import scala.collection.mutable.{MutableList, ListBuffer, Queue, Stack, PriorityQueue};
import scala.util.control.Breaks;

/**
 * @author Raekye
 */
object Sorting {
	
	/**
	 * - Stable: yes
	 * - Worst case complexity: hmm
	 * - Average case complexity: hmm
	 * - Best case complexity: hmm
	 * - Space: hmm
	 */
	def mergesort[T <% Comparable[T]: Manifest](li: List[T]): List[T] = {
		if (li.length < 2) {
			return li;
		}
		val left = mergesort(li.dropRight(li.length / 2));
		val right = mergesort(li.drop(math.ceil(li.length / 2.0).toInt));
		var i: Int = 0;
		var j: Int = 0;
		var sorted: List[T] = List[T]();
		while (i < left.length && j < right.length) {
			if (right(j).compareTo(left(i)) >= 0) {
				sorted = sorted :+ left(i);
				i += 1;
			} else {
				sorted = sorted :+ right(j);
				j += 1;
			}
		}
		for (a <- i until left.length) {
			sorted = sorted :+ left(a);
		}
		for (b <- j until right.length) {
			sorted = sorted :+ right(b);
		}
		return sorted;
	}
	
	/**
	 * - Stable: yes
	 * - Worst case complexity: hmm
	 * - Average case complexity: hmm
	 * - Best case complexity: hmm
	 * - Space: hmm
	 */
	def mergesortInplace[T <% Comparable[T]: Manifest](li: List[T]): List[T] = {
		return null;
	}
	
	/**
	 * - Stable: no
	 * - Worst case complexity: hmm
	 * - Average case complexity: hmm
	 * - Best case complexity: hmm
	 * - Space: hmm
	 */
	def quicksort[T <% Comparable[T]: Manifest](li: List[T]): List[T] = {
		if (li.length < 2) {
			return li;
		}
		if (li.length == 2) {
			if (li(0).compareTo(li(1)) <= 0) {
				return li;
			} else {
				return li.reverse;
			}
		}
		val pivotIndex =
			if (li(0).compareTo(li(li.length / 2)) >= 0 && li(0).compareTo(li(li.length - 1)) <= 0) 0
			else if (li(li.length / 2).compareTo(li(0)) > 0 && li(li.length / 2).compareTo(li(li.length - 1)) <= 0) li.length / 2
			else li.length - 1;
		var left: List[T] = List[T]();
		var right: List[T] = List[T]();
		li.indices.foreach { i: Int =>
			if (i != pivotIndex) {
				if (li(i).compareTo(li(pivotIndex)) <= 0) {
					left = left :+ li(i);
				} else {
					right = right :+ li(i);
				}
			}
		};
		return (quicksort(left) :+ li(pivotIndex)) ++ quicksort(right);
	}
	
	/**
	 * - Stable: no
	 * - Worst case complexity: hmm
	 * - Average case complexity: hmm
	 * - Best case complexity: hmm
	 * - Space: hmm
	 */
	def quicksortInplace[T <% Comparable[T]: Manifest](li: List[T]): List[T] = {
		return null;
	}
	
	/**
	 * - Stable: no
	 * - Worst case complexity: hmm
	 * - Average case complexity: hmm
	 * - Best case complexity: hmm
	 * - Space: hmm
	 */
	def insertionsort[T <% Comparable[T]: Manifest](li: List[T]): List[T] = {
		def insert(li: List[T], elem: T, index: Int): List[T] = {
			if (index == 0) {
				return elem :: li;
			}
			var i: Int = 0;
			return li.foldLeft(List[T]()) ((aggregate: List[T], each: T) => {
				i += 1;
				if (i == index) aggregate :+ each :+ elem else aggregate :+ each;
			});
		}
		
		def bsearchIndex(li: List[T], elem: T): Int = {
			var low: Int = 0;
			var high: Int = li.length - 1;
			while (low <= high) {
				var mid: Int = (low + high) / 2;
				var comp: Int = ((n: Int) => if (n == 0) 0 else n / math.abs(n))(elem.compareTo(li(mid)));
				comp match {
					case 0 => return mid;
					case 1 => low = mid + 1;
					case -1 => high = mid - 1;
				}
			}
			return low;
		}
		
		if (li.length < 2) {
			return li;
		}
		var sorted: List[T] = List[T](li(0));
		var unsorted: List[T] = li.drop(1);
		for (i <- 1 until li.length) {
			val index = bsearchIndex(sorted, unsorted.head);
			sorted = insert(sorted, unsorted.head, index);
			unsorted = unsorted.drop(1);
		}
		
		return sorted;
	}
	
	/**
	 * - Stable: hmm
	 * - Worst case complexity: hmm
	 * - Average case complexity: hmm
	 * - Best case complexity: hmm
	 * - Space: hmm
	 */
	def insertionsortInplace[T <% Comparable[T]: Manifest](li: List[T]): List[T] = {
		return null;
	}
	
	/**
	 * - Stable: hmm
	 * - Worst case complexity: hmm
	 * - Average case complexity: hmm
	 * - Best case complexity: hmm
	 * - Space: hmm
	 */
	def selectionsort[T <% Comparable[T]: Manifest](li: List[T]): List[T] = {
		def popSmallest(li: List[T]): (T, List[T]) = {
			var i: Int = 1;
			var smallest: Int = 0;
			for (each <- li.drop(1)) {
				if (li(i).compareTo(li(smallest)) < 0) {
					smallest = i;
				}
				i += 1;
			}
			i = 0;
			val rest = li.filter ((elem: T) => {
				i += 1;
				i - 1 != smallest;
			});
			return (li(smallest), rest);
		}
		if (li.length < 2) {
			return li;
		}
		val sorted = new ListBuffer[T]();
		var unsearched: List[T] = li;
		for (i <- 0 until li.length) {
			val (smallest, rest) = popSmallest(unsearched);
			sorted += smallest;
			unsearched = rest;
		}
		return sorted.toList;
	}
	
	/**
	 * - Stable: hmm
	 * - Worst case complexity: hmm
	 * - Average case complexity: hmm
	 * - Best case complexity: hmm
	 * - Space: hmm
	 */
	def selectionsortInplace[T <% Comparable[T]: Manifest](li: List[T]): List[T] = {
		return null;
	}
	
	/**
	 * - Stable: hmm
	 * - Worst case complexity: hmm
	 * - Average case complexity: hmm
	 * - Best case complexity: hmm
	 * - Space: hmm
	 */
	def heapsort[T <% Comparable[T]: Manifest](li: List[T]): List[T] = {
		val heap = new PriorityQueue[T]();
		val it = li.iterator;
		while (it.hasNext) {
			heap += it.next();
		}
		val buf = new ListBuffer[T];
		while (!heap.isEmpty) {
			buf.+=:(heap.dequeue());
		}
		return buf.toList;
	}
	
	/**
	 * - Stable: hmm
	 * - Worst case complexity: hmm
	 * - Average case complexity: hmm
	 * - Best case complexity: hmm
	 * - Space: hmm
	 */
	def introsort[T <% Comparable[T]: Manifest](li: List[T]): List[T] = {
		def helper(li: List[T], maxDepth: Int, curDepth: Int): List[T] = {
			if (li.length < 2) {
				return li;
			}
			if (curDepth > maxDepth) {
				return heapsort(li);
			}
			val left: ListBuffer[T] = new ListBuffer[T];
			val right: ListBuffer[T] = new ListBuffer[T];
			val pivot = li.head;
			li.drop(1).foreach { elem: T =>
				if (elem.compareTo(pivot) >= 0) {
					right += elem;
				} else {
					left += elem;
				}
			}
			return (helper(left.toList, maxDepth, curDepth + 1) :+ pivot) ++ helper(right.toList, maxDepth, curDepth + 1);
		}
		return helper(li, math.ceil(math.log(li.length) / math.log(2)).toInt, 1);
	}
	
	/**
	 * - Stable: hmm
	 * - Worst case complexity: hmm
	 * - Average case complexity: hmm
	 * - Best case complexity: hmm
	 * - Space: hmm
	 */
	def timsort[T <% Comparable[T]: Manifest](li: List[T]): List[T] = {
		return null;
	}
	
	/**
	 * - Stable: hmm
	 * - Worst case complexity: hmm
	 * - Average case complexity: hmm
	 * - Best case complexity: hmm
	 * - Space: hmm
	 */
	def smoothsort[T <% Comparable[T]: Manifest](li: List[T]): List[T] = {
		return null;
	}

	/**
	 * - Stable: hmm
	 * - Worst case complexity: hmm
	 * - Average case complexity: hmm
	 * - Best case complexity: hmm
	 * - Space: hmm
	 */
	def quickmergesort[T <% Comparable[T]: Manifest](li: List[T]): List[T] = {
		if (li.length < 2) {
			return li;
		}
		if (li.length == 2) {
			return if (li(0).compareTo(li(1)) <= 0) li else li.reverse;
		}

		// Note: there was no mathematical reasoning behind this choice
		// I just picked the golden ratio because it's cool
		val GOLDEN_RATIO: Double = (1 + math.sqrt(5)) / 2;
		def unbalanced(a: Int, b: Int): Boolean = {
			if (a == 0) {
				return b >= 4;
			} else if (b == 0) {
				return a >= 4;
			}
			val ratio: Double = if (a >= b) a.toDouble / b else b.toDouble / a;
			return ratio >= GOLDEN_RATIO;
		}

		val left: ListBuffer[T] = new ListBuffer[T];
		val right: ListBuffer[T] = new ListBuffer[T];
		val evenHalf: ListBuffer[T] = new ListBuffer[T];
		val oddHalf: ListBuffer[T] = new ListBuffer[T];

		val pivot = li.head;
		evenHalf += pivot;
		var i: Int = 1;

		for (each <- li.drop(1)) {
			val comp: Int = each.compareTo(pivot);
			if (comp >= 0) {
				right += each;
			} else {
				left += each;
			}
			if (i % 2 == 0) {
				evenHalf += each;
			} else {
				oddHalf += each;
			}
			i += 1;
		}

		if (unbalanced(left.length, right.length)) {
			assert(evenHalf.length > 0);
			assert(oddHalf.length > 0);
			val evenMerged: List[T] = quickmergesort(evenHalf.toList);
			val oddMerged: List[T] = quickmergesort(oddHalf.toList);
			val sorted: ListBuffer[T] = new ListBuffer[T];
			val evenIt: Iterator[T] = evenMerged.iterator;
			val oddIt: Iterator[T] = oddMerged.iterator;

			var lastEven: T = evenIt.next();
			var lastOdd: T = oddIt.next();

			val loop = new Breaks();
			loop.breakable {
				while (true) {
					if (lastEven.compareTo(lastOdd) <= 0) {
						sorted += lastEven;
						if (!evenIt.hasNext) {
							sorted += lastOdd;
							while (oddIt.hasNext) {
								sorted += oddIt.next();
							}
							loop.break;
						}
						lastEven = evenIt.next();
					} else {
						sorted += lastOdd;
						if (!oddIt.hasNext) {
							sorted += lastEven;
							while (evenIt.hasNext) {
								sorted += evenIt.next();
							}
							loop.break;
						}
						lastOdd = oddIt.next();
					}
				}
			}

			return sorted.toList;
		} else {
			return (quickmergesort(left.toList) :+ pivot) ++ quickmergesort(right.toList);
		}
	}

	/**
	 * - Stable: hmm
	 * - Worst case complexity: hmm
	 * - Average case complexity: hmm
	 * - Best case complexity: hmm
	 * - Space: hmm
	 *
	 * - check for nearly sorted lists (1 in 8 deviation) -> insertionsort
	 * - check for nearly sorted reversed lists (1 in 8 deviation) -> insertionsort . reverse
	 * - 
	 */
	def datasort[T <% Comparable[T]: Manifest](li: List[T]): List[T] = {
		val LN2: Double = math.log(2);
		val MAX_OUTLIER_RATIO: Double = 8.0;
		def shouldRunEnd(ordered: Int, outliers: Int): Boolean = {
			return (ordered.toDouble + outliers) / outliers <= MAX_OUTLIER_RATIO
		}
		def reverseStable(li: List[T]): List[T] = {
			if (li.length < 2) {
				return li;
			}
			val buf: ListBuffer[T] = new ListBuffer[T]();
			val stack: Stack[T] = new Stack[T]();
			stack.push(li.head);
			for (each <- li.drop(1)) {
				if (each.compareTo(stack.head) == 0) {
					stack.push(each);
				} else {
					while (stack.length > 0) {
						buf.+=:(stack.pop());
					}
					stack.push(each);
				}
			}
			while (stack.length > 0) {
				buf.+=:(stack.pop());
			}
			return buf.toList;
		}
		if (li.length < 2) {
			return li;
		}
		if (li.length == 2) {
			return if (li(0).compareTo(li(1)) <= 0) li else li.reverse;
		}
		//val runs: Queue[(Int, Int)] = Queue[(Int, Int)];
		val MIN_RUN_SIZE: Double = math.log(li.length) / LN2;
		var asc: Int = 0;
		var outliers: Int = 0;
		var desc: Int = 0;
		var lastMergeIndex: Int = 0;
		for (i <- 1 until li.length) {
			val comp = li(i - 1).compareTo(li(i));
			if (comp == 0) {
				if (desc > 0) {
					desc += 1;
				} else {
					asc += 1;
				}
			} else if (comp < 0) {
				if (desc > 0) {
					outliers += 1;
					if (shouldRunEnd(desc, outliers)) {
						// push run
						// merge run
					}
				} else {
					asc += 1;
				}
			} else if (comp > 0) {
				if (asc > 0) {
					outliers += 1;
					if (shouldRunEnd(desc, outliers)) {
						// push run
						// merge run
					}
				} else {
					desc += 1;
				}
			}
		}
		return mergesort(li);
	}
}
