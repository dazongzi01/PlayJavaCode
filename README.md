# PlayJavaCode
一个综合java工具类代码和示例的仓库

gitHub https://github.com/stivepeimm/PlayJavaCode
gitee https://gitee.com/stivepeim/PlayJavaCode
# springbootDemo 一个基础的Springboot项目示例


# springbootAliSDK 一个准备综合ali常用SDK的项目示例
# 阿里短信使用示例
## 使用方法
申请对应的短信模版
如示例中alisms.request对象累死，根据申请的模版变量创建自己的request对象
如：crmeb 您有${order}的待支付订单，请在${time}时间内支付，过期将自动关闭
request内容应该为
~~~
private String order；
private String time;
~~~
## 实际指定的发送短信方法,这里默认使用批量发送的方式，一条多条都能发。
~~~
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
~~~
## 怎么调用呢？
~~~
 // 组装邮寄短信参数
AliSmsPostRequest aliSmsPostRequest =
        new AliSmsPostRequest(time,
                orderInfoVo.getInfo().getProductInfo().getStoreName(),orderInfoVo.getInfo().getCartNum()+"");
aliSmsRequest.setPhone(storeOrder.getUserPhone());
aliSmsRequest.setSignName(AliSmsUtils.ALiSmsTempSign);
aliSmsRequest.setSmsPramObj(aliSmsPostRequest);
pramList.add(aliSmsRequest);
logger.info("即将发短信的参数:"+JSON.toJSONString(pramList));
AliSmsUtils.SendAliSms(AliSmsUtils.AliSmsTempCodeSendPost,pramList);
~~~

## 怎么样，在业务中使用简单吧！


