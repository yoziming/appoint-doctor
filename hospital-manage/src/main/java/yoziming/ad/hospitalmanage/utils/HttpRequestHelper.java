package yoziming.ad.hospitalmanage.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@Slf4j
public class HttpRequestHelper {

    //private final static String signKey = "09c1ff67d1ae4999e137f34b0dff1046";

    public static void main(String[] args) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("d", "4");
        paramMap.put("b", "2");
        paramMap.put("c", "3");
        paramMap.put("a", "1");
        log.info(getSign(paramMap, ""));
    }

    /**
     * @param paramMap
     * @return
     */
    public static Map<String, Object> switchMap(Map<String, String[]> paramMap) {
        Map<String, Object> resultMap = new HashMap<>();
        for (Map.Entry<String, String[]> param : paramMap.entrySet()) {
            resultMap.put(param.getKey(), param.getValue()[0]);
        }
        return resultMap;
    }

    /**
     * 請求數據獲取簽名
     *
     * @param paramMap
     * @return
     */
    public static String getSign(Map<String, Object> paramMap, String signKey) {
        if (paramMap.containsKey("sign")) {
            paramMap.remove("sign");
        }
        TreeMap<String, Object> sorted = new TreeMap<>(paramMap);
        StringBuilder str = new StringBuilder();
        for (Map.Entry<String, Object> param : sorted.entrySet()) {
            str.append(param.getValue()).append("|");
        }
        str.append(signKey);
        log.info("加密前：" + str.toString());
        String md5Str = MD5.encrypt(signKey);
        log.info("加密后：" + md5Str);
        return md5Str;
    }

    /**
     * 簽名校驗
     *
     * @param paramMap
     * @return
     */
    public static boolean isSignEquals(Map<String, Object> paramMap, String signKey) {
        String sign = (String) paramMap.get("sign");
        String md5Str = getSign(paramMap, signKey);
        if (!sign.equals(md5Str)) {
            return false;
        }
        return true;
    }

    /**
     * 獲取時間戳
     *
     * @return
     */
    public static long getTimestamp() {
        return new Date().getTime();
    }

    /**
     * 封裝同步請求
     *
     * @param paramMap
     * @param url
     * @return
     */
    public static JSONObject sendRequest(Map<String, Object> paramMap, String url) {
        String result = "";
        try {
            //封裝post參數
            StringBuilder postdata = new StringBuilder();
            for (Map.Entry<String, Object> param : paramMap.entrySet()) {
                postdata.append(param.getKey()).append("=")
                        .append(param.getValue()).append("&");
            }
            log.info(String.format("--> 發送請求：post data %1s", postdata));
            byte[] reqData = postdata.toString().getBytes("utf-8");
            byte[] respdata = HttpUtil.doPost(url, reqData);
            result = new String(respdata);
            log.info(String.format("--> 應答結果：result data %1s", result));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return JSONObject.parseObject(result);
    }
}
