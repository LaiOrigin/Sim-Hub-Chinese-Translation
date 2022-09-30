import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class XmlTest {
    public static void main(String[] argv) {

        try {
            File inputFile = new File("resources/SimHub.resx");
            DocumentBuilderFactory docFactory =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder =
                    docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(inputFile);
//            Node supercar = doc.getElementsByTagName("supercars").item(0);
            NodeList data = doc.getElementsByTagName("data");
            int l = data.getLength();
            Node dataItem, valueItem;
            for (int i = 0; i < l; i++) {
                dataItem = data.item(i);
                NodeList childNodes = dataItem.getChildNodes();
//                System.out.println("LocalizationKey: " + dataItem.getAttributes().getNamedItem("name").getTextContent());
                for (int v = 0; v < childNodes.getLength(); v++) {
                    valueItem = childNodes.item(v);
                    if (valueItem.getNodeType() == Node.ELEMENT_NODE) {
                        Element e = (Element) valueItem;
//                        e.setTextContent("test");
                        System.out.println(e.getTextContent());
                    }
                }
            }

            // write the content on console
            TransformerFactory transformerFactory =
                    TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            System.out.println("-----------Modified File-----------");
            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
