/**
 * 
 */
package cn.aposoft.ecommerce.payment.wechat;

import java.util.List;

/**
 * {@code OrderQueryResponse} 是订单查询的响应报文的解析后数据类
 * 
 * <pre>
 	<xml>
	   <return_code><![CDATA[SUCCESS]]></return_code>
	   <return_msg><![CDATA[OK]]></return_msg>
	   <appid><![CDATA[wx2421b1c4370ec43b]]></appid>
	   <mch_id><![CDATA[10000100]]></mch_id>
	   <device_info><![CDATA[1000]]></device_info>
	   <nonce_str><![CDATA[TN55wO9Pba5yENl8]]></nonce_str>
	   <sign><![CDATA[BDF0099C15FF7BC6B1585FBB110AB635]]></sign>
	   <result_code><![CDATA[SUCCESS]]></result_code>
	   <openid><![CDATA[oUpF8uN95-Ptaags6E_roPHg7AG0]]></openid>
	   <is_subscribe><![CDATA[Y]]></is_subscribe>
	   <trade_type><![CDATA[MICROPAY]]></trade_type>
	   <bank_type><![CDATA[CCB_DEBIT]]></bank_type>
	   <total_fee>1</total_fee>
	   <fee_type><![CDATA[CNY]]></fee_type>
	   <transaction_id><![CDATA[1008450740201411110005820873]]></transaction_id>
	   <out_trade_no><![CDATA[1415757673]]></out_trade_no>
	   <attach><![CDATA[订单额外描述]]></attach>
	   <time_end><![CDATA[20141111170043]]></time_end>
	   <trade_state><![CDATA[SUCCESS]]></trade_state>
	</xml>
 * </pre>
 * 
 * <pre>
 * 	 * 错误代码:
	 * <ul>
	 * <li>名称 描述 原因 解决方案</li>
	 * <li>ORDERNOTEXIST 此交易订单号不存在 查询系统中不存在此交易订单号
	 * 该API只能查提交支付交易返回成功的订单，请商户检查需要查询的订单号是否正确</li>
	 * <li>SYSTEMERROR 系统错误 后台系统返回错误 系统异常，请再调用发起查询</li>
	 * </ul>
 * </pre>
 * 
 * @author Yujinshui
 *
 */
public class OrderQueryResponse extends ResponseBase {
	/**
	 * 用户标识 openid 是 String(128) wxd930ea5d5a258f4f
	 * <p>
	 * 用户在商户appid下的唯一标识
	 */
	private String openid;

	/**
	 * 是否关注公众账号 String(1) Y/N 用户是否关注公众账号，Y-关注，N-未关注，仅在公众账号类型支付有效
	 */
	private String is_subscribe;

	/**
	 * 交易类型 :String(16) JSAPI
	 * <p>
	 * 调用接口提交的交易类型，取值如下：JSAPI，NATIVE，APP，MICROPAY，详细说明见参数规定
	 * https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=4_2
	 */
	private String trade_type;

	/**
	 * 交易状态 trade_state 是 String(32) SUCCESS
	 * <ul>
	 * <li>SUCCESS—支付成功</li>
	 * <li>REFUND—转入退款</li>
	 * <li>NOTPAY—未支付</li>
	 * <li>CLOSED—已关闭</li>
	 * <li>REVOKED—已撤销（刷卡支付）</li>
	 * <li>USERPAYING--用户支付中</li>
	 * <li>PAYERROR--支付失败(其他原因，如银行返回失败)</li>
	 * </ul>
	 */
	private String trade_state;

	/**
	 * 付款银行 bank_type 是 String(16) CMC 银行类型，采用字符串类型的银行标识
	 */
	private String bank_type;

	/**
	 * 总金额 total_fee 是 Int 100 订单总金额，单位为分
	 */
	private Integer total_fee;

	/**
	 * 货币种类 否 String(8) CNY 货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
	 * https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=4_2
	 */
	private String fee_type;

