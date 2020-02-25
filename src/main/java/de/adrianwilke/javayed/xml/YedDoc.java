package de.adrianwilke.javayed.xml;

import javax.xml.XMLConstants;

import org.w3c.dom.Element;

import de.adrianwilke.javayed.graph.Edge;
import de.adrianwilke.javayed.graph.Graph;
import de.adrianwilke.javayed.graph.Node;
import de.adrianwilke.javayed.properties.Properties;
import de.adrianwilke.javayed.style.EdgeStyle;
import de.adrianwilke.javayed.style.NodeStyle;

/**
 * Creates yEd graph files using extended GraphML.
 * 
 * Execute {@link #initialize()} first. Afterwards, add nodes and edges.
 *
 * @see https://www.yworks.com/products/yed
 *
 * @author Adrian Wilke
 */
public class YedDoc extends GraphmlDoc {

	public static final String ID_EDGE_GRAPHICS = "d0";
	public static final String ID_NODE_GRAPHICS = "d1";

	protected void addNode(Node node) {
		if (node.getStyle() == null) {
			node.setStyle(new NodeStyle());
		}

		Element nodeLabel = null;
		if (node.getLabel() != null) {
			nodeLabel = document.createElement("y:NodeLabel");
			nodeLabel.appendChild(document.createTextNode(node.getLabel()));
			if (node.getStyle().getFontFamily() != null) {
				nodeLabel.setAttribute("fontFamily", node.getStyle().getFontFamily());
			}
			if (node.getStyle().getFontSize() != null) {
				nodeLabel.setAttribute("fontSize", String.valueOf(node.getStyle().getFontSize()));
			}
			if (node.getStyle().getFontStyle() != null) {
				nodeLabel.setAttribute("fontStyle", node.getStyle().getFontStyle());
			}
		}

		Element fill = null;
		if (node.getStyle().getFillColor() != null) {
			fill = document.createElement("y:Fill");
			fill.setAttribute("color", node.getStyle().getFillColor());
		}

		Element shape = document.createElement("y:ShapeNode");
		if (nodeLabel != null) {
			shape.appendChild(nodeLabel);
		}
		if (fill != null) {
			shape.appendChild(fill);
		}

		Element data = document.createElement("data");
		data.setAttribute("key", ID_NODE_GRAPHICS);
		data.appendChild(shape);

		Element nodeElement = document.createElement("node");
		nodeElement.setAttribute("id", node.getId());
		nodeElement.appendChild(data);
		getDefaultGraph().appendChild(nodeElement);

		// Currently Not integrated
		// Add additional properties
		// if (node.getStyle().getProperties() != null) {
		// addProperties(nodeElement, node.getStyle().getProperties());
		// }

	}

	protected void addEdge(Edge edge) {
		if (edge.getStyle() == null) {
			edge.setStyle(new EdgeStyle());
		}

		Element edgeLabel = null;
		if (edge.getLabel() != null) {
			edgeLabel = document.createElement("y:EdgeLabel");
			edgeLabel.appendChild(document.createTextNode(edge.getLabel()));
			if (edge.getStyle().getFontFamily() != null) {
				edgeLabel.setAttribute("fontFamily", edge.getStyle().getFontFamily());
			}
			if (edge.getStyle().getFontSize() != null) {
				edgeLabel.setAttribute("fontSize", String.valueOf(edge.getStyle().getFontSize()));
			}
			if (edge.getStyle().getFontStyle() != null) {
				edgeLabel.setAttribute("fontStyle", edge.getStyle().getFontStyle());
			}
		}

		Element lineStyle = document.createElement("y:LineStyle");
		if (edge.getStyle().getLineColor() != null) {
			lineStyle.setAttribute("color", edge.getStyle().getLineColor());
		}
		if (edge.getStyle().getLineWidth() != null) {
			lineStyle.setAttribute("width", edge.getStyle().getLineWidth().toString());
		}

		Element arrows = document.createElement("y:Arrows");
		arrows.setAttribute("source", "none");
		arrows.setAttribute("target", "delta");

		Element edgeType = document.createElement("y:PolyLineEdge");
		if (lineStyle != null) {
			edgeType.appendChild(lineStyle);
		}
		if (edgeLabel != null) {
			edgeType.appendChild(edgeLabel);
		}
		edgeType.appendChild(arrows);

		Element data = document.createElement("data");
		data.setAttribute("key", ID_EDGE_GRAPHICS);
		data.appendChild(edgeType);

		Element edgeElement = document.createElement("edge");
		edgeElement.setAttribute("id", edge.getId());
		edgeElement.setAttribute("source", edge.getSource().getId());
		edgeElement.setAttribute("target", edge.getTarget().getId());
		edgeElement.appendChild(data);

		getDefaultGraph().appendChild(edgeElement);

		// Currently Not integrated
		// Add additional properties
		// if (node.getStyle().getProperties() != null) {
		// addProperties(nodeElement, node.getStyle().getProperties());
		// }

	}

	protected void addProperties(Element element, Properties properties) {
		// Currently Not integrated
		// Will maybe become an Element directly

		// for (Entry<String, String> attribute : properties.getAttributes().entrySet())
		// {
		// if (attribute.getKey() == null || attribute.getValue() == null) {
		// throw new RuntimeException();
		// }
		// element.setAttribute(attribute.getKey(), attribute.getValue());
		// }
		//
		// if (properties.getText() != null) {
		// element.appendChild(document.createTextNode(properties.getText()));
		// }
		//
		// for (Properties nestedProperties : properties.getChildren()) {
		// addProperties(element, nestedProperties);
		// }
		//
		// parent.appendChild(element);
	}

	public void create(Graph graph) {
		initialize();

		for (Node node : graph.getNodes()) {
			addNode(node);
		}

		for (Node node : graph.getNodes()) {
			for (Edge edge : node.getEdges()) {
				addEdge(edge);
			}
		}
	}

	/**
	 * Initializes yEd document.
	 */
	protected void initialize() throws RuntimeException {
		if (document == null) {
			super.createDocument();
		}

		graphml = document.createElementNS("http://graphml.graphdrawing.org/xmlns", "graphml");
		graphml.setAttributeNS(XMLConstants.XMLNS_ATTRIBUTE_NS_URI, XMLConstants.XMLNS_ATTRIBUTE + ":" + "y",
				"http://www.yworks.com/xml/graphml");
		document.appendChild(graphml);

		Element edgeGraphics = document.createElement("key");
		edgeGraphics.setAttribute("id", ID_EDGE_GRAPHICS);
		edgeGraphics.setAttribute("for", "edge");
		edgeGraphics.setAttribute("yfiles.type", "edgegraphics");
		graphml.appendChild(edgeGraphics);

		Element nodeGraphics = document.createElement("key");
		nodeGraphics.setAttribute("id", ID_NODE_GRAPHICS);
		nodeGraphics.setAttribute("for", "node");
		nodeGraphics.setAttribute("yfiles.type", "nodegraphics");
		graphml.appendChild(nodeGraphics);

		addGraph(GraphType.DIRECTED);
	}

	/**
	 * Gets the yEd graph element.
	 */
	public Element getDefaultGraph() {
		return graphs.get(0);
	}

}