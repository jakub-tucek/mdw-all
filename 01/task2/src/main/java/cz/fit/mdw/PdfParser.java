package cz.fit.mdw;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class PdfParser {

    private static void log(String s) {
        System.out.println(s);
    }

    private static String formatLine(String key, String value) {
        return String.format("%s: \"%s\"", key, value);
    }

    public void parse() throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        DocumentBuilder builder = factory.newDocumentBuilder();

        Document document = builder.parse(
                new File(getClass().getResource("/records.xml").getFile()));

        NodeList nodeList = document.getElementsByTagName("record");

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            Node attribute = node.getAttributes().getNamedItem("type");

            log(formatLine(attribute.getNodeName(), attribute.getNodeValue()));

            NodeList childNodes = node.getChildNodes();
            for (int j = 0; j < childNodes.getLength(); j++) {
                Node childNode = childNodes.item(j);
                if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                    log(formatLine(childNode.getNodeName(), childNode.getTextContent()));
                }
            }

            log("");
        }
    }
}
