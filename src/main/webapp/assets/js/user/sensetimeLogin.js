var lang = {
	'cn.loginBT' : '登 录',
	'cn.loginUser' : '用户名',
	'cn.loginPass' : '密码',
	'cn.verficode' : '验证码',
	'cn.userNull' : '用户名不能为空',
	'cn.passNull' : '密码不能为空',
	'cn.verficodeNull' : '验证码不能为空',
	'cn.verficodeWrong' : '验证码错误，请重新输入！',
	'cn.userpassWrong' : '用户名或密码错误，请重新输入！',
	'cn.faceRecog' : '商汤软件产品运营平台',
	
	'en.loginBT' : 'Login',
	'en.loginUser' : 'User Name',
	'en.loginPass' : 'Password',
	'en.verficode' : 'Verification Code',
	'en.userNull' : 'Please enter User Name',
	'en.passNull' : 'Please enter Password',
	'en.verficodeNull' : 'Please enter Verification Code',
	'en.verficodeWrong' : 'Verification Code is incorrectly entered, please reinput!',
	'en.userpassWrong' : 'User Name or Password is incorrectly entered, please reinput!',
	'en.faceRecog' : 'Sensetime Platform',
	
	'cn.szx' : '只支持数字、字母、下划线、点、@、横杠',
	'en.szx' : 'only support numbers、letters、underlines、point、@、-',
	
	'cn.notactive' : '该账号状态为未激活，请重置密码进行激活！',
	'en.notactive' : 'The account status is not activated. Please reset the password to activate!',
	'cn.oldPass' : '原密码',
	'en.oldPass' : 'Initial Password',
	'cn.newPass' : '新密码',
	'en.newPass' : 'New Password',
	'cn.rePass' : '确认密码',
	'en.rePass' : 'Confirm Password',
	'cn.confirm' : '确认',
	'en.confirm' : 'Confirm',
	'cn.resetPass' : '重置密码',
	'en.resetPass' : 'Reset Password',
	'cn.passNotSame' : '新密码与确认密码不一致',
	'en.passNotSame' : 'New Password is not consistent with Confirm password',
	'cn.activeSucc' : '激活成功，请重新登录！',
	'en.activeSucc' : 'Active successful, please login again!',
	'cn.notNull' : '不能为空',
	'en.notNull' : 'can not be empty',
	'cn.length620' : '长度为6-20个字符',
	'en.length620' : 'length is 6-20 characters',
	'cn.oldpassWrong' : '原密码错误，请重新输入！！！',
	'en.oldpassWrong' : 'Initial Password is incorrectly entered, please reinput!!!',
	'cn.forgetpass' : '找回密码',
	'en.forgetpass' : 'Forgot Password',
	'cn.email' : '邮箱',
	'en.email' : 'Email',
	'cn.forgetpassT1' : '找回密码-邮箱验证',
	'en.forgetpassT1' : 'Forgot Password - Email Verification',
	'cn.next' : '下一步',
	'en.next' : 'Next Step',
	'cn.emailNotExist' : '邮箱不存在',
	'en.emailNotExist' : 'Email not exist',
	'cn.forgetpassT2' : '找回密码-安全验证',
	'en.forgetpassT2' : 'Forgot Password - Security Verification',
	'cn.emailSecurityCode' : '邮箱安全码',
	'en.emailSecurityCode' : 'Security Code',
	'cn.sendSecurityCode' : '发送验证码',
	'en.sendSecurityCode' : 'Send',
	'cn.send_again' : '重新发送',
	'en.send_again' : 'Resend',
	'cn.sendSucc' : '发送成功',
	'en.sendSucc' : 'Send successful',
	'cn.sendFail' : '发送失败',
	'en.sendFail' : 'Send fail',
	'cn.sendOver' : '发送超过上限',
	'en.sendOver' : 'Exceed the upper limit',
	'cn.resetSucc' : '密码重置成功，请重新登录！',
	'en.resetSucc' : 'Reset password successful, please login again!',
	'cn.userFreezed' : '该账号已冻结，请联系管理员！',
	'en.userFreezed' : 'The account status is freezed. Please contact the administrator!',
	'cn.compatibility' : '建议使用IE11+、火狐、chrome浏览器',
	'en.compatibility' : 'IE11+, Firefox, Chrome is suggested'
	
};

$(function() {

});

function updateLanguage(langs) {
	$('#lang').val(langs);
	language = langs;
	
	$('#name').attr('placeholder',lang[langs + '.' + 'loginUser']);
	$('#pwd').attr('placeholder',lang[langs + '.' + 'loginPass']);
	$('#verficode').attr('placeholder',lang[langs + '.' + 'verficode']);
	$('#loginBT').html(lang[langs + '.' + 'loginBT']);
	$('#faceRecog').html(lang[langs + '.' + 'faceRecog']);
	
	showLanguageText();
}

function validateForm(name, message, language) {
	 var szx = /^[a-zA-Z0-9_.@-]+$/g;
	 if (!szx.test(message)) {
		face_alert(name + " " + lang[language + ".szx"]);
		return false;
	 } else {
		return true;
	 }
}

//1-不能为空 2-数据字母下划线点@- 3-6-20长度
function validateForm1(name, message) {
	 var szx = /^[a-zA-Z0-9_.@-]+$/g;
	 if (message == ""){
		face_alert(name + " " + lang[language + ".notNull"]);
		return false;
	 } else if (!szx.test(message)) {
		face_alert(name + " " + lang[language + ".szx"]);
		return false;
	 } else if(getByteLen(message) < 6 || getByteLen(message) > 20){
		face_alert(name + " " + lang[language + ".length620"]);
		return false;
	 } else {
		return true;
	 }
}

//1-不为空 2-邮箱格式
function validateForm2(name, message) {
	 var szx = /^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$/g;
	 if (message == ""){
		face_alert(name + " " + lang[language + ".notNull"]);
		return false;
	 } else if (!szx.test(message)) {
		face_alert(name + " " + lang[language + ".emailFormat"]);
		return false;
	 } else {
		return true;
	 }
}

//获取字符长度 
function getByteLen(val) {
    var len = 0;
    for (var i = 0; i < val.length; i++) {
        var a = val.charAt(i);
        if (a.match(/[^\x00-\xff]/ig) != null) {
            len += 2;
        }
        else {
            len += 1;
        }
    }
    return len;
}
