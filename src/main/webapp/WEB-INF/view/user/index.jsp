<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="com.portal.model.User"%>
<!DOCTYPE html>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	User user = (User) request.getSession().getAttribute("cUserSession");
%>
<html>
<head>
<meta charset="utf-8">
<title>portal-demo</title>
<link
	href="${pageContext.request.contextPath}/assets/css/font-awesome.min.css?v=0.0.3"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/assets/css/nav-menu.css?v=0.0.9"
	rel="stylesheet">
<jsp:include page="${path}/assets/include/include.jsp?v=0.0.3"></jsp:include>
</head>
<body class="fixed-sidebar full-height-layout gray-bg"
	style="overflow: hidden">
	<div id="wrapper">
		<!--左侧导航开始-->
		<nav class="navbar-default navbar-static-side" role="navigation">
			<div class="nav-close">
				<i class="fa fa-times-circle"></i>
			</div>
			<div class="sidebar-collapse">
				<ul class="nav" id="side-menu">
				</ul>
			</div>
		</nav>
		<!--左侧导航结束-->
		<!--右侧部分开始-->
		<div id="page-wrapper" class="gray-bg dashbard-1">
			<div class="row border-bottom">
				<nav class="navbar navbar-static-top" role="navigation"
					style="margin-bottom: 0">
					<div class="navbar-header" style="height:60px;line-height:60px;">
						<a class="navbar-minimalize minimalize-styl-2 btn btn-info "
							href="#"><i class="fa fa-bars"></i> </a>
					</div>
					<ul class="nav navbar-top-links navbar-right marginR0" style="height:60px;line-height:60px;">
						<li id="todayWeek" style="padding-right:15px;font-weight: bold;">2018-05-08 星期二</li>
						<%-- <li><i class="fa fa-user"> <strong>${cUserSession.name}</strong></i></li> --%>
						<%-- <li>
							<a id="loginOut" class="count-info" href="${pageContext.request.contextPath}/userController/loginOut">
								<i class="fa fa-sign-out"></i> <strong langtag='langtag_loginOut'>退出</strong>
							</a>
						</li> --%>
						<li>|</li>
						<li>
							<div id="nav-menu">
								<ul class="menu" style="padding-left:0px;">
									<li class="stmenu">
										<a href="#" id="xialaguang" class="xialaguang">
											<i class="fa fa-user"> <strong>${cUserSession.name}</strong></i>
										</a>
										<!-- <div id="trangle" class="trangle"></div> -->
										<ul id="children" class="children">
											<% if(user.getBrand_id() != 0) {%>
											<li><h3>
													<a tabindex="-1" data-toggle="modal"
														data-target="#updateInfoDiv" style="cursor: pointer"><span
														langtag='langtag_updateInfo'>修改信息</span></a>
												</h3></li>
											<li><h3>
													<a tabindex="-1" data-toggle="modal"
														data-target="#updatePassDiv" style="cursor: pointer"><span
														langtag='langtag_updatePass' onclick="clearUpdatePassForm()">修改密码</span></a>
												</h3></li>
											<% } %>
											<% if(user.getId() == 42 || user.getId() == 46) {%>
												<li class="navBottom"><h3>
													<a tabindex="-1"
														href="${pageContext.request.contextPath}/userController/sensetimeLoginOut"><span
														langtag='langtag_loginOut'>退出</span></a>
												</h3></li>
											<% } else { %>
												<li class="navBottom"><h3>
													<a tabindex="-1"
														href="${pageContext.request.contextPath}/userController/loginOut"><span
														langtag='langtag_loginOut'>退出</span></a>
												</h3></li>
											<% } %>
										</ul>
									</li>
								</ul>
							</div>
						</li>
					</ul>
				</nav>
			</div>
			<div class="row J_mainContent" id="content-main">
				<iframe id="J_iframe" width="100%" height="100%"
					src="${pageContext.request.contextPath}/userController/index_v1"
					frameborder="0" data-id="index_v1.html" seamless></iframe>
			</div>
			<!-- <div class="copyright" style="text-align:center;height:50px;line-height:50px;background-color:white;margin:0px -20px;border-top: 1px solid #e7eaec !important;">
				<span style="color:#797979">Copyright © 2012-2018 DEDECMS. 广升人脸识别 版权所有</span>
			</div> -->
		</div>
		<!--右侧部分结束-->
		
		<!-- 编辑模态框 begin -->
		<div class="modal fade" id="updateInfoDiv" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-dialog-center" style="width:600px;height:310px;transform:translateX(-50%) translateY(-50%);">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only"><span
								langtag='langtag_cancel'>取消</span></span>
						</button>
						<h4 class="modal-title" id="myModalLabel">
							<span langtag='langtag_updateInfo'>修改信息</span>
						</h4>
					</div>
					<div class="modal-body">
						<form id="ui_Form" role="form" class="form-horizontal m-t">
							<input id="uiid" name="id" value="${cUserSession.id}" style="display:none;"/>
							<div class="form-group">
								<label class="col-sm-4 control-label"><span style="color:red">* </span><span
									langtag='langtag_username'>账号</span> : </label>
								<div class="col-sm-6">
									<input id="uiname" name="name" class="form-control"
										type="text" aria-required="true" aria-invalid="true" value="${cUserSession.name}" disabled="disabled">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 control-label"><span style="color:red">* </span><span
									langtag='langtag_contact'>联系人</span> : </label>
								<div class="col-sm-6">
									<input id="uicontact" name="contact" class="form-control"
										type="text" aria-required="true" value="${cUserSession.contact}" aria-invalid="true">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 control-label"><span style="color:red">* </span><span
									langtag='langtag_phone'>联系电话</span> : </label>
								<div class="col-sm-6">
									<input id="uiphone" name="phone" class="form-control"
										type="text" aria-required="true" value="${cUserSession.phone}" aria-invalid="true">
								</div>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">
							<span class="glyphicon glyphicon-remove tableFuncIcon"
								aria-hidden="true"></span><span langtag='langtag_cancel'>取消</span>
						</button>
						<button type="button" class="btn btn-primary"
							onclick="updateInfoValidate()">
							<span class="glyphicon glyphicon-floppy-disk tableFuncIcon"
								aria-hidden="true"></span><span langtag='langtag_confirm'>确认</span>
						</button>
					</div>
				</div>
			</div>
		</div>
		<!-- 编辑模态框 end -->
		
		<!-- 编辑模态框 begin -->
		<div class="modal fade" id="updatePassDiv" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-dialog-center" style="width:600px;height:360px;transform:translateX(-50%) translateY(-50%);">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only"><span
								langtag='langtag_cancel'>取消</span></span>
						</button>
						<h4 class="modal-title" id="myModalLabel">
							<span langtag='langtag_updatePass'>修改密码</span>
						</h4>
					</div>
					<div class="modal-body">
						<form id="up_Form" role="form" class="form-horizontal m-t">
							<input id="upid" name="id" value="${cUserSession.id}" style="display:none;"/>
							<div class="form-group">
								<label class="col-sm-4 control-label"><span style="color:red">* </span><span
									langtag='langtag_username'>账号</span> : </label>
								<div class="col-sm-6">
									<input id="upname" name="name" class="form-control"
										type="text" aria-required="true" aria-invalid="true" value="${cUserSession.name}" disabled="disabled">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 control-label"><span style="color:red">* </span><span
									langtag='langtag_oldPass'>原密码</span> : </label>
								<div class="col-sm-6">
									<input id="upoldpass" name="old_pass" class="form-control"
										type="password" aria-required="true" aria-invalid="true">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 control-label"><span style="color:red">* </span><span
									langtag='langtag_newPass'>新密码</span> : </label>
								<div class="col-sm-6">
									<input id="upnewpass" name="new_pass" class="form-control"
										type="password" aria-required="true" aria-invalid="true">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 control-label"><span style="color:red">* </span><span
									langtag='langtag_rePass'>确认密码</span> : </label>
								<div class="col-sm-6">
									<input id="uprepass" name="re_pass" class="form-control"
										type="password" aria-required="true" aria-invalid="true">
								</div>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">
							<span class="glyphicon glyphicon-remove tableFuncIcon"
								aria-hidden="true"></span><span langtag='langtag_cancel'>取消</span>
						</button>
						<button type="button" class="btn btn-primary"
							onclick="updatePassValidate()">
							<span class="glyphicon glyphicon-floppy-disk tableFuncIcon"
								aria-hidden="true"></span><span langtag='langtag_confirm'>确认</span>
						</button>
					</div>
				</div>
			</div>
		</div>
		<!-- 编辑模态框 end -->
		
	</div>

	<script type="text/javascript">
		var cUserSessionId = '${cUserSession.id}';
		//var language = '${lang}';

		var treeData = "";
		var tempValue = "";
		var buttonPer = "";
		var faceRecog = "portal-demo";
		if (language == 'en'){
			faceRecog = 'portal-demon';
		}
		
		if (cUserSessionId == 42 || cUserSessionId == 46){
			if (language == 'en'){
				faceRecog = 'portal-demo';
			} else {
				faceRecog = "portal-demo";
			}
		}

		$(function() {
			showLanguageText();
			date_show();
			
			$("#nav-menu").mouseover(function(){
	        	//$("#trangle").show();
	            $("#children").show();
	        });
	 
	        $("#nav-menu").mouseout(function(){
	        	//$("#trangle").hide();
	            $("#children").hide();
	        });
			
			// 初始化用户菜单
			$
					.ajax({
						type : 'post',
						data : {
							userid : cUserSessionId
						},
						url : 'getUserMenu',
						success : function(data) {
							result = data;
							treeData = "<li class='nav-header'> <div class='dropdown profile-element'> <span class='clear'> <img src='${pageContext.request.contextPath}/assets/favicon.ico' style='float:left;margin-right:10px;width:25px;margin-top:2px;'/> <span class='block m-t-xs' style='font-size:14px;'> <strong class='font-bold'>" + faceRecog + "</strong> </span> </span> </div> <div class='logo-element'>Portal</div> </li>";
							treeData = treeData + allnode(1, 0, haschild(1));
							$("#side-menu").html(treeData);

							//菜单点击
							$(".J_menuItem").on('click', function() {
								var url = $(this).attr('href');
								$("#J_iframe").attr('src', url);
								showLanguageText();
								return false;
							});

							// MetsiMenu
							$('#side-menu').metisMenu();

							// 打开右侧边栏
							$('.right-sidebar-toggle').click(function() {
								$('#right-sidebar').toggleClass('sidebar-open');
							});

							//固定菜单栏
							$(function() {
								$('.sidebar-collapse').slimScroll({
									height : '100%',
									railOpacity : 0.9,
									alwaysVisible : false
								});
							});

							// 菜单切换
							$('.navbar-minimalize').click(function() {
								$("body").toggleClass("mini-navbar");
								SmoothlyMenu();
							});

							// 侧边栏高度
							function fix_height() {
								var heightWithoutNavbar = $("body > #wrapper")
										.height() - 61;
								$(".sidebard-panel").css("min-height",
										heightWithoutNavbar + "px");
							}
							fix_height();

							$(window).bind("load resize click scroll", function() {
								if (!$("body").hasClass('body-small')) {
									fix_height();
								}
							});

							//侧边栏滚动
							$(window).scroll(function() {
								if ($(window).scrollTop() > 0
										&& !$('body').hasClass(
												'fixed-nav')) {
									$('#right-sidebar').addClass(
											'sidebar-top');
								} else {
									$('#right-sidebar').removeClass(
											'sidebar-top');
								}
							});

							$('.full-height-scroll').slimScroll({
								height : '100%'
							});

							$('#side-menu>li').click(function() {
								if ($('body').hasClass('mini-navbar')) {
									NavToggle();
								}
							});
							$('#side-menu>li li a').click(function() {
								if ($(window).width() < 769) {
									NavToggle();
								}
							});

							$('.nav-close').click(NavToggle);

							//ios浏览器兼容性处理
							if (/(iPhone|iPad|iPod|iOS)/i
									.test(navigator.userAgent)) {
								$('#content-main').css('overflow-y', 'auto');
							}

							$(window).bind("load resize", function() {
								if ($(this).width() < 769) {
									$('body').addClass('mini-navbar');
									$('.navbar-static-side').fadeIn();
								}
							});

							function NavToggle() {
								$('.navbar-minimalize').trigger('click');
							}

							function SmoothlyMenu() {
								if (!$('body').hasClass('mini-navbar')) {
									$('#side-menu').hide();
									setTimeout(function() {
										$('#side-menu').fadeIn(500);
									}, 100);
								} else if ($('body').hasClass('fixed-sidebar')) {
									$('#side-menu').hide();
									setTimeout(function() {
										$('#side-menu').fadeIn(500);
									}, 300);
								} else {
									$('#side-menu').removeAttr('style');
								}
							}
							
							$('#side-menu>li a').click(function() {
				                $(this).parent("li").addClass("active");
				                $(this).parent("li").siblings().removeClass("active").children("ul.in").collapse("hide");
				            });

						}
					});
			
			//递归出来的值
			function allnode(index, childNum, hasChild) {
				for (var row = 0; row < result.length; row++) {
					if (row == 0){
						$("#J_iframe").attr('src', '${pageContext.request.contextPath}' + result[row].url);
					}
					if (result[row].ptype == 0) {//菜单权限
						if (result[row].flevel == index) {
							if (result[row].state == 0) {
								childNum++;
								tempValue = tempValue
										+ "<li> <a class='J_menuItem' href='${pageContext.request.contextPath}"
										+ result[row].url + "'> <i class='fa "
										+ result[row].icon
										+ "'></i> <span class='nav-label'>"
										+ (language == 'cn' ? result[row].cn_name
												: result[row].en_name)
										+ "</span> </a> </li>";
							} else if (result[row].state == 1) {//有子节点
								if (haschild(result[row].id) == 0) {//无子节点
									childNum++;
									tempValue = tempValue
											+ "<li> <a class='J_menuItem' href='${pageContext.request.contextPath}"
											+ result[row].url + "'> <i class='fa "
											+ result[row].icon
											+ "'></i> <span class='nav-label'>"
											+ (language == 'cn' ? result[row].cn_name : result[row].en_name)
											+ "</span> </a> </li>";
								} else {
									childNum++;
									tempValue = tempValue
											+ "<li> <a href='#'> <i class='fa "
											+ result[row].icon
											+ "'></i> <span class='nav-label'>"
											+ (language == 'cn' ? result[row].cn_name
													: result[row].en_name)
											+ "</span> <span class='fa arrow'></span> </a> <ul class='nav nav-second-level'>";
									tempValue = allnode(result[row].id, 0, haschild(result[row].id));
								}
							}
							if (hasChild == childNum) {
								tempValue = tempValue + "</ul></li>";
								continue;
							}
						} else {
							continue;
						}
					}

					if (index == 1 && result[row].ptype == 1) {// 按钮权限
						buttonPer = buttonPer + result[row].url + "|";
					}
				}
				return tempValue;
			}

			function haschild(index) {
				var count = 0;
				for (var row = 0; row < result.length; row++) {
					if (result[row].ptype == 0) {
						if (result[row].flevel == index) {
							count++;
						}
					}
				}
				return count;
			}
			
			function date_show(){
		        var week;
		        if (language == 'en'){
		        	week = ["Sun.", "Mon.", "Tues.", "Wed.", "Thur.", "Fri.", "Sat."];
		        } else {
		        	week = ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"];
		        }
		        var d = new Date();
		        var localToday = d.getFullYear() + "-" + (d.getMonth() + 1) + "-" + d.getDate();
		        document.getElementById('todayWeek').innerHTML = localToday + "&nbsp;&nbsp;&nbsp;" + week[d.getDay()];
			}

		});
		
		function updateInfoValidate() {
			var id = ui_Form['uiid'].value;
			var contact = ui_Form['uicontact'].value;
			var phone = ui_Form['uiphone'].value;
			
			if(!validateFormForContact(lang[language + ".contact"], contact)){
				return false;
			}
			if(!validateFormForPhone(lang[language + ".phone"], phone)){
				return false;
			}
			
			updateInfo(id, contact, phone);
		}
		
		function updateInfo(id, contact, phone){
			var data = {
				id : id,
				contact : contact,
				phone : phone
			};
			$.ajax({
				type : 'post',
				url : '${pageContext.request.contextPath}/userController/updateInfo',
				data : data,
				dataType : 'json',
				success : function(result) {
					if (result.status == "success") {
						$('#updateInfoDiv').modal('hide');
						face_succ(lang[language + ".editSucc"]);
					} else {
						return false;
					}
				}
			});
		}
		
		function updatePassValidate() {
			var id = up_Form['upid'].value;
			var pwd = up_Form['upoldpass'].value;
			var newpwd = up_Form['upnewpass'].value;
			var repwd = up_Form['uprepass'].value;
			
			if (pwd == "") {
				face_alert(lang[language + ".passNull"]);
				return false;
			}
			if(!validateForm1(lang[language + ".oldPass"], pwd)){
				return false;
			}
			if (newpwd == "") {
				face_alert(lang[language + ".passNull"]);
				return false;
			}
			if(!validateForm1(lang[language + ".newPass"], newpwd)){
				return false;
			}
			if (repwd == "") {
				face_alert(lang[language + ".passNull"]);
				return false;
			}
			if(!validateForm1(lang[language + ".rePass"], repwd)){
				return false;
			}
			if (newpwd == pwd) {
				face_alert(lang[language + ".newpassSame"]);
				return false;
			}
			if (repwd != newpwd) {
				face_alert(lang[language + ".passNotSame"]);
				return false;
			}
			
			updatePass(id, pwd, newpwd);
		}
		
		function updatePass(id, pwd, newpwd){
			var data = {
				id : id,
				name : '${cUserSession.name}',
				pwd : pwd,
				newpwd : newpwd
			};
			$.ajax({
				type : 'post',
				url : '${pageContext.request.contextPath}/userController/updatePass',
				data : data,
				dataType : 'json',
				success : function(result) {
					if (result.status == "success") {
						$('#updatePassDiv').modal('hide');
						face_succ_confirm(lang[language + ".updatePwdSucc"]);
						//点击确定事件
						$("#successConfirmButton").click(function(){
							location.href = "${pageContext.request.contextPath}/userController/loginOut";
						});
					} else if (result.status == "error") {
						face_alert(lang[language + ".oldpassWrong"]);
						return false;
					} else {
						return false;
					}
				}
			});
		}
		
		function clearUpdatePassForm() {
			up_Form['upoldpass'].value = "";
			up_Form['upnewpass'].value = "";
			up_Form['uprepass'].value = "";
		}
		
	</script>
</body>

</html>