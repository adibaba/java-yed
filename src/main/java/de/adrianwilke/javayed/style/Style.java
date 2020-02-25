package de.adrianwilke.javayed.style;

import de.adrianwilke.javayed.properties.Properties;

public class Style {

	protected String fontFamily;
	protected Integer fontSize;
	protected String fontStyle;
	protected Properties properties;

	/**
	 * Gets default font family for nodes and edges.
	 */
	public String getFontFamily() {
		return fontFamily;
	}

	/**
	 * Gets default font size for nodes and edges.
	 */
	public Integer getFontSize() {
		return fontSize;
	}

	/**
	 * Gets default font style for nodes and edges.
	 */
	public String getFontStyle() {
		return fontStyle;
	}

	public Properties getProperties() {
		return properties;
	}

	/**
	 * Sets default font family for nodes and edges.
	 */
	public Style setFontFamily(String defaultFontFamily) {
		this.fontFamily = defaultFontFamily;
		return this;
	}

	/**
	 * Sets default font size for nodes and edges.
	 */
	public Style setFontSize(int fontSize) {
		this.fontSize = fontSize;
		return this;
	}

	/**
	 * Sets default font style for nodes and edges.
	 */
	public Style setFontStyle(String fontStyle) {
		this.fontStyle = fontStyle;
		return this;
	}

	public Style setProperties(Properties properties) {
		this.properties = properties;
		return this;
	}
}