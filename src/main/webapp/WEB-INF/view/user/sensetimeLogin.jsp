<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>portal-demo</title>
<meta name="keywords" content="">
<meta name="description" content="">
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/assets/favicon.ico">
<link
	href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css?v=3.3.7"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/assets/css/font-awesome.css?v=4.4.0"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/css/animate.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/assets/css/style.css?v=0.0.11"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/assets/css/plugins/toastr/toastr.min.css?v=0.0.1"
	rel="stylesheet">
<script language="javascript">
    //防止页面后退
   	history.pushState(null, null, document.URL);
    window.addEventListener('popstate', function () {
        history.pushState(null, null, document.URL);
    });
</script>
<script type="text/javascript">
	var url = window.location.href;
	if (url.indexOf("https") < 0) {
	    url = url.replace("http:", "https:");
	    window.location.replace(url);
	}
</script>
<script>
	if (window.top !== window.self) {
		window.top.location = window.location;
	}
	
	function keyLogin(){
		if (event.keyCode==13)  //回车键的键值为13
		document.getElementById("loginBT").click(); //调用登录按钮的登录事件
	}
</script>
</head>

<body onkeydown="keyLogin();" style="background-color: #428bca">
	<div class="middle-box text-center loginscreen  animated fadeInDown">
		<div class="logo" style="float:left;">
			<img src="${pageContext.request.contextPath}/assets/logo.png"/>
		</div>
		<div class="loginDiv">
			<div>
				<h3 id="faceRecog" style="float:left;margin:5px 0px 18px 0px;">商汤软件产品运营平台</h3>
				<span style="float:right;margin-top:5px;"><a href="#" onclick="updateLanguage('cn')"><small>中文</small></a> | <a href="#" onclick="updateLanguage('en')"><small>English</small></a></span>
			</div>
			<div class="form-group">
				<input type="hidden" id="lang" name="lang" value="cn"/>
				<input id="name" type="text" class="form-control" placeholder="用户名"
					required>
			</div>
			<div class="form-group">
				<input id="pwd" type="password" class="form-control"
					placeholder="密码" required>
			</div>
			<div class="form-group" style="height: 30px;">
				<input id="verficode" type="text" maxlength="4" class="form-control" style="width:55%;float: left;"
					placeholder="验证码" required>
				<img title="看不清，换一张"
								src="${pageContext.request.contextPath}/servlet/VerifiCodeFilter"
								style="float: left; margin-left: 10px;" id="validateCodeImg"
								onclick="changeImg(this,'nl')">
			</div>
			<button id="loginBT" type="submit" class="btn btn-primary block full-width m-b"
				onclick="login()">登 录</button>
			<p class="text-muted text-center">
				<a href="#" onclick="forgetPassClick1()"><small langtag="langtag_forgetpass">找回密码</small></a>
			</p>
		</div>
		<div class="copyright">
			<!-- <p>Copyright © 2012-2018 allensu</p> -->
			<p langtag="langtag_compatibility">建议使用IE11+、火狐、chrome浏览器</p>
		</div>
	</div>
	
	<!-- 模态框 begin -->
	<div class="modal fade" id="infoModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true" style="z-index: 3000 !important;">
		<div class="modal-dialog modal-dialog-center" style="width:400px;transform:translateX(-50%) translateY(-50%);">
			<div class="modal-content">
				<div class="modal-body" style="padding:20px 30px;">
					<label class="control-label">
						<span class="glyphicon glyphicon-exclamation-sign tableFuncIcon"
							aria-hidden="true" style="color: #337ab7"></span><span id="infoText"></span>
					</label>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">
						<span langtag='langtag_confirm'>确认</span>
					</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 模态框 end -->
	
	<!-- 模态框 begin -->
	<div class="modal fade" id="confirmModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true" style="z-index: 3000 !important;">
		<div class="modal-dialog modal-dialog-center" style="width:400px;transform:translateX(-50%) translateY(-50%);">
			<div class="modal-content">
				<div class="modal-body" style="padding:20px 30px;">
					<label class="control-label">
						<span id="confirmText"></span>
					</label>
				</div>
				<div class="modal-footer">
					<button id="confirmButton" type="button" class="btn btn-primary">
						<span class="glyphicon glyphicon-floppy-disk tableFuncIcon"
							aria-hidden="true"></span><span langtag='langtag_confirm'>确认</span>
					</button> 
				</div>
			</div>
		</div>
	</div>
	<!-- 模态框 end -->
	
	<!-- 编辑模态框 begin -->
	<div class="modal fade" id="resetPassModel" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-center" style="width:600px;height:360px;transform:translateX(-50%) translateY(-50%);">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only"><span
							langtag='langtag_cancel'>取消</span></span>
					</button>
					<h4 class="modal-title" id="myModalLabel">
						<span langtag='langtag_resetPass'>重置密码</span>
					</h4>
				</div>
				<div class="modal-body">
					<form id="reset_Form" role="form" class="form-horizontal m-t">
						<div class="form-group">
							<label class="col-sm-4 control-label"><span style="color:red">* </span><span
								langtag='langtag_loginUser'>用户名</span> : </label>
							<div class="col-sm-6">
								<input id="resetname" name="name" class="form-control"
									type="text" aria-required="true" aria-invalid="true" disabled="disabled">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label"><span style="color:red">* </span><span
								langtag='langtag_oldPass'>原密码</span> : </label>
							<div class="col-sm-6">
								<input id="resetoldpass" name="old_pass" class="form-control"
									type="password" aria-required="true" aria-invalid="true">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label"><span style="color:red">* </span><span
								langtag='langtag_newPass'>新密码</span> : </label>
							<div class="col-sm-6">
								<input id="resetnewpass" name="new_pass" class="form-control"
									type="password" aria-required="true" aria-invalid="true">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label"><span style="color:red">* </span><span
								langtag='langtag_rePass'>确认密码</span> : </label>
							<div class="col-sm-6">
								<input id="resetrepass" name="re_pass" class="form-control"
									type="password" aria-required="true" aria-invalid="true">
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary"
						onclick="resetValidate()">
						<span class="glyphicon glyphicon-floppy-disk tableFuncIcon"
							aria-hidden="true"></span><span langtag='langtag_confirm'>确认</span>
					</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 编辑模态框 end -->
	
	<!-- 编辑模态框 begin -->
	<div class="modal fade" id="forgetpassModel1" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-center" style="width:600px;height:262px;transform:translateX(-50%) translateY(-50%);">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only"><span
							langtag='langtag_cancel'>取消</span></span>
					</button>
					<h4 class="modal-title" id="myModalLabel">
						<span langtag='langtag_forgetpassT1'>找回密码-邮箱验证</span>
					</h4>
				</div>
				<div class="modal-body">
					<form id="fp1_Form" role="form" class="form-horizontal m-t">
						<div class="form-group">
							<label class="col-sm-4 control-label"><span style="color:red">* </span><span
								langtag='langtag_email'>邮箱</span> : </label>
							<div class="col-sm-6">
								<input id="fp1email" name="email" class="form-control"
									type="text" aria-required="true" aria-invalid="true">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label"><span style="color:red">* </span><span
								langtag='langtag_verficode'>验证码</span> : </label>
							<div class="col-sm-6">
								<input id="fp1verficode" type="text" maxlength="4" class="form-control" style="width:100px;float:left;" required>
								<img title="看不清，换一张"
												src="${pageContext.request.contextPath}/servlet/VerifiCodeFilter"
												style="float: left; margin-left: 10px;" id="validateCodeImgf"
												onclick="changeImg(this,'nl')">
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary"
						onclick="forgetPassValidate1()">
						<span class="glyphicon glyphicon-floppy-disk tableFuncIcon"
							aria-hidden="true"></span><span langtag='langtag_next'>下一步</span>
					</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 编辑模态框 end -->
	
	<!-- 编辑模态框 begin -->
	<div class="modal fade" id="forgetpassModel2" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-center" style="width:600px;height:262px;transform:translateX(-50%) translateY(-50%);">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only"><span
							langtag='langtag_cancel'>取消</span></span>
					</button>
					<h4 class="modal-title" id="myModalLabel">
						<span langtag='langtag_forgetpassT2'>找回密码-安全验证</span>
					</h4>
				</div>
				<div class="modal-body">
					<form id="fp2_Form" role="form" class="form-horizontal m-t">
						<div class="form-group">
							<label class="col-sm-4 control-label"><span style="color:red">* </span><span
								langtag='langtag_email'>邮箱</span> : </label>
							<div class="col-sm-6">
								<input id="fp2email" name="email" class="form-control"
									type="text" aria-required="true" aria-invalid="true" disabled="disabled">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label"><span style="color:red">* </span><span
								langtag='langtag_emailSecurityCode'>邮箱验证码</span> : </label>
							<div class="col-sm-8">
								<input id="fp2emailcode" type="text" maxlength="8" class="form-control" style="width:120px;float:left;margin-right:5px;" required>
								<button id="sentMail" type="button" class="btn btn-primary" onclick="sendSecurityCode()">
									<span id="sentMailText" langtag='langtag_sendSecurityCode'>发送验证码</span>
								</button>
								<span id="sentMailMessage" style="padding-left:5px;"></span>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary"
						onclick="forgetPassValidate2()">
						<span class="glyphicon glyphicon-floppy-disk tableFuncIcon"
							aria-hidden="true"></span><span langtag='langtag_next'>下一步</span>
					</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 编辑模态框 end -->
	
	<!-- 编辑模态框 begin -->
	<div class="modal fade" id="forgetpassModel3" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-center" style="width:600px;height:310px;transform:translateX(-50%) translateY(-50%);">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only"><span
							langtag='langtag_cancel'>取消</span></span>
					</button>
					<h4 class="modal-title" id="myModalLabel">
						<span langtag='langtag_forgetpassT3'>找回密码-重置密码</span>
					</h4>
				</div>
				<div class="modal-body">
					<form id="fp3_Form" role="form" class="form-horizontal m-t">
						<div class="form-group">
							<label class="col-sm-4 control-label"><span style="color:red">* </span><span
								langtag='langtag_email'>邮箱</span> : </label>
							<div class="col-sm-6">
								<input id="fp3email" name="email" class="form-control"
									type="text" aria-required="true" aria-invalid="true" disabled="disabled">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label"><span style="color:red">* </span><span
								langtag='langtag_newPass'>新密码</span> : </label>
							<div class="col-sm-6">
								<input id="fp3newpass" name="new_pass" class="form-control"
									type="password" aria-required="true" aria-invalid="true">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label"><span style="color:red">* </span><span
								langtag='langtag_rePass'>确认密码</span> : </label>
							<div class="col-sm-6">
								<input id="fp3repass" name="re_pass" class="form-control"
									type="password" aria-required="true" aria-invalid="true">
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary"
						onclick="forgetPassValidate3()">
						<span class="glyphicon glyphicon-floppy-disk tableFuncIcon"
							aria-hidden="true"></span><span langtag='langtag_confirm'>确认</span>
					</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 编辑模态框 end -->
	
	<script type="text/javascript">
		var language = document.getElementById('lang').value;
	</script>
	
	<!-- 全局js -->
	<script
		src="${pageContext.request.contextPath}/assets/js/jquery.min.js?v=2.1.4"></script>
	<script
		src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js?v=3.3.6"></script>
	<script
		src="${pageContext.request.contextPath}/assets/js/plugins/toastr/toastr.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/assets/js/user/sensetimeLogin.js?v=0.0.2"></script>
	<script
		src="${pageContext.request.contextPath}/assets/js/language/languageComm.js?v=0.0.1"></script>
	<script type="text/javascript">
		toastr.options = {
	        closeButton: true,
	        debug: false,
	        progressBar: false,
	        positionClass: "toast-center-center",
	        onclick: null,
	        showDuration: "300",
	        hideDuration: "1000",
	        timeOut: "2000",
	        extendedTimeOut: "1000",
	        //showEasing: "swing",
	        //hideEasing: "linear",
	        showMethod: "fadeIn",
	        hideMethod: "fadeOut"
	    };
	</script>
	<script type="text/javascript">
		//刷新验证码
		function changeImg(obj, createTypeFlag) {
			document.getElementById(obj.id).src = "${pageContext.request.contextPath}/servlet/VerifiCodeFilter?createTypeFlag="
						+ createTypeFlag + "&" + Math.random();
		}
		
		function face_alert(text){
			toastr.warning(text);
			//$('#infoText').html(text);
			//$('#infoModal').modal('show');
		}
		
		function face_confirm(text){
			$('#confirmText').html(text);
			$('#confirmModal').modal('show');
			$("#confirmButton").unbind();
		}
	</script>
	<script language="javascript">
		function login() {
			var name = document.getElementById('name').value;
			var pwd = document.getElementById('pwd').value;
			var verficode = document.getElementById('verficode').value;
			var langs = document.getElementById('lang').value;
			if (name == "") {
				face_alert(lang[langs + ".userNull"]);
				return false;
			}
			if(!validateForm(lang[langs + ".loginUser"], name, langs)){
				return false;
			}
			if (pwd == "") {
				face_alert(lang[langs + ".passNull"]);
				return false;
			}
			if(!validateForm(lang[langs + ".loginPass"], pwd, langs)){
				return false;
			}
			if (verficode == "") {
				face_alert(lang[langs + ".verficodeNull"]);
				return false;
			}
			if(!validateForm(lang[langs + ".verficode"], verficode, langs)){
				return false;
			}
			
			userLogin(name, pwd, verficode, langs);
		}
		
		function userLogin(name, pwd, verficode, langs) {
			var data = {
				name : name,
				pwd : pwd,
				verficode : verficode,
				lang : langs
			};
			$
					.ajax({
						type : 'post',
						url : '${pageContext.request.contextPath}/userController/doLogin',
						data : data,
						dataType : 'json',
						success : function(result) {
							if (result.status == "success") {
								window.location.href = "${pageContext.request.contextPath}/userController/index";
							} else if(result.status == "notactive"){// 未激活
								face_confirm(lang[langs + ".notactive"]);
								changeImg(document.getElementById('validateCodeImg'),'nl');
								//点击确定事件
								$("#confirmButton").click(function(){
									$("#confirmModal").modal('hide');
									reset_Form['resetname'].value = result.message;
									reset_Form['resetoldpass'].value = "";
									reset_Form['resetnewpass'].value = "";
									reset_Form['resetrepass'].value = "";
									$("#resetPassModel").modal('show');
								});
							} else if(result.status == "freezed"){// 冻结
								face_alert(lang[langs + ".userFreezed"]);
								changeImg(document.getElementById('validateCodeImg'),'nl');
							} else if(result.status == "codeerror"){
								face_alert(lang[langs + ".verficodeWrong"]);
								changeImg(document.getElementById('validateCodeImg'),'nl');
								document.getElementById('verficode').value = "";
								document.getElementById('verficode').focus();
								return false;
							} else {
								face_alert(lang[langs + ".userpassWrong"]);
								changeImg(document.getElementById('validateCodeImg'),'nl');
								document.getElementById('name').value = "";
								document.getElementById('pwd').value = "";
								document.getElementById('verficode').value = "";
								document.getElementById('name').focus();
								return false;
							}
						}
					});
		}
		
		function resetValidate() {
			var name = reset_Form['resetname'].value;
			var pwd = reset_Form['resetoldpass'].value;
			var newpwd = reset_Form['resetnewpass'].value;
			var repwd = reset_Form['resetrepass'].value;
			
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
			if (repwd != newpwd) {
				face_alert(lang[language + ".passNotSame"]);
				return false;
			}
			
			reset(name, pwd, newpwd);
		}

		function reset(name, pwd, newpwd) {
			var data = {
				name : name,
				pwd : pwd,
				newpwd : newpwd
			};
			$
					.ajax({
						type : 'post',
						url : '${pageContext.request.contextPath}/userController/activeUser',
						data : data,
						dataType : 'json',
						success : function(result) {
							if (result.status == "success") {
								$('#resetPassModel').modal('hide');
								face_confirm(lang[language + ".activeSucc"]);
								//点击确定事件
								$("#confirmButton").click(function(){
									$("#confirmModal").modal('hide');
									changeImg(document.getElementById('validateCodeImg'),'nl');
									document.getElementById('name').value = "";
									document.getElementById('pwd').value = "";
									document.getElementById('verficode').value = "";
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
		
		function forgetPassClick1(){
			document.getElementById('fp1email').value = "";
			document.getElementById('fp1verficode').value = "";
			changeImg(document.getElementById('validateCodeImgf'),'nl');
			$("#forgetpassModel1").modal('show');
		}
		
		function forgetPassValidate1(){
			var email = fp1_Form['fp1email'].value;
			var verficode = fp1_Form['fp1verficode'].value;
			
			if(!validateForm2(lang[language + ".email"], email)){
				return false;
			}
			if (verficode == "") {
				face_alert(lang[language + ".verficodeNull"]);
				return false;
			}
			if(!validateForm(lang[language + ".verficode"], verficode, language)){
				return false;
			}
			
			forgetPass1(email, verficode);
		}
		
		function forgetPass1(email, verficode){
			var data = {
				email : email,
				verficode : verficode
			};
			$
					.ajax({
						type : 'post',
						url : '${pageContext.request.contextPath}/userController/emailValidate',
						data : data,
						dataType : 'json',
						success : function(result) {
							if (result.status == "success") {
								$("#forgetpassModel1").modal('hide');
								fp2_Form['fp2email'].value = email;
								document.getElementById('fp2emailcode').value = "";
								$("#forgetpassModel2").modal('show');
							} else if(result.status == "codeerror"){
								face_alert(lang[language + ".verficodeWrong"]);
								changeImg(document.getElementById('validateCodeImgf'),'nl');
								document.getElementById('fp1verficode').value = "";
								document.getElementById('fp1verficode').focus();
								return false;
							} else if (result.status == "emailNotExist") {
								face_alert(lang[language + ".emailNotExist"]);
								document.getElementById('fp1email').value = "";
								document.getElementById('fp1verficode').value = "";
								document.getElementById('fp1email').focus();
								return false;
							} else if(result.status == "notactive"){// 未激活
								$("#forgetpassModel1").modal('hide');
								face_confirm(lang[language + ".notactive"]);
								//点击确定事件
								$("#confirmButton").click(function(){
									$("#confirmModal").modal('hide');
									reset_Form['resetname'].value = result.message;
									reset_Form['resetoldpass'].value = "";
									reset_Form['resetnewpass'].value = "";
									reset_Form['resetrepass'].value = "";
									$("#resetPassModel").modal('show');
								});
							} else if(result.status == "freezed"){// 冻结
								$("#forgetpassModel1").modal('hide');
								face_alert(lang[language + ".userFreezed"]);
							} else {
								return false;
							}
						}
					});
		}
		
		function sendSecurityCode(){
			$("#sentMail").attr("disabled", true);
			var i = 60;
			var code = null;
			function timeshow() {
				i--;
				$("#sentMail").css({
					"background-color" : "#d0d0d0",
					"color" : "#666"
				});
				$("#sentMail").attr("disabled", true);
				$("#sentMailText").html(lang[language + ".send_again"] + "(" + i + "s)");
				if (i < 1) {
					clearInterval(code);
					$("#sentMail").css({
						"background-color" : "#428bca",
						"color" : "#fff"
					});
					$("#sentMail").attr("disabled", false);
					$("#sentMailText").html(lang[language + ".send_again"]);
					$("#sentMailMessage").html("");
				}
			}
			;
			code = setInterval(timeshow, 1000);

			var email = fp2_Form['fp2email'].value;
			var data = {
				email : email
			};
			$
			.ajax({
				type : 'post',
				url : '${pageContext.request.contextPath}/userController/sendSecurityCode',
				data : data,
				dataType : 'json',
				success : function(result) {
					if (result.status == "success") {
						$("#sentMailMessage").css({"color":"#999"});
						$("#sentMailMessage").html(lang[language + ".sendSucc"]);
					} else if (result.status == "sendover") {
						$("#sentMailMessage").css({"color":"red"});
						$("#sentMailMessage").html(lang[language + ".sendOver"]);
					} else {
						$("#sentMailMessage").css({"color":"red"});
						$("#sentMailMessage").html(lang[language + ".sendFail"]);
					}
				},
				error: function (XMLHttpRequest, textStatus, errorThrown) {
					$("#sentMailMessage").css({"color":"red"});
					$("#sentMailMessage").html(lang[language + ".sendFail"]);
				}
			});
		}
		
		function forgetPassValidate2(){
			var email = fp2_Form['fp2email'].value;
			var verficode = fp2_Form['fp2emailcode'].value;
			
			if (verficode == "") {
				face_alert(lang[language + ".verficodeNull"]);
				return false;
			}
			if(!validateForm(lang[language + ".verficode"], verficode, language)){
				return false;
			}
			
			forgetPass2(email, verficode);
		}
		
		function forgetPass2(email, verficode){
			var data = {
				verficode : verficode
			};
			$
			.ajax({
				type : 'post',
				url : '${pageContext.request.contextPath}/userController/securityValidate',
				data : data,
				dataType : 'json',
				success : function(result) {
					if (result.status == "success") {
						$("#forgetpassModel2").modal('hide');
						fp3_Form['fp3email'].value = email;
						fp3_Form['fp3newpass'].value = "";
						fp3_Form['fp3repass'].value = "";
						$("#forgetpassModel3").modal('show');
					} else {
						face_alert(lang[language + ".verficodeWrong"]);
						document.getElementById('fp2emailcode').value = "";
						document.getElementById('fp2emailcode').focus();
						return false;
					}
				}
			});
		}
		
		function forgetPassValidate3() {
			var email = fp3_Form['fp3email'].value;
			var newpwd = fp3_Form['fp3newpass'].value;
			var repwd = fp3_Form['fp3repass'].value;
			
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
			if (repwd != newpwd) {
				face_alert(lang[language + ".passNotSame"]);
				return false;
			}
			
			forgetPass3(email, newpwd);
		}
		
		function forgetPass3(email, newpwd){
			var data = {
				email : email,
				pwd : newpwd
			};
			$
			.ajax({
				type : 'post',
				url : '${pageContext.request.contextPath}/userController/resetPassword',
				data : data,
				dataType : 'json',
				success : function(result) {
					if (result.status == "success") {
						$("#forgetpassModel3").modal('hide');
						face_confirm(lang[language + ".resetSucc"]);
						//点击确定事件
						$("#confirmButton").click(function(){
							$("#confirmModal").modal('hide');
							changeImg(document.getElementById('validateCodeImg'),'nl');
							document.getElementById('name').value = "";
							document.getElementById('pwd').value = "";
							document.getElementById('verficode').value = "";
						});
					} else {
						return false;
					}
				}
			});
		}
		
	</script>
</body>
</html>
