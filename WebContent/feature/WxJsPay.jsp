<%@ page language="java"
	import="java.util.Map.Entry,java.util.*,com.wx.pay.business.JsApiPay,org.zero.db.entity.user.SdUser,org.zero.db.entity.activity.*,org.zero.db.entity.order.*,org.zero.db.entity.ticket.*,com.wx.pay.api.WxPayData, com.wx.pay.api.WxPayApi, com.wx.pay.lib.WxPayConfig"
	pageEncoding="UTF-8"
%>
<%
	WxPayData wxJsApiParam = null;
	String openid = (String)session.getAttribute("openid");
	SdUser user = (SdUser) session.getAttribute("USER");
	if (user == null) {
		response.sendRedirect("index.html");
		return;
	}
	
	SdExUserRelationDAO relationDao = new SdExUserRelationDAO();
	List relations = relationDao.findByUserId(user.getId());
	if (!relations.isEmpty()) {
		SdExUserRelation relation = (SdExUserRelation) relations.get(0);
		SdOrderDAO orderDao = new SdOrderDAO();
		SdOrder order = orderDao.findById(relation.getOrderId());

		SdTicketDAO dao = new SdTicketDAO();
		SdTicket ticket = dao.findById(order.getTicket());

		int total_fee = (int) (order.getPrice() * 100);
		
		//JSAPI支付预处理
		try {
			WxPayData unifiedOrderResult = JsApiPay.GetUnifiedOrderResult(openid, order.getId()+"", ticket.getName(),ticket.getDescription(),(int) (ticket.getPrice() * 100), ticket.getCode());
			wxJsApiParam = JsApiPay.GetJsApiParameters(unifiedOrderResult);//获取H5调起JS API参数
		} catch (Exception ex) {
			out.println("<span style='color:#FF0000;font-size:20px'>" + "下单失败，请返回重试" + ex.getMessage() + "</span>");
			return;
		}

		String nonceStr = WxPayApi.GenerateNonceStr();
		String jsapi_ticket = JsApiPay.getJsApiTicket(JsApiPay
		.getAccessToken());
		long timestamp = System.currentTimeMillis();
		String host = request.getRequestURL().toString();
		String queryString = request.getQueryString();
		String url = host + (queryString != null ? ("?" + queryString) : "");

		String signature = JsApiPay.getSignature(jsapi_ticket, nonceStr,
		timestamp, url);
%>
<!DOCTYPE html>
<html>
<head>
<title>微信支付</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="../css/jquery.mobile-1.4.5.min.css">
<link rel="stylesheet" href="../css/feature.css">
<script src="../js/jquery-2.1.4.min.js"></script>
<script src="../js/jquery.mobile-1.4.5.min.js"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script language="javascript">
	//调用微信JS api 支付
	wx.config({
	    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
	    appId: '<%=WxPayConfig.APPID%>', // 必填，公众号的唯一标识
	    timestamp: <%=timestamp%>, // 必填，生成签名的时间戳
	    nonceStr: '<%=nonceStr%>', // 必填，生成签名的随机串
	    signature: '<%=signature%>', // 必填，签名，见附录1
		jsApiList : [ 'chooseWXPay' ] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
	});

	function callpay() {
		wx.chooseWXPay({
			timestamp: <%=wxJsApiParam.GetValue("timeStamp")%>, 
		    nonceStr: '<%=wxJsApiParam.GetValue("nonceStr")%>',
		    package: '<%=wxJsApiParam.GetValue("package")%>', 
		    signType : '<%=wxJsApiParam.GetValue("signType")%>',
		    paySign: '<%=wxJsApiParam.GetValue("paySign")%>',
		    success: function(res) {
		    	alert('支付成功');
		    	window.location = "invoice.html";
		    }
		});
	}
</script>
</head>
<body>
	<div data-role="page">
		<div class="coverPage"></div>
		<div data-role="popup" id="errorMessage"></div>
		<div data-role="header" data-theme="a">
			<h1>
				订单详情 <span id="displayHeader"></span>
			</h1>
		</div>
		<!-- /header -->
		<div role="main" class="ui-content">
			<div id="orderContainer">
				<ul data-role="listview" data-inset="true">
					<%
						for (Entry<String, Object> pair : wxJsApiParam.GetValues().entrySet()) {
							if (pair.getValue() != null) {
								out.println("<li>" + pair.getKey() + ": " + pair.getValue() + "</li>");
							}
						}
					%>
				</ul>
			</div>
			<div id="pay" class="ui-input-btn ui-btn ui-corner-all">
				立即支付<input type="button" data-enhanced="true" value="立即支付" onclick="callpay();">
			</div>
		</div>
	</div>
</body>
</html>
<%
	}
%>
