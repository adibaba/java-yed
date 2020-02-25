package de.adrianwilke.javayed;

import java.io.File;

import de.adrianwilke.javayed.graph.Graph;
import de.adrianwilke.javayed.graph.Node;
import de.adrianwilke.javayed.style.EdgeStyle;
import de.adrianwilke.javayed.style.NodeStyle;
import de.adrianwilke.javayed.xml.YedDoc;

/**
 * Examples.
 *
 * @author Adrian Wilke
 */
public class Examples {

	public static void main(String[] args) throws Exception {
		Examples examples = new Examples();
		examples.exampleA();
		examples.exampleB();
	}

	public void exampleA() throws Exception {

		// Set file to write
		File file = new File("exampleA.yEd.graphml");
		System.out.println("Example file: " + file.getAbsolutePath());

		Graph graph = new Graph();

		// Create nodes
		NodeStyle nodeStyle = new NodeStyle();
		Node a = graph.createNode().setStyle(nodeStyle).setLabel("Adrian");
		Node b = graph.createNode().setStyle(nodeStyle).setLabel("Benjamin");
		Node c = graph.createNode().setStyle(nodeStyle).setLabel("Caesar");

		// Create edges
		EdgeStyle edgeStyle = new EdgeStyle();
		graph.createEdge(a, b).setStyle(edgeStyle);
		graph.createEdge(b, c).setStyle(edgeStyle);
		graph.createEdge(c, a).setStyle(edgeStyle);

		// Create document
		YedDoc yedDoc = new YedDoc();
		yedDoc.create(graph);

		// Write file
		Io.write(yedDoc.getDocument(), file);
	}

	public void exampleB() throws Exception {

		// Set file to write
		File file = new File("exampleB.yEd.graphml");
		System.out.println("Example file: " + file.getAbsolutePath());

		Graph graph = new Graph();

		// Styles
		NodeStyle heroStyle = new NodeStyle().setFillColor(Colors.ORANGE);
		heroStyle.setFontFamily("Roboto").setFontSize(14).setFontStyle("bold");
		NodeStyle robotStyle = new NodeStyle().setFillColor(Colors.YELLOW);
		robotStyle.setFontFamily("Roboto").setFontSize(14).setFontStyle("bold");
		EdgeStyle knowsStyle = new EdgeStyle().setLineColor("#000000");
		knowsStyle.setFontFamily("Roboto").setFontSize(14).setFontStyle("bold");
		EdgeStyle loveStyle = new EdgeStyle().setLineColor("#990000").setLineWidth(2);
		loveStyle.setFontFamily("Roboto").setFontSize(14).setFontStyle("bold");
		EdgeStyle edgeStyle = new EdgeStyle();

		// Create nodes
		Node s = graph.createNode().setStyle(heroStyle).setLabel("Superman");
		Node b = graph.createNode().setStyle(heroStyle).setLabel("Batman");
		Node h = graph.createNode().setStyle(heroStyle).setLabel("Harley Quinn");
		Node r = graph.createNode().setStyle(robotStyle).setLabel("Robocop");
		Node t = graph.createNode().setStyle(robotStyle).setLabel("T-1000");

		// Create edges
		graph.createEdge(s, b).setLabel("knows").setStyle(knowsStyle);
		graph.createEdge(t, s).setLabel("knows").setStyle(knowsStyle);
		graph.createEdge(s, h).setLabel("loves").setStyle(loveStyle);
		graph.createEdge(b, h).setLabel("loves").setStyle(loveStyle);
		graph.createEdge(h, t).setLabel("loves").setStyle(loveStyle);
		graph.createEdge(r, h).setStyle(edgeStyle);

		YedDoc yedDoc = new YedDoc();
		yedDoc.create(graph);

		// Write file
		Io.write(yedDoc.getDocument(), file);
	}
}