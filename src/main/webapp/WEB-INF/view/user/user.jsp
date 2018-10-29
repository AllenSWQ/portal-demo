<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<jsp:include page="${path}/assets/include/include.jsp?v=0.0.3"></jsp:include>
<script type="text/javascript">
	var buttonPerList = window.parent.buttonPer;
	var language = window.parent.language;
	var loginId = window.parent.cUserSessionId;
	var src = "${pageContext.request.contextPath}/assets/js/plugins/bootstrap-table/locale/bootstrap-table-"
			+ language + ".js";
	loadJs(src);
</script>
</head>

<body style="background-color:#f0f3f4;">
	<!-- 角色模态框 begin -->
	<div class="modal fade" id="roleModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-center" style="width:500px;height:206px;transform:translateX(-50%) translateY(-50%);">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only"><span
							langtag='langtag_cancel'>取消</span></span>
					</button>
					<h4 class="modal-title" id="myModalLabel">
						<span langtag='langtag_asignRole'>分配角色</span>
					</h4>
				</div>
				<div class="modal-body">
					<form id="asign_Form" role="form" class="form-horizontal m-t">
						<input id="asignid" name="id" type="hidden">
						<div class="form-group">
							<label class="col-sm-4 control-label"><span style="color:red">* </span><span
								langtag='langtag_rolename'>角色</span> : </label>
							<div class="col-sm-6">
								<select id="asignrole" class="multiselect">
								</select>
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
						onclick="asignValidate()">
						<span class="glyphicon glyphicon-floppy-disk tableFuncIcon"
							aria-hidden="true"></span><span langtag='langtag_save'>保存</span>
					</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 角色模态框 end -->

	<!-- 添加模态框 begin -->
	<div class="modal fade" id="addModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-center" style="width:630px;height:350px;transform:translateX(-50%) translateY(-50%);">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only"><span
							langtag='langtag_cancel'>取消</span></span>
					</button>
					<h4 class="modal-title" id="myModalLabel">
						<span langtag='langtag_addUser'>添加账号</span>
					</h4>
				</div>
				<div class="modal-body">
					<form id="add_Form" role="form" class="form-horizontal m-t">
						<div class="form-group">
							<label class="col-sm-4 control-label"><span style="color:red">* </span><span
								langtag='langtag_usertype'>账号类型</span> : </label>
							<div class="col-sm-6">
								<select id="addusertype" class="multiselect" onchange="changeUserType()">
									<option value="0"><span langtag='langtag_admin'>管理员</span></option>
									<option value="1"><span langtag='langtag_multibrand'>单厂商</span></option>
									<option value="2"><span langtag='langtag_singlebrand'>多厂商</span></option>
								</select>
							</div>
						</div>
						<div id="singleBrandSel" class="form-group" style="display:none;">
							<label class="col-sm-4 control-label"><span style="color:red">* </span><span
								langtag='langtag_brand'>厂商</span> : </label>
							<div class="col-sm-6">
								<select id="addbrand" class="multiselect">
								</select>
							</div>
						</div>
						<div id="multiBrandSel" class="form-group" style="display:none;">
							<label class="col-sm-4 control-label"><span style="color:red">* </span><span
								langtag='langtag_brand'>厂商</span> : </label>
							<div class="col-sm-6">
								<select id="addbrandM" class="multiselect" multiple="multiple">
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label"><span style="color:red">* </span><span
								langtag='langtag_email'>邮箱</span> : </label>
							<div class="col-sm-6">
								<input id="addemail" name="email" class="form-control"
									type="text" aria-required="true" aria-invalid="true">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label"><span style="color:red">* </span><span
								langtag='langtag_contact'>联系人</span> : </label>
							<div class="col-sm-6">
								<input id="addcontact" name="contact" class="form-control"
									type="text" aria-required="true" aria-invalid="true">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label"><span style="color:red">* </span><span
								langtag='langtag_phone'>联系电话</span> : </label>
							<div class="col-sm-6">
								<input id="addphone" name="phone" class="form-control"
									type="text" aria-required="true" aria-invalid="true">
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
						onclick="addValidate()">
						<span class="glyphicon glyphicon-floppy-disk tableFuncIcon"
							aria-hidden="true"></span><span langtag='langtag_save'>保存</span>
					</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 添加模态框end -->
	
	<!-- 角色模态框 begin -->
	<div class="modal fade" id="brandModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-center" style="width:600px;height:206px;transform:translateX(-50%) translateY(-50%);">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only"><span
							langtag='langtag_cancel'>取消</span></span>
					</button>
					<h4 class="modal-title" id="myModalLabel">
						<span langtag='langtag_asignBrand'>分配厂商</span>
					</h4>
				</div>
				<div class="modal-body">
					<form id="asignb_Form" role="form" class="form-horizontal m-t">
						<input id="asignbid" name="id" type="hidden">
						<div class="form-group">
							<label class="col-sm-4 control-label"><span style="color:red">* </span><span
								langtag='langtag_brand'>厂商</span> : </label>
							<div class="col-sm-6">
								<select id="asignbBrand" class="multiselect" multiple="multiple">
								</select>
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
						onclick="modifyUserBrandValidate()">
						<span class="glyphicon glyphicon-floppy-disk tableFuncIcon"
							aria-hidden="true"></span><span langtag='langtag_save'>保存</span>
					</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 角色模态框 end -->
	
	<!-- 信息 begin -->
	<div id="searchDiv" class="container"
		style="margin-top: 15px; padding-top: 0px; padding-bottom: 0px; display: none;">
		<div id="dashboardBodyDiv"
			class="padding-md dashboard-body search-range">
			<div id="search-div">
				<form id="search_Form" role="form" class="form-horizontal">
					<input id="searchUser" name="searchUser" class="form-control hide" type="text"
						aria-required="true" aria-invalid="true" style="width:200px;float:left;margin-right:5px;margin-top:1px;" placeholder="邮箱">
					<button id="btn_search_user" type="button" class="btn btn-default btn-search hide"
						onclick="doSearch()">
						<span langtag='langtag_search'>查询</span>
					</button>
					<button id="btn_add_user" type="button" class="btn btn-default btn-export hide"
						onclick="addUserClick()">
						<span class="glyphicon glyphicon-plus tableFuncIcon"
							aria-hidden="true"></span><span langtag='langtag_addUser'>添加账号</span>
					</button>
				</form>
			</div>
		</div>
	</div>

	<div class="container">
		<div class="panel-heading dashboard-head">
			<b langtag='langtag_UserMngt'></b>
		</div>
		<div id="dashboardBodyDiv"
			class="padding-md dashboard-body table-range">
			<!-- <div id="toolbar" class="btn-group" style="width: 100%"></div> -->

			<table id="table" class="table table-bordered table-striped"
				style="font-size: 12px;">
			</table>
		</div>
	</div>

	<script>
		$(document).ready(function() {
			showLanguageText();
			$('#searchUser').attr('placeholder',lang[language + '.email']);
			
			if (buttonPerList.indexOf('addUserClick') != -1) {
				$("#searchDiv").css('display', 'block');
				$("#btn_add_user").removeClass('hide');
			}
			if (buttonPerList.indexOf('searchUserClick') != -1) {
				$("#searchDiv").css('display', 'block');
				$("#searchUser").removeClass('hide');
				$("#btn_search_user").removeClass('hide');
			}
			initTable();
			initUserTypeSelect("#addusertype");
			initBrandSelect("#addbrand");
			initBrandSelectM("#addbrandM");
			initBrandSelectM("#asignbBrand");
			initRoleSelect("#asignrole");
		});

		function addUserClick() {
			add_Form['addusertype'].value = "";
			$("#addusertype").multiselect("refresh");
			add_Form['addbrand'].value = "";
			$("#addbrand").multiselect("refresh");
			add_Form['addbrandM'].value = "";
			$("#addbrandM").multiselect("refresh");
			add_Form['addemail'].value = "";
			add_Form['addcontact'].value = "";
			add_Form['addphone'].value = "";
			$('#addModal').modal('show');
		}

		function addValidate() {
			var usertype = add_Form['addusertype'].value;
			var brand = add_Form['addbrand'].value;
			var email = add_Form['addemail'].value;
			var contact = add_Form['addcontact'].value;
			var phone = add_Form['addphone'].value;
			
			var brandObj = $('#addbrandM').val();
			var brand_array = '';
			if (brandObj != '' && brandObj != 'undefined' && brandObj != null){
				brand_array = brandObj.join("#");
			}
			
			if (usertype == '1'){//单厂商
				if (brand == "") {
					face_alert(lang[language + ".brandNull"]);
					return false;
				}
			}
			
			if (usertype == '2'){//多厂商
				if (brand_array == "") {
					face_alert(lang[language + ".brandNull"]);
					return false;
				}
			}
			
			if(!validateForm3(lang[language + ".email"], email)){
				return false;
			}
			if(!validateFormForContact(lang[language + ".contact"], contact)){
				return false;
			}
			if(!validateFormForPhone(lang[language + ".phone"], phone)){
				return false;
			}
			
			add(email, brand, email, contact, phone, usertype, brand_array);
		}

		function add(name, brand, email, contact, phone, usertype, brand_array) {
			var data = {
				name : name,
				brand_id : brand,
				email : email,
				contact : contact,
				phone : phone,
				type : usertype,
				brand_array : brand_array
			};
			$("button").attr('disabled',true);
			$
					.ajax({
						type : 'post',
						url : '${pageContext.request.contextPath}/userController/addUser',
						data : data,
						dataType : 'json',
						success : function(result) {
							$("button").attr('disabled',false);
							if (result.status == "emailnotunique" || result.status == "notunique") {
								face_alert(lang[language + ".emailNoUnique"]);
							} else if (result.status == "emailformat") {
								face_alert(lang[language + ".email"] + " " + lang[language + ".emailFormat"]);
							} else if (result.status == "success") {
								$('#addModal').modal('hide');
								$('#table').bootstrapTable('refresh');
								face_succ(lang[language + ".addSucc"]);
							} else {
								return false;
							}
						},
						error: function (XMLHttpRequest, textStatus, errorThrown) {
							$("button").attr('disabled',false);
							face_alert(lang[language + ".connectionout"]);
							//点击确定事件
							$("#infoButton").click(function(){
								location.href = "${pageContext.request.contextPath}/userController/login";
							});
						}
					});
		}

		function paginationParam(params) {
			return {
				filter : params.filter,
				order : params.order,
				sort : params.sort,
				search : params.search,
				limit : params.limit,
				offset : params.offset,
			};
		}

		function initTable() {
			$('#table')
					.bootstrapTable(
							{
								cache : false,
								method : 'get',
								url : '${pageContext.request.contextPath}/userController/userList',
								dataType : 'json',
								striped : true,
								uniqueId : "id",
								pagination : true, //在表格底部显示分页工具栏
								pageSize : 15,
								pageNumber : 1,
								pageList : [ 15, 30, 50, 'ALL' ],
								paginationFirstText : lang[language + ".firstPage"],
								paginationPreText : lang[language + ".lastPage"],
								paginationNextText : lang[language + ".nextPage"],
								paginationLastText : lang[language + ".endPage"],
								sortable : false,
								showColumns : false, //显示隐藏列 
								showRefresh : false, //显示刷新按钮
								search : buttonPerList.indexOf('searchUserClick') != -1 ? false : false,//是否显示右上角的搜索框
								clickToSelect : true,//点击行即可选中单选/复选框
								sidePagination : "server",//表格分页的位置
								queryParams : paginationParam, //参数
								toolbar : "#toolbar", //设置工具栏的Id或者class
								advancedSearch : false,
								idTable : "advancedTable",
								singleSelect : true,
								sortName : 'id',
								sortOrder : 'asc',
								columns : [
										{
											field : 'id',
											title : lang[language + ".id"],
											sortable : false,
											width : '5%',
											visible : false
										},
										{
											field : language == 'en' ? 'brand_en' : 'brand_cn',
											title : lang[language + ".brand"],
											sortable : false,
											width : '8%'
										},
										{
											field : 'email',
											title : lang[language + ".email"],
											sortable : false,
											width : '12%'
										},
										{
											field : 'contact',
											title : lang[language + ".contact"],
											sortable : false,
											width : '8%'
										},
										{
											field : 'phone',
											title : lang[language + ".phone"],
											sortable : false,
											width : '10%'
										},
										{
											field : 'type',
											title : lang[language + ".usertype"],
											sortable : false,
											width : '8%',
											formatter : function(value, row, index) {
												var type = lang[language + ".singlebrand"];
												if (row.type == 0) {
													type = lang[language + ".admin"];
												} else if (row.type == 2) {
													type = lang[language + ".multibrand"];
												}
												return type;
											}
										},
										{
											field : 'rolename',
											title : lang[language + ".rolename"],
											sortable : false,
											width : '10%'
										},
										{
											field : 'state',
											title : lang[language + ".state"],
											sortable : false,
											width : '9%',
											formatter : function(value, row, index) {
												var status = lang[language + ".notactived"];
												if (row.state == 1) {
													status = "<span class='status_yes'>" + lang[language + ".actived"] + "</span>";
												} else if (row.state == 2) {
													status = lang[language + ".freezed"];
												}
												return status;
											}
										},
										{
											field : 'create_time',
											title : lang[language
													+ ".createtime"],
											sortable : false,
											width : '10%',
											visible : false
										},
										{
											title : lang[language
													+ ".operation"],
											align : 'left',
											width : '30%',
											visible : (buttonPerList
													.indexOf('freezeUserClick') == -1 && buttonPerList
													.indexOf('asignRoleClick') == -1 && buttonPerList
													.indexOf('deleteUserClick') == -1) ? false
													: true,
											formatter : function(value, row,
													index) {
												var e = '<button type="button" class="btn btn-default btn-operate" style="margin-right:5px" onclick="freezeUserClick(\''
														+ row.id
														+ '\', \''
														+ row.name
														+ '\')">' + lang[language + ".freezed"] + '</button>';
												if (buttonPerList
														.indexOf('freezeUserClick') == -1) {
													e = "";
												} else if (row.state == 2){
													if (loginId == row.id){
														e = '<button type="button" class="btn btn-operate" disabled="disabled" style="margin-right:5px" onclick="activeUserClick(\''
															+ row.id
															+ '\', \''
															+ row.name
															+ '\')">' + lang[language + ".actived"] + '</button>';
													} else {
														e = '<button type="button" class="btn btn-default btn-operate" style="margin-right:5px" onclick="activeUserClick(\''
															+ row.id
															+ '\', \''
															+ row.name
															+ '\')">' + lang[language + ".actived"] + '</button>';
													}
												} else {
													if (loginId == row.id){
														e = '<button type="button" class="btn btn-operate" disabled="disabled" style="margin-right:5px" onclick="freezeUserClick(\''
															+ row.id
															+ '\', \''
															+ row.name
															+ '\')">' + lang[language + ".freezed"] + '</button>';
													}
												}
												var a = '<button type="button" class="btn btn-default btn-operate" style="margin-right:5px" onclick="asignRoleClick(\''
														+ row.id
														+ '\', \''
														+ row.roleid
														+ '\')">' + lang[language + ".asignRole"] + '</button>';
												if (buttonPerList
														.indexOf('asignRoleClick') == -1) {
													a = "";
												} else {
													if (loginId == row.id){
														a = '<button type="button" class="btn btn-operate" disabled="disabled" style="margin-right:5px" onclick="asignRoleClick(\''
															+ row.id
															+ '\', \''
															+ row.roleid
															+ '\')">' + lang[language + ".asignRole"] + '</button>';
													}
												}
												var d = '<button type="button" class="btn btn-default btn-operate" style="margin-right:5px" onclick="deleteUserClick(\''
														+ row.id
														+ '\', \''
														+ row.name
														+ '\')">' + lang[language + ".delete"] + '</button>';
												if (buttonPerList
														.indexOf('deleteUserClick') == -1) {
													d = "";
												} else {
													if (loginId == row.id){
														d = '<button type="button" class="btn btn-operate" disabled="disabled" style="margin-right:5px" onclick="deleteUserClick(\''
															+ row.id
															+ '\', \''
															+ row.name
															+ '\')">' + lang[language + ".delete"] + '</button>';
													}
												}
												var b = '';
												if (row.type == 2){
													if (loginId == row.id){
														b = '<button type="button" class="btn btn-operate" disabled="disabled" onclick="modifyUserBrandClick(\''
															+ row.id
															+ '\')">' + lang[language + ".asignBrand"] + '</button>';
													} else {
														b = '<button type="button" class="btn btn-default btn-operate" onclick="modifyUserBrandClick(\''
																+ row.id
																+ '\')">' + lang[language + ".asignBrand"] + '</button>';
													}
												}
												return e + a + d + b;
											}
										} ]
							});
		}
		
		function freezeUserClick(id, name){
			face_confirm(lang[language + ".confirmFre"], name);
			
			//点击确定事件
			$("#confirmButton").click(function(){
				$("#confirmModal").modal('hide');
				$.ajax({
					type : 'post',
					url : '${pageContext.request.contextPath}/userController/updateUserState',
					data : {
						id : id,
						state : 2
					},
					dataType : 'json',
					success : function(result) {
						if (result.status == "success") {
							$('#table').bootstrapTable('refresh');
							face_succ(lang[language + ".freSucc"]);
						} else {
							return false;
						}
					},
					error: function (XMLHttpRequest, textStatus, errorThrown) {
						face_alert(lang[language + ".connectionout"]);
						//点击确定事件
						$("#infoButton").click(function(){
							location.href = "${pageContext.request.contextPath}/userController/login";
						});
					}
				});
			});
		}
		
		function activeUserClick(id, name){
			face_confirm(lang[language + ".confirmActive"], name);
			
			//点击确定事件
			$("#confirmButton").click(function(){
				$("#confirmModal").modal('hide');
				$.ajax({
					type : 'post',
					url : '${pageContext.request.contextPath}/userController/updateUserState',
					data : {
						id : id,
						state : 1
					},
					dataType : 'json',
					success : function(result) {
						if (result.status == "success") {
							$('#table').bootstrapTable('refresh');
							face_succ(lang[language + ".activeSucc"]);
						} else {
							return false;
						}
					},
					error: function (XMLHttpRequest, textStatus, errorThrown) {
						face_alert(lang[language + ".connectionout"]);
						//点击确定事件
						$("#infoButton").click(function(){
							location.href = "${pageContext.request.contextPath}/userController/login";
						});
					}
				});
			});
		}

		function deleteUserClick(id, name) {
			face_confirm(lang[language + ".confirmDel"], name);
			
			//点击确定事件
			$("#confirmButton").click(function(){
				$("#confirmModal").modal('hide');
				$.ajax({
					type : 'post',
					url : '${pageContext.request.contextPath}/userController/updateUserState',
					data : {
						id : id,
						state : 3
					},
					dataType : 'json',
					success : function(result) {
						if (result.status == "success") {
							$('#table').bootstrapTable('refresh');
							face_succ(lang[language + ".delSucc"]);
						} else {
							return false;
						}
					},
					error: function (XMLHttpRequest, textStatus, errorThrown) {
						face_alert(lang[language + ".connectionout"]);
						//点击确定事件
						$("#infoButton").click(function(){
							location.href = "${pageContext.request.contextPath}/userController/login";
						});
					}
				});
			});
		}

		// 分配角色
		function asignRoleClick(id, roleid) {
			asign_Form['asignid'].value = id;
			asign_Form['asignrole'].value = roleid;
			$("#asignrole").multiselect("refresh");
			$('#roleModal').modal('show');
		}
		
		function asignValidate(){
			var id = asign_Form['id'].value;
			var roleid = asign_Form['asignrole'].value;
			
			if (roleid == "") {
				face_alert(lang[language + ".roleNull"]);
				return false;
			}
			
			asign(id, roleid);
		}
		
		function asign(id, roleid){
			var data = {
					userid : id,
					roleid : roleid
				};
				$
						.ajax({
							type : 'post',
							url : '${pageContext.request.contextPath}/userController/editUserRole',
							data : data,
							dataType : 'json',
							success : function(result) {
								if (result.status == "success") {
									$('#roleModal').modal('hide');
									$('#table').bootstrapTable('refresh');
									face_succ(lang[language + ".asignSucc"]);
								} else {
									return false;
								}
							},
							error: function (XMLHttpRequest, textStatus, errorThrown) {
								face_alert(lang[language + ".connectionout"]);
								//点击确定事件
								$("#infoButton").click(function(){
									location.href = "${pageContext.request.contextPath}/userController/login";
								});
							}
						});
		}
		
		function doSearch(){
			var data = {
				url: "${pageContext.request.contextPath}/userController/userList?search=" + $("#searchUser").val()
			};
			$('#table').bootstrapTable('refresh', data);
		}
		
		function changeUserType(){
			var userType = $("#addusertype").val();
			if (userType == '0'){
				$("#singleBrandSel").css('display', 'none');
				$("#multiBrandSel").css('display', 'none');
			} else if(userType == '1'){
				$("#singleBrandSel").css('display', 'block');
				$("#multiBrandSel").css('display', 'none');
			} else if(userType == '2'){
				$("#singleBrandSel").css('display', 'none');
				$("#multiBrandSel").css('display', 'block');
			}
		}
		
		function modifyUserBrandClick(id){
			asignb_Form['asignbid'].value = id;
			var select_array = [];
			$.ajax({
				type : 'post',
				url : '${pageContext.request.contextPath}/userController/brandSelected?uid=' + id,
				dataType : 'json',
				success : function(result) {
					select_array = result.select_array;
					initSearchUserBrandSelectM("#asignbBrand", select_array);
				}
			});
			$('#brandModal').modal('show');
		}
		
		function modifyUserBrandValidate() {
			var uid = asignb_Form['asignbid'].value;
			
			var brandObj = $('#asignbBrand').val();
			var brand_array = '';
			if (brandObj != '' && brandObj != 'undefined' && brandObj != null){
				brand_array = brandObj.join("#");
			}
			
			if (brand_array == "") {
				face_alert(lang[language + ".brandNull"]);
				return false;
			}
			
			modify(uid, brand_array);
		}

		function modify(uid, brand_array) {
			var data = {
				uid : uid,
				brand_array : brand_array
			};
			$("button").attr('disabled',true);
			$
					.ajax({
						type : 'post',
						url : '${pageContext.request.contextPath}/userController/modifyUserBrand',
						data : data,
						dataType : 'json',
						success : function(result) {
							$("button").attr('disabled',false);
							if (result.status == "success") {
								$('#brandModal').modal('hide');
								face_succ(lang[language + ".saveSucc"]);
							} else {
								return false;
							}
						},
						error: function (XMLHttpRequest, textStatus, errorThrown) {
							$("button").attr('disabled',false);
							face_alert(lang[language + ".connectionout"]);
							//点击确定事件
							$("#infoButton").click(function(){
								location.href = "${pageContext.request.contextPath}/userController/login";
							});
						}
					});
		}

	</script>
</body>
</html>
