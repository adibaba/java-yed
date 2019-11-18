package de.adrianwilke.javayed;

/**
 * Sets color based on ID.
 * 
 * Uses colors in {@link Colors} and {@link Colors#getColor(int)}.
 *
 * @author Adrian Wilke
 */
public class EdgeType {

	public final static String DEFAULT_COLOR = "#000000";

	protected String color;

	public EdgeType(Integer id) {
		if (id == null) {
			color = DEFAULT_COLOR;
		} else {
			color = computeColor(id);
		}
	}

	public String getColor() {
		return color;
	}

	protected static String computeColor(int number) {

		// Computed gray
		// Range: [1, 200]
		int code = number + 1;
		if (code >= 1 && code <= 200) {
			String hex = Integer.toHexString(code);
			if (code < 10) {
				hex = "0" + hex;
			}
			return "#" + hex + hex + hex;
		}

		// Default color

		return DEFAULT_COLOR;
	}
}