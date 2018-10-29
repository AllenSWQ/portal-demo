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
	<!-- 信息 begin -->
	<div id="containerDiv" class="container"
		style="margin-top: 15px; padding-top: 0px; padding-bottom: 0px;">
		<div id="dashboardBodyDiv"
			class="padding-md dashboard-body search-range">
			<div id="search-div">
				<form id="search_Form" role="form" class="form-horizontal">
					<button id="btn_return" type="button" class="btn btn-default"
						onclick="returnClick()" style="margin-right:5px;">
						<span class="glyphicon glyphicon-share-alt tableFuncIcon"
							aria-hidden="true"></span><span langtag='langtag_return'>返回</span>
					</button>
				</form>
			</div>
		</div>
	</div>

	<div class="container">
		<div id="dashboardBodyDiv"
			class="padding-md dashboard-body table-range">
				<div style="margin-bottom:12px;">
					<select id="searchmodel" name="searchmodel" class="multiselect"></select>
				</div>
				<div style="margin-bottom: 12px;">
					<div id="Permtree" style="overflow:scroll;max-height:600px;"></div>
				</div>
				<div style="text-align:center">
					<button type="button" class="btn btn-primary"
						onclick="save()">
						<span class="glyphicon glyphicon-floppy-disk tableFuncIcon"
							aria-hidden="true"></span><span langtag='langtag_save'>保存</span>
					</button>
					<button type="button" class="btn btn-primary"
						onclick="reset()">
						<span class="glyphicon glyphicon-remove tableFuncIcon"
							aria-hidden="true"></span><span langtag='langtag_reset'>重置</span>
					</button>
				</div>
		</div>
	</div>

	<script type="text/javascript">
		var brand_id = '${cUserSession.brand_id}';
		$(document).ready(function() {
			showLanguageText();
			initSearchModelSelectL("#searchmodel", brand_id, null);
			initpermtreeAll();
		});

		var childTrigger = false;
		var userid;
		var messageState = true;

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

		function setPerNode() {
			$
					.ajax({
						type : 'post',
						data : {
							userid : userid
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
							for (var i = 1; i <= permissionlistlength; i++) {
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
		
		function returnClick() {
			window.location.href = "${pageContext.request.contextPath}/subaccountController/subaccount";
		}
	</script>
</body>

</html>
