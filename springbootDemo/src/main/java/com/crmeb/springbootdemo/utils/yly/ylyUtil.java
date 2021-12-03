package com.crmeb.springbootdemo.utils.yly;


import com.alibaba.fastjson.JSON;
import com.crmeb.springbootdemo.utils.yly.request.YlyPrintRequest;
import com.crmeb.springbootdemo.utils.yly.request.YlyPrintRequestGoods;
import com.crmeb.springbootdemo.utils.yly.response.YlyAccessTokenResponse;
import com.yly.java.yly_sdk.RequestMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


/** 易联云 工具类
 * @program: crmeb
 * @author: 大粽子
 * @create: 2021-11-26 15:36
 **/
public class ylyUtil {
    private static final Logger logger = LoggerFactory.getLogger(ylyUtil.class);
    private static YlyAccessTokenResponse ylyAccessTokenResponse;

    // 易联云颁发给开发者的应用ID
    private static String client_id = "client_id";
    // 易联云颁发给开发者的应用密钥
    private static String client_secret = "client_secret";
    // 易联云打印机设备唯一串码
    private static String machine_code = "machine_code";
    // 易联云打印机终端密钥
    private static String msign = "msign";

    /**
     * 初始化易联云打印机并链接
     * 添加打印机
     * 参数：* @param machine_code 易联云打印机终端号
     *      * @param msign 易联云打印机终端密钥
     *      * @param access_token 授权的token 必要参数，有效时间35天
     */
    public ylyUtil() {
        try {
            // 初始化易联云
            RequestMethod.init(client_id,client_secret);
            // 获取Access Token
            ylyAccessTokenResponse = JSON.parseObject(RequestMethod.getAccessToken(),YlyAccessTokenResponse.class);

            logger.info("获取的易联云AccessToken:"+JSON.toJSONString(ylyAccessTokenResponse));
            String addedPrint = RequestMethod.getInstance().addPrinter(machine_code, msign, ylyAccessTokenResponse.getBody().getAccess_token());
            logger.info("添加打印机结果:"+addedPrint);
        }catch (Exception e){
            logger.error("添加易联云打印机失败"+e.getMessage());
            logger.error(String.format("易联云 配置参数 client_id=%s client_secret=%s machine_code=%s msign=%s",client_id,client_secret,machine_code,msign));
        }

    }

    /**
     * 设置内置语音接口 设置了易联云也不会播放 暂时停用
     *        注意: 仅支持K4-WA、K4-GAD、K4-WGEAD、k6型号（除k6-wh外）
     *        RequestMethod.getInstance().printerSetVoice(String access_token,String machine_code,String content,String is_file,String aid,String origin_id)
     *        参数：* @param access_token 授权的token 必要参数
     *             * @param machine_code 易联云打印机终端号
     *             * @param content 播报内容 , 音量(1~9) , 声音类型(0,1,3,4) 组成json ! 示例 ["测试",9,0] 或者是在线语音链接! 语音内容请小于24kb
     *             * @param is_file true or false , 判断content是否为在线语音链接，格式MP3
     *             * @param aid 0~9 , 定义需设置的语音编号,若不提交,默认升序
     *             * @param origin_id 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母 ，且在同一个client_id下唯一。详见商户订单号
     */
    public void ylyVoice() throws Exception {
        RequestMethod.getInstance().printerSetVoice(
                ylyAccessTokenResponse.getBody().getAccess_token(),
                machine_code,"[\"CRMEB 来新单了\",9,0]","false",
                "9","ORDER xxx");
        logger.info("设置语音成功");
    }

    /**
     * 声音调节接口
     *        RequestMethod.getInstance().printSetSound(String access_token,String machine_code,String response_type,String voice)
     *        参数：* @param access_token 授权的token 必要参数
     *             * @param machine_code 易联云打印机终端号
     *             * @param response_type 蜂鸣器:buzzer,喇叭:horn
     *             * @param voice [0,1,2,3] 4种音量设置
     */
    public void ylySetSound(String responseType,String volume) throws Exception {
        RequestMethod.getInstance().printSetSound(ylyAccessTokenResponse.getBody().getAccess_token(),
                machine_code,responseType,volume);
        logger.info("设置音量成功");
    }

    /**
     * 取消所有未打印订单
     * RequestMethod.getInstance().printCancelAll(String access_token,String machine_code)
     * 参数：* @param access_token 授权的token 必要参数
     * * @param machine_code 易联云打印机终端号
     */
    public void ylyCancelAll() throws Exception {
        String cancelAllPrint = RequestMethod.getInstance().printCancelAll(ylyAccessTokenResponse.getBody().getAccess_token(), machine_code);
        logger.info("取消掉所有打印订单"+cancelAllPrint);
    }


