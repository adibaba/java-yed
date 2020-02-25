package de.adrianwilke.javayed.style;

public class EdgeStyle extends Style {

	protected String lineColor = "#000000";
	private Integer lineWidth;

	public String getLineColor() {
		return lineColor;
	}

	public Integer getLineWidth() {
		return lineWidth;
	}

	public EdgeStyle setLineColor(String lineColor) {
		this.lineColor = lineColor;
		return this;
	}

	public EdgeStyle setLineWidth(Integer lineWidth) {
		this.lineWidth = lineWidth;
		return this;
	}

}