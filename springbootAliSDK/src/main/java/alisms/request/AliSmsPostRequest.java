package alisms.request;

import lombok.Data;

/**
 * 阿里短信 下单成功-邮寄模板
 * 模版类型:
 * 短信通知
 * 模版名称:
 * 下单成功-邮寄
 * 模版CODE:
 * 标签:
 * 模版内容:。
 * 变量属性:
 * 申请说明:
 * 下单成功邮寄票据地址
 * @program: server
 * @author: 大粽子
 * @create: 2021-08-30 23:10
 **/
@Data
public class AliSmsPostRequest {
    private String time;
    private String name;
    private String num;

    public AliSmsPostRequest(String time, String name, String num) {
        this.time = time;
        this.name = name;
        this.num = num;
    }
}
