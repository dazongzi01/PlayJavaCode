package alisms.utils;


import com.alibaba.fastjson.JSON;
import com.aliyun.dysmsapi20170525.models.SendBatchSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendBatchSmsResponse;
import com.aliyun.teaopenapi.models.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 阿里云短信工具类
 * @program: server
 * @author: 大粽子
 * @create: 2021-08-30 23:17
 **/
public class AliSmsUtils {

    //日志
    private static final Logger logger = LoggerFactory.getLogger(AliSmsUtils.class);
    // 用户权限信息
    private static String AK="LTAI5t7NySkPhTZCkhfFCZA2";
    private static String AS="igeQjnrQZb8l1TvamwhF3sUDopihw7";

    // 短信模板和签名
    public static String ALiSmsTempSign = "乃票";
    public static String AliSmsTempCodeGetSelf = "SMS_223195090"; // 用户自取
    public static String AliSmsTempCodeSendPost = "SMS_223195091"; // 快递邮寄


    /**
     *  根据短信模版Code 发送短信
     * @param smsTempCode       模板Code
     * @param aliSmsRequests    自定义组装对象 这个集合对象中的pramObj就是你的参数对象，和code是对应的，自行根据ali云申请的模板自己创建
     * @throws Exception        异常信息
     */
    public static void SendAliSms(String smsTempCode,List<AliSmsRequest> aliSmsRequests) {
        if(null == aliSmsRequests) return;
        if (aliSmsRequests.size() == 0) return;
        // 根据业务参数拆分成API可用数据参数
        List<String> phones = new ArrayList<>();
        List<String> signNames = new ArrayList<>();
        List<Object> pramObj = new ArrayList<>();
        for (AliSmsRequest aliSmsRequest : aliSmsRequests) {
            phones.add(aliSmsRequest.getPhone());
            signNames.add(aliSmsRequest.getSignName());
            pramObj.add(aliSmsRequest.getSmsPramObj());
        }
        SendBatchSmsRequest sendBatchSmsRequest = new SendBatchSmsRequest()
                .setPhoneNumberJson(JSON.toJSONString(phones))
                .setSignNameJson(JSON.toJSONString(signNames))
                .setTemplateCode(smsTempCode)
                .setTemplateParamJson(JSON.toJSONString(pramObj));
        com.aliyun.dysmsapi20170525.Client client = null;
        try {
            client = createClient(AK, AS);
            SendBatchSmsResponse sendBatchSmsResponse = client.sendBatchSms(sendBatchSmsRequest);
            logger.info("Send aliSms success",sendBatchSmsResponse);
        } catch (Exception exception) {
            logger.error("Send Ali Sms Error:",exception.getMessage());
        }

    }


    /**
     * 使用AK&SK初始化账号Client
     * @param accessKeyId ak
     * @param accessKeySecret as
     * @return Client
     * @throws Exception e
     */
    private static com.aliyun.dysmsapi20170525.Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
        Config config = new Config()
                // 您的AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 您的AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        // 访问的域名
        config.endpoint = "dysmsapi.aliyuncs.com";
        return new com.aliyun.dysmsapi20170525.Client(config);
    }

}
