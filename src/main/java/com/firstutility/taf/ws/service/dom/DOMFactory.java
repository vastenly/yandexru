package com.firstutility.taf.ws.service.dom;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.xpath.XPathFactory;

public class DOMFactory {
	public static final ThreadLocal<DocumentBuilderFactory> localDocumentBuilderFactory = new ThreadLocal<DocumentBuilderFactory>() {
		protected DocumentBuilderFactory initialValue() {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
					.newInstance();
			documentBuilderFactory.setNamespaceAware(true);
			return documentBuilderFactory;
		}
	};

	public static final ThreadLocal<DocumentBuilder> localDocumentBuilder = new ThreadLocal<DocumentBuilder>() {
		protected DocumentBuilder initialValue() {
			try {
				return localDocumentBuilderFactory.get().newDocumentBuilder();
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			}
			return null;
		}
	};

	public static final ThreadLocal<TransformerFactory> localTransformerFactory = new ThreadLocal<TransformerFactory>() {
		protected TransformerFactory initialValue() {
			return TransformerFactory.newInstance();
		}
	};

	public static final ThreadLocal<Transformer> localTransformer = new ThreadLocal<Transformer>() {
		protected Transformer initialValue() {
			try {
				return localTransformerFactory.get().newTransformer();
			} catch (TransformerConfigurationException e) {
				e.printStackTrace();
			}
			return null;
		}
	};

	public static final ThreadLocal<XPathFactory> localXPathFactory = new ThreadLocal<XPathFactory>() {
		protected XPathFactory initialValue() {
			return XPathFactory.newInstance();
		}
	};
	
	@SuppressWarnings("restriction")
	public static final ThreadLocal<XMLInputFactory> localXMLInputFactory = new ThreadLocal<XMLInputFactory>() {
		protected XMLInputFactory initialValue() {
			return XMLInputFactory.newInstance();
		}
	};
}
