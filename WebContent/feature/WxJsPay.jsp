<%@ page language="java"
	import="java.util.Map.Entry,java.util.*,com.wx.pay.business.JsApiPay,org.zero.db.entity.user.SdUser,org.zero.db.entity.activity.*,org.zero.db.entity.order.*,org.zero.db.entity.ticket.*,com.wx.pay.api.WxPayData"
	pageEncoding="UTF-8"%>
<%
	JsApiPay jsApiPay = new JsApiPay(request, response);
	jsApiPay.GetOpenidAndAccessToken();
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

		jsApiPay.total_fee = (int) (order.getPrice() * 100);
		WxPayData unifiedOrderResult = jsApiPay.GetUnifiedOrderResult(
				order.getId(), ticket.getName(), ticket
						.getDescription(), ticket.getCode());
		String wxJsApiParam = jsApiPay.GetJsApiParameters();
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
		<script language="javascript">
			//调用微信JS api 支付
			function jsApiCall() {
				WeixinJSBridge.invoke('getBrandWCPayRequest',
				<%=wxJsApiParam%>,//josn串
				function(res) {
					WeixinJSBridge.log(res.err_msg);
					alert(res.err_code + res.err_desc + res.err_msg);
				});
			}
			
			function callpay() {
				if (typeof WeixinJSBridge == "undefined") {
					if (document.addEventListener) {
						document.addEventListener('WeixinJSBridgeReady', jsApiCall, false);
					} else if (document.attachEvent) {
						document.attachEvent('WeixinJSBridgeReady', jsApiCall);
						document.attachEvent('onWeixinJSBridgeReady', jsApiCall);
					}
				} else {
					jsApiCall();
				}
			}
		</script>
	</head>
	<body>
		<div data-role="page">
			<div class="coverPage"></div>
			<div data-role="popup" id="errorMessage">
			</div>

			<div data-role="header" data-theme="a">
				<h1>
					订单详情
					<span id="displayHeader"></span>
				</h1>
			</div>
			<!-- /header -->

			<div role="main" class="ui-content">
				<div id="orderContainer">
					<ul data-role="listview" data-inset="true">
					<%
						for (Entry<String, Object> pair : unifiedOrderResult.GetValues().entrySet()) {
							if (pair.getValue() != null) {
								out.println("<li>" + pair.getKey() + ": " + pair.getValue() + "</li>");
							}
						}
					%>
					</ul>
				</div>
				<div id="pay" class="ui-input-btn ui-btn ui-corner-all">立即支付<input type="button" data-enhanced="true" value="立即支付" onclick="callpay();"></div>
			</div>
		</div>
	</body>
</html>
<%
	}
%>