	/**
	 * 现金支付金额 cash_fee 是 Int 100 现金支付金额订单现金支付金额，详见支付金额
	 * https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=4_2
	 */
	private Integer cash_fee;
	/**
	 * 现金支付货币类型 cash_fee_type 否 String(16) CNY 货币类型，符合ISO
	 * 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
	 * https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=4_2
	 */
	private String cash_fee_type;
	/**
	 * “代金券或立减优惠”金额<=订单总金额，订单总金额-“代金券或立减优惠”金额=现金支付金额，详见支付金额
	 * https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=4_2
	 */
	private Integer coupon_fee;
	/**
	 * 代金券或立减优惠使用数量 coupon_count 否 Int 1 代金券或立减优惠使用数量
	 */
	private Integer coupon_count;

	/**
	 * 微信订单号 [是] String(28)
	 */
	private String transaction_id;
	/**
	 * 商户订单号 [是] String(32)
	 */
	private String out_trade_no;

	/**
	 * 附加数据 attach 否 String(128) 深圳分店 附加数据，原样返回
	 */
	private String attach;

	/**
	 * 支付完成时间 time_end 是 String(14) 20141030133525
	 * 订单支付时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。其他详见时间规则
	 * https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=4_2
	 */
	private String time_end;

	/**
	 * 交易状态描述 trade_state_desc 是 String(256) 支付失败，请重新下单支付 对当前查询订单状态的描述和下一步操作的指引
	 */
	private String trade_state_desc;

	/**
	 * 用于封装查询结果中涉及到优惠券$n的三个字段: coupon_batch_id_$n , coupon_id_$n , coupon_fee_$n
	 * 
	 */
	private List<Coupon> couponItems;

	/**
	 * 是否关注公众账号 String(1) Y/N 用户是否关注公众账号，Y-关注，N-未关注，仅在公众账号类型支付有效
	 *
	 * @return
	 * @author Yujinshui
	 * @time 2015年12月11日 上午11:38:20
	 */
	public String getIs_subscribe() {
		return is_subscribe;
	}

	/**
	 * 是否关注公众账号 String(1) Y/N 用户是否关注公众账号，Y-关注，N-未关注，仅在公众账号类型支付有效
	 *
	 * @param is_subscribe
	 * @author Yujinshui
	 * @time 2015年12月11日 上午11:41:30
	 */
	public void setIs_subscribe(String is_subscribe) {
		this.is_subscribe = is_subscribe;
	}

	/**
	 * 交易类型 :String(16) JSAPI
	 * <p>
	 * 调用接口提交的交易类型，取值如下：JSAPI，NATIVE，APP，MICROPAY，详细说明见参数规定
	 * https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=4_2
	 *
	 * @return
	 * @author Yujinshui
	 * @time 2015年12月11日 上午11:38:20
	 */
	public String getTrade_type() {
		return trade_type;
	}

