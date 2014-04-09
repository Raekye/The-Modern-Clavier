package com.creatifcubed.clavier.models;

trait IGraph[T <: IGraph.Node[T]] {
	def nodes: List[T];
	def distanceBetween(a: T, b: T): Double;
}

object IGraph {
	trait Node[T <: IGraph.Node[T]] {
		def neighbors: List[T];
	}
}
