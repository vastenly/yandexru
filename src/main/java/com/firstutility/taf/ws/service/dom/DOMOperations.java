package com.firstutility.taf.ws.service.dom;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.NamespaceContext;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMOperations {
	
	private static final String ENCODING = "UTF-8";
	private static final Logger log = Logger.getLogger(DOMOperations.class);

	public String generateStringWrappedObject(String rootElement, Map<String, String> parameters, String xslPath) {
		Document document = buildWrappedObject(rootElement, parameters, xslPath);
		return convertToString(document);
	}
	
	public String generateStringWrappedObject(Document document, String xslPath) {
		Document wrappedObject = buildWrappedObject(document, xslPath);
		return convertToString(wrappedObject);
	}
	
	public String generateStringDocumentObject(String rootElement, Map<String, String> parameters) {
		Document document = buildDocumentObject(rootElement, parameters);
		return convertToString(document);
	}
	
	public Document insertObjectInDocument(Document document, String rootElement,  Map<String, String> parameters, String place){
		addNodesToDocument(document, rootElement, parameters,findFirstElementByTag(document, place));
		return document;
	}
	
	public Document buildWrappedObject(String rootElement, Map<String, String> parameters, String xslPath) {
		Document documentObject = buildDocumentObject(rootElement, parameters);
		return insertInTemplate(documentObject, xslPath);
	}
	
	private Document buildWrappedObject(Document document, String xslPath) {
		return insertInTemplate(document, xslPath);
	}
	
	public Document buildDocumentObject(String rootElement, Map<String, String> parameters){
		return createDocument(rootElement, parameters);
	}
	
	private Document insertInTemplate (Document documentObject, String reqTemplateFilePath) {
		log.info("[DOMOperations] Request content body loaded from [" +reqTemplateFilePath+ "] file.");
		InputStream template = null;
		try {
			template = new FileInputStream(reqTemplateFilePath);
		} catch (FileNotFoundException e) {
			log.error(e);
			e.printStackTrace();
		}
		return completeRequest(documentObject, template);
	}
	
	public String convertToString(Document documentObject) {
		return documentToString(documentObject);
	}

	public Document convertToDocument(String string) {
		return stringToDocument(string);
	}
	
	public Node findFirstElementByTag(Document document, String tagName){
		return document.getElementsByTagName(tagName).item(0);
	}
	
	public String findElementValueByXpath(Node document, String xpath) {
		return getResultParameterValue(document, xpath);
	}
	
	public List<String> findElementValuesByXpath(Node document, String xpath) {
		return getResultParameterValues(document, xpath);
	}
	
	public List<String> findElementTextsByXpath(Node document, String xpath) {
		return getResultParameterText(document, xpath);
	}
	
	public Node findElementByXpath(Node document, String xpath){
		return getResultParameterNode(document, xpath);
	}

	private String documentToString(Document xmlSource) {
		Transformer transformer = DOMFactory.localTransformer.get();
		StringWriter result = new StringWriter();
		transformer.setOutputProperty(OutputKeys.ENCODING, ENCODING);
		try {
			transformer.transform(new DOMSource(xmlSource), new StreamResult(result));
		} catch (TransformerException e) {
			log.error(e);
			e.printStackTrace();
		}
		return result.toString();
	}

	private Document stringToDocument(String xmlString) {
		DocumentBuilder documentBuilder = DOMFactory.localDocumentBuilder.get();
		byte[] xmlBytes = null;
		try {
			xmlBytes = xmlString.getBytes(ENCODING);
		} catch (UnsupportedEncodingException e) {
			log.error(e);
			e.printStackTrace();
		}
		InputStream xmlStream = new ByteArrayInputStream(xmlBytes);
		try {
			return documentBuilder.parse(xmlStream);
		} catch (SAXException e) {
			log.error(e);
			e.printStackTrace();
		} catch (IOException e) {
			log.error(e);
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			log.error(e);
			e.printStackTrace();
		}
		throw new RuntimeException("[DOMOperations] Input string is NOT parsed, DOM document is NOT created!");
	}

	private Document completeRequest(Document node, InputStream requestPath) {
		
		TransformerFactory transformerFactory = DOMFactory.localTransformerFactory.get();
		Source source = new StreamSource(requestPath);
		
		try {
			Templates template = transformerFactory.newTemplates(source);
			Transformer transformer = template.newTransformer();
			DOMResult request = new DOMResult();
			transformer.transform(new DOMSource(node), request);
			return (Document) request.getNode();
		} catch (TransformerConfigurationException e) {
			log.error(e);
			e.printStackTrace();
		} catch (TransformerException e) {
			log.error(e);
			e.printStackTrace();
		}
		throw new RuntimeException("[DOMOperations] Complete request operation failed due to errors!");
	}

	private Object getResultParameter(Node parentNode, NamespaceContext context, String xPathExpression, QName returnType) {
		XPathFactory xPathFactory = DOMFactory.localXPathFactory.get();
		XPath xPath = xPathFactory.newXPath();
		if (context != null) {
			xPath.setNamespaceContext(context);
		}
		try {
			return xPath.evaluate(xPathExpression, parentNode, returnType);
		} catch (XPathExpressionException e) {
			log.error("<code>xPathExpression</code> cannot be evaluated: " +e);
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			log.error("<code>returnType</code> is not one of the types defined in {@link XPathConstants}: " +e);
			e.printStackTrace();
		} catch (NullPointerException e) {
			log.error("<code>xPathExpression</code> or <code>returnType</code> is <code>null</code>: " +e);
			e.printStackTrace();
		}
		return null;
	}
	
	private String getResultParameterValue(Node parentNode, String xPathExpression) {
		return getResultParameter(parentNode, null, xPathExpression, XPathConstants.STRING).toString();
	}
	
	private List<String> getResultParameterValues(Node parentNode, String xPathExpression) {
		List<String> values = new ArrayList<String>();
		NodeList nodes = getResultParameterNodes(parentNode, xPathExpression);
		if (nodes != null) {
			int nodesNumber = nodes.getLength();
			log.debug("Found " + nodesNumber + " nodes matching XPath " + xPathExpression);
			
			for(int i = 0; i< nodesNumber; i++){
				Node node = nodes.item(i);
				values.add(node.getTextContent());
			}
		} else {
			log.debug("No nodes found matching XPath " + xPathExpression);
		}
		return values;
	}
	
	private List<String> getResultParameterText(Node parentNode, String xPathExpression) {
		List<String> values = new ArrayList<String>();
		NodeList nodes = getResultParameterNodes(parentNode, xPathExpression);
		if (nodes != null) {
			int nodesNumber = nodes.getLength();
			log.debug("Found " + nodesNumber + " nodes matching XPath " + xPathExpression);
			
			for (int i = 0; i< nodesNumber; i++) {
				Node node = nodes.item(i);
				values.add(node.getFirstChild().getNodeValue());
			}
			
		} else {
			log.debug("No nodes found matching XPath " + xPathExpression);
		}
		return values;
	}
	
	private Node getResultParameterNode(Node parentNode, String xPathExpression) {
		return (Node) getResultParameter(parentNode, null, xPathExpression, XPathConstants.NODE);
	}
	
	private NodeList getResultParameterNodes(Node parentNode, String xPathExpression) {
		return (NodeList) getResultParameter(parentNode, null, xPathExpression, XPathConstants.NODESET);
	}

	private Document createDocument() {
		DocumentBuilder documentBuilder = DOMFactory.localDocumentBuilder.get();
		return documentBuilder.newDocument();
	}
	
	private Document createDocument(String rootElementName, Map<String, String> parametersMap) {
		Document document = createDocument();
		Element rootElement = document.createElement(rootElementName);
		document.appendChild(rootElement);
		Node parentNode = document.getFirstChild();
		return addNodesToDocument(document, parametersMap, parentNode);
	}
	
	private Document addNodesToDocument(Document document, Map<String, String> parametersMap, Node parentNode){	
		if (document != null) {
			for (String key : parametersMap.keySet()) {
				Element childNode = document.createElement(key);
				childNode.setTextContent(parametersMap.get(key));
				parentNode.appendChild(childNode);
			}
		}
		return document;
	}
	
	private Document addNodesToDocument(Document document, String rootElementName, Map<String, String> parametersMap, Node parentNode){
		if (document != null) {
			Element rootElement = document.createElement(rootElementName);
			parentNode.appendChild(rootElement);
			parentNode = parentNode.getFirstChild();
			for (String key : parametersMap.keySet()) {
				Element childNode = document.createElement(key);
				childNode.setTextContent(parametersMap.get(key));
				parentNode.appendChild(childNode);
			}
		}
		return document;
	}
}