	/**
	 * 交易类型 :String(16) JSAPI
	 * <p>
	 * 调用接口提交的交易类型，取值如下：JSAPI，NATIVE，APP，MICROPAY，详细说明见参数规定
	 * https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=4_2
	 *
	 * @param trade_type
	 * @author Yujinshui
	 * @time 2015年12月11日 上午11:41:47
	 */
	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}

	/**
	 * 交易状态 trade_state 是 String(32) SUCCESS
	 * <ul>
	 * <li>SUCCESS—支付成功</li>
	 * <li>REFUND—转入退款</li>
	 * <li>NOTPAY—未支付</li>
	 * <li>CLOSED—已关闭</li>
	 * <li>REVOKED—已撤销（刷卡支付）</li>
	 * <li>USERPAYING--用户支付中</li>
	 * <li>PAYERROR--支付失败(其他原因，如银行返回失败)</li>
	 * </ul>
	 * 
	 * @return
	 * @author Yujinshui
	 * @time 2015年12月11日 上午11:38:20
	 */
	public String getTrade_state() {
		return trade_state;
	}

	/**
	 * 交易状态 trade_state 是 String(32) SUCCESS
	 * <ul>
	 * <li>SUCCESS—支付成功</li>
	 * <li>REFUND—转入退款</li>
	 * <li>NOTPAY—未支付</li>
	 * <li>CLOSED—已关闭</li>
	 * <li>REVOKED—已撤销（刷卡支付）</li>
	 * <li>USERPAYING--用户支付中</li>
	 * <li>PAYERROR--支付失败(其他原因，如银行返回失败)</li>
	 * </ul>
	 * 
	 * @param trade_state
	 * @author Yujinshui
	 * @time 2015年12月11日 上午11:42:02
	 */
	public void setTrade_state(String trade_state) {
		this.trade_state = trade_state;
	}

	/**
	 * 付款银行 bank_type 是 String(16) CMC 银行类型，采用字符串类型的银行标识
	 *
	 * @return
	 * @author Yujinshui
	 * @time 2015年12月11日 上午11:38:20
	 */
	public String getBank_type() {
		return bank_type;
	}

	/**
	 * 付款银行 bank_type 是 String(16) CMC 银行类型，采用字符串类型的银行标识
	 *
	 * @param bank_type
	 * @author Yujinshui
	 * @time 2015年12月11日 上午11:42:14
	 */
	public void setBank_type(String bank_type) {
		this.bank_type = bank_type;
	}

	/**
	 * 总金额 total_fee 是 Int 100 订单总金额，单位为分
	 *
	 * @return
	 * @author Yujinshui
	 * @time 2015年12月11日 上午11:42:30
	 */
	public Integer getTotal_fee() {
		return total_fee;
	}

	/**
	 * 总金额 total_fee 是 Int 100 订单总金额，单位为分
	 *
	 * @param total_fee
	 * @author Yujinshui
	 * @time 2015年12月11日 上午11:42:34
	 */
	public void setTotal_fee(Integer total_fee) {
		this.total_fee = total_fee;
	}

	/**
	 * 货币种类 否 String(8) CNY 货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
	 * https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=4_2
	 *
	 * @return
	 * @author Yujinshui
	 * @time 2015年12月11日 上午11:38:20
	 */
	public String getFee_type() {
		return fee_type;
	}

	/**
	 * 货币种类 否 String(8) CNY 货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
	 * https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=4_2
	 *
	 * @param fee_type
	 * @author Yujinshui
	 * @time 2015年12月11日 上午11:42:46
	 */
	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}

	/**
	 * 现金支付金额 cash_fee 是 Int 100 现金支付金额订单现金支付金额，详见支付金额
	 * https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=4_2
	 *
	 * @return
	 * @author Yujinshui
	 * @time 2015年12月11日 上午11:42:54
	 */
	public Integer getCash_fee() {
		return cash_fee;
	}

	/**
	 * 现金支付金额 cash_fee 是 Int 100 现金支付金额订单现金支付金额，详见支付金额
	 * https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=4_2
	 *
	 * @param cash_fee
	 * @author Yujinshui
	 * @time 2015年12月11日 上午11:42:58
	 */
	public void setCash_fee(Integer cash_fee) {
		this.cash_fee = cash_fee;
	}

	/**
	 * 现金支付货币类型 cash_fee_type 否 String(16) CNY 货币类型，符合ISO
	 * 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
	 * https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=4_2
	 *
	 * @return
	 * @author Yujinshui
	 * @time 2015年12月11日 上午11:38:20
	 */
	public String getCash_fee_type() {
		return cash_fee_type;
	}

	/**
	 * 现金支付货币类型 cash_fee_type 否 String(16) CNY 货币类型，符合ISO
	 * 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
	 * https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=4_2
	 *
	 * @param cash_fee_type
	 * @author Yujinshui
	 * @time 2015年12月11日 上午11:43:11
	 */
	public void setCash_fee_type(String cash_fee_type) {
		this.cash_fee_type = cash_fee_type;
	}

	/**
	 * “代金券或立减优惠”金额<=订单总金额，订单总金额-“代金券或立减优惠”金额=现金支付金额，详见支付金额
	 * https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=4_2
	 *
	 * @return
	 * @author Yujinshui
	 * @time 2015年12月11日 上午11:43:24
	 */
	public Integer getCoupon_fee() {
		return coupon_fee;
	}

	/**
	 * “代金券或立减优惠”金额<=订单总金额，订单总金额-“代金券或立减优惠”金额=现金支付金额，详见支付金额
	 * https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=4_2
	 *
	 * @param coupon_fee
	 * @author Yujinshui
	 * @time 2015年12月11日 上午11:43:27
	 */
	public void setCoupon_fee(Integer coupon_fee) {
		this.coupon_fee = coupon_fee;
	}

	/**
	 * 代金券或立减优惠使用数量 coupon_count 否 Int 1 代金券或立减优惠使用数量
	 *
	 * @return
	 * @author Yujinshui
	 * @time 2015年12月11日 上午11:43:36
	 */
	public Integer getCoupon_count() {
		return coupon_count;
	}

	/**
	 * 代金券或立减优惠使用数量 coupon_count 否 Int 1 代金券或立减优惠使用数量
	 *
	 * @param coupon_count
	 * @author Yujinshui
	 * @time 2015年12月11日 上午11:43:39
	 */
	public void setCoupon_count(Integer coupon_count) {
		this.coupon_count = coupon_count;
	}

	/**
	 * 微信订单号 [是] String(28)
	 * 
	 * @return
	 * @author Yujinshui
	 * @time 2015年12月11日 上午11:38:20
	 */
	public String getTransaction_id() {
		return transaction_id;
	}

	/**
	 * 微信订单号 [是] String(28)
	 * 
	 * @param transaction_id
	 * @author Yujinshui
	 * @time 2015年12月11日 上午11:43:49
	 */
	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	/**
	 * 商户订单号 [是] String(32)
	 * 
	 * @return
	 * @author Yujinshui
	 * @time 2015年12月11日 上午11:38:20
	 */
	public String getOut_trade_no() {
		return out_trade_no;
	}

	/**
	 * 商户订单号 [是] String(32)
	 * 
	 * @param out_trade_no
	 * @author Yujinshui
	 * @time 2015年12月11日 上午11:44:08
	 */
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	/**
	 * 附加数据 attach 否 String(128) 深圳分店 附加数据，原样返回
	 *
	 * @return
	 * @author Yujinshui
	 * @time 2015年12月11日 上午11:38:20
	 */
	public String getAttach() {
		return attach;
	}

	/**
	 * 附加数据 attach 否 String(128) 深圳分店 附加数据，原样返回
	 *
	 * @param attach
	 * @author Yujinshui
	 * @time 2015年12月11日 上午11:44:18
	 */
	public void setAttach(String attach) {
		this.attach = attach;
	}

	/**
	 * 支付完成时间 time_end 是 String(14) 20141030133525
	 * 订单支付时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。其他详见时间规则
	 * https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=4_2
	 *
	 * @return
	 * @author Yujinshui
	 * @time 2015年12月11日 上午11:38:20
	 */
	public String getTime_end() {
		return time_end;
	}

	/**
	 * 支付完成时间 time_end 是 String(14) 20141030133525
	 * 订单支付时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。其他详见时间规则
	 * https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=4_2
	 *
	 * @param time_end
	 * @author Yujinshui
	 * @time 2015年12月11日 上午11:44:32
	 */
	public void setTime_end(String time_end) {
		this.time_end = time_end;
	}

	/**
	 * 交易状态描述 trade_state_desc 是 String(256) 支付失败，请重新下单支付 对当前查询订单状态的描述和下一步操作的指引
	 *
	 * @return
	 * @author Yujinshui
	 * @time 2015年12月11日 上午11:38:20
	 */
	public String getTrade_state_desc() {
		return trade_state_desc;
	}

	/**
	 * 交易状态描述 trade_state_desc 是 String(256) 支付失败，请重新下单支付 对当前查询订单状态的描述和下一步操作的指引
	 *
	 * @param trade_state_desc
	 * @author Yujinshui
	 * @time 2015年12月11日 上午11:44:41
	 */
	public void setTrade_state_desc(String trade_state_desc) {
		this.trade_state_desc = trade_state_desc;
	}

	/**
	 * 用户标识 openid 是 String(128) wxd930ea5d5a258f4f
	 * <p>
	 * 用户在商户appid下的唯一标识
	 * 
	 * @return
	 * @author Yujinshui
	 * @time 2015年12月11日 上午11:38:20
	 */
	public String getOpenid() {
		return openid;
	}

	/**
	 * 用户标识 openid 是 String(128) wxd930ea5d5a258f4f
	 * <p>
	 * 用户在商户appid下的唯一标识
	 * 
	 * @param openid
	 * @author Yujinshui
	 * @time 2015年12月11日 上午11:41:04
	 */
	public void setOpenid(String openid) {
		this.openid = openid;
	}

	/**
	 * 用于封装查询结果中涉及到优惠券$n的三个字段: coupon_batch_id_$n , coupon_id_$n , coupon_fee_$n
	 * 
	 * @return
	 * @author Yujinshui
	 * @time 2015年12月11日 上午11:44:54
	 */
	public List<Coupon> getCouponItems() {
		return couponItems;
	}

	/**
	 * 用于封装查询结果中涉及到优惠券$n的三个字段: coupon_batch_id_$n , coupon_id_$n , coupon_fee_$n
	 * 
	 * @param couponItems
	 * @author Yujinshui
	 * @time 2015年12月11日 上午11:44:57
	 */
	public void setCouponItems(List<Coupon> couponItems) {
		this.couponItems = couponItems;
	}

	public String toString() {
		return "return_code = " + return_code //
				+ "\r\n<br>,return_msg = " + return_msg //
				+ "\r\n<br>,appid = " + appid //
				+ "\r\n<br>,mch_id = " + mch_id //
				+ "\r\n<br>,device_info = " + device_info //
				+ "\r\n<br>,nonce_str = " + nonce_str //
				+ "\r\n<br>,sign = " + sign //
				+ "\r\n<br>,result_code = " + result_code //
				+ "\r\n<br>,err_code = " + err_code //
				+ "\r\n<br>,err_code_des = " + err_code_des //
				+ "\r\n<br>,openid = " + openid //
				+ "\r\n<br>,is_subscribe = " + is_subscribe //
				+ "\r\n<br>,trade_type = " + trade_type //
				+ "\r\n<br>,trade_state = " + trade_state //
				+ "\r\n<br>,bank_type = " + bank_type //
				+ "\r\n<br>,total_fee = " + total_fee //
				+ "\r\n<br>,fee_type = " + fee_type //
				+ "\r\n<br>,cash_fee = " + cash_fee //
				+ "\r\n<br>,cash_fee_type = " + cash_fee_type //
				+ "\r\n<br>,coupon_fee = " + coupon_fee //
				+ "\r\n<br>,coupon_count = " + coupon_count //
				+ "\r\n<br>,transaction_id = " + transaction_id //
				+ "\r\n<br>,out_trade_no = " + out_trade_no //
				+ "\r\n<br>,attach = " + attach //
				+ "\r\n<br>,time_end = " + time_end //
				+ "\r\n<br>,trade_state_desc = " + trade_state_desc //
				+ "\r\n<br>,couponItems = " + couponItems;
	}

}
