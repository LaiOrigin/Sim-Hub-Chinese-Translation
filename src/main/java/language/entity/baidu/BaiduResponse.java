package language.entity.baidu;

import lombok.Data;

import java.util.List;

/**
 * @author Origin
 */
@Data
public class BaiduResponse {
    /**
     * 原文语种
     */
    private String from;

    /**
     * 目标语种
     */
    private String to;

    /**
     * 翻译结果
     */
    private List<BaiduTranslateResult> trans_result;

    /**
     * 错误编码, 当报错时才有值
     */
    private String error_code;

    /**
     * 错误信息, 当报错时才有值
     */
    private String error_msg;
}
