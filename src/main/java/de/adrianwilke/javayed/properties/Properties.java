package de.adrianwilke.javayed.properties;

import java.util.HashMap;
import java.util.Map;

public class Properties {

	protected Map<String, String> attributes = new HashMap<>();
	protected Map<String, Properties> children = new HashMap<>();
	protected String text;

	public Properties putAttribute(String key, String value) {
		attributes.put(key, value);
		return this;
	}

	public Properties putChild(String key, Properties properties) {
		children.put(key, properties);
		return this;
	}

	public Properties setText(String text) {
		this.text = text;
		return this;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public Map<String, Properties> getChildren() {
		return children;
	}

	public String getText() {
		return text;
	}
}