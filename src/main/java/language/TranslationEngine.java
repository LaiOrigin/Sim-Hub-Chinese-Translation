package language;

/**
 * 翻译工具集
 *
 * @author Origin
 */
public interface TranslationEngine {
    /**
     * 翻译接口
     *
     * @param query          要翻译的文章
     * @param sourceLanguage 源语言
     * @param targetLanguage 目标语言
     * @return 翻译之后的值
     */
    String translate(String query, String sourceLanguage, String targetLanguage);
}
