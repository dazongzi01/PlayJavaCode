package alisms.request;

import lombok.Data;

/**
 * 模版类型:
 * 短信通知
 * 模版名称:
 * 下单成功-现场取票
 * 模版CODE:
 * 你申请的模版CODE
 * 标签:
 * 模版内容:
 * 你申请的模版内容
 * 变量属性:
 * 变量对应的数据
 * 申请说明:
 * 下单后通知用户
 * @program: server
 * @author: 大粽子
 * @create: 2021-08-30 23:14
 **/
@Data
public class AliSmsGetSelfRequest {
    private String name;
    private String time;
    private String num;
    private String hour;
    private String address;
    private String phone;

    public AliSmsGetSelfRequest(String name, String time, String num, String hour, String address, String phone) {
        this.name = name;
        this.time = time;
        this.num = num;
        this.hour = hour;
        this.address = address;
        this.phone = phone;
    }
}
