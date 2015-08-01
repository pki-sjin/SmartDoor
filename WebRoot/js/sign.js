$(function() {
	$(function(){
		$( "[data-role='header']" ).toolbar();
		$("#sendCode").click(function(){
			$("#sendCode").addClass("ui-state-disabled");
			var button = $("#sendCode").contents()[0];
			var time = 5;
			button.data = time-- + "秒后可再次发送";
			var interval = setInterval(function(){
				if (time == 0) {
					clearInterval(interval);
					$("#sendCode").removeClass("ui-state-disabled");
					button.data = "发送认证码";
				} else {
					button.data = time-- + "秒后可再次发送";
				}
			}, 1000);
		});
	});
});

function showLoading() {
	$(".coverPage").show();
	$.mobile.loading( "show", {
		            text: "",
		            textVisible: false,
		            theme: $.mobile.loader.prototype.options.theme,
		            textonly: false,
		            html: ""
		    });
}

function hideLoading() {
	$(".coverPage").hide();
	$.mobile.loading("hide");
}