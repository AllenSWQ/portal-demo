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
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only"><span langtag='langtag_cancel'>取消</span></span>
						</button>
						<h4 class="modal-title" id="myModalLabel"><span langtag='langtag_addPer'>新增权限</span></h4>
					</div>
					<div class="modal-body">
						<form id="add_Form" role="form" class="form-horizontal m-t">
							<div class="form-group">
								<label class="col-sm-3 control-label"><span style="color:red">* </span><span langtag='langtag_cnname'>中文名称</span> : </label>
								<div class="col-sm-8">
									<input id="addCnname" name="cn_name" class="form-control"
										type="text" aria-required="true" aria-invalid="true">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label"><span style="color:red">* </span><span langtag='langtag_enname'>英文名称</span> : </label>
								<div class="col-sm-8">
									<input id="addEnname" name="en_name" class="form-control"
										type="text" aria-required="true" aria-invalid="true">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label"><span langtag='langtag_describe'>描述</span> : </label>
								<div class="col-sm-8">
									<input id="adddesc" name="describe" class="form-control"
										type="text">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">Icon : </label>
								<div class="col-sm-8">
									<input id="addicon" name="icon" class="form-control"
										type="text">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">URL : </label>
								<div class="col-sm-8">
									<input id="addnurl" name="url" class="form-control" type="text"
										aria-required="true" aria-invalid="true">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label"><span style="color:red">* </span><span langtag='langtag_type'>类型</span> : </label>
								<div class="col-sm-8" style="overflow: hidden;">
									<input id="addptype" name="ptype" class="form-control"
										type="radio" value="0" aria-required="true"
										aria-invalid="true" checked="checked"
										style="width: 20px; vertical-align: middle; margin-top: 0; float: left;">
									<label class="control-label"
										style="float: left; margin: 0px 40px 0px 5px;"><span langtag='langtag_menu'>菜单</span></label> <input
										id="addptype" name="ptype" class="form-control" type="radio"
										value="1" aria-required="true" aria-invalid="true"
										style="width: 20px; vertical-align: middle; margin-top: 0; float: left;">
									<label class="control-label"
										style="float: left; margin: 0px 40px 0px 5px;"><span langtag='langtag_button'>按钮</span></label>
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
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only"><span langtag='langtag_cancel'>取消</span></span>
						</button>
						<h4 class="modal-title" id="myModalLabel"><span langtag='langtag_editPer'>修改权限</span></h4>
					</div>
					<div class="modal-body">
						<form id="edit_Form" role="form" class="form-horizontal m-t">
							<div class="form-group">
								<label class="col-sm-3 control-label"><span style="color:red">* </span><span langtag='langtag_cnname'>中文名称</span> : </label>
								<div class="col-sm-8">
									<input id="editCnname" name="cn_name" class="form-control"
										type="text" aria-required="true" aria-invalid="true">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label"><span style="color:red">* </span><span langtag='langtag_enname'>英文名称</span> : </label>
								<div class="col-sm-8">
									<input id="editEnname" name="en_name" class="form-control"
										type="text" aria-required="true" aria-invalid="true">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label"><span langtag='langtag_describe'>描述</span> : </label>
								<div class="col-sm-8">
									<input id="editdesc" name="describe" class="form-control"
										type="text">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">Icon : </label>
								<div class="col-sm-8">
									<input id="editicon" name="icon" class="form-control"
										type="text" aria-required="true" aria-invalid="true">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">URL : </label>
								<div class="col-sm-8">
									<input id="editnurl" name="url" class="form-control"
										type="text" aria-required="true" aria-invalid="true">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label"><span style="color:red">* </span><span langtag='langtag_type'>类型</span> : </label>
								<div class="col-sm-8" style="overflow: hidden;">
									<input id="editptype" name="ptype" class="form-control"
										type="radio" value="0" aria-required="true"
										aria-invalid="true" checked="checked"
										style="width: 20px; vertical-align: middle; margin-top: 0; float: left;">
									<label class="control-label"
										style="float: left; margin: 0px 40px 0px 5px;"><span langtag='langtag_menu'>菜单</span></label> <input
										id="editptype" name="ptype" class="form-control" type="radio"
										value="1" aria-required="true" aria-invalid="true"
										style="width: 20px; vertical-align: middle; margin-top: 0; float: left;">
									<label class="control-label"
										style="float: left; margin: 0px 40px 0px 5px;"><span langtag='langtag_button'>按钮</span></label>
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
			<div class="col-xs-1" style="position: fixed;width:6% !important;">
				<div style="margin-bottom: 12px;">
					<button id="btn_add_per" type="button" class="btn btn-default hide"
						onclick="addPerClick()">
						<span class="glyphicon glyphicon-plus tableFuncIcon"
							aria-hidden="true"></span><span langtag='langtag_add'>新增</span>
					</button>
					<button id="btn_edit_per" type="button"
						class="btn btn-default hide" onclick="editPerClick()">
						<span class="glyphicon glyphicon-pencil tableFuncIcon"
							aria-hidden="true"></span><span langtag='langtag_edit'>修改</span>
					</button>
					<button id="btn_del_per" type="button" class="btn btn-default hide"
						onclick="delPerClick()">
						<span class="glyphicon glyphicon-remove tableFuncIcon"
							aria-hidden="true"></span><span langtag='langtag_del'>删除</span>
					</button>
				</div>
			</div>
			<div class="col-xs-4" style="margin-left:9%;overflow:scroll;height:900px;">
				<div id="Permtree"></div>
			</div>
		</div>
	</div>
</body>

<script type="text/javascript">
	$(document).ready(function() {
		showLanguageText();

		if (buttonPerList.indexOf('addPerClick') != -1) {
			$("#btn_add_per").removeClass('hide');
		}
		if (buttonPerList.indexOf('editPerClick') != -1) {
			$("#btn_edit_per").removeClass('hide');
		}
		if (buttonPerList.indexOf('delPerClick') != -1) {
			$("#btn_del_per").removeClass('hide');
		}

		initpermtreeAll();
	});

	var childTrigger = false;
	var userid;
	var messageState = true;

	function initpermtreeAll() {
		$
				.ajax({
					type : 'post',
					url : '${pageContext.request.contextPath}/userController/permissionList',
					success : function(data) {
						permissionlistlength = data.length;
						result = data;
						treeData = "{ nodeid: '" + result[0].id + "',text:'"
								+ (language == 'cn' ? result[0].cn_name : result[0].en_name) + "',en_name:'"
								+ (language == 'cn' ? result[0].en_name : result[0].cn_name) + "',tag:'"
								+ result[0].describe + "', url:'"
								+ result[0].url + "', level:'"
								+ result[0].state + "', flevel:'"
								+ result[0].flevel + "', ptype:'"
								+ result[0].ptype + "', icon:'"
								+ result[0].icon + "', nodes: [";
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
						+ (language == 'cn' ? result[row].en_name : result[row].cn_name) + "', tag:'"
						+ result[row].describe + "', url:'" + result[row].url
						+ "', level:'" + result[row].state + "', flevel:'"
						+ result[row].flevel + "', ptype:'" + result[row].ptype
						+ "', icon:'" + result[row].icon + "'},";
			} else if (result[row].flevel == index && result[row].state == 1) {
				tempValue = tempValue + "{ nodeid: '" + result[row].id
						+ "',text:'" + (language == 'cn' ? result[row].cn_name : result[row].en_name) + "',en_name:'"
						+ (language == 'cn' ? result[row].en_name : result[row].cn_name) + "', tag:'"
						+ result[row].describe + "', url:'" + result[row].url
						+ "', level:'" + result[row].state + "', flevel:'"
						+ result[row].flevel + "', ptype:'" + result[row].ptype
						+ "', icon:'" + result[row].icon + "', nodes: [";
				tempValue = tempValue + allnode(result[row].id);
			}
		}
		return tempValue + "]},";
	}

	function initpermtree(tree) {
		var $PermtreeTree = $('#Permtree').treeview({
			data : tree,
			levels : 5,
			showTags : true,
			multiSelect : false,
			showCheckbox : false,
			showIcon : true,
			onNodeSelected : function(event, data) {
			},
			onNodeChecked : function(e, node) {
				messageState = false;
				var that = $(this);
				var children = node.nodes;
				if (children != undefined) {
					$.each(node.nodes, function(i, child) {
						that.treeview('checkNode', [ child.nodeId ]);
					});
				}

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
				if (boolCheckAll && parentId != undefined) {
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

				//作为子节点：取消勾选时，同时取消勾选父节点
				var parentId = node.parentId;
				if (parentId != undefined) {
					childTrigger = true;
					that.treeview('uncheckNode', [ parentId ]);
					childTrigger = false;
				}
			}
		});
	}

	function addPerClick() {
		var nodenum = $('#Permtree').treeview('getSelected');
		if (nodenum == 0) {
			face_alert(lang[language + ".chooseFPer"]);
			return;
		} else if (nodenum[0].ptype == '1') {
			face_alert(lang[language + ".buttonPerNoChild"]);
			return;
		} else {
			add_Form['addCnname'].value = "";
			add_Form['addEnname'].value = "";
			add_Form['adddesc'].value = "";
			add_Form['addicon'].value = "";
			add_Form['addnurl'].value = "";
			add_Form['addptype'].value = "0";
			$('#addModal').modal('show');
		}
	}

	function addValidate() {
		var cn_name = add_Form['addCnname'].value;
		var en_name = add_Form['addEnname'].value;
		var describe = add_Form['adddesc'].value;
		var icon = add_Form['addicon'].value;
		var url = add_Form['addnurl'].value;
		var ptype = add_Form['addptype'].value;
		var nodenum = $('#Permtree').treeview('getSelected');
		
		if(!validateForm5(lang[language + ".cnname"], cn_name)){
			return false;
		}
		if(!validateForm6(lang[language + ".enname"], en_name)){
			return false;
		}
		if(!validateForm2(lang[language + ".describe"], describe)){
			return false;
		}
		if(!validateForm7("Icon", icon)){
			return false;
		}
		if(!validateForm7("URL", url)){
			return false;
		}
		
		addPer(cn_name, en_name, describe, icon, url, nodenum[0].nodeid, ptype);
	}

	function addPer(cn_name, en_name, describe, icon, url, flevel, ptype) {
		var data = {
			cn_name : cn_name,
			en_name : en_name,
			describe : describe,
			icon : icon,
			url : url,
			flevel : flevel,
			ptype : ptype
		};
		$.ajax({
			type : 'post',
			url : '${pageContext.request.contextPath}/perController/addPer',
			data : data,
			dataType : 'json',
			success : function(result) {
				if (result != 0) {
					$('#addModal').modal('hide');
					add_Form['addCnname'].value = "";
					add_Form['addEnname'].value = "";
					add_Form['adddesc'].value = "";
					add_Form['addicon'].value = "";
					add_Form['addnurl'].value = "";
					add_Form['addptype'].value = "0";
					initpermtreeAll();
					face_succ(lang[language + ".addSucc"]);
				} else {
					return false;
				}
			}
		});
	}

	function editPerClick() {
		var nodenum = $('#Permtree').treeview('getSelected');
		if (nodenum == 0) {
			face_alert(lang[language + ".choosePer"]);
			return;
		} else {
			edit_Form['editCnname'].value = (language == 'cn' ? nodenum[0].text : nodenum[0].en_name);
			edit_Form['editEnname'].value = (language == 'cn' ? nodenum[0].en_name : nodenum[0].text);
			edit_Form['editdesc'].value = nodenum[0].tag;
			edit_Form['editicon'].value = nodenum[0].icon;
			edit_Form['editnurl'].value = nodenum[0].url;
			edit_Form['editptype'].value = nodenum[0].ptype;
			$('#editModal').modal('show');
		}
	}

	function editValidate() {
		var cn_name = edit_Form['editCnname'].value;
		var en_name = edit_Form['editEnname'].value;
		var describe = edit_Form['editdesc'].value;
		var icon = edit_Form['editicon'].value;
		var url = edit_Form['editnurl'].value;
		var ptype = edit_Form['editptype'].value;
		var nodenum = $('#Permtree').treeview('getSelected');
		
		if(!validateForm5(lang[language + ".cnname"], cn_name)){
			return false;
		}
		if(!validateForm6(lang[language + ".enname"], en_name)){
			return false;
		}
		if(!validateForm2(lang[language + ".describe"], describe)){
			return false;
		}
		if(!validateForm7("Icon", icon)){
			return false;
		}
		if(!validateForm7("URL", url)){
			return false;
		}
		
		editPer(cn_name, en_name, describe, icon, url, nodenum[0].nodeid, ptype);
	}

	function editPer(cn_name, en_name, describe, icon, url, id, ptype) {
		var data = {
			cn_name : cn_name,
			en_name : en_name,
			describe : describe,
			icon : icon,
			url : url,
			id : id,
			ptype : ptype
		};
		$.ajax({
			type : 'post',
			url : '${pageContext.request.contextPath}/perController/editPer',
			data : data,
			dataType : 'json',
			success : function(result) {
				if (result != 0) {
					$('#editModal').modal('hide');
					edit_Form['editCnname'].value = "";
					edit_Form['editEnname'].value = "";
					edit_Form['editdesc'].value = "";
					edit_Form['editicon'].value = "";
					edit_Form['editnurl'].value = "";
					edit_Form['editptype'].value = "0";
					initpermtreeAll();
					face_succ(lang[language + ".editSucc"]);
				} else {
					return false;
				}
			}
		});
	}

	//删除权限！
	function delPerClick() {
		var nodenum = $('#Permtree').treeview('getSelected');
		if (nodenum == 0) {
			face_alert(lang[language + ".choosePer"]);
			return;
		}

		var data = {
			rid : Number(nodenum[0].nodeid),
			flevel : Number(nodenum[0].flevel)
		};
		
		face_confirm(lang[language + ".confirmDel"], nodenum[0].text);
		
		//点击确定事件
		$("#confirmButton").click(function(){
			$("#confirmModal").modal('hide');
			$.ajax({
				type : 'post',
				url : '${pageContext.request.contextPath}/perController/delPer',
				data : data,
				success : function(result) {
					if (result != 0) {
						initpermtreeAll();
						face_succ(lang[language + ".delSucc"]);
					} else {
						return false;
					}
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

</html>
