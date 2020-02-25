package de.adrianwilke.javayed;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import de.adrianwilke.javayed.graph.Graph;
import de.adrianwilke.javayed.xml.YedDoc;

/**
 * Tests {@link Io}.
 *
 * @author Adrian Wilke
 */
public class IoTest {

	public static final boolean DELETE_FILES_ON_EXIT = true;

	@Test
	public void testWriter() throws Exception {
		File file = File.createTempFile(IoTest.class.getName() + ".", ".yEd.graphml");
		System.out.println("Writing test file: " + file.getPath());
		if (DELETE_FILES_ON_EXIT) {
			file.deleteOnExit();
		}

		Graph graph = new Graph();
		graph.createEdge(graph.createNode().setLabel("a"), graph.createNode().setLabel("b"));
		YedDoc yedDoc = new YedDoc();
		yedDoc.create(graph);
		Io.write(yedDoc.getDocument(), file);

		Assert.assertTrue("File created", file.exists());
		Assert.assertTrue("File not empty", file.length() > 0);
	}

	@Test
	public void testReader() throws Exception {

		// Create file
		File file = File.createTempFile(IoTest.class.getName() + ".", ".yEd.graphml");
		System.out.println("Reading test file: " + file.getPath());
		if (DELETE_FILES_ON_EXIT) {
			file.deleteOnExit();
		}

		// Write
		Graph graph = new Graph();
		graph.createEdge(graph.createNode().setLabel("a"), graph.createNode().setLabel("b"));
		YedDoc yedDoc = new YedDoc();
		yedDoc.create(graph);
		Io.write(yedDoc.getDocument(), file);

		// Read and check graphml element
		Document document = Io.read(file);
		Node graphmlNode = document.getFirstChild();
		Assert.assertEquals("Got graphml node", "graphml", graphmlNode.getNodeName());

		// Check graph element
		Element graphNode = null;
		NodeList graphmlChildNodes = graphmlNode.getChildNodes();
		for (int i = 0; i < graphmlChildNodes.getLength(); i++) {
			if (graphmlChildNodes.item(i).getNodeName().equals("graph")) {
				if (graphmlChildNodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
					graphNode = (Element) graphmlChildNodes.item(i);
					break;
				}
			}
		}
		Assert.assertNotNull(graphNode);

		// Check number of nodes
		Assert.assertEquals("2 nodes", 2, graphNode.getElementsByTagName("node").getLength());
	}

}
