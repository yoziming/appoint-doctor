package yoziming.ad.hospitalmanage.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import yoziming.ad.hospitalmanage.mapper.HospitalSetMapper;
import yoziming.ad.hospitalmanage.mapper.ScheduleMapper;
import yoziming.ad.hospitalmanage.model.HospitalSet;
import yoziming.ad.hospitalmanage.model.Schedule;
import yoziming.ad.hospitalmanage.service.ApiService;
import yoziming.ad.hospitalmanage.utils.BeanUtils;
import yoziming.ad.hospitalmanage.utils.HospitalException;
import yoziming.ad.hospitalmanage.utils.HttpRequestHelper;
import yoziming.ad.hospitalmanage.utils.MD5;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@Service
@Slf4j
public class ApiServiceImpl implements ApiService {

    @Autowired
    private ScheduleMapper scheduleMapper;

    @Autowired
    private HospitalSetMapper hospitalSetMapper;

    @Autowired
    private ApiService apiService;

    @Value("classpath:hospital.json")
    private Resource hospitalResource;

    public static String getImgStr(String imgFile) {
        byte[] data = getImageBytes(imgFile);
        return new String(Base64.encodeBase64(data));
    }

    public static byte[] getImageBytes(String imgUrl) {
        URL url = null;
        InputStream is = null;
        ByteArrayOutputStream outStream = null;
        HttpURLConnection httpUrl = null;
        try {
            url = new URL(imgUrl);
            httpUrl = (HttpURLConnection) url.openConnection();
            httpUrl.connect();
            httpUrl.getInputStream();
            is = httpUrl.getInputStream();
            outStream = new ByteArrayOutputStream();
            //創建一個Buffer字符串
            byte[] buffer = new byte[1024];
            //每次讀取的字符串長度，如果為-1，代表全部讀取完畢
            int len = 0;
            //使用一個輸入流從buffer里把數據讀取出來
            while ((len = is.read(buffer)) != -1) {
                //用輸出流往buffer里寫入數據，中間參數代表從哪個位置開始讀，len代表讀取的長度
                outStream.write(buffer, 0, len);
            }
            // 對字節數組Base64編碼
            return outStream.toByteArray();
        } catch (ConnectException e) {
            log.error("獲取圖片時連接異常，url:{}", imgUrl, e);
        } catch (MalformedURLException e) {
            log.error("url出現異常，url:{}", imgUrl, e);
        } catch (IOException e) {
            log.error("讀取圖片時發生異常，url:{}", imgUrl, e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outStream != null) {
                try {
                    outStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (httpUrl != null) {
                httpUrl.disconnect();
            }
        }
        return null;
    }

    @Override
    public String getHoscode() {
        HospitalSet hospitalSet = hospitalSetMapper.selectById(1);
        return hospitalSet.getHoscode();
    }

    @Override
    public String getSignKey() {
        HospitalSet hospitalSet = hospitalSetMapper.selectById(1);
        return hospitalSet.getSignKey();
    }

    private String getApiUrl() {
        HospitalSet hospitalSet = hospitalSetMapper.selectById(1);
        return hospitalSet.getApiUrl();
    }

    @Override
    public JSONObject getHospital() {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("hoscode", this.getHoscode());
        paramMap.put("timestamp", HttpRequestHelper.getTimestamp());
        paramMap.put("sign", MD5.encrypt(this.getSignKey()));
        JSONObject respone = HttpRequestHelper.sendRequest(paramMap, this.getApiUrl() + "/api/hosp/hospital/show");
        System.out.println(respone.toJSONString());
        if (null != respone && 200 == respone.getIntValue("code")) {
            JSONObject jsonObject = respone.getJSONObject("data");
            return jsonObject;
        }
        return null;
    }

    @Override
    public boolean saveHospital(String data) {
        JSONObject jsonObject = JSONObject.parseObject(data);

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("hoscode", this.getHoscode());
        paramMap.put("hosname", jsonObject.getString("hosname"));
        paramMap.put("hostype", jsonObject.getString("hostype"));
        paramMap.put("provinceCode", jsonObject.getString("provinceCode"));
        paramMap.put("cityCode", jsonObject.getString("cityCode"));
        paramMap.put("districtCode", jsonObject.getString("districtCode"));
        paramMap.put("address", jsonObject.getString("address"));
        paramMap.put("intro", jsonObject.getString("intro"));
        paramMap.put("route", jsonObject.getString("route"));
        //圖片
        paramMap.put("logoData", jsonObject.getString("logoData"));

        JSONObject bookingRule = jsonObject.getJSONObject("bookingRule");
        paramMap.put("bookingRule", bookingRule.toJSONString());

        paramMap.put("timestamp", HttpRequestHelper.getTimestamp());
        paramMap.put("sign", MD5.encrypt(this.getSignKey()));

        JSONObject respone =
                HttpRequestHelper.sendRequest(paramMap, this.getApiUrl() + "/api/hosp/saveHospital");
        System.out.println(respone.toJSONString());

        if (null != respone && 200 == respone.getIntValue("code")) {
            return true;
        } else {
            throw new HospitalException(respone.getString("message"), 201);
        }
    }

    @Override
    public Map<String, Object> findDepartment(int pageNum, int pageSize) {
        Map<String, Object> result = new HashMap();

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("hoscode", this.getHoscode());
        //paramMap.put("depcode",depcode);
        paramMap.put("page", pageNum);
        paramMap.put("limit", pageSize);
        paramMap.put("timestamp", HttpRequestHelper.getTimestamp());
        paramMap.put("sign", HttpRequestHelper.getSign(paramMap, this.getSignKey()));
        JSONObject respone = HttpRequestHelper.sendRequest(paramMap, this.getApiUrl() + "/api/hosp/department/list");
        if (null != respone && 200 == respone.getIntValue("code")) {
            JSONObject jsonObject = respone.getJSONObject("data");

            result.put("total", jsonObject.getLong("totalElements"));
            result.put("pageNum", pageNum);
            result.put("list", jsonObject.getJSONArray("content"));
        } else {
            throw new HospitalException(respone.getString("message"), 201);
        }
        return result;
    }

    @Override
    public boolean saveDepartment(String data) {
        JSONArray jsonArray = new JSONArray();
        if (!data.startsWith("[")) {
            JSONObject jsonObject = JSONObject.parseObject(data);
            jsonArray.add(jsonObject);
        } else {
            jsonArray = JSONArray.parseArray(data);
        }

        for (int i = 0, len = jsonArray.size(); i < len; i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("hoscode", this.getHoscode());
            paramMap.put("depcode", jsonObject.getString("depcode"));
            paramMap.put("depname", jsonObject.getString("depname"));
            paramMap.put("intro", jsonObject.getString("intro"));
            paramMap.put("bigcode", jsonObject.getString("bigcode"));
            paramMap.put("bigname", jsonObject.getString("bigname"));

            paramMap.put("timestamp", HttpRequestHelper.getTimestamp());
            paramMap.put("sign", MD5.encrypt(this.getSignKey()));
            JSONObject respone = HttpRequestHelper.sendRequest(paramMap, this.getApiUrl() + "/api/hosp/saveDepartment");
            System.out.println(respone.toJSONString());

            if (null == respone || 200 != respone.getIntValue("code")) {
                throw new HospitalException(respone.getString("message"), 201);
            }
        }
        return true;
    }

    @Override
    public boolean removeDepartment(String depcode) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("hoscode", this.getHoscode());
        paramMap.put("depcode", depcode);
        paramMap.put("timestamp", HttpRequestHelper.getTimestamp());
        paramMap.put("sign", HttpRequestHelper.getSign(paramMap, this.getSignKey()));
        JSONObject respone = HttpRequestHelper.sendRequest(paramMap, this.getApiUrl() + "/api/hosp/department/remove");
        System.out.println(respone.toJSONString());
        if (null != respone && 200 == respone.getIntValue("code")) {
            return true;
        } else {
            throw new HospitalException(respone.getString("message"), 201);
        }
    }

    @Override
    public Map<String, Object> findSchedule(int pageNum, int pageSize) {
        Map<String, Object> result = new HashMap();
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("hoscode", this.getHoscode());
        //paramMap.put("depcode",depcode);
        paramMap.put("page", pageNum);
        paramMap.put("limit", pageSize);
        paramMap.put("timestamp", HttpRequestHelper.getTimestamp());
        paramMap.put("sign", HttpRequestHelper.getSign(paramMap, this.getSignKey()));
        JSONObject respone = HttpRequestHelper.sendRequest(paramMap, this.getApiUrl() + "/api/hosp/schedule/list");
        System.out.println(respone.toJSONString());
        if (null != respone && 200 == respone.getIntValue("code")) {
            JSONObject jsonObject = respone.getJSONObject("data");

            result.put("total", jsonObject.getLong("totalElements"));
            result.put("pageNum", pageNum);
            result.put("list", jsonObject.getJSONArray("content"));
        } else {
            throw new HospitalException(respone.getString("message"), 201);
        }
        return result;
    }

    @Override
    public boolean saveSchedule(String data) {
        JSONArray jsonArray = new JSONArray();
        if (!data.startsWith("[")) {
            JSONObject jsonObject = JSONObject.parseObject(data);
            jsonArray.add(jsonObject);
        } else {
            jsonArray = JSONArray.parseArray(data);
        }

        for (int i = 0, len = jsonArray.size(); i < len; i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            Long id = jsonObject.getLong("hosScheduleId");
            Schedule schedule = new Schedule();
            schedule.setId(id);
            schedule.setHoscode(this.getHoscode());
            schedule.setDepcode(jsonObject.getString("depcode"));
            schedule.setTitle(jsonObject.getString("title"));
            schedule.setDocname(jsonObject.getString("docname"));
            schedule.setSkill(jsonObject.getString("skill"));
            schedule.setWorkDate(jsonObject.getString("workDate"));
            schedule.setWorkTime(jsonObject.getInteger("workTime"));
            schedule.setReservedNumber(jsonObject.getInteger("reservedNumber"));
            schedule.setAvailableNumber(jsonObject.getInteger("availableNumber"));
            schedule.setAmount(jsonObject.getString("amount"));
            schedule.setStatus(1);

            Schedule targetSchedule = scheduleMapper.selectById(id);
            if (null != targetSchedule) {
                //值copy不為null的值，該方法為自定義方法
                BeanUtils.copyBean(schedule, targetSchedule, Schedule.class);
                scheduleMapper.updateById(targetSchedule);
            } else {
                scheduleMapper.insert(schedule);
            }

            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("hoscode", schedule.getHoscode());
            paramMap.put("depcode", schedule.getDepcode());
            paramMap.put("title", schedule.getTitle());
            paramMap.put("docname", schedule.getDocname());
            paramMap.put("skill", schedule.getSkill());
            paramMap.put("workDate", schedule.getWorkDate());
            paramMap.put("workTime", schedule.getWorkTime());
            paramMap.put("reservedNumber", schedule.getReservedNumber());
            paramMap.put("availableNumber", schedule.getAvailableNumber());
            paramMap.put("amount", schedule.getAmount());
            paramMap.put("status", schedule.getStatus());
            paramMap.put("hosScheduleId", schedule.getId());
            paramMap.put("timestamp", HttpRequestHelper.getTimestamp());
            paramMap.put("sign", HttpRequestHelper.getSign(paramMap, this.getSignKey()));

            JSONObject respone = HttpRequestHelper.sendRequest(paramMap, this.getApiUrl() + "/api/hosp/saveSchedule");
            System.out.println(respone.toJSONString());
            if (null == respone || 200 != respone.getIntValue("code")) {
                throw new HospitalException(respone.getString("message"), 201);
            }
        }
        return false;
    }

    @Override
    public boolean removeSchedule(String hosScheduleId) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("hoscode", this.getHoscode());
        paramMap.put("hosScheduleId", hosScheduleId);
        paramMap.put("timestamp", HttpRequestHelper.getTimestamp());
        paramMap.put("sign", HttpRequestHelper.getSign(paramMap, this.getSignKey()));
        JSONObject respone = HttpRequestHelper.sendRequest(paramMap, this.getApiUrl() + "/api/hosp/schedule/remove");
        System.out.println(respone.toJSONString());
        if (null != respone && 200 == respone.getIntValue("code")) {
            return true;
        } else {
            throw new HospitalException(respone.getString("message"), 201);
        }
    }

