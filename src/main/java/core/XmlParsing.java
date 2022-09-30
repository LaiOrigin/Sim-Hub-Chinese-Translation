package core;

import language.TranslationEngine;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
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
    private TranslationEngine translationEngine;
    private final File file;
    private DOMSource source;

    public XmlParsing(TranslationEngine translationEngine, File file) {
        this.translationEngine = translationEngine;
        this.file = file;
    }

    public void change() {
        try {
            DocumentBuilderFactory docFactory =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder =
                    docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(file);

            NodeList data = doc.getElementsByTagName("data"), childNodes;
            int l = data.getLength();
            Node dataItem, valueItem;
            for (int i = 0; i < l; i++) {
                dataItem = data.item(i);
                childNodes = dataItem.getChildNodes();
                System.out.println("LocalizationKey: " + dataItem.getAttributes().getNamedItem("name").getTextContent());
                for (int v = 0; v < childNodes.getLength(); v++) {
                    valueItem = childNodes.item(v);
                    if (valueItem.getNodeType() == Node.ELEMENT_NODE) {
                        Element e = (Element) valueItem;

                        String sourceStr = e.getTextContent();
                        //防止api获取过频繁
                        String translate = translationEngine.translate(sourceStr, "en", "zh");
                        e.setTextContent(translate);
                        System.out.println("DefaultValue: " + sourceStr + " -> " + translate);
                        Thread.sleep(100);
                    }
                }
            }

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

    public void writeIn(File file) {
        if (file == null) {
            System.out.println("文件为null");
            return;
        }
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
