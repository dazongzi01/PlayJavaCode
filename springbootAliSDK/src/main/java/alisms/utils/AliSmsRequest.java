package alisms.utils;

import lombok.Data;

/**
 * 封装阿里云短信的自定义对象
 * @program: server
 * @author: 大粽子
 * @create: 2021-09-01 17:14
 **/
@Data
public class AliSmsRequest {
    private String Phone;       // 手机号码
    private String SignName;    // 签名 这里单独拿出来也是参考ali短信的api用于模板和签名较多的用户
    private Object SmsPramObj;  // 这里的Object 就是要根据短信模板创建自己的参数使体内，字段必须和参数对应，发送时会序列化成json参数

    public AliSmsRequest(String phone, String signName, Object smsPramObj) {
        Phone = phone;
        SignName = signName;
        SmsPramObj = smsPramObj;
    }

    public AliSmsRequest() {
    }
}
