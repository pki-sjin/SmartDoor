$(function() {
//	$("#tabs").find("a")[1].click();
	
	$(".resetPassword").click(function(e){
		window.location = "login/password.html";
	});
	
	$("#guestSignUp").click(function(e){
		window.location = "login/guestsignup.html";
	});
	$("#exhibitionSignUp").click(function(e){
		window.location = "login/exhibitionsignup.html";
	});
	$("#stageSignUp").click(function(e){
		window.location = "login/stagesignup.html";
	});
	$("#guestSignIn").click(function(e){
		var username = $("#username").val();
		var password = $("#password").val();
		if (username == "") {
			showErrorMessage("请输入用户名");
			return;
		}
		
		if (password == "") {
			showErrorMessage("请输入密码");
			return;
		}
		
		showLoading();
		
		$.ajax({ 
			url: "Login",
			type: "POST",
			data: "username=" + username +  "&password=" + password,
			success: function(resp){
				if (resp.status == 1) {
					window.location = "feature/user.html";
				} else {
					hideLoading();
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