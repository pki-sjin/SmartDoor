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