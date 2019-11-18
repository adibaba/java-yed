package de.adrianwilke.javayed;

/**
 * Sets color based on ID.
 * 
 * Uses colors in {@link Colors} and {@link Colors#getColor(int)}.
 *
 * @author Adrian Wilke
 */
public class NodeType {

	public final static String DEFAULT_COLOR = "#ffffff";

	protected String color;

	public NodeType(Integer id) {
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

		// yEd colors

		switch (number) {
		case 0:
			return Colors.ORANGE;
		case 1:
			return Colors.YELLOW;
		case 2:
			return Colors.GREEN;
		case 3:
			return Colors.BLUE;
		case 4:
			return Colors.PURPLE;
		default:
			break;
		}

		// Computed gray
		// Range: [255, 1]
		// 5 -> 255 (number 5 not predefined)
		int code = 255 + 5 - number;
		if (code > 0) {
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