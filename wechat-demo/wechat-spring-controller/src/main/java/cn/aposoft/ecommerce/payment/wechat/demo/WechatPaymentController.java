package cn.aposoft.ecommerce.payment.wechat.demo;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.aposoft.ecommerce.payment.wechat.Order;
import cn.aposoft.ecommerce.payment.wechat.PayResponse;
import cn.aposoft.ecommerce.payment.wechat.PaymentService;
import cn.aposoft.ecommerce.payment.wechat.bean.OrderVo;
import cn.aposoft.ecommerce.payment.wechat.service.PaymentStorageException;
import cn.aposoft.ecommerce.payment.wechat.service.PaymentStoreService;
import cn.aposoft.ecommerce.payment.wechat.vo.WechatPaymentModel;

/**
 * 支付控制器
 * 
 * @author Jann Liu
 *
 */
@Controller
public class WechatPaymentController {
	private static final Logger logger = LoggerFactory.getLogger(WechatPaymentController.class);
	@Autowired
	private PaymentService payService;

	@Autowired
	private PaymentStoreService paymentStoreService;

	public WechatPaymentController() {
	}

	/**
	 * 入口订单处理登陆页
	 * 
	 * @return 登陆处理页地址
	 */

	@RequestMapping("/topay")
	public String toPay() {
		return "payment/topay";
	}

	/**
	 * 接收订单提交的post请求,并进行预付款处理
	 * 
	 * @param order
	 *            待付款订单
	 * @param req
	 *            {@link HttpServletRequest}
	 * @return 预付款处理结果视图地址
	 */
	@RequestMapping(value = "/order", method = RequestMethod.POST)
	public String submitOrder(OrderVo order, HttpServletRequest req) {
		Order o = null;
		try {
			o = createOrder(order);
			PayResponse result = payService.preparePay(o);

			if (!StringUtils.isEmpty(result.getCode_url())) {
				try {
					WechatPaymentModel model = new WechatPaymentModel();
					model.setOrderNo(o.getOut_trade_no());
					model.setPngUrl(URLEncoder.encode(result.getCode_url(), "UTF-8"));
					model.setTotalFee(String.valueOf(o.getTotal_fee()));
					req.getSession().setAttribute("order:" + o.getOut_trade_no(), model);

				} catch (UnsupportedEncodingException e) {
					logger.error("虚拟机不支持UTF-8编码");// this will never happen.
				}
				return "redirect:wechat?orderNo=" + order.getOut_trade_no();
			} else {
				// redirect to 500 页面
				return "payment/wechat500";
			}
		} catch (PaymentStorageException e) {
			logger.error("访问持久化读取SequenceOrderNo失败,", e);
			// redirect to 500 页面
			return "payment/wechat500";
		}
	}

	@RequestMapping(value = "/wechat")
	public String showWechatPaymentPage(String orderNo, HttpServletRequest req) {
		WechatPaymentModel model = (WechatPaymentModel) req.getSession().getAttribute("order:" + orderNo);
		if (model != null) {
			req.setAttribute("model", model);
			return "payment/wechat";
		} else {
			return "payment/wechat500";
		}
	}

	@RequestMapping("/searchOrder/{orderNo}")
	@ResponseBody
	public String searchOrderState(@PathVariable("orderNo") String orderNo, HttpServletRequest req) {
		return "1";
	}

	// 创建通信的订单对象
	private Order createOrder(OrderVo order) throws PaymentStorageException {
		// 从存储读取自增订单编号
		String orderNo = paymentStoreService.getNextOrderNo();
		order.setBody(order.getBody());
		order.setGoods_tag("no");
		order.setOut_trade_no(orderNo);// 只要未支付，即可继续重复使用该单号
		order.setSpbill_create_ip("127.0.0.1");
		order.setTrade_type("NATIVE");
		order.setTotal_fee(order.getTotal_fee());
		return order;
	}

	/**
	 * 退款操作 未实现
	 * 
	 * @param model
	 *            输出对象
	 * @return 退款跳转页面view地址
	 */
	@RequestMapping("/refund")
	public String refund(Map<String, Object> model) {
		long sleep = 5 * 1000;
		System.out.println("in: /payment/refund , at:" + new Date());
		try {
			Thread.sleep(sleep);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "payment/wechat";
	}

}
