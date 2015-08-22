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
			var country = $("#country").val();
			var province = $("#province").val();
			var city = $("#city").val();
			var company = $("#company").val();
			var address = $("#address").val();
			var web = $("#web").val();
			var postcode = $("#postcode").val();
			var position = $("#position").val();
			var tel = $("#tel").val();
			var cell = $("#cell").val();
			var fax = $("#fax").val();
			var mail = $("#mail").val();
			
			if (!display || !country || !province || !city || !company || !address || !postcode ||
					!position || !tel || !cell || !fax || !mail || !web) {
				showErrorMessage("请将资料填写完整");
				return;
			}
			
			var postStr = "display=" + display +
							"&country=" + country +
							"&province=" + province +
							"&city=" + city +
							"&company=" + company +
							"&address=" + address +
							"&web=" + web +
							"&postcode=" + postcode +
							"&position=" + position +
							"&tel=" + tel +
							"&cell=" + cell +
							"&fax=" + fax +
							"&mail=" + mail;
			
			showLoading();
			$.ajax({
				url: "../EditUser",
				type: "POST",
				data: postStr,
				success: function(resp){
					hideLoading();
					console.log(resp);
					showErrorMessage(resp.data);
					if (resp.status == 1) {
						$("#displayHeader").text(display);
						showErrorMessage(resp.data);
					} else if (resp.status == -1){
						window.location = "../index.html";
					}
		      	},
		      	error: function(resp) {
		      		hideLoading();
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
					$("#country").val(user.country);
					$("#province").val(user.province);
					$("#city").val(user.city);
					$("#company").val(user.company);
					$("#address").val(user.address);
					$("#web").val(user.web);
					$("#postcode").val(user.postcode);
					$("#position").val(user.position);
					$("#tel").val(user.tel);
					$("#cell").val(user.cell);
					$("#fax").val(user.fax);
					$("#mail").val(user.mail);
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
				table.empty();
				if (!!list.length) {
					var thead = $('<thead><tr><th style="width:10%;">序号</th><th style="width:43%;">展会</th><th style="width:33%;">时间</th><th style="width:13%;">展台</th></tr></thead>')
					table.append(thead);
					var tbody = $("<tbody>");
					for(var i = 0, length = list.length; i < length; i++) {
						var ex = list[i];
						var tr = $("<tr>");
						var th = $("<th>" + ex.id + "</th>");
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
		
		$.ajax({
			url: "../GetActivities",
			type: "POST",
			success: function(resp){
				console.log(resp);
				var list = resp.list;
				var table = $("#activities-table");
				table.empty();
				if (!!list.length) {
					var thead = $('<thead><tr><th style="width:10%;">序号</th><th style="width:43%;">展会</th><th style="width:33%;">状态</th></tr></thead>')
					table.append(thead);
					var tbody = $("<tbody>");
					for(var i = 0, length = list.length; i < length; i++) {
						var ac = list[i];
						var tr = $("<tr>");
						var th = $("<th>" + ac.id + "</th>");
						var td1 = $("<td><a target='new' href='" + ac.url + "'>" + ac.subject + "</a></td>");
						var td2 = $("<td></td>");
						var action = $("<a href='#'></a>");
						if (ac.join_id == 1) {
							action.text("可以参加");
						} else if (ac.join_id == 2) {
							action.text("门票未购买");
						} else if (ac.join_id == 3) {
							action.text("门票已购买");
						} else if (ac.join_id == 4) {
							action.text("已参加");
						} else if (ac.join_id == 5) {
							action.text("展会进行中");
						} else if (ac.join_id == 6) {
							action.text("展会已结束");
						}
						action.click(function(e){
							var maxHeight = $( window ).height() - 68;
							if (maxHeight < 180) {
								maxHeight = 180;
							} else if (maxHeight > 300) {
								maxHeight = 300;
							}
							var maxWidth = $( window ).width() - 30;
							if (maxHeight > maxWidth) {
								maxHeight = maxWidth;
							}
							$.ajax({
								url: "../Join",
								type: "POST",
								data: "join_id=" + ac.join_id + "&order_id=" + ac.order_id +"&size=" + maxHeight,
								success: function(resp){
									console.log(resp);
									var closebtn = '<a href="#" data-rel="back" class="ui-btn ui-corner-all ui-btn-a ui-icon-delete ui-btn-icon-notext ui-btn-right">Close</a>',
									            header = '<div data-role="header"><h2>活动详情</h2></div>',
									            content = '<div id="qrcodeImg" align="center"></div><div id="qrcodeSend" align="center"><div id="sendQRCode" class="ui-input-btn ui-btn ui-btn-inline ui-corner-all">发送二维码到手机<input type="button" data-enhanced="true" value="发送二维码到手机"></div></div><div id="qrcodeTips" align="center"></div><div id="purchaseDiv" align="center"><div id="onlinePurchase" class="ui-input-btn ui-btn ui-btn-inline ui-corner-all">在线购票<input type="button" data-enhanced="true" value="在线购票"></div></div></div>',
									            popup = '<div data-role="popup" id="popup-activity" data-corners="false" data-tolerance="15"></div>';
									        // Create the popup.
									        $( header )
									            .appendTo( $( popup )
									                .appendTo( $.mobile.activePage )
									                .popup() )
									            .toolbar()
									            .before( closebtn )
									            .after( content );
									
									var img = $("<img>");
									img.attr("src",resp.qrcode);
									$("#qrcodeImg").append(img);
									$("#qrcodeTips").text(resp.message);
									
									if (ac.join_id == 1 || ac.join_id == 2 || ac.join_id == 5) {
										$("#purchaseDiv").show();
										$("#onlinePurchase").click(function(e) {
											window.open("tickets.html");
										});
									}
									
									$("#popup-activity").css("width",maxHeight);
									$("#popup-activity").css("maxWidth",maxWidth);
									$("#popup-activity").popup("open");
									
						      	},
						      	error: function(resp) {
						      		console.log(resp);
						      	}
							});
							e.preventDefault();
						});
						
						td2.append(action);
						tr.append(th);
						tr.append(td1);
						tr.append(td2);
						tbody.append(tr);
					}
					table.append(tbody);
				} else {
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
		
		$( document ).on( "popupafterclose", "#popup-activity,#popup-stage", function() {
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