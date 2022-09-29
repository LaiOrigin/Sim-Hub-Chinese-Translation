package language.entity.baidu;

import lombok.Data;

/**
 * @author Origin
 */
@Data
public class BaiduTranslateResult {
    /**
     * 原文
     */
    private String src;

    /**
     * 翻译
     */
    private String dst;
}
