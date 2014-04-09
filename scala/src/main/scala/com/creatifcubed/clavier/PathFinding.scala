package com.creatifcubed.clavier;

import scala.collection.mutable.{ Set, HashSet, Map, HashMap, ListBuffer }
import scala.reflect.runtime.universe.TypeTag;

/* import scalax.collection.Graph; */
/* import scalax.collection.GraphEdge.HyperEdge; */

import com.creatifcubed.clavier.models.IGraph;
import com.creatifcubed.clavier.models.Graph2dCartesianGrid;

/**
 * @author Raekye
 */
object PathFinding {
	def astar[T <: IGraph.Node[T]: TypeTag](g: IGraph[T], start: T, end: T): List[T] = {
		val closed: Set[T] = new HashSet[T]();
		val open: Set[T] = new HashSet[T]();
		open.add(start);
		val came_from: Map[T, T] = new HashMap[T, T]();
		val g_score: Map[T, Double] = new HashMap[T, Double]();
		val f_score: Map[T, Double] = new HashMap[T, Double]();

		g_score(start) = 0;
		f_score(start) = g_score(start) + 0; // TODO: heuristic cost estimate
		while (!open.isEmpty) {
			var current: T = open.reduce ((a, x) => {
				if (f_score(x) < f_score(a)) x else a;
			});
			if (current == end) {
				return astar_reconstruct_path(came_from, end).toList;
			}
			open -= current;
			closed += current
			for (n <- current.neighbors) {
				if (!closed.contains(n)) { // TODO: make me pretty
					val tentative_g_score: Double = g_score(current) + g.distanceBetween(current, n);
					if (!open.contains(n) || tentative_g_score < g_score(n)) {
						came_from(n) = current;
						g_score(n) = tentative_g_score;
						f_score(n) = g_score(n) + 0; // TODO: heuristic cost estimate(n, goal);
						open += n;
					}
				}
			}
		}
		return null;
	}

	def astar_reconstruct_path[T: TypeTag](came_from: Map[T, T], current_node: T): ListBuffer[T] = {
		if (came_from.contains(current_node)) {
			val path = astar_reconstruct_path(came_from, came_from(current_node));
			return path :+ current_node;
		}
		return (new ListBuffer[T]()) :+ current_node;
	}

	def main(args: Array[String]): Unit = {
		val g = new Graph2dCartesianGrid(16, 16);
		g.mark(14, 14, true);
		val start = new Graph2dCartesianGrid.Node(g, 0, 0);
		val end = new Graph2dCartesianGrid.Node(g, 15, 15);
		val path = astar(g, start, end);
		for (n <- path) {
			println(n.x.toString + ", " + n.y.toString);
		}
	}
}
