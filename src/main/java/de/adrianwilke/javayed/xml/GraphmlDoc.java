package de.adrianwilke.javayed.xml;

import java.util.LinkedList;
import java.util.List;

import org.w3c.dom.Element;

/**
 * GraphML document.
 * 
 * Creates graphml-element in document. Adds graph-elements in graphml-element.
 * 
 * @see http://graphml.graphdrawing.org/specification/xsd.html
 *
 * @author Adrian Wilke
 */
public class GraphmlDoc extends XmlDoc {

	public enum GraphType {
		DIRECTED, UNDIRECTED
	}

	protected Element graphml;
	protected List<Element> graphs = new LinkedList<>();

	/**
	 * Creates default root element, named "graphml".
	 */
	public GraphmlDoc createGraphml() {
		if (document == null) {
			super.createDocument();
		}

		Element element = document.createElement("graphml");
		document.appendChild(element);

		graphml = element;

		return this;
	}

	/**
	 * Gets the default root element, named "graphml".
	 */
	public Element getGraphml() {
		if (graphml == null) {
			createGraphml();
		}

		return graphml;
	}

	/**
	 * Appends graph element to root.
	 */
	public GraphmlDoc addGraph(GraphType edgedefault) {
		if (graphml == null) {
			createGraphml();
		}

		Element graph = document.createElement("graph");
		graph.setAttribute("edgedefault", edgedefault.toString().toLowerCase());
		graphml.appendChild(graph);
		graphs.add(graph);
		return this;
	}

	/**
	 * Gets list of graphs.
	 */
	public List<Element> getGraphs() {
		return graphs;
	}

}