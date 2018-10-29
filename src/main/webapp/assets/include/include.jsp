<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/assets/favicon.ico">
<link
	href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css?v=0.0.35"
	rel="stylesheet" type="text/css">
<link
	href="${pageContext.request.contextPath}/assets/css/plugins/bootstrap-table/bootstrap-table.css?v=0.0.12"
	rel="stylesheet" type="text/css">
<link
	href="${pageContext.request.contextPath}/assets/css/plugins/treeview/bootstrap-treeview.css?v=0.0.12"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/assets/css/plugins/multiselect/bootstrap-multiselect.css?v=0.0.1"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/assets/css/plugins/datepicker/jquery.datetimepicker.css?v=0.0.1"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/assets/css/plugins/poshytip/jquery.poshytip.css?v=0.0.6"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/assets/jquery-ui/jquery-ui.min.css?v=0.0.4"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/css/animate.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/assets/css/dashboard.css?v=0.0.1"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/assets/css/style.css?v=0.0.65"
	rel="stylesheet">
<script src="${pageContext.request.contextPath}/assets/js/jquery.min.js?v=0.0.1"></script>
<script
	src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js?v=0.0.1"></script>
<script
	src="${pageContext.request.contextPath}/assets/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
<script
	src="${pageContext.request.contextPath}/assets/js/plugins/treeview/bootstrap-treeview.js?v=0.0.1"></script>
<script
	src="${pageContext.request.contextPath}/assets/js/pinyin.js?v=0.0.1"></script>
<script
	src="${pageContext.request.contextPath}/assets/js/plugins/multiselect/bootstrap-multiselect.js?v=0.0.3"></script>
<script
	src="${pageContext.request.contextPath}/assets/js/plugins/datepicker/jquery.datetimepicker.js?v=0.0.1"></script>
<script
	src="${pageContext.request.contextPath}/assets/js/plugins/poshytip/jquery.poshytip.js?v=0.0.1"></script>
<script
	src="${pageContext.request.contextPath}/assets/js/plugins/bootstrap-table/extensions/toolbar/bootstrap-table-toolbar.js"></script>
<script
	src="${pageContext.request.contextPath}/assets/js/plugins/bootstrap-table/extensions/flat-json/bootstrap-table-flat-json.js"></script>
<script
	src="${pageContext.request.contextPath}/assets/js/plugins/bootstrap-table/extensions/multiple-sort/bootstrap-table-multiple-sort.js"></script>
<script
	src="${pageContext.request.contextPath}/assets/js/plugins/metisMenu/jquery.metisMenu.js?v=0.0.4"></script>
<script
	src="${pageContext.request.contextPath}/assets/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<script
	src="${pageContext.request.contextPath}/assets/js/plugins/layer/layer.min.js"></script>
<script
	src="${pageContext.request.contextPath}/assets/jquery-ui/jquery-ui.min.js?v=0.0.2"></script>
<script
	src="${pageContext.request.contextPath}/assets/js/language/language.js?v=0.0.59"></script>
<script
	src="${pageContext.request.contextPath}/assets/js/language/languageComm.js?v=0.0.1"></script>
<link href="${pageContext.request.contextPath}/assets/css/plugins/toastr/toastr.min.css?v=0.0.10" rel="stylesheet" />
<script src="${pageContext.request.contextPath}/assets/js/plugins/toastr/toastr.min.js"></script>
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
</head>
<body>
	<!-- 模态框 begin -->
	<div class="modal fade" id="confirmModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true" style="z-index: 3000 !important;">
		<div class="modal-dialog modal-dialog-center" style="width:400px;transform:translateX(-50%) translateY(-50%);">
			<div class="modal-content">
				<div class="modal-body" style="padding:20px 30px;word-break:break-all;">
					<label class="control-label">
						<span id="confirmText1"></span>
						<span id="confirmText2"></span>
					</label>
				</div>
				<div class="modal-footer">
					<button id="confirmButton" type="button" class="btn btn-primary">
						<span class="glyphicon glyphicon-floppy-disk tableFuncIcon"
							aria-hidden="true"></span><span langtag='langtag_confirm'>确认</span>
					</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">
						<span class="glyphicon glyphicon-remove tableFuncIcon"
							aria-hidden="true"></span><span langtag='langtag_cancel'>取消</span>
					</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 模态框 end -->
	
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
					<button id="infoButton" type="button" class="btn btn-default" data-dismiss="modal">
						<span langtag='langtag_confirm'>确认</span>
					</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 模态框 end -->
	
	<!-- 模态框 begin -->
	<div class="modal fade" id="successModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true" style="z-index: 3000 !important;">
		<div class="modal-dialog modal-dialog-center" style="width:400px;transform:translateX(-50%) translateY(-50%);">
			<div class="modal-content">
				<div class="modal-body" style="padding:20px 30px;">
					<label class="control-label">
						<span class="glyphicon glyphicon-ok-sign tableFuncIcon"
							aria-hidden="true" style="color: green"></span><span id="successText"></span>
					</label>
				</div>
				<div class="modal-footer">
					<button id="successConfirmButton" type="button" class="btn btn-default" data-dismiss="modal">
						<span langtag='langtag_confirm'>确认</span>
					</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 模态框 end -->
	
</body>
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
<script type="text/javascript">
	var language = '${lang}';

	function face_confirm(text1, text2){
		$('#confirmText1').html(text1);
		$('#confirmText2').html(text2);
		$('#confirmModal').modal('show');
		$("#confirmButton").unbind();
	}
	function face_alert(text){
		toastr.warning(text);
		//$('#infoText').html(text);
		//$('#infoModal').modal('show');
	}
	function face_alert_confirm(text){
		$('#infoText').html(text);
		$('#infoModal').modal('show');
	}
	function face_succ(text){
		toastr.success(text);
		//$('#successText').html(text);
		//$('#successModal').modal('show');
	}
	function face_succ_confirm(text){
		$('#successText').html(text);
		$('#successModal').modal('show');
	}
	
	// 1-不能为空 2-数据字母下划线点@- 3-6-20长度
	function validateForm1(name, message) {
		 var szx = /^[a-zA-Z0-9_.@-]+$/g;
		 if (message.trim() == ""){
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
	
	// 1-可以为空 2-中文数据字母下划线空格 3-长度<=100
	function validateForm2(name, message) {
		 var szx = /^[a-zA-Z 0-9_\u4e00-\u9fa5]+$/g;
		 if (message.trim() == ""){
			return true;
		 } else {
			if (!szx.test(message)) {
				face_alert(name + " " + lang[language + ".zszx"]);
				return false;
			 } else if(getByteLen(message) > 100){
				face_alert(name + " " + lang[language + ".length100"]);
				return false;
			 } else {
				return true;
			 }
		 }
	}
	
	// 1-不能为空 2-邮箱格式 3-长度不超过50
	function validateForm3(name, message) {
		 var szx = /^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$/g;
		 if (message.trim() == ""){
			face_alert(name + " " + lang[language + ".notNull"]);
			return false;
		 } else if (!szx.test(message)) {
			face_alert(name + " " + lang[language + ".emailFormat"]);
			return false;
		 } else if(getByteLen(message) > 50){
			face_alert(name + " " + lang[language + ".length50"]);
			return false;
		 } else {
			return true;
		 }
	}
	
	// 1-不能为空 2-中文数据字母下划线 3-6-20长度
	function validateForm4(name, message) {
		var szx = /^[a-zA-Z0-9_\u4e00-\u9fa5]+$/g;
		 if (message.trim() == ""){
			face_alert(name + " " + lang[language + ".notNull"]);
			return false;
		 } else if (!szx.test(message)) {
			face_alert(name + " " + lang[language + ".zszx"]);
			return false;
		 } else if(getByteLen(message) < 6 || getByteLen(message) > 20){
			face_alert(name + " " + lang[language + ".length620"]);
			return false;
		 } else {
			return true;
		 }
	}
	
	// 1-不能为空 2-中文数据字母下划线空格 3-长度<=20
	function validateForm5(name, message) {
		var szx = /^[a-zA-Z 0-9_\u4e00-\u9fa5]+$/g;
		 if ($.trim(message) == ""){
			face_alert(name + " " + lang[language + ".notNull"]);
			return false;
		 } else if (!szx.test(message)) {
			face_alert(name + " " + lang[language + ".zszxk"]);
			return false;
		 } else if(getByteLen(message) > 20){
			face_alert(name + " " + lang[language + ".length20"]);
			return false;
		 } else {
			return true;
		 }
	}
	
	// 1-不能为空 2-数据字母下划线空格 3-长度<=20
	function validateForm6(name, message) {
		var szx = /^[a-z A-Z0-9_]+$/g;
		 if ($.trim(message) == ""){
			face_alert(name + " " + lang[language + ".notNull"]);
			return false;
		 } else if (!szx.test(message)) {
			face_alert(name + " " + lang[language + ".szxk"]);
			return false;
		 } else if(getByteLen(message) > 20){
			face_alert(name + " " + lang[language + ".length20"]);
			return false;
		 } else {
			return true;
		 }
	}
	
	// 1-可以为空 2-数据字母下划线横杠斜杠 3-长度<=100
	function validateForm7(name, message) {
		 var szx = /^[a-z\/A-Z 0-9_-]+$/g;
		 if (message.trim() == ""){
			return true;
		 } else {
			if (!szx.test(message)) {
				face_alert(name + " " + lang[language + ".szxhx"]);
				return false;
			 } else if(getByteLen(message) > 100){
				face_alert(name + " " + lang[language + ".length100"]);
				return false;
			 } else {
				return true;
			 }
		 }
	}
	
	// 1-不能为空 2-数据字母下划线 3-长度<=30
	function validateForm8(name, message) {
		 var szx = /^\w+$/g;
		 if (message.trim() == ""){
			face_alert(name + " " + lang[language + ".notNull"]);
			return false;
		 } else if (!szx.test(message)) {
			face_alert(name + " " + lang[language + ".szx"]);
			return false;
		 } else if(getByteLen(message) > 30){
			face_alert(name + " " + lang[language + ".length30"]);
			return false;
		 } else {
			return true;
		 }
	}
	
	// 1-不能为空 2-正整数
	function validateForm9(name, message) {
		 var szx = /^[1-9]\d*$/g;
		 if (message.trim() == ""){
			face_alert(name + " " + lang[language + ".notNull"]);
			return false;
		 } else if (!szx.test(message)) {
			face_alert(name + " " + lang[language + ".positiveInt"]);
			return false;
		 } else {
			return true;
		 }
	}
	
	// 1-不能为空 2-数据字母下划线空格 3-长度<=100
	function validateForm10(name, message) {
		var szx = /^[a-z A-Z0-9_]+$/g;
		 if ($.trim(message) == ""){
			face_alert(name + " " + lang[language + ".notNull"]);
			return false;
		 } else if (!szx.test(message)) {
			face_alert(name + " " + lang[language + ".szxk"]);
			return false;
		 } else if(getByteLen(message) > 100){
			face_alert(name + " " + lang[language + ".length100"]);
			return false;
		 } else {
			return true;
		 }
	}
	
	// 1-不能为空 2-长度<=100
	function validateForm11(name, message) {
		 if (message.trim() == ""){
			face_alert(name + " " + lang[language + ".notNull"]);
			return false;
		 } else if(getByteLen(message) > 100){
			face_alert(name + " " + lang[language + ".length100"]);
			return false;
		 } else {
			return true;
		 }
	}
	
	// 1-可以为空 2-手机格式
	function validateForm12(name, message) {
		 var szx = /^(0?(13[0-9]|15[012356789]|17[013678]|18[0-9]|14[57])[0-9]{8})|(400|800)([0-9\\-]{7,10})|(([0-9]{4}|[0-9]{3})(-| )?)?([0-9]{7,8})((-| |转)*([0-9]{1,4}))?$/g;
		 if (message.trim() == ""){
			return true;
		 } else {
			if (!szx.test(message)) {
				face_alert(name + " " + lang[language + ".phoneFormat"]);
				return false;
			 } else {
				return true;
			 }
		 }
	}
	
	// 1-不能为空 2-数据字母下划线加号减号横杠空格 3-长度<=100
	function validateForm13(name, message) {
		var szx = /^[a-z A-Z0-9_+-—)]+$/g;
		 if ($.trim(message) == ""){
			face_alert(name + " " + lang[language + ".notNull"]);
			return false;
		 } else if (!szx.test(message)) {
			face_alert(name + " " + lang[language + ".szxk13"]);
			return false;
		 } else if(getByteLen(message) > 100){
			face_alert(name + " " + lang[language + ".length100"]);
			return false;
		 } else {
			return true;
		 }
	}
	
	// 1-不能为空 2-中文数据字母下划线空格 3-长度<=100
	function validateForm14(name, message) {
		var szx = /^[a-zA-Z 0-9_\u4e00-\u9fa5]+$/g;
		 if ($.trim(message) == ""){
			face_alert(name + " " + lang[language + ".notNull"]);
			return false;
		 } else if (!szx.test(message)) {
			face_alert(name + " " + lang[language + ".zszx"]);
			return false;
		 } else if(getByteLen(message) > 100){
			face_alert(name + " " + lang[language + ".length100"]);
			return false;
		 } else {
			return true;
		 }
	}
	
	// 1-不能为空 2-数据字母下划线点横杠 3-不超过50长度
	function validateFormForChannel(name, message) {
		 var szx = /^[a-zA-Z0-9_.-]+$/g;
		 if (message.trim() == ""){
			face_alert(name + " " + lang[language + ".notNull"]);
			return false;
		 } else if (!szx.test(message)) {
			face_alert(name + " " + lang[language + ".szxdh"]);
			return false;
		 } else if(getByteLen(message) > 50){
			face_alert(name + " " + lang[language + ".length50"]);
			return false;
		 } else {
			return true;
		 }
	}
	
	// 联系人： 1-不能为空 2-中文数字字母空格 3-长度<=20
	function validateFormForContact(name, message) {
		 var szx = /^[a-zA-Z 0-9\u4e00-\u9fa5]+$/g;
		 if (message.trim() == ""){
			face_alert(name + " " + lang[language + ".notNull"]);
			return false;
		 } else if (!szx.test(message)) {
			face_alert(name + " " + lang[language + ".contactValidate"]);
			return false;
		 } else if(getByteLen(message) > 20){
			face_alert(name + " " + lang[language + ".length20"]);
			return false;
		 } else {
			return true;
		 }
	}
	
	// 联系电话：1-不能为空 2-手机格式
	function validateFormForPhone(name, message) {
		 var szx = /^(0?(13[0-9]|15[012356789]|17[013678]|18[0-9]|14[57])[0-9]{8})|(400|800)([0-9\\-]{7,10})|(([0-9]{4}|[0-9]{3})(-| )?)?([0-9]{7,8})((-| |转)*([0-9]{1,4}))?$/g;
		 var sh = /^[0-9-)]+$/g;
		 if (message.trim() == ""){
			face_alert(name + " " + lang[language + ".notNull"]);
			return false;
		 } else if (!szx.test(message) || !sh.test(message)) {
			face_alert(name + " " + lang[language + ".phoneFormat"]);
			return false;
		 } else {
			return true;
		 }
	}
	
	// 厂商中文名：1-不能为空 2-汉字数字字母下划线横杠点空格 3-长度<=50
	function validateFormForBrandCn(name, message) {
		var szx = /^[a-z A-Z0-9\u4e00-\u9fa5_.-]+$/g;
		 if ($.trim(message) == ""){
			face_alert(name + " " + lang[language + ".notNull"]);
			return false;
		 } else if (!szx.test(message)) {
			face_alert(name + " " + lang[language + ".brandcnValidate"]);
			return false;
		 } else if(getByteLen(message) > 50){
			face_alert(name + " " + lang[language + ".length50"]);
			return false;
		 } else {
			return true;
		 }
	}
	
	// 厂商英文名：1-不能为空 2-数字字母下划线横杠点空格 3-长度<=50
	function validateFormForBrandEn(name, message) {
		var szx = /^[a-z A-Z0-9_.-]+$/g;
		 if ($.trim(message) == ""){
			face_alert(name + " " + lang[language + ".notNull"]);
			return false;
		 } else if (!szx.test(message)) {
			face_alert(name + " " + lang[language + ".brandenValidate"]);
			return false;
		 } else if(getByteLen(message) > 50){
			face_alert(name + " " + lang[language + ".length50"]);
			return false;
		 } else {
			return true;
		 }
	}
	
	// 更新点：1-不能为空 2-长度<=1000
	function validateFormForContent(name, message) {
		 if ($.trim(message) == ""){
			face_alert(name + " " + lang[language + ".notNull"]);
			return false;
		 } else if(getByteLen(message) > 1000){
			face_alert(name + " " + lang[language + ".length1000"]);
			return false;
		 } else {
			return true;
		 }
	}
	
	// 获取字符长度 
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
	
	//返回JSON长度
	function JSONLength(obj) {
		var size = 0;
		for ( var key in obj) {
			if (obj.hasOwnProperty(key))
				size++;
		}
		return size;
	};
	//删除数组指定元素 
	function removeByValue(arr, val) {
		for (var i = 0; i < arr.length; i++) {
			if (arr[i] == val) {
				arr.splice(i, 1);
				break;
			}
		}
	}
	//获取数组最大值
	Array.max = function(array) {
		return Math.max.apply(Math, array);
	};
	//账号类型下拉框
	function initUserTypeSelect(id) {
		$(id).multiselect({
        	nonSelectedText : lang[language + ".chooseUserType"],
        	enableFiltering : true,
			maxHeight : 320,
			buttonWidth : 200
        }); 
	}
	//厂商下拉框
	function initBrandSelect(id) {
		$(id).multiselect({
        	nonSelectedText : lang[language + ".chooseBrand"],
        	enableFiltering : true,
			maxHeight : 320,
			buttonWidth : 200
        });
	    $.ajax({  
	        type : "get",  
	        url : "${pageContext.request.contextPath}/brandController/brandSelect", 
	        dataType : "json",  
	        success : function(data) {  
	            $(id).html("");
	            for (var i = 0; i < data.rows.length; i++) {
	            	if (language == 'en'){
	            		$(id).append("<option value='" + data.rows[i].id + "'>" + data.rows[i].brand_en + "</option>");
	            	} else {
	            		$(id).append("<option value='" + data.rows[i].id + "'>" + data.rows[i].brand_cn + "</option>");
	            	}
	            }  
	            $(id).multiselect("destroy").multiselect({
	            	nonSelectedText : lang[language + ".chooseBrand"],
	            	enableFiltering : true,
	            	enableCaseInsensitiveFiltering : true,
					maxHeight : 320,
					buttonWidth : 200
	            });  
	        }  
	    });  
	}
	//厂商下拉框
	function initBrandSelectM(id) {
		$(id).multiselect({
        	nonSelectedText : lang[language + ".chooseBrand"],
        	enableFiltering : true,
			maxHeight : 280,
			buttonWidth : 200
        });
	    $.ajax({  
	        type : "get",  
	        url : "${pageContext.request.contextPath}/brandController/brandSelect?v=" + Math.random(), 
	        dataType : "json",  
	        success : function(data) {  
	            $(id).html("");
	            for (var i = 0; i < data.rows.length; i++) {
	            	if (language == 'en'){
	            		$(id).append("<option value='" + data.rows[i].id + "'>" + data.rows[i].brand_en + "</option>");
	            	} else {
	            		$(id).append("<option value='" + data.rows[i].id + "'>" + data.rows[i].brand_cn + "</option>");
	            	}
	            }  
	            $(id).multiselect("destroy").multiselect({
	            	nonSelectedText : lang[language + ".noneselected"],
	            	selectAllText : lang[language + ".selectAll"],
	            	enableFiltering : true,
	            	enableCaseInsensitiveFiltering : true,
					maxHeight : 280,
					buttonWidth : 200,
					includeSelectAllOption: true,
					nSelectedText: lang[language + ".selected"],
					numberDisplayed: 0
	            });  
	        }  
	    });  
	}
	//角色下拉框
	function initRoleSelect(id) {
		$(id).multiselect({
        	nonSelectedText : lang[language + ".chooseRole"],
        	enableFiltering : true,
			maxHeight : 240,
			buttonWidth : 200
        });
	    $.ajax({  
	        type : "get",  
	        url : "${pageContext.request.contextPath}/roleController/roleList", 
	        dataType : "json",  
	        success : function(data) {  
	            $(id).html("");
	            for (var i = 0; i < data.length; i++) {
	            	$(id).append("<option value='" + data[i].id + "'>" + data[i].name + "</option>");
	            }  
	            $(id).multiselect("destroy").multiselect({
	            	nonSelectedText : lang[language + ".chooseRole"],
	            	enableFiltering : true,
	            	enableCaseInsensitiveFiltering : true,
					maxHeight : 240,
					buttonWidth : 200
	            });
	        }  
	    });  
	}
	//厂商Input
	function initBrandInput(id) {
		var source_data = [];
	    $.ajax({  
	        type : "get",  
	        url : "${pageContext.request.contextPath}/brandController/brandSelect", 
	        dataType : "json",  
	        success : function(data) {
	            for (var i = 0; i < data.rows.length; i++) {
	            	if ($.inArray(data.rows[i].brand_en, source_data) == -1){
	            		source_data.push(data.rows[i].brand_en);
	            	}
	            	if ($.inArray(data.rows[i].brand_cn, source_data) == -1){
	            		source_data.push(data.rows[i].brand_cn);
	            	}
	            }
	        }
	    });
	    $(id).autocomplete({
		    source : source_data
		});
	}
	//厂商查询下拉框
	function initSearchBrandSelect(id) {
		$(id).multiselect({
        	nonSelectedText : lang[language + ".allbrand"],
        	enableFiltering : true,
			maxHeight : 320,
			buttonWidth : 170
        });
	    $.ajax({  
	        type : "get",  
	        url : "${pageContext.request.contextPath}/brandController/brandSelect", 
	        dataType : "json",  
	        success : function(data) {  
	            $(id).html("");
	            $(id).append("<option value=''>" + lang[language + ".allbrand"] + "</option>");
	            for (var i = 0; i < data.rows.length; i++) {
	            	if (language == 'en'){
	            		$(id).append("<option value='" + data.rows[i].brand_en + "'>" + data.rows[i].brand_en + "</option>");
	            	} else {
	            		$(id).append("<option value='" + data.rows[i].brand_cn + "'>" + data.rows[i].brand_cn + "</option>");
	            	}
	            }  
	            $(id).multiselect("destroy").multiselect({
	            	nonSelectedText : lang[language + ".chooseBrand"],
	            	enableFiltering : true,
	            	enableCaseInsensitiveFiltering : true,
					maxHeight : 320,
					buttonWidth : 170
	            });
	            $(id).val("");
	            $(id).multiselect('refresh');
	        }  
	    });  
	}
	//厂商查询下拉框
	function initSearchBrandSelectL(id) {
		$(id).multiselect({
         	nonSelectedText : lang[language + ".allbrand"],
         	enableFiltering : true,
			maxHeight : 320,
			buttonWidth : 170
        });
	    $.ajax({  
	        type : "get",  
	        url : "${pageContext.request.contextPath}/brandController/brandSelect", 
	        dataType : "json",  
	        success : function(data) {  
	            $(id).html("");
	            $(id).append("<option value=''>" + lang[language + ".allbrand"] + "</option>");
	            for (var i = 0; i < data.rows.length; i++) {
	            	if (language == 'en'){
	            		$(id).append("<option value='" + data.rows[i].id + "'>" + data.rows[i].brand_en + "</option>");
	            	} else {
	            		$(id).append("<option value='" + data.rows[i].id + "'>" + data.rows[i].brand_cn + "</option>");
	            	}
	            }  
	            $(id).multiselect("destroy").multiselect({
	            	nonSelectedText : lang[language + ".chooseBrand"],
	            	enableFiltering : true,
	            	enableCaseInsensitiveFiltering : true,
					maxHeight : 320,
					buttonWidth : 170
	            });
	            if ('${cUserSession.brand_id}' == 0) {
	            	 $(id).val("");	
	            } else {
	            	$(id).val('${cUserSession.brand_id}');
	            }
	            $(id).multiselect('refresh');
	        }  
	    });  
	}
	//渠道号下拉框
	function initSearchChannelSelectL(id, brand_id) {
		$(id).multiselect({
        	nonSelectedText : lang[language + ".allchannel"],
        	enableFiltering : true,
			maxHeight : 300,
			buttonWidth : 170
        });
	    $.ajax({  
	        type : "get",  
	        url : "${pageContext.request.contextPath}/channelController/channelSelect?brand_id=" + brand_id, 
	        dataType : "json",  
	        success : function(data) {  
	            $(id).html("");
	            $(id).append("<option value=''>" + lang[language + ".allchannel"] + "</option>");
	            for (var i = 0; i < data.rows.length; i++) {
	            	$(id).append("<option value='" + data.rows[i].id + "'>" + data.rows[i].channel + "</option>");
	            }  
	            $(id).multiselect("destroy").multiselect({
	            	nonSelectedText : lang[language + ".chooseChannel"],
	            	enableFiltering : true,
	            	enableCaseInsensitiveFiltering : true,
					maxHeight : 320,
					buttonWidth : 170
	            });
	            $(id).val("");
	            $(id).multiselect('refresh');
	        }  
	    });  
	}
	//机型下拉框
	function initSearchModelSelectL(id, brand_id, channel_id) {  
		$(id).multiselect({
         	nonSelectedText : lang[language + ".allmodel"],
         	enableFiltering : true,
			maxHeight : 320,
			buttonWidth : 170
        });
	    $.ajax({  
	        type : "get",  
	        url : "${pageContext.request.contextPath}/modelController/modelSelect?brand_id=" + brand_id + "&channel_id=" + channel_id, 
	        dataType : "json",  
	        success : function(data) {  
	            $(id).html("");
	            $(id).append("<option value=''>" + lang[language + ".allmodel"] + "</option>");
	            for (var i = 0; i < data.rows.length; i++) {
	            	$(id).append("<option value='" + data.rows[i].model + "'>" + data.rows[i].model + "</option>");
	            }  
	            $(id).multiselect("destroy").multiselect({
	            	nonSelectedText : lang[language + ".chooseModel"],
	            	enableFiltering : true,
	            	enableCaseInsensitiveFiltering : true,
					maxHeight : 320,
					buttonWidth : 170
	            });
	            $(id).val("");
	            $(id).multiselect('refresh');
	        }  
	    });  
	}
	//机型下拉框(多选)
	function initSearchModelSelectM(id, brand_id, select_array) {
	    $.ajax({  
	        type : "get",  
	        url : "${pageContext.request.contextPath}/modelController/modelSelect?brand_id=" + brand_id, 
	        dataType : "json",  
	        success : function(data) {  
	            $(id).html("");
	            for (var i = 0; i < data.rows.length; i++) {
	            	$(id).append("<option value='" + data.rows[i].model + "'>" + data.rows[i].model + "</option>");
	            }  
	            $(id).multiselect("destroy").multiselect({
	            	nonSelectedText : lang[language + ".noneselected"],
	            	selectAllText : lang[language + ".selectAll"],
	            	enableFiltering : true,
	            	enableCaseInsensitiveFiltering : true,
					maxHeight : 320,
					buttonWidth : 210,
					includeSelectAllOption: true,
					nSelectedText: lang[language + ".selected"],
					numberDisplayed: 0
	            });
	            $(id).val(select_array);
	            $(id).multiselect('refresh');
	        }  
	    });  
	}
	//多厂商下拉框(多选)
	function initSearchUserBrandSelectM(id, select_array) {
	    $.ajax({  
	        type : "get",  
	        url : "${pageContext.request.contextPath}/brandController/brandSelect?v=" + Math.random(), 
	        dataType : "json",  
	        success : function(data) {  
	            $(id).html("");
	            for (var i = 0; i < data.rows.length; i++) {
	            	if (language == 'en'){
	            		$(id).append("<option value='" + data.rows[i].id + "'>" + data.rows[i].brand_en + "</option>");
	            	} else {
	            		$(id).append("<option value='" + data.rows[i].id + "'>" + data.rows[i].brand_cn + "</option>");
	            	}
	            }  
	            $(id).multiselect("destroy").multiselect({
	            	nonSelectedText : lang[language + ".noneselected"],
	            	selectAllText : lang[language + ".selectAll"],
	            	enableFiltering : true,
	            	enableCaseInsensitiveFiltering : true,
					maxHeight : 280,
					buttonWidth : 200,
					includeSelectAllOption: true,
					nSelectedText: lang[language + ".selected"],
					numberDisplayed: 0
	            });
	            $(id).val(select_array);
	            $(id).multiselect('refresh');
	        }  
	    });  
	}
	function getFilePath(input){
	    if(input){
	        if(window.navigator.userAgent.indexOf("MSIE")>=1){  //如果是IE    
	            input.select();      
	          return document.selection.createRange().text;
	        }      
	        else if(window.navigator.userAgent.indexOf("Firefox")>=1){  //如果是火狐
	            if(input.files){      
	                return input.files.item(0).getAsDataURL();      
	            }      
	            return input.value;      
	        }      
	        return input.value;   
	    }  
	}  
	function getRealPath(node) {
	    var url = "";
	    try{
	        var file = null;
	        if(node.files && node.files[0] ){
	            file = node.files[0];
	        }else if(node.files && node.files.item(0)) {
	            file = node.files.item(0);
	        }
	        //Firefox 因安全性问题已无法直接通过input[file].value 获取完整的文件路径
	        try{
	            //Firefox7.0
	            url =  file.getAsDataURL();
	            //alert("//Firefox7.0"+imgRUL);
	        }catch(e){
	            //Firefox8.0以上
	            url = window.URL.createObjectURL(file);
	            //alert("//Firefox8.0以上"+imgRUL);
	        }
	    }catch(e){      //这里不知道怎么处理了，如果是遨游的话会报这个异常
	        //支持html5的浏览器,比如高版本的firefox、chrome、ie10
	        if (node.files && node.files[0]) {
	            var reader = new FileReader();
	            reader.onload = function (e) {
	            	url = e.target.result;
	            };
	            reader.readAsDataURL(node.files[0]);
	        }
	    }
	    return url;
	}

	//编码 
	function html_encode(str) {
		var s = "";
		if (str.length == 0)
			return "";
		s = str.replace(/&/g, ">");
		s = s.replace(/</g, "<");
		s = s.replace(/>/g, ">");
		s = s.replace(/ /g, " ");
		s = s.replace(/\'/g, "'");
		s = s.replace(/\"/g, '"');
		s = s.replace(/\n/g, "<br>");
		return s;
	}
	
	//解码 
	function html_decode(str) {
		var s = "";
		if (str.length == 0)
			return "";
		s = str.replace(/>/g, "&");
		s = s.replace(/</g, "<");
		s = s.replace(/>/g, ">");
		s = s.replace(/ /g, " ");
		s = s.replace(/'/g, "\'");
		s = s.replace(/"/g, "\"");
		s = s.replace(/<br>/g, "\n");
		return s;
	}
	
	function getDateStr(n) {
		if (n == 'all'){
			return '2018-01-01';
		}
		var bef = new Date();
		bef.setDate(bef.getDate() - n);
		befTime = bef.getFullYear()
				+ '-'
				+ (((bef.getMonth() + 1) > 9 ? "" : "0") + (bef.getMonth() + 1))
				+ '-' + ((bef.getDate() > 9 ? '' : '0') + bef.getDate());
		return befTime;
	}

	function initDateTime() {
		$('#start_date').datetimepicker(
				{
					lang : language == 'cn' ? 'ch' : 'en',
					format : "Y-m-d",
					timepicker : false,
					maxDate : 0,
					/* showOn: "button",
					buttonImage: "${pageContext.request.contextPath}/assets/img/loading.gif",
				    buttonImageOnly: true,
				    buttonText: "Select date", */
					onClose : function(current_time, $input) {
						if ($("#end_date").val() != ""
								&& $("#start_date").val() > $("#end_date")
										.val()) {
							$("#start_date").val("");
							toastr.warning(lang[language + ".startLessEnd"]);
						}
					}
				});
		$('#end_date').datetimepicker(
				{
					lang : language == 'cn' ? 'ch' : 'en',
					format : "Y-m-d",
					timepicker : false,
					maxDate : 0,
					onClose : function(current_time, $input) {
						if ($("#start_date").val() != ""
								&& $("#start_date").val() > $("#end_date")
										.val()) {
							$("#end_date").val("");
							toastr.warning(lang[language + ".endMoreStart"]);
						}
					}
				});
	}
	
	function setDate(n) {
		$('#start_date').val(getDateStr(n));
		$('#end_date').val(getDateStr(1));
	}
	
	function doFocus(id) {
		$(id).focus();
	}
	
	function filterStrLength(str, size){
		if (getByteLen(str) <= size){
			return str;
		} else {
			var returnValue = '';
			var byteValLen = 0;
			for (var i = 0; i < str.length; i++) {
				if (str[i].match(/[^\x00-\xff]/ig) != null)
				byteValLen += 2;
				else
				byteValLen += 1;
				if (byteValLen > size)
				break;
				returnValue += str[i];
			}
			return returnValue + "...";
		}
	}
</script>
</html>
