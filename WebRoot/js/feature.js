$(function() {
	$(function(){
		$( "[data-role='header']" ).toolbar();
		$("#userPanel li a").click(function(e){
			var id = $(this).attr("for");
			$(".contentPanel").hide();
			$("#"+id).show();
			return $.killevent(e);
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