package huang.yong.chang.base;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 用户发送信息模板
 */
public class UserMsgContent {


    public static final String RECHARGEFORADMIN = "用户：%s [%s,%s,%s] 在 %s 进行了【充值】，金额为：%s 元 ，请确认。";

    public static final String BROKEREMSG= "您的下级联系人 %s [手机号：%s]进行了充值，您得到了佣金：%s 元，积分：%s 分。";

    public static final String RECHARGEFORUSER = "管理员已对你的【充值】，充值金额： %s 元， 进行了确认，请查验。";

    public static final String CASHFORADMIN = "用户：%s [%s,%s,%s] 在 %s 进行了【提现】，金额为：%s 元 ，请确认。";

    public static final String CASHFFORUSER = "管理员已对你的【提现】申请，提现金额： %s 元 进行了确认，请查验。";
}
