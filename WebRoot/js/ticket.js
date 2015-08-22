$(function(){
	$.ajax({
		url: "../GetTickets",
		type: "POST",
		success: function(resp){
			console.log(resp);
			if (resp.status == 1 || resp.status == 0) {
				if (resp.status == 0) {
					showErrorMessage(resp.data);
				} else {
					{
						var fieldSet = $("<fieldset id=\"tickets_list\" data-role=\"controlgroup\">");
						for(var i = 0, length = resp.list.length; i < length; i++) {
							var ticket = resp.list[i];
							var input = $("<input type=\"radio\" name=\"tickets-choice\">");
							if (i == 0) {
								input.attr("checked", "checked");
							}
							input.attr("id", ticket.code);
							input.val(ticket.id);
							var label = $("<label>");
							label.attr("for", ticket.code);
							label.append($("<div>")
									.append($("<span>").text(ticket.name))
									.append($("<br>"))
									.append($("<span class=\"price\">").text(ticket.price)));
							fieldSet.append(input).append(label);
						}
						$(".payDiv").append(fieldSet);
						$("#tickets_list").controlgroup();
					}
					
					{
						var fieldSet = $("<fieldset id=\"qrcodePay\" data-role=\"controlgroup\">");
						{
							var input = $("<input type=\"radio\" name=\"radio-choice-pay\" id=\"radio-choice-pay-alipay\" value=\"alipay\" checked=\"checked\">");
							var label = $("<label for=\"radio-choice-pay-alipay\">支付宝支付</label>");
							fieldSet.append(input).append(label);
						}
						
						{
							var input = $("<input type=\"radio\" name=\"radio-choice-pay\" id=\"radio-choice-pay-wechat\" value=\"wechat\">");
							var label = $("<label for=\"radio-choice-pay-wechat\">微信支付</label>");
							fieldSet.append(input).append(label);
						}
						
						$(".payDiv").append(fieldSet);
						$("#qrcodePay").controlgroup();
						var button = $("<div id=\"qrcodeGoToPay\" class=\"ui-input-btn ui-btn ui-corner-all ui-shadow\">前往支付<input type=\"button\" data-enhanced=\"true\" value=\"前往支付\"></div>");
						$(".payDiv").append(button);
						button.click(function(e) {
							window.open("../Buy");
						});
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