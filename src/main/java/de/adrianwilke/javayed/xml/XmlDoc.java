package de.adrianwilke.javayed.xml;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;

/**
 * XML document.
 * 
 * To create an underlying {@link Document}, {@link #createDocument()} can be
 * used.
 *
 * @author Adrian Wilke
 */
public class XmlDoc {

	protected DocumentBuilderFactory documentBuilderFactory;
	protected DocumentBuilder documentBuilder;
	protected Document document;

	/**
	 * Creates default {@link DocumentBuilderFactory}.
	 */
	public XmlDoc createDocumentBuilderFactory() {
		documentBuilderFactory = DocumentBuilderFactory.newInstance();
		return this;
	}

	/**
	 * Creates default {@link DocumentBuilder}.
	 */
	public XmlDoc createDocumentBuilder() {
		if (documentBuilderFactory == null) {
			createDocumentBuilderFactory();
		}

		try {
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			throw new RuntimeException(e);
		}
		return this;
	}

	/**
	 * Creates default {@link Document}.
	 */
	public XmlDoc createDocument() {
		if (documentBuilder == null) {
			createDocumentBuilder();
		}

		document = documentBuilder.newDocument();
		return this;
	}

	/**
	 * Gets the XML {@link Document}.
	 */
	public Document getDocument() {
		return document;
	}
}