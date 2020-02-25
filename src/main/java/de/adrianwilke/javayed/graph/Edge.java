package de.adrianwilke.javayed.graph;

import de.adrianwilke.javayed.style.EdgeStyle;

public class Edge {

	protected String id;
	protected Node source;
	protected Node target;
	protected String label;
	protected EdgeStyle style;

	public Edge(Node source, Node target) {
		this.source = source;
		this.target = target;
	}

	public Edge setId(String id) {
		this.id = id;
		return this;
	}

	public Edge setLabel(String label) {
		this.label = label;
		return this;
	}

	public Edge setStyle(EdgeStyle style) {
		this.style = style;
		return this;
	}

	public String getId() {
		return id;
	}

	public Node getSource() {
		return source;
	}

	public Node getTarget() {
		return target;
	}

	public String getLabel() {
		return label;
	}

	public EdgeStyle getStyle() {
		return style;
	}
}