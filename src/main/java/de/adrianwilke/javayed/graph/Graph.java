package de.adrianwilke.javayed.graph;

import java.util.LinkedList;
import java.util.List;

public class Graph {

	protected List<Node> nodes = new LinkedList<>();
	protected long nodeCounter = 0;
	protected long edgeCounter = 0;

	public Node createNode() {
		Node node = new Node().setId("n" + nodeCounter++);
		this.nodes.add(node);
		return node;
	}

	public Edge createEdge(Node source, Node target) {
		Edge edge = new Edge(source, target).setId("e" + edgeCounter++);
		source.addEdge(edge);
		return edge;
	}

	public List<Node> getNodes() {
		return nodes;
	}
}