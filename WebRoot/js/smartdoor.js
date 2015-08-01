$(function() {
//	$("#tabs").find("a")[1].click();
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
		$("#guestSignIn").click(function(e){
			var username = $("#username").val();
			var password = $("#password").val();
			if (username == "") {
				
				return;
			}
			
			if (password == "") {
				
				return;
			}
			
			$.ajax({ 
				url: "Login",
				type: "POST",
				data: "&username=" + username +  "&password=" + password,
				success: function(resp){
		        	console.log(resp);
		      	},
		      	error: function(resp) {
		      		console.log(resp);
		      	}
			});
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