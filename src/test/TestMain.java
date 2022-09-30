import core.XmlParsing;
import language.TranslationEngine;
import language.translate.BaiduTranslate;

import java.io.File;
import java.io.IOException;

public class TestMain {
    //todo 删除这个key
    private static final String APP_ID = "";
    private static final String SECURITY_KEY = "";

    public static void main(String[] args) throws IOException {
        File preview = new File("resources/Simhub.zh-cn.resx");
        preview.createNewFile();
        File f = new File("resources/SimHub.resx");
        TranslationEngine te = new BaiduTranslate(APP_ID, SECURITY_KEY);
        XmlParsing xml = new XmlParsing(te, f);
        xml.change();
        xml.writeIn(preview);
    }
}