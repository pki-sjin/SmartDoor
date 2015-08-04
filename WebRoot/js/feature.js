$(function() {
	$(function(){
		$( "[data-role='header']" ).toolbar();
		$("#userPanel li a").click(function(e){
			var id = $(this).attr("for");
			if (id) {
				$(".contentPanel").hide();
				$("#"+id).show();
				$("#userPanel").panel("close");
				e.preventDefault();
			}
		});
		
		$("#edit").click(function(e){
			var display = $("#display").val();
			var company = $("#company").val();
			var position = $("#position").val();
			var phone = $("#phone").val();
			var cell = $("#cell").val();
			var fax = $("#fax").val();
			var email = $("#email").val();
			
			if (!display || !company || !position || !phone || !cell || !fax || !email) {
				showErrorMessage("请将资料填写完整");
				return;
			}
			
			var postStr = "display=" + display +
							"&company=" + company +
							"&position=" + position +
							"&phone=" + phone +
							"&cell=" + cell +
							"&fax=" + fax +
							"&email=" + email;
			
			showLoading();
			$.ajax({
				url: "../EditUser",
				type: "POST",
				data: postStr,
				success: function(resp){
					hideLoading()
					console.log(resp);
					showErrorMessage(resp.data);
					if (resp.status == 1) {
						$("#displayHeader").text(display);
					} else if (resp.status == -1){
						window.location = "../index.html";
					}
		      	},
		      	error: function(resp) {
		      		hideLoading()
		      		console.log(resp);
		      	}
			});
		});
		
		$.ajax({
			url: "../GetUser",
			type: "POST",
			success: function(resp){
				console.log(resp);
				if (resp.status == 1 || resp.status == 0) {
					if (resp.status == 0) {
						showErrorMessage(resp.data);
					}
					var user = resp.user;
					$("#displayHeader").text(user.display ? user.display : user.username);
					$("#display").val(user.display);
					$("#company").val(user.company);
					$("#position").val(user.position);
					$("#phone").val(user.tel);
					$("#cell").val(user.cell);
					$("#fax").val(user.fax);
					$("#email").val(user.mail);
				} else {
					window.location = "../index.html";
				}
	      	},
	      	error: function(resp) {
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