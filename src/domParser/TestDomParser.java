package domParser;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class TestDomParser {

	// uses log4j api
	private static final Logger LOG = Logger.getLogger(TestDomParser.class);

	// uses commons.logging api which is an abstract layer and it wraps other
	// logging libraries(including log4j)
	// log4j is a logging framework, i.e. it provides the code to log messages.
	// Commons-logging is an abstraction layer for logging frameworks, it
	// doesn't log anything itself. For example if I write code using commons
	// logging and deploy it on JBoss, the logging is done by log4j, but if I
	// deploy it on WebSphere logging is done by WebSphere's own logging
	// implementation. If I run the same code as a stand alone application it
	// Java's own logging that is used
	private static final Log LOG1 = LogFactory.getLog(TestDomParser.class);

	private TestDomParser() {

	}

	public static void main(String[] args) throws SAXException, IOException {

		LOG.info("Reading xm file");
		File file = new File(
				"C:/DevelopmentWorkspace/SAXParser/src/domParser/student.xml");
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory
				.newInstance();
		try {
			DocumentBuilder documentBuilder = builderFactory
					.newDocumentBuilder();
			Document document = documentBuilder.parse(file);

			// root element
			Element root = document.getDocumentElement();
			root.normalize();
			LOG.info("Root element is :: " + root.getNodeName());

			// Children of root element
			NodeList childs = root.getElementsByTagName("student");
			LOG.info("Children length is :: " + childs.getLength());

			for (int j = 0; j < childs.getLength(); j++) {

				Node node = childs.item(j);
				LOG.info("Current node" + node.getNodeName());

				Element element = (Element) node;
				LOG.info("Roll no is : " + element.getAttribute("rollno"));
				LOG.info("First Name is : "
						+ element.getElementsByTagName("firstname").item(0)
								.getTextContent());
				LOG.info("Last name is: "
						+ element.getElementsByTagName("lastname").item(0)
								.getTextContent());
				LOG.info("Nick name is : "
						+ element.getElementsByTagName("nickname").item(0)
								.getTextContent());
				LOG.info("marks are : "
						+ element.getElementsByTagName("marks").item(0)
								.getTextContent());

				// another way of iterating but it takes space as children so
				// not the best
				/*
				 * for (int k = 0; k < childs.item(j).toString().length(); k++)
				 * {
				 * 
				 * NodeList stuChild = childs.item(j).getChildNodes(); String
				 * nodeName = stuChild.item(k).getNodeName(); String nodeValue =
				 * stuChild.item(k).getTextContent();
				 * 
				 * LOG.info(nodeName + "  " + nodeValue);
				 * 
				 * }
				 */
			}
		} catch (ParserConfigurationException e) {
			LOG.error("Something went wrong here", e);
		}

	}

}
