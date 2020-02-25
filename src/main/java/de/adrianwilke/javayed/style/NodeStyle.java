package de.adrianwilke.javayed.style;

public class NodeStyle extends Style {

	protected String fillColor = "#ffffff";

	public String getFillColor() {
		return fillColor;
	}

	public NodeStyle setFillColor(String fillColor) {
		this.fillColor = fillColor;
		return this;
	}

}