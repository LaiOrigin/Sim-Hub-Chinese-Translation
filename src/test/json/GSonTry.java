package json;

import com.google.gson.Gson;
import language.entity.baidu.BaiduResponse;

public class GSonTry {
    public static void main(String[] args) {
        Gson gson = new Gson();
        String str = "{\"from\":\"en\",\"to\":\"zh\",\"trans_result\":[{\"src\":\"Height 600m\",\"dst\":\"\\u9ad8\\u5ea6600m\"}]}";
        BaiduResponse t = gson.fromJson(str, BaiduResponse.class);
        System.out.println(t.getTrans_result().get(0).getDst());
    }
}
