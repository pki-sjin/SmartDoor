$(function(){
		$( "[data-role='header']" ).toolbar();
		$("#sendCode").click(function(){
			$("#sendCode").addClass("ui-state-disabled");
			var button = $("#sendCode").contents()[0];
			var time = 60;
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
			
			$.ajax({
				url: "../SendCode",
				type: "POST",
				data: "cell=" + $("#username").val(),
				success: function(resp){
					console.log(resp);
					if(resp.status == 1) {
						showErrorMessage("短信发送成功");
						$("#username").attr("data-sms",resp.id);
						$("#codeTag").html("短信识别码:" + resp.tag).show();
					} else {
						showErrorMessage(resp.data);
					}
		      	},
		      	error: function(resp) {
		      		console.log(resp);
		      	}
			});
		});
		
		$("#register").click(function(){
			var username = $("#username").val();
			var password1 = $("#password1").val();
			var password2 = $("#password2").val();
			var sendCode = $("#code").val();
			var sms_id = $("#username").attr("data-sms");
			if (username == "") {
				showErrorMessage("请输入用户名");
				return;
			}
			
			if (password1 == "" || password2 == "") {
				showErrorMessage("请输入密码");
				return;
			}
			
			if(password1 != password2) {
				showErrorMessage("两次输入的密码不一样");
				return;
			}
			
			if (sendCode == "") {
				showErrorMessage("请输入验证码");
				return;
			}
			
			if (!sms_id) {
				sms_id = 0;
			}
			showLoading();
			$.ajax({
				url: "../Register",
				type: "POST",
				data: "username=" + username + "&password=" + password1 + "&code=" + sendCode + "&sms_id=" + sms_id,
				success: function(resp){
					hideLoading();
					console.log(resp);
					if(resp.status == 1) {
						// register ok
						window.location = "../feature/user.html";
					} else {
						showErrorMessage(resp.data);	
					}
		      	},
		      	error: function(resp) {
		      		hideLoading();
		      		console.log(resp);
		      	}
			});
		});
	});

function showErrorMessage(message) {
	$("#errorMessage").html(message);
	$("#errorMessage").popup("open", {
		  transition: "slidedown",
		  positionTo: "window"
	});
}

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