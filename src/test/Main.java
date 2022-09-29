import core.XmlParsing;

import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class Main {
    public static void main(String[] args) {
//        随便测试，明天再弄
        File f = new File("resources/hello.xml");
        StreamResult consoleResult = new StreamResult(System.out);
        XmlParsing xml = new XmlParsing(f);
        xml.change();
        xml.previewChanges(consoleResult);
        xml.writeIn();
    }
}