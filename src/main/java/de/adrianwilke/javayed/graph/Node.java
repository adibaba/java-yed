package de.adrianwilke.javayed.graph;

import java.util.LinkedList;
import java.util.List;

import de.adrianwilke.javayed.style.NodeStyle;

public class Node {

	protected List<Edge> edges = new LinkedList<>();
	protected String id;
	protected String label;
	protected NodeStyle style;

	public Node addEdge(Edge edge) {
		this.edges.add(edge);
		return this;
	}

	public Node setId(String id) {
		this.id = id;
		return this;
	}

	public Node setLabel(String label) {
		this.label = label;
		return this;
	}

	public Node setStyle(NodeStyle style) {
		this.style = style;
		return this;
	}

	public List<Edge> getEdges() {
		return edges;
	}

	public String getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}

	public NodeStyle getStyle() {
		return style;
	}
}