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
<jsp:include page="${path}/assets/include/include.jsp?v=0.0.2"></jsp:include>
<script type="text/javascript">
	var buttonPerList = window.parent.buttonPer;
	var language = window.parent.language;
</script>
</head>
<body>
	<div class="container" style="width: 100%, text-align:center">
		<!-- 添加模态框 begin -->
		<div class="modal fade" id="addModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-dialog-center" style="width:500px;height:206px;transform:translateX(-50%) translateY(-50%);">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only"><span langtag='langtag_cancel'>取消</span></span>
						</button>
						<h4 class="modal-title" id="myModalLabel"><span langtag='langtag_addRole'>新增角色</span></h4>
					</div>
					<div class="modal-body">
						<form id="add_Form" role="form" class="form-horizontal m-t">
							<div class="form-group">
								<label class="col-sm-4 control-label"><span style="color:red">* </span><span langtag='langtag_rolename'>名称</span> : </label>
								<div class="col-sm-6">
									<input id="addname" name="name" class="form-control"
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

		<!-- 修改模态框 begin -->
		<div class="modal fade" id="editModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-dialog-center" style="transform:translateX(-50%) translateY(-50%);">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only"><span langtag='langtag_cancel'>取消</span></span>
						</button>
						<h4 class="modal-title" id="myModalLabel"><span langtag='langtag_editRole'>修改角色</span></h4>
					</div>
					<div class="modal-body">
						<form id="edit_Form" role="form" class="form-horizontal m-t">
							<div class="form-group">
								<label class="col-sm-3 control-label"><span style="color:red">* </span><span langtag='langtag_rolename'>名称</span> : </label>
								<div class="col-sm-8">
									<input id="editname" name="name" class="form-control"
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
							onclick="editValidate()">
							<span class="glyphicon glyphicon-floppy-disk tableFuncIcon"
								aria-hidden="true"></span><span langtag='langtag_confirm'>修改</span>
						</button>
					</div>
				</div>
			</div>
		</div>
		<!-- 修改模态框end -->

		<div>
			<div class="col-xs-4">
				<div style="margin-bottom: 12px;">
					<button id="btn_add_role" type="button"
						class="btn btn-default btn-export hide" onclick="addRoleClick()" style="float:none;">
						<span class="glyphicon glyphicon-plus tableFuncIcon"
							aria-hidden="true"></span><span langtag='langtag_addrole'>添加角色</span>
					</button>
					<button id="btn_del_role" type="button"
						class="btn btn-default btn-export hide" onclick="deleteRoleClick()" style="float:none;">
						<span class="glyphicon glyphicon-remove tableFuncIcon"
							aria-hidden="true"></span><span langtag='langtag_delrole'>删除角色</span>
					</button>
				</div>
				<div id="roletree"></div>
			</div>
			<div class="col-xs-4" style="overflow-y:scroll;height:900px;">
				<div id="Permtree" class="hide"></div>
			</div>
			<div id="userInfoDiv" class="col-xs-4" style="display: none;">
				<textarea id="userrole" class="form-control" readOnly="true"
					style="resize: none; height: 300px; line-height: 25px;"><span langtag='langtag_belongRoleUser'>属于该角色的账号:</span></textarea>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		$(document).ready(function() {
			showLanguageText();

			if (buttonPerList.indexOf('addRoleClick') != -1) {
				$("#btn_add_role").removeClass('hide');
			}
			if (buttonPerList.indexOf('deleteRoleClick') != -1) {
				$("#btn_del_role").removeClass('hide');
			}
			if (buttonPerList.indexOf('asignPermissionClick') != -1) {
				$("#Permtree").removeClass('hide');
			}

			initroletreeAll();
			initpermtreeAll();
		});

		var childTrigger = false;
		var roleid;
		var messageState = true;

		function initroletreeAll() {
			$.ajax({
				type : 'post',
				url : '${pageContext.request.contextPath}/roleController/roleList',
				success : function(result) {
					var treeData = "{text: '" + lang[language + '.roleList'] + "',nodes: [";
					var num = 0;
					'${cUserSession.name}' == 'admin' ? num = 0 : num = 1;
					for (var i = num; i < result.length; i++) {
						treeData = treeData + "{ nodeid: '"
								+ result[i].id + "',text:'"
								+ result[i].name + "'},";
					}
					treeData = treeData + "]}";
					var temp = eval('(' + treeData + ')');
					initroletree([ temp ]);
				}
			});
		}

		function initroletree(tree) {
			var $checkableTree = $('#roletree').treeview({
				data : tree,
				levels : 2,
				multiSelect : false,
				//icon: "glyphicon glyphicon-stop",
				//selectedIcon: "glyphicon glyphicon-stop",
				color : "#000000",
				backColor : "#FFFFFF",
				selectable : false,
				showCheckbox : false,
				onNodeUnselected : function(event, node) {
					$('#Permtree').treeview('uncheckAll', {
						silent : true
					});
					roleid = "";
					$('#userInfoDiv').css('display', 'none');
				},
				onNodeSelected : function(event, data) {
					if (data.nodeid != undefined) {
						$('#userInfoDiv').css('display', 'block');
						roleid = data.nodeid;
						$('#Permtree').treeview('uncheckAll', {
							silent : true
						});
						setRoleNode();
						setUserRole();
					}
				}
			});
		}

		function initpermtreeAll() {
			$.ajax({
				type : 'post',
				url : '${pageContext.request.contextPath}/userController/permissionList',
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
				}
			});
		}

		//递归出来的值
		function allnode(index) {
			var tempValue = new Array();
			for (var row = 0; row < result.length; row++) {
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
			return tempValue + "]},";
		}

		function initpermtree(tree) {
			var $PermtreeTree = $('#Permtree').treeview({
				data : tree,
				levels : 4,
				showTags : true,
				multiSelect : false,
				selectable : false,
				showCheckbox : true,
				onNodeSelected : function(event, data) {
					//console.log("单击的值" + data.nodeid);
					//$(this).treeview('checkNode', [data.nodeId]);
				},
				onNodeChecked : function(e, node) {
					messageState = false;
					//添加权限
					var nodenum = $('#roletree').treeview('getSelected');
					if (nodenum == 0 || nodenum[0].nodeid == undefined) {
						$('#Permtree').treeview('uncheckAll', {
							silent : true
						});
						face_alert(lang[language + ".chooseRole"]);
						return;
					}
					//添加角色
					addrolePer(node.nodeid);
					var that = $(this);
					/* if(node.nodeid == 1){
						var children = node.nodes;
						if (children != undefined) {
							$.each(node.nodes, function(i, child) {
								that.treeview('checkNode', [child.nodeId]);
								var children2 = child.nodes;
								if (children2 != undefined) {
									$.each(child.nodes, function(i, child) {
										that.treeview('checkNode', [child.nodeId]);
										var children3 = child.nodes;
										if (children3 != undefined) {
											$.each(child.nodes, function(i, child) {
												that.treeview('checkNode', [child.nodeId]);
											});
									    }
									});
							    }
							});
					    }
					} */

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
					/* if (boolCheckAll && parentId != undefined) {
						messageState = true;
						that.treeview('checkNode', [parentId]);
					} */

					if (parentId != undefined) {
						messageState = true;
						that.treeview('checkNode', [ parentId ]);
					}

					/* var parents = that.treeview('getParent', node);
					if (parents != undefined) {
						$.each(parents, function(i, child) {
							that.treeview('checkNode', [child.nodeId]);
						});
					} */

				},

				//取消勾选事件
				onNodeUnchecked : function(e, node) {
					var that = $(this);
					var nodenum = $('#roletree').treeview('getSelected');
					if (nodenum == 0)
						return;
					delrolePer(node.nodeid);
					//作为父节点：取消勾选时，同时取消勾选所有子节点
					var children = node.nodes;
					if (children != undefined && !childTrigger) {
						$.each(children, function(i, child) {
							that.treeview('uncheckNode', [ child.nodeId ]);
						});
					}

					//作为子节点：取消勾选时，同时取消勾选父节点
					/* var parentId = node.parentId;
					if (parentId != undefined) {
						childTrigger = true;
						that.treeview('uncheckNode', [parentId]);
						childTrigger = false;
					} */
				}
			});
		}

		function setRoleNode() {
			$
					.ajax({
						type : 'post',
						data : {
							roleid : roleid
						},
						url : '${pageContext.request.contextPath}/roleController/rolepermissionlist',
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

		//添加
		function addrolePer(pid) {
			var datalist = {
				rid : roleid,
				pid : pid,
			};
			$
					.ajax({
						type : 'post',
						data : datalist,
						url : '${pageContext.request.contextPath}/roleController/addrolePer',
						success : function(result) {

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

		function delrolePer(pid) {
			var datalist = {
				rid : roleid,
				pid : pid,
			};
			$
					.ajax({
						type : 'post',
						data : datalist,
						url : '${pageContext.request.contextPath}/roleController/delrolePer',
						success : function(result) {

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

		function setUserRole() {
			$
					.ajax({
						type : 'post',
						data : {
							roleid : roleid
						},
						url : '${pageContext.request.contextPath}/userController/getRoleUser',
						success : function(result) {
							var text = lang[language + ".belongRoleUser"] + "\r\n";
							if (result.length == 0) {
								document.getElementById("userrole").value = text;
								return;
							}
							var rolejson = eval('(' + result + ')');
							for (var i = 0; i < rolejson.userinfo.length; i++) {
								text = text + lang[language + ".username"] + " : "
										+ rolejson.userinfo[i].name + "\r\n";
								//console.log("rolejson.userinfo[i].name" + text);
							}
							document.getElementById("userrole").value = text;
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

		function addRoleClick() {
			add_Form['addname'].value = "";
			$('#addModal').modal('show');
		}

		function addValidate() {
			var name = add_Form['addname'].value;
			
			if(!validateForm4(lang[language + ".rolename"], name)){
				return false;
			}
			
			addRole(name);
		}

		function addRole(name) {
			var data = {
				name : name
			};
			$("button").attr('disabled',true);
			$
					.ajax({
						type : 'post',
						url : '${pageContext.request.contextPath}/roleController/addRole',
						data : data,
						dataType : 'json',
						success : function(result) {
							$("button").attr('disabled',false);
							if (result == 2) {
								face_alert(lang[language + ".roleNoUnique"]);
							} else {
								if (result != 0) {
									$('#addModal').modal('hide');
									add_Form['addname'].value = "";
									initroletreeAll();
									face_succ(lang[language + ".addSucc"]);
								} else {
									return false;
								}
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

		function editRoleClick() {
			var nodenum = $('#roletree').treeview('getSelected');
			if (nodenum == 0) {
				face_alert(lang[language + ".chooseRole"]);
				return;
			} else {
				edit_Form['editname'].value = nodenum[0].text;
				$('#editModal').modal('show');
			}
		}

		function editValidate() {
			var name = edit_Form['editname'].value;
			var nodenum = $('#roletree').treeview('getSelected');
			
			if(!validateForm4(lang[language + ".rolename"], name)){
				return false;
			}
			
			editRole(name, nodenum[0].nodeid);
		}

		function editRole(name, id) {
			var data = {
				id : id,
				name : name
			};
			$
					.ajax({
						type : 'post',
						url : '${pageContext.request.contextPath}/roleController/editRole',
						data : data,
						dataType : 'json',
						success : function(result) {
							if (result == 2) {
								face_alert(lang[language + ".roleNoUnique"]);
							} else {
								if (result != 0) {
									$('#editModal').modal('hide');
									edit_Form['editname'].value = "";
									initroletreeAll();
									face_succ(lang[language + ".editSucc"]);
								} else {
									return false;
								}
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

		//删除角色
		function deleteRoleClick() {
			var strIds = [];
			var nodenum = $('#roletree').treeview('getSelected');
			if (nodenum == 0) {
				face_alert(lang[language + ".chooseRole"]);
				return;
			}
			strIds.push(Number(nodenum[0].nodeid));
			var ids = strIds.join(",");
			
			face_confirm(lang[language + ".confirmDel"], nodenum[0].text);
			
			//点击确定事件
			$("#confirmButton").click(function(){
				$("#confirmModal").modal('hide');
				$.ajax({
					type : 'post',
					url : '${pageContext.request.contextPath}/roleController/delRole',
					data : {
						ids : ids
					},
					success : function(result) {
						if (result != 0) {
							initroletreeAll();
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
	</script>
</body>

</html>
