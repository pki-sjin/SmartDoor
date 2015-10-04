$(function(){
	$.ajax({
		url: "../GetInvoice",
		type: "POST",
		success: function(resp){
			console.log(resp);
			if (resp.status == 1 || resp.status == 0) {
				if (resp.status == 0) {
					showErrorMessage(resp.data);
				} else {
					{
						var ul = $("<ul data-role=\"listview\" data-inset=\"true\">");
						ul.append($("<li>订单号：" + resp.order + "</li>"));
						ul.append($("<li>价格：" + resp.price + "元</li>"));
						ul.append($("<li>门票类型：" + resp.ticket + "</li>"));
						ul.append($("<li>购买渠道：" + resp.method + "</li>"));
						ul.append($("<li>开始时间：" + resp.create + "</li>"));
						ul.append($("<li>完成时间：" + resp.finish + "</li>"));
						ul.append($("<li>状态：" + resp.state + "</li>"));
						$("#invoiceContainer").append(ul);
						ul.listview();
					}
				}
			} else {
				window.location = "../index.html";
			}
      	},
      	error: function(resp) {
      		console.log(resp);
      	}
	});
});

function showErrorMessage(message) {
	$("#errorMessage").html(message);
	$("#errorMessage").popup("open", {
		  transition: "slidedown",
		  positionTo: "window"
	});
}