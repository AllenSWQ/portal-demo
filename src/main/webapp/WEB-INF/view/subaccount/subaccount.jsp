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
	<!-- 权限模态框 begin -->
	<div class="modal fade" id="perModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width:750px;height:650px;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only"><span
							langtag='langtag_cancel'>取消</span></span>
					</button>
					<h4 class="modal-title" id="myModalLabel">
						<span langtag='langtag_asignPer'>分配权限</span>
					</h4>
				</div>
				<div class="modal-body">
					<form id="asign_Form" role="form" class="form-horizontal m-t">
						<input id="asignid" name="id" type="hidden">
						<div class="form-group">
							<label class="col-sm-4 control-label"><span style="color:red">* </span><span
								langtag='langtag_modelAppoint'>机型指定</span> : </label>
							<div class="col-sm-6">
								<select id="searchmodel" name="searchmodel" class="multiselect" multiple="multiple"></select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label"><span style="color:red">* </span><span
								langtag='langtag_perAppoint'>权限指定</span> : </label>
							<div class="col-sm-5">
								<div id="Permtree" style="overflow-y:scroll;max-height:500px;"></div>
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
		<div class="modal-dialog" style="width:750px;height:650px;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only"><span
							langtag='langtag_cancel'>取消</span></span>
					</button>
					<h4 class="modal-title" id="myModalLabel">
						<span langtag='langtag_addSubAccount'>添加子账号</span>
					</h4>
				</div>
				<div class="modal-body">
					<form id="add_Form" role="form" class="form-horizontal m-t">
						<div class="form-group">
							<label class="col-sm-4 control-label"><span style="color:red">* </span><span
								langtag='langtag_email'>邮箱</span> : </label>
							<div class="col-sm-4">
								<input id="addemail" name="email" class="form-control"
									type="text" aria-required="true" aria-invalid="true">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label"><span style="color:red">* </span><span
								langtag='langtag_contact'>联系人</span> : </label>
							<div class="col-sm-4">
								<input id="addcontact" name="contact" class="form-control"
									type="text" aria-required="true" aria-invalid="true">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label"><span style="color:red">* </span><span
								langtag='langtag_phone'>联系电话</span> : </label>
							<div class="col-sm-4">
								<input id="addphone" name="phone" class="form-control"
									type="text" aria-required="true" aria-invalid="true">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label"><span style="color:red">* </span><span
								langtag='langtag_modelAppoint'>机型指定</span> : </label>
							<div class="col-sm-6">
								<select id="addsearchmodel" name="addsearchmodel" class="multiselect" multiple="multiple"></select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label"><span style="color:red">* </span><span
								langtag='langtag_perAppoint'>权限指定</span> : </label>
							<div class="col-sm-5">
								<div id="addPermtree" style="overflow-y:scroll;max-height:500px;"></div>
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
	
	<!-- 信息 begin -->
	<div id="searchDiv" class="container"
		style="margin-top: 15px; padding-top: 0px; padding-bottom: 0px;">
		<div id="dashboardBodyDiv"
			class="padding-md dashboard-body search-range">
			<div id="search-div">
				<form id="search_Form" role="form" class="form-horizontal">
					<input id="searchUser" name="searchUser" class="form-control" type="text"
						aria-required="true" aria-invalid="true" style="width:200px;float:left;margin-right:5px;margin-top:1px;" placeholder="邮箱">
					<button id="btn_search_user" type="button" class="btn btn-default btn-search"
						onclick="doSearch()">
						<span langtag='langtag_search'>查询</span>
					</button>
					<button id="btn_add_user" type="button" class="btn btn-default btn-export"
						onclick="addUserClick()">
						<span class="glyphicon glyphicon-plus tableFuncIcon"
							aria-hidden="true"></span><span langtag='langtag_addSubAccount'>添加子账号</span>
					</button>
				</form>
			</div>
		</div>
	</div>

	<div class="container">
		<div class="panel-heading dashboard-head">
			<b langtag='langtag_SubUserMngt'></b>
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
		var brand_id = '${cUserSession.brand_id}';
		$(document).ready(function() {
			showLanguageText();
			$('#searchUser').attr('placeholder',lang[language + '.email']);
			
			initTable();
			initBrandSelect("#addbrand");
			initRoleSelect("#asignrole");
		});

		function addUserClick() {
			add_Form['addemail'].value = "";
			add_Form['addcontact'].value = "";
			add_Form['addphone'].value = "";
			initSearchModelSelectM("#addsearchmodel", brand_id, null);
			initaddpermtreeAll();
			$('#addModal').modal('show');
		}

		function addValidate() {
			var email = add_Form['addemail'].value;
			var contact = add_Form['addcontact'].value;
			var phone = add_Form['addphone'].value;
			var modelObj = $('#addsearchmodel').val();
			var model = '';
			if (modelObj != '' && modelObj != 'undefined' && modelObj != null){
				model = modelObj.join("#");
			}
			var perObj = $('#addPermtree').treeview('getChecked');
			var per = '';
			$.each(perObj, function(i, child) {
				if (i == 0){
					per = child.nodeid;
				} else {
					per = per + '#' + child.nodeid;
				}
			});
			
			if(!validateForm3(lang[language + ".email"], email)){
				return false;
			}
			if(!validateFormForContact(lang[language + ".contact"], contact)){
				return false;
			}
			if(!validateFormForPhone(lang[language + ".phone"], phone)){
				return false;
			}
			if(model == ''){
				face_alert(lang[language + ".asignModelNotNull"]);
				return false;
			}
			if(per == ''){
				face_alert(lang[language + ".asignPerNotNull"]);
				return false;
			}
			
			add(email, email, contact, phone, model, per);
		}

		function add(name, email, contact, phone, model, per) {
			var data = {
				brand_id : '${cUserSession.brand_id}',
				name : name,
				email : email,
				contact : contact,
				phone : phone,
				fuser : loginId,
				model : model,
				per : per
			};
			$("button").attr('disabled',true);
			$
					.ajax({
						type : 'post',
						url : '${pageContext.request.contextPath}/subaccountController/addUser',
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
								url : '${pageContext.request.contextPath}/subaccountController/subaccountList?fuser=' + loginId,
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
											field : 'email',
											title : lang[language + ".email"],
											sortable : false,
											width : '18%'
										},
										{
											field : 'contact',
											title : lang[language + ".contact"],
											sortable : false,
											width : '15%'
										},
										{
											field : 'phone',
											title : lang[language + ".phone"],
											sortable : false,
											width : '18%'
										},
										{
											field : 'state',
											title : lang[language + ".state"],
											sortable : false,
											width : '15%',
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
											title : lang[language
													+ ".operation"],
											align : 'left',
											width : '34%',
											formatter : function(value, row,
													index) {
												var e = '<button type="button" class="btn btn-default btn-operate" onclick="freezeUserClick(\''
														+ row.id
														+ '\', \''
														+ row.name
														+ '\')">' + lang[language + ".freezed"] + '</button>';
												if (row.state == 2){
													e = '<button type="button" class="btn btn-default btn-operate" onclick="activeUserClick(\''
														+ row.id
														+ '\', \''
														+ row.name
														+ '\')">' + lang[language + ".actived"] + '</button>';
												}
												var a = '<button type="button" class="btn btn-default btn-operate" onclick="asignPerClick(\''
														+ row.id
														+ '\')">' + lang[language + ".permission"] + '</button>';
												var d = '<button type="button" class="btn btn-default btn-operate" onclick="deleteUserClick(\''
														+ row.id
														+ '\', \''
														+ row.name
														+ '\')">' + lang[language + ".delete"] + '</button>';
												return e + "<span style='margin-left:10px'/>" + a + "<span style='margin-left:10px'/>" + d;
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

		// 分配权限
		function asignPerClick(id) {
			asign_Form['asignid'].value = id;
			var select_array = [];
			$.ajax({
				type : 'post',
				url : '${pageContext.request.contextPath}/subaccountController/modelSelected?uid=' + id,
				dataType : 'json',
				success : function(result) {
					select_array = result.select_array;
					initSearchModelSelectM("#searchmodel", brand_id, select_array);
				}
			});
			initpermtreeAll(id);
			$('#perModal').modal('show');
		}
		
		var childTrigger = false;
		var userid;
		var messageState = true;

		function initpermtreeAll(id) {
			$.ajax({
				type : 'post',
				url : '${pageContext.request.contextPath}/subaccountController/permissionList?uid=' + loginId,
				success : function(data) {
					permissionlistlength = data.length;
					result = data;
					treeData = "{ nodeid: '" + result[0].id
							+ "',text:'" + (language == 'cn' ? result[0].cn_name : result[0].en_name)
							+ "',en_name:'" + (language == 'cn' ? result[0].en_name : result[0].cn_name)
							+ "', nodes: [";
					treeData = treeData + allnode(1);
					treeData = eval('[' + treeData + ']');
					initpermtree(treeData);
					setPerNode(id);
				}
			});
		}
			
		function initaddpermtreeAll() {
			$.ajax({
				type : 'post',
				url : '${pageContext.request.contextPath}/subaccountController/permissionList?uid=' + loginId,
				success : function(data) {
					permissionlistlength = data.length;
					result = data;
					treeData = "{ nodeid: '" + result[0].id
							+ "',text:'" + (language == 'cn' ? result[0].cn_name : result[0].en_name)
							+ "',en_name:'" + (language == 'cn' ? result[0].en_name : result[0].cn_name)
							+ "', nodes: [";
					treeData = treeData + allnode(1);
					treeData = eval('[' + treeData + ']');
					initaddpermtree(treeData);
				}
			});
		}

		//递归出来的值
		function allnode(index) {
			var tempValue = new Array();
			for (var row = 0; row < result.length; row++) {
				if (result[row].id != 4 && result[row].flevel != 4){
					if (result[row].flevel == index && result[row].state == 0) {
						tempValue = tempValue + "{ nodeid: '" + result[row].id
								+ "',text:'" + (language == 'cn' ? result[row].cn_name : result[row].en_name) + "',en_name:'"
								+ (language == 'cn' ? result[row].en_name : result[row].cn_name) + "'},";
					} else if (result[row].flevel == index
							&& result[row].state == 1) {
						tempValue = tempValue + "{ nodeid: '" + result[row].id
								+ "',text:'" + (language == 'cn' ? result[row].cn_name : result[row].en_name) + "',en_name:'"
								+ (language == 'cn' ? result[row].en_name : result[row].cn_name) + "', nodes: [";
						tempValue = tempValue + allnode(result[row].id);
					}
				}
			}
			return tempValue + "]},";
		}

		function initpermtree(tree) {
			var $PermtreeTree = $('#Permtree').treeview({
				data : tree,
				levels : 4,
				showTags : true,
				multiSelect : false,
				selectable : false,
				/* nodeIcon: 'glyphicon glyphicon-globe', */
				expandIcon: 'glyphicon',
				collapseIcon: 'glyphicon',
				nodeIcon: 'glyphicon',
				showCheckbox : true,
				onNodeSelected : function(event, data) {
				},
				onNodeChecked : function(e, node) {
					messageState = false;
					//添加权限
					var that = $(this);
					var siblings = that.treeview('getSiblings', node);
					var boolCheckAll = true;
					if (siblings != undefined) {
						$.each(siblings, function(i, child) {
							if (!child.state.checked) {
								boolCheckAll = false;
							}
						});
					}
					var parentId = node.parentId;

					if (parentId != undefined) {
						messageState = true;
						that.treeview('checkNode', [ parentId ]);
					}

				},

				//取消勾选事件
				onNodeUnchecked : function(e, node) {
					var that = $(this);
					var nodenum = $('#roletree').treeview('getSelected');
					if (nodenum == 0)
						return;
					//作为父节点：取消勾选时，同时取消勾选所有子节点
					var children = node.nodes;
					if (children != undefined && !childTrigger) {
						$.each(children, function(i, child) {
							that.treeview('uncheckNode', [ child.nodeId ]);
						});
					}
				}
			});
		}
		
		function initaddpermtree(tree) {
			var $addPermtreeTree = $('#addPermtree').treeview({
				data : tree,
				levels : 4,
				showTags : true,
				multiSelect : false,
				selectable : false,
				/* nodeIcon: 'glyphicon glyphicon-globe', */
				expandIcon: 'glyphicon',
				collapseIcon: 'glyphicon',
				nodeIcon: 'glyphicon',
				showCheckbox : true,
				onNodeSelected : function(event, data) {
				},
				onNodeChecked : function(e, node) {
					messageState = false;
					//添加权限
					var that = $(this);
					var siblings = that.treeview('getSiblings', node);
					var boolCheckAll = true;
					if (siblings != undefined) {
						$.each(siblings, function(i, child) {
							if (!child.state.checked) {
								boolCheckAll = false;
							}
						});
					}
					var parentId = node.parentId;

					if (parentId != undefined) {
						messageState = true;
						that.treeview('checkNode', [ parentId ]);
					}

				},

				//取消勾选事件
				onNodeUnchecked : function(e, node) {
					var that = $(this);
					var nodenum = $('#roletree').treeview('getSelected');
					if (nodenum == 0)
						return;
					//作为父节点：取消勾选时，同时取消勾选所有子节点
					var children = node.nodes;
					if (children != undefined && !childTrigger) {
						$.each(children, function(i, child) {
							that.treeview('uncheckNode', [ child.nodeId ]);
						});
					}
				}
			});
		}

		function setPerNode(uid) {
			$
					.ajax({
						type : 'post',
						data : {
							uid : uid
						},
						url : '${pageContext.request.contextPath}/subaccountController/perSelected',
						success : function(result) {
							if (result.length == 0)
								return;
							var roledata = new Array();
							//用户新的添加or删除的只能数组
							addperrole = new Array();
							for (var i = 0; i < result.length; i++) {
								roledata.push(result[i].pid);
							}
							addperrole = roledata;
							for (var i = 0; i <= permissionlistlength; i++) {
								var ta = $('#Permtree').treeview('getNode', i);
								if (roledata.indexOf(Number(ta.nodeid)) != -1) {
									$('#Permtree').treeview('checkNode', [ i, {
										silent : true
									} ]);
								}
								;
							}
							;
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

		ShowSuccessMessage = function(message, life) {
			if (messageState == false)
				return;
			var time = 5000;
			if (!life) {
				time = life;
			}
			if ($("#tip_message").text().length > 0) {
				var msg = "<span>" + message + "</span>";
				$("#tip_message").empty().append(msg);
			} else {
				var msg = "<div id='tip_message'><span>" + message
						+ "</span></div>";
				$("body").append(msg);
			}
			$("#tip_message").fadeIn(time);
			setTimeout($("#tip_message").fadeOut(2500, setmessage()), 2000);
		};

		function setmessage() {
			messageState = true;
		}

		//提示错误信息  
		ShowErrorMessage = function(message, life) {
			ShowSuccessMessage(message, life);
			$("#tip_message span").addClass("error");
		};
		
		function doSearch(){
			var data = {
				url: "${pageContext.request.contextPath}/subaccountController/subaccountList?fuser=" + loginId + "&search=" + $("#searchUser").val()
			};
			$('#table').bootstrapTable('refresh', data);
		}
		
		function asignValidate() {
			var uid = asign_Form['asignid'].value;
			var modelObj = $('#searchmodel').val();
			var model = '';
			if (modelObj != '' && modelObj != 'undefined' && modelObj != null){
				model = modelObj.join("#");
			}
			var perObj = $('#Permtree').treeview('getChecked');
			var per = '';
			$.each(perObj, function(i, child) {
				if (i == 0){
					per = child.nodeid;
				} else {
					per = per + '#' + child.nodeid;
				}
			});
			
			if(model == ''){
				face_alert(lang[language + ".asignModelNotNull"]);
				return false;
			}
			if(per == ''){
				face_alert(lang[language + ".asignPerNotNull"]);
				return false;
			}
			
			var data = {
				uid : uid,
				model : model,
				per : per
			};
			$
			.ajax({
				type : 'post',
				url : '${pageContext.request.contextPath}/subaccountController/assignPer',
				data : data,
				dataType : 'json',
				success : function(result) {
					if (result.status == "success") {
						$('#perModal').modal('hide');
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
