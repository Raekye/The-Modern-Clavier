package com.creatifcubed.clavier.models;

import scala.math;

class Graph2dCartesianGrid(val xBound: Int, val yBound: Int) extends IGraph[Graph2dCartesianGrid.Node] {

	val grid = Array.ofDim[Boolean](yBound, xBound);

	override def nodes: List[Graph2dCartesianGrid.Node] = {
		(0 until this.xBound).foldLeft(List.empty[Graph2dCartesianGrid.Node]) ((a, x) => {
			(0 until this.yBound).foldLeft(a) ((b, y) => {
				this.nodeAt(x, y) match {
					case z: Graph2dCartesianGrid.Node =>
						z :: b;
					case _ =>
						b;
				}
			});
		});
	}

	override def distanceBetween(a: Graph2dCartesianGrid.Node, b: Graph2dCartesianGrid.Node): Double = {
		return math.sqrt(math.pow(a.x - b.x, 2) + math.pow(a.y - b.y, 2));
	}

	def mark(x: Int, y: Int, flag: Boolean): Graph2dCartesianGrid = {
		this.grid(y)(x) = flag;
		return this;
	}

	def at(x: Int, y: Int): Option[Boolean] = {
		if (x < 0 || y < 0 || x >= this.xBound || y >= this.yBound) {
			return None;
		}
		return Some(this.grid(y)(x));
	}

	def nodeAt(x: Int, y: Int): Graph2dCartesianGrid.Node = {
		this.at(x, y) match {
			case None =>
				null;
			case Some(true) =>
				null;
			case _ =>
				new Graph2dCartesianGrid.Node(this, x, y);
		}
	}
}

object Graph2dCartesianGrid {
	class Node(val grid: Graph2dCartesianGrid, val x: Int, val y: Int) extends IGraph.Node[Node] {
		override def neighbors: List[Node] = {
			return (-1 to 1).foldLeft(List.empty[Node]) ((a, i) => {
				(-1 to 1).foldLeft(a) ((b, j) => {
					val xPos = this.x + i;
					val yPos = this.y + j;
					this.grid.nodeAt(xPos, yPos) match {
						case z: Node =>
							z :: b;
						case _ =>
							b;
					}
				})
			});
		}
		override def equals(that: Any): Boolean = {
			that match {
				case other: Node =>
					this.grid == other.grid && this.x == other.x && this.y == other.y;
				case _ =>
					false;
			}
		}

		override def hashCode(): Int = {
			return (this.grid.hashCode() * 31 + this.x) * 31 + this.y;
		}
	}
}