    /**
     * 文本打印
     * 参数：* @param access_token 授权的token 必要参数
     *      * @param machine_code 易联云打印机终端号
     *      * @param content 打印内容(需要urlencode)，排版指令详见打印机指令
     *      * @param origin_id 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母 ，且在同一个client_id下唯一。详见商户订单号
     *   String printContent = "一段美好的文字";
     *   模版 http://dev.10ss.net/printtemplate###
     * @throws Exception
     */
    public void ylyPrint(YlyPrintRequest ylyPrintRequest) throws Exception {
        String printSb = "<FH><FB><center>"+ylyPrintRequest.getBusinessName()+"</center></FB></FH>" +
                "********************************<FH>" +
                "订单编号：" + ylyPrintRequest.getOrderNo()+"\n"+
                "日   期：" + ylyPrintRequest.getDate()+"\n"+
                "时   间: " + ylyPrintRequest.getTime()+"\n"+
                "姓   名：" + ylyPrintRequest.getName()+"\n"+
                "电   话：" + ylyPrintRequest.getPhone()+"\n"+
                "地   址：" + ylyPrintRequest.getAddress()+"\n"+
                "订单备注："+ ylyPrintRequest.getNote()+"</FH>\n" +
                "********************************\n" +
                "<FH>" +
                "商品名称 单价 数量 金额\n" +
                "" + ylyPrintFormatGoodsList(ylyPrintRequest.getGoods())+
                "</FH>" +
                "********************************\n" +
                "<FH>" +
                "<LR>合计：¥"+ ylyPrintRequest.getAmount()+"元，优惠：¥"+ylyPrintRequest.getDiscount()+"元</LR>" +
                "<LR>邮费：¥"+ylyPrintRequest.getPostal()+"元，抵扣：¥"+ylyPrintRequest.getDeduction()+"元</LR>" +
                "</FH>" +
                "<FH><right>实际支付：¥"+ylyPrintRequest.getPayMoney()+"元</right></FH>" +
                "<FB><FB><center>完</center></FB></FB>";
        RequestMethod.getInstance().printIndex(ylyAccessTokenResponse.getBody().getAccess_token(),machine_code,
                URLEncoder.encode(printSb, "utf-8"),"order111");
    }

    /**
     * 格式化商品详情打印格式
     * @param goods 待格式化的商品详情
     * @return 格式化后的商品详情
     */
    public String ylyPrintFormatGoodsList(List<YlyPrintRequestGoods> goods){
        StringBuilder printGoodsString = new StringBuilder();
        for (YlyPrintRequestGoods goood : goods) {
            printGoodsString.append(goood.getGoodsName());
            printGoodsString.append(" ").append(goood.getUnitPrice());
            printGoodsString.append(" ").append(goood.getNum());
            printGoodsString.append(" ").append(goood.getMoney()).append("\n");
        }
        return printGoodsString.toString();
    }

    public static void main(String[] args) throws Exception {
        ylyUtil ylyUtil = new ylyUtil();
//        ylyUtil.ylyVoice();
        // 响应类型 蜂鸣器:buzzer,喇叭:horn
        // 音量大小 【1234】
//        ylyUtil.ylySetSound(EnumYly.VOLUME_RESPONSE_TYPE_FENGMINGQI.getCode(),
//                EnumYly.VOLUME_RESPONSE_VOICE3.getCode());


        // 根据商品对象打印商品信息
        List<YlyPrintRequestGoods> goods = new ArrayList<>();
        YlyPrintRequestGoods g1 = new YlyPrintRequestGoods("红轴的机械键盘","110","1","110");
        YlyPrintRequestGoods g2 = new YlyPrintRequestGoods("新版的Iphone18 工程机 侧面带滑轮的那种","9999","1","9999");
        goods.add(g1);
        goods.add(g2);
        YlyPrintRequest ylyPrintRequest = new YlyPrintRequest();
        ylyPrintRequest.setBusinessName("CRMEB Java Order");
        ylyPrintRequest.setOrderNo("Order110");
        ylyPrintRequest.setDate("20211127");
        ylyPrintRequest.setTime("12:00:00");
        ylyPrintRequest.setName("大粽子");
        ylyPrintRequest.setPhone("18292417675");
        ylyPrintRequest.setAddress("陕西省 西安市 雁塔区 春林东街");
        ylyPrintRequest.setNote("死鬼 来的是否先打电话");

        ylyPrintRequest.setGoods(goods);
        ylyPrintRequest.setAmount("10109");
        ylyPrintRequest.setDiscount("100");
        ylyPrintRequest.setPostal("0");
        ylyPrintRequest.setDeduction("9");
        ylyPrintRequest.setPayMoney("10000");
        // 执行打印
        ylyUtil.ylyPrint(ylyPrintRequest);

        // 取消多有待打印订单 根据需求调用
//        ylyUtil.ylyCancelAll();


        // 刷新token 自行根据业务实现
        //RequestMethod.getInstance().getRefreshAccessToken(ylyAccessToken);
    }
}
