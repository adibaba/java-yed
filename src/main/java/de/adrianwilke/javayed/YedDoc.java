package de.adrianwilke.javayed;

import javax.xml.XMLConstants;

import org.w3c.dom.Element;

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

	protected boolean initialized = false;
	protected long nodeCounter = 0;
	protected long edgeCounter = 0;
	private String fontFamily;
	private int fontSize = -1;
	private String fontStyle;

	/**
	 * Initializes document.
	 * 
	 * @throws RuntimeException if already initialized.
	 */
	public YedDoc initialize() throws RuntimeException {
		if (initialized) {
			throw new RuntimeException("Already initialized. " + YedDoc.class.getName());
		} else {
			createRoot();
			createStructure();
			addGraph(GraphType.DIRECTED);
			initialized = true;
		}

		return this;
	}

	/**
	 * Creates edge and returns the related edge ID.
	 * 
	 * Uses default edge-type. Does not set label.
	 * 
	 * @throws RuntimeException if not initialized.
	 */
	public String createEdge(String sourceId, String targetId) throws RuntimeException {
		return createEdge(sourceId, targetId, null, null);
	}

	/**
	 * Creates edge and returns the related edge ID.
	 * 
	 * @throws RuntimeException if not initialized.
	 */
	public String createEdge(String sourceId, String targetId, String label, Integer type) throws RuntimeException {

		if (!initialized) {
			throw new RuntimeException("Not initialized. " + YedDoc.class.getName());
		}

		String edgeId = "e" + edgeCounter++;

		Element lineStyle = document.createElement("y:LineStyle");
		lineStyle.setAttribute("color", new EdgeType(type).getColor());
		lineStyle.setAttribute("type", "line");
		lineStyle.setAttribute("width", "1.0");

		Element arrows = document.createElement("y:Arrows");
		arrows.setAttribute("source", "none");
		arrows.setAttribute("target", "delta");

		Element edgeType = document.createElement("y:PolyLineEdge");
		edgeType.appendChild(lineStyle);
		edgeType.appendChild(arrows);

		if (label != null) {
			Element edgeLabel = document.createElement("y:EdgeLabel");
			edgeLabel.appendChild(document.createTextNode(label));
			edgeType.appendChild(edgeLabel);
			if (fontFamily != null) {
				edgeLabel.setAttribute("fontFamily", fontFamily);
			}
			if (fontSize != -1) {
				edgeLabel.setAttribute("fontSize", String.valueOf(fontSize));
			}
			if (fontStyle != null) {
				edgeLabel.setAttribute("fontStyle", fontStyle);
			}
		}

		Element data = document.createElement("data");
		data.setAttribute("key", ID_EDGE_GRAPHICS);
		data.appendChild(edgeType);

		Element edge = document.createElement("edge");
		edge.setAttribute("id", edgeId);
		edge.setAttribute("source", sourceId);
		edge.setAttribute("target", targetId);
		edge.appendChild(data);

		getDefaultGraph().appendChild(edge);

		return edgeId;
	}

	/**
	 * Creates node and returns the related node ID.
	 * 
	 * @throws RuntimeException if not initialized.
	 */
	public String createNode(String label) throws RuntimeException {
		return createNode(label, null);
	}

	/**
	 * Creates node and returns the related node ID.
	 * 
	 * @throws RuntimeException if not initialized.
	 */
	public String createNode(String label, Integer type) throws RuntimeException {

		if (!initialized) {
			throw new RuntimeException("Not initialized. " + YedDoc.class.getName());
		}

		String nodeId = "n" + nodeCounter++;

		Element nodeLabel = document.createElement("y:NodeLabel");
		nodeLabel.appendChild(document.createTextNode(label));
		if (fontFamily != null) {
			nodeLabel.setAttribute("fontFamily", fontFamily);
		}
		if (fontSize != -1) {
			nodeLabel.setAttribute("fontSize", String.valueOf(fontSize));
		}
		if (fontStyle != null) {
			nodeLabel.setAttribute("fontStyle", fontStyle);
		}

		Element fill = document.createElement("y:Fill");
		fill.setAttribute("color", new NodeType(type).getColor());
		fill.setAttribute("transparent", "false");

		Element shapeNode = document.createElement("y:ShapeNode");
		shapeNode.appendChild(nodeLabel);
		shapeNode.appendChild(fill);

		Element data = document.createElement("data");
		data.setAttribute("key", ID_NODE_GRAPHICS);
		data.appendChild(shapeNode);

		Element node = document.createElement("node");
		node.setAttribute("id", nodeId);
		node.appendChild(data);

		getDefaultGraph().appendChild(node);

		return nodeId;
	}

	/**
	 * Gets the yEd graph element.
	 */
	public Element getDefaultGraph() {
		return graphs.get(0);
	}

	/**
	 * Creates default root element, named "graphml".
	 * 
	 * Adds yEd namespace information.
	 * 
	 * Will automatically be called, as overrides {@link GraphmlDoc#createRoot()}.
	 */
	@Override
	public GraphmlDoc createRoot() {
		if (document == null) {
			createDocument();
		}

		Element element = document.createElementNS("http://graphml.graphdrawing.org/xmlns", "graphml");
		element.setAttributeNS(XMLConstants.XMLNS_ATTRIBUTE_NS_URI, XMLConstants.XMLNS_ATTRIBUTE + ":" + "y",
				"http://www.yworks.com/xml/graphml");
		document.appendChild(element);

		root = element;

		return this;
	}

	/**
	 * Creates default data structures for yEd files.
	 * 
	 * Integrated in {@link #initialize()}.
	 */
	public YedDoc createStructure() {
		Element edgeGraphics = document.createElement("key");
		edgeGraphics.setAttribute("id", ID_EDGE_GRAPHICS);
		edgeGraphics.setAttribute("for", "edge");
		edgeGraphics.setAttribute("yfiles.type", "edgegraphics");
		root.appendChild(edgeGraphics);

		Element nodeGraphics = document.createElement("key");
		nodeGraphics.setAttribute("id", ID_NODE_GRAPHICS);
		nodeGraphics.setAttribute("for", "node");
		nodeGraphics.setAttribute("yfiles.type", "nodegraphics");
		root.appendChild(nodeGraphics);

		return this;
	}

	/**
	 * Gets default font family for nodes and edges.
	 */
	public String getFontFamily() {
		return fontFamily;
	}

	/**
	 * Sets default font family for nodes and edges.
	 */
	public void setFontFamily(String defaultFontFamily) {
		this.fontFamily = defaultFontFamily;
	}

	/**
	 * Gets default font size for nodes and edges.
	 */
	public int getFontSize() {
		return fontSize;
	}

	/**
	 * Sets default font size for nodes and edges.
	 */
	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

	/**
	 * Gets default font style for nodes and edges.
	 */
	public String getFontStyle() {
		return fontStyle;
	}

	/**
	 * Sets default font style for nodes and edges.
	 */
	public void setFontStyle(String fontStyle) {
		this.fontStyle = fontStyle;
	}

}