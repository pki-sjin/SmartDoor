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
		
		$.ajax({
			url: "../GetExhibition",
			type: "POST",
			success: function(resp){
				console.log(resp);
				var list = resp.list;
				var table = $("#exhibition-table");
				if (!!list.length) {
					var tbody = $("<tbody>");
					for(var i = 0, length = list.length; i < length; i++) {
						var ex = list[i];
						var tr = $("<tr>");
						var th = $("<th>" + ex.id + "</th>")
						var td1 = $("<td><a target='new' href='" + ex.url + "'>" + ex.subject + "</a></td>");
						var td2 = $("<td>"+ ex.start + "<br/>" + ex.end +"</td>");
						var td3 = $("<td>");
						var detail = $("<a href='#'>详细</a>");
						detail.click(function(e){
							$.ajax({
								url: "../GetStage",
								type: "POST",
								data: "ex_id=" + ex.id,
								success: function(resp){
									console.log(resp);
									var closebtn = '<a href="#" data-rel="back" class="ui-btn ui-corner-all ui-btn-a ui-icon-delete ui-btn-icon-notext ui-btn-right">Close</a>',
									            header = '<div data-role="header"><h2>展台列表</h2></div>',
									            content = '<ul id="stage-list" data-role="listview" data-inset="false"></ul>',
									            popup = '<div data-role="popup" id="popup-stage" data-corners="false" data-tolerance="15"></div>';
									        // Create the popup.
									        $( header )
									            .appendTo( $( popup )
									                .appendTo( $.mobile.activePage )
									                .popup() )
									            .toolbar()
									            .before( closebtn )
									            .after( content );
									var maxHeight = $( window ).height() - 68;
									var maxWidth = $( window ).width() - 30;
									$("#popup-stage").css("width",maxHeight);
									$("#popup-stage").css("height",maxHeight);
									$("#popup-stage").css("maxWidth",maxWidth);
									$("#popup-stage").css("maxHeight",maxHeight);
									$("#popup-stage").popup("open");
									
									var stages = resp.list;
									for(var j = 0, length = stages.length; j < length; j++) {
										var stage = stages[j];
										var li = $("<li>");
										var a = $("<a target='new'>");
										a.attr("href", stage.url);
										a.html(stage.subject);
										li.append(a);
										$("#stage-list").append(li);
									}
									$("#stage-list").listview();
						      	},
						      	error: function(resp) {
						      		console.log(resp);
						      	}
							});
							e.preventDefault();
						});
						td3.append(detail);
						tr.append(th);
						tr.append(td1);
						tr.append(td2);
						tr.append(td3);
						tbody.append(tr);
					}
					table.append(tbody);
				} else {
					table.empty();
					var thead = $("<thead>");
					var tr = $("<tr>");
					var th = $("<th style='text-align:center;'>没有会展信息</th>");
					tr.append(th);
					thead.append(tr);
					table.append(thead);
				}
	      	},
	      	error: function(resp) {
	      		console.log(resp);
	      	}
		});
		
		$( document ).on( "popupafterclose", ".ui-popup", function() {
			 $( this ).remove();
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