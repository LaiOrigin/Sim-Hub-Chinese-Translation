package core;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author Origin
 */
public class XmlParsing {
    private final File file;
    private DOMSource source;

    public XmlParsing(File file) {
        this.file = file;
    }

    public void change() {
        try {
            DocumentBuilderFactory docFactory =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder =
                    docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(file);


            NodeList carname = doc.getElementsByTagName("carname");
            for (int i = 0; i < carname.getLength(); i++) {
                Element e = (Element) carname.item(i);
                e.setTextContent("test" + i);
                System.out.println(e.getTextContent());
            }

            System.out.println("=====");
            /*
            Node cars = doc.getFirstChild();
            Node supercar = doc.getElementsByTagName("supercars").item(0);
            // update supercar attribute
            NamedNodeMap attr = supercar.getAttributes();
            Node nodeAttr = attr.getNamedItem("company");
            nodeAttr.setTextContent("Lamborigini");

            // loop the supercar child node
            NodeList list = supercar.getChildNodes();
            for (int temp = 0; temp < list.getLength(); temp++) {
                Node node = list.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    if ("carname".equals(eElement.getNodeName())) {
                        if ("Ferrari 101".equals(eElement.getTextContent())) {
                            eElement.setTextContent("Lamborigini 001");
                        }
                        if ("Ferrari 202".equals(eElement.getTextContent())) {
                            eElement.setTextContent("Lamborigini 002");
                        }
                    }
                }
            }
            NodeList childNodes = cars.getChildNodes();
            for (int count = 0; count < childNodes.getLength(); count++) {
                Node node = childNodes.item(count);
                if ("luxurycars".equals(node.getNodeName())) {
                    cars.removeChild(node);
                }
            }*/

            //change
            this.source = new DOMSource(doc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeIn() {
        if (source == null) {
            System.out.println("请先转换");
            return;
        }
        try {
            OutputStream fos = new FileOutputStream(file);
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer();
            StreamResult sr = new StreamResult(fos);
            transformer.transform(source, sr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void previewChanges(StreamResult streamResult) {
        if (source == null) {
            try {
                streamResult.getOutputStream().write("null\n".getBytes());
                streamResult.getOutputStream().flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return;
        }
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer;
        try {
            transformer = transformerFactory.newTransformer();
            transformer.transform(source, streamResult);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }
}
