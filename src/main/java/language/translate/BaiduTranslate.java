package language.translate;

import com.google.gson.Gson;
import language.TranslationEngine;
import language.entity.baidu.BaiduResponse;
import language.utils.HttpGet;
import language.utils.Md5;

import java.util.HashMap;
import java.util.Map;

/**
 * 参照百度翻译文档
 *
 * @author Origin
 */
public class BaiduTranslate implements TranslationEngine {
    private static final String TRANS_API_HOST = "http://api.fanyi.baidu.com/api/trans/vip/translate";

    private final String appid;
    private final String securityKey;
    private String targetLanguage;
    private String sourceLanguage;

    public BaiduTranslate(String appid, String securityKey) {
        this.sourceLanguage = "auto";
        this.appid = appid;
        this.securityKey = securityKey;
    }

    public BaiduTranslate(String appid, String targetLanguage, String sourceLanguage, String securityKey) {
        this.appid = appid;
        this.targetLanguage = targetLanguage;
        this.sourceLanguage = sourceLanguage;
        this.securityKey = securityKey;
    }

    public void setTargetLanguage(String targetLanguage) {
        this.targetLanguage = targetLanguage;
    }

    public void setSourceLanguage(String sourceLanguage) {
        this.sourceLanguage = sourceLanguage;
    }

    private Map<String, String> buildParams(String query, String from, String to) {
        Map<String, String> params = new HashMap<>();
        params.put("q", query);
        params.put("from", from);
        params.put("to", to);

        params.put("appid", appid);

        // 随机数
        String salt = String.valueOf(System.currentTimeMillis());
        params.put("salt", salt);

        // 签名
        // 加密前的原文
        String src = appid + query + salt + securityKey;
        params.put("sign", Md5.md5(src));

        return params;
    }

    @Override
    public String translate(String query, String sourceLanguage, String targetLanguage) {
        Map<String, String> params = buildParams(query, sourceLanguage, targetLanguage);
        Gson gson = new Gson();
        BaiduResponse baiduResponse = gson.fromJson(HttpGet.get(TRANS_API_HOST, params), BaiduResponse.class);
        return baiduResponse.getTrans_result().get(0).getDst();
    }

    public String translate(String query) {
        if (targetLanguage == null) {
            return null;
        }
        Map<String, String> params = buildParams(query, sourceLanguage, targetLanguage);
        Gson gson = new Gson();
        BaiduResponse baiduResponse = gson.fromJson(HttpGet.get(TRANS_API_HOST, params), BaiduResponse.class);
        return baiduResponse.getTrans_result().get(0).getDst();
    }
}
