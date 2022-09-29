package translate;


import language.TranslationEngine;
import language.translate.BaiduTranslate;

public class Baidu {
    // 在平台申请的APP_ID 详见 http://api.fanyi.baidu.com/api/trans/product/desktop?req=developer
    private static final String APP_ID = "";
    private static final String SECURITY_KEY = "";

    public static void main(String[] args) {
        TranslationEngine te = new BaiduTranslate(APP_ID, SECURITY_KEY);

        String query = "Height 600m";
        System.out.println(te.translate(query, "auto", "zh"));
    }
}