    @Override
    public void saveBatchHospital() throws IOException {
        File file = hospitalResource.getFile();
        String jsonData = this.jsonRead(file);
        JSONArray jsonArray = JSONArray.parseArray(jsonData);
        for (int i = 1, len = jsonArray.size(); i < len; i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("hoscode", "1000_" + i);
            paramMap.put("hosname", jsonObject.getString("hosname"));
            paramMap.put("hostype", i % 3 == 0 ? 1 : i % 3 == 1 ? 2 : 4);
            paramMap.put("provinceCode", "110000");
            paramMap.put("cityCode", "110100");
            if (i % 3 == 0) {
                paramMap.put("districtCode", "110101");
            }
            if (i % 3 == 1) {
                paramMap.put("districtCode", "110102");
            }
            if (i % 3 == 2) {
                paramMap.put("districtCode", "110106");
            }

            paramMap.put("address", "");
            String intro =
                    "北京協和醫院是集醫療、教學、科研於一體的大型三級甲等綜合醫院，是國家衛生計生委指定的全國疑難重症診治指導中心，也是最早承擔高幹保健和外賓醫療任務的醫院之一，以學科齊全、技術力量雄厚、特色專科突出、多學科綜合優勢強大享譽海內外。在2010、2011、2012、2013、2014年復旦大學醫院管理研究所公布的“中國最佳醫院排行榜”中連續五年名列榜首。\n" +
                            "\n" +
                            "醫院建成於1921年，由洛克菲勒基金會創辦。建院之初，就志在“建成亞洲最好的醫學中心”。90" +
                            "餘年來，醫院形成了“嚴謹、求精、勤奮、奉獻”的協和精神和兼容並蓄的特色文化風格，創立了“三基”、“三嚴”的現代醫學教育理念，形成了以“教授、病案、圖書館”著稱的協和“三寶”，培養造就了張孝騫、林巧稚等一代醫學大師和多位中國現代醫學的領軍人物，並向全國輸送了大批的醫學管理人才，創建了當今知名的10餘家大型綜合及專科醫院。2011年在總結90年發展經驗的基礎上，創新性提出了“待病人如親人，提高病人滿意度；待同事如家人，提高員工幸福感”新辦院理念。\n" +
                            "\n" +
                            "目前，醫院共有2個院區、總建築面積53萬平方米，在職職工4000餘名、兩院院士5人、臨床和醫技科室53個、國家級重點學科20個、國家臨床重點專科29個、博士點16個、碩士點29" +
                            "個、國家級繼續醫學教育基地6個、二級學科住院醫師培養基地18個、三級學科專科醫師培養基地15個。開放住院床位2000餘張，單日最高門診量約1" +
                            ".5萬人次、年出院病人約8" +
                            "萬餘人次。被評為“全國文明單位”、“全國創先爭優先進基層黨組織”、“全國衛生系統先進集體”、“首都衛生系統文明單位”、“最受歡迎三甲醫院”，榮獲全國五一勞動獎章。同時，醫院還承擔著支援老少邊窮地區、國家重要活動和突發事件主力醫療隊的重任，在2008年北京奧運工作中榮獲“特別貢獻獎”。\n" +
                            "\n" +
                            "90多年來，協和人以執着的醫志、高尚的醫德、精湛的醫術和嚴謹的學風書寫了輝煌的歷史，今天的協和人正為打造“國際知名、國內一流”醫院的目標而繼續努力。";
            paramMap.put("intro", intro);
            String route = "\n" +
                    "東院區乘車路線：106、108、110、111、116、684、685路到東單路口北；41、104快、814路到東單路口南；1、52、802路到東單路口西；20、25、37、39" +
                    "路到東單路口東；103、104、420、803路到新東安市場；地鐵1、5號線到東單。\n" +
                    "西院區乘車路線：68路到辟才衚衕東口；更多乘車路線詳見須知。\n" +
                    "\n";
            paramMap.put("route", route);
            //logo
            //paramMap.put("logoData", this.getImgStr(jsonObject.getString("picture")));

            Map<String, Object> bookingRuleMap = new HashMap<>();
            bookingRuleMap.put("cycle", 10);
            bookingRuleMap.put("releaseTime", jsonObject.getString("releaseTime"));
            bookingRuleMap.put("stopTime", "12:30");
            bookingRuleMap.put("quitDay", -1);
            bookingRuleMap.put("quitTime", "15:30");
            bookingRuleMap.put("rule", "[\"西院區預約號取號地點：西院區門診樓一層大廳挂號窗口取號\"]");
            paramMap.put("bookingRule", JSONObject.toJSONString(bookingRuleMap));

            paramMap.put("timestamp", HttpRequestHelper.getTimestamp());
            paramMap.put("sign", HttpRequestHelper.getSign(paramMap, apiService.getSignKey()));

            JSONObject respone = HttpRequestHelper.sendRequest(paramMap, "http://localhost/api/hosp/saveHospital");
            System.out.println(respone.toJSONString());
            if (null == respone || 200 != respone.getIntValue("code")) {
                throw new HospitalException(respone.getString("message"), 201);
            }
        }
    }

    private String jsonRead(File file) {
        Scanner scanner = null;
        StringBuilder buffer = new StringBuilder();
        try {
            scanner = new Scanner(file, "utf-8");
            while (scanner.hasNextLine()) {
                buffer.append(scanner.nextLine());
            }
        } catch (Exception e) {

        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        return buffer.toString();
    }
}
