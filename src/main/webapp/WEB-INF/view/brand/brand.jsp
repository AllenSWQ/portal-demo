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
<jsp:include page="${path}/assets/include/include.jsp?v=0.0.4"></jsp:include>
<script type="text/javascript">
	var buttonPerList = window.parent.buttonPer;
	var language = window.parent.language;
	var src = "${pageContext.request.contextPath}/assets/js/plugins/bootstrap-table/locale/bootstrap-table-"
			+ language + ".js";
	loadJs(src);
</script>
</head>

<body style="background-color:#f0f3f4;">

	<!-- 添加模态框 begin -->
	<div class="modal fade" id="addBrand" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-center" style="width:650px;height:252px;transform:translateX(-50%) translateY(-50%);">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only"><span
							langtag='langtag_cancel'>取消</span></span>
					</button>
					<h4 class="modal-title" id="myModalLabel">
						<span langtag='langtag_addbrand'>添加厂商</span>
					</h4>
				</div>
				<div class="modal-body">
					<form id="add_Form" role="form" class="form-horizontal m-t">
						<div class="form-group">
							<label class="col-sm-4 control-label"><span style="color:red">* </span><span
								langtag='langtag_branden'>厂商英文名称</span> : </label>
							<div class="col-sm-6">
								<input id="addbranden" name="brand_en" class="form-control" type="text"
									aria-required="true" aria-invalid="true">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label"><span style="color:red">* </span><span
								langtag='langtag_brandcn'>厂商中文名称</span> : </label>
							<div class="col-sm-6">
								<input id="addbrandcn" name="brand_cn" class="form-control" type="text"
									aria-required="true" aria-invalid="true">
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

	<!-- 编辑模态框 begin -->
	<div class="modal fade" id="editBrand" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-center" style="width:650px;height:252px;transform:translateX(-50%) translateY(-50%);">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only"><span
							langtag='langtag_cancel'>取消</span></span>
					</button>
					<h4 class="modal-title" id="myModalLabel">
						<span langtag='langtag_editbrand'>修改厂商</span>
					</h4>
				</div>
				<div class="modal-body">
					<form id="edit_Form" role="form" class="form-horizontal m-t">
						<input id="editid" name="id" type="hidden">
						<div class="form-group">
							<label class="col-sm-4 control-label"><span style="color:red">* </span><span
								langtag='langtag_branden'>厂商英文名称</span> : </label>
							<div class="col-sm-6">
								<input id="editbrandenold" name="brand_en_old" type="hidden">
								<input id="editbranden" name="brand_en" class="form-control"
									type="text" aria-required="true" aria-invalid="true">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label"><span style="color:red">* </span><span
								langtag='langtag_brandcn'>厂商中文名称</span> : </label>
							<div class="col-sm-6">
								<input id="editbrandcnold" name="brand_cn_old" type="hidden">
								<input id="editbrandcn" name="brand_cn" class="form-control"
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
	<!-- 编辑模态框 end -->
	
	<!-- 信息 begin -->
	<div id="searchDiv" class="container"
		style="margin-top: 15px; padding-top: 0px; padding-bottom: 0px; display: none;">
		<div id="dashboardBodyDiv"
			class="padding-md dashboard-body search-range">
			<div id="search-div">
				<form id="search_Form" role="form" class="form-horizontal">
					<!-- <input id="searchbrand" name="searchbrand" class="form-control hide" type="text"
						aria-required="true" aria-invalid="true" style="width:200px;float:left;margin-right:5px;margin-top:1px;" placeholder="厂商"> -->
					<select id="searchbrand" name="searchbrand" class="multiselect"></select>
					<button id="btn_search_brand" type="button" class="btn btn-default btn-search hide"
						onclick="doSearch()">
						<span langtag='langtag_search'>查询</span>
					</button>
					<button id="btn_add_brand" type="button" class="btn btn-default btn-export hide"
						onclick="addBrandClick()">
						<span class="glyphicon glyphicon-plus tableFuncIcon"
							aria-hidden="true"></span><span langtag='langtag_addbrand'>添加厂商</span>
					</button>
				</form>
			</div>
		</div>
	</div>

	<div class="container">
		<div class="panel-heading dashboard-head">
			<b langtag='langtag_BrandMngt'></b>
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
			$('#searchbrand').attr('placeholder',lang[language + '.brand']);

			if (buttonPerList.indexOf('addBrandClick') != -1) {
				$("#searchDiv").css('display', 'block');
				$("#btn_add_brand").removeClass('hide');
			}
			if (buttonPerList.indexOf('searchBrandClick') != -1){
				$("#searchDiv").css('display', 'block');
				//initBrandInput("#searchbrand");
				initSearchBrandSelect("#searchbrand");
				$("#searchbrand").removeClass('hide');
				$("#btn_search_brand").removeClass('hide');
			}
			initTable();
		});
		
		/* $('input[type=number]').keypress(function(e) {
			if (!String.fromCharCode(e.keyCode).match(/[0-9\.]/)) {
				return false;
			}
		}); */

		function addBrandClick() {
			add_Form['addbranden'].value = "";
			add_Form['addbrandcn'].value = "";
			$('#addBrand').modal('show');
		}

		function addValidate() {
			var brand_en = add_Form['addbranden'].value;
			var brand_cn = add_Form['addbrandcn'].value;
			
			if(!validateFormForBrandEn(lang[language + ".branden"], brand_en)){
				return false;
			}
			if(!validateFormForBrandCn(lang[language + ".brandcn"], brand_cn)){
				return false;
			}
			
			add(brand_en, brand_cn);
		}

		function add(brand_en, brand_cn) {
			var data = {
				brand_en : brand_en,
				brand_cn : brand_cn
			};
			$("button").attr('disabled',true);
			$.ajax({
				type : 'post',
				url : '${pageContext.request.contextPath}/brandController/addBrand',
				data : data,
				dataType : 'json',
				success : function(result) {
					$("button").attr('disabled',false);
					if (result.status == "notunique") {
						face_alert(lang[language + ".brandNoUnique"]);
					} else if (result.status == "success") {
						$('#addBrand').modal('hide');
						initBrandInput("#searchbrand");
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
		
		function editBrandClick(id, brand_en, brand_cn) {
			edit_Form['editid'].value = id;
			edit_Form['editbranden'].value = brand_en;
			edit_Form['editbrandcn'].value = brand_cn;
			edit_Form['editbrandenold'].value = brand_en;
			edit_Form['editbrandcnold'].value = brand_cn;
			$('#editBrand').modal('show');
		}

		function editValidate() {
			var id = edit_Form['editid'].value;
			var brand_en = edit_Form['editbranden'].value;
			var brand_cn = edit_Form['editbrandcn'].value;
			var brand_en_old = edit_Form['editbrandenold'].value;
			var brand_cn_old = edit_Form['editbrandcnold'].value;
			
			if(!validateFormForBrandEn(lang[language + ".branden"], brand_en)){
				return false;
			}
			if(!validateFormForBrandCn(lang[language + ".brandcn"], brand_cn)){
				return false;
			}
			if (brand_en.trim() == brand_en_old.trim()
					&& brand_cn.trim() == brand_cn_old.trim()) {
				face_alert(lang[language + ".brandeditValidate"]);
				return false;
			}

			edit(id, brand_en, brand_cn);
		}

		function edit(id, brand_en, brand_cn) {
			var data = {
				id : id,
				brand_en : brand_en,
				brand_cn : brand_cn
			};
			$
					.ajax({
						type : 'post',
						url : '${pageContext.request.contextPath}/brandController/editBrand',
						data : data,
						dataType : 'json',
						success : function(result) {
							if (result.status == "notunique") {
								face_alert(lang[language + ".brandNoUnique"]);
							} else if (result.status == "success") {
								$('#editBrand').modal('hide');
								initBrandInput("#searchbrand");
								$('#table').bootstrapTable('refresh');
								face_succ(lang[language + ".editSucc"]);
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
								url : '${pageContext.request.contextPath}/brandController/brandList',
								dataType : 'json',
								striped : true,
								uniqueId : "id",
								pagination : true, //在表格底部显示分页工具栏
								pageSize : 15,
								pageNumber : 1,
								pageList : [ 15, 30, 50, 'ALL' ],
								paginationFirstText : lang[language
										+ ".firstPage"],
								paginationPreText : lang[language + ".lastPage"],
								paginationNextText : lang[language
										+ ".nextPage"],
								paginationLastText : lang[language + ".endPage"],
								sortable : false,
								showColumns : false, //显示隐藏列 
								showRefresh : false, //显示刷新按钮
								search : buttonPerList
										.indexOf('searchBrandClick') != -1 ? false
										: false,//是否显示右上角的搜索框
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
											field : 'brand_cn',
											title : lang[language + ".brandcn"],
											sortable : false,
											width : '30%'
										},
										{
											field : 'brand_en',
											title : lang[language + ".branden"],
											sortable : false,
											width : '30%'
										},
										{
											title : lang[language
													+ ".operation"],
											align : 'left',
											width : '40%',
											visible : (buttonPerList
													.indexOf('editBrandClick') == -1 && buttonPerList
													.indexOf('deleteBrandClick') == -1) ? false
													: true,
											formatter : function(value, row,
													index) {
												var brand_name = row.brand_cn;
												if (language == 'en') {
													brand_name = row.brand_en;
												}
												var e = '<button type="button" class="btn btn-default btn-operate" onclick="editBrandClick(\''
													+ row.id
													+ '\', \''
													+ row.brand_en
													+ '\', \''
													+ row.brand_cn
													+ '\')">'
													+ lang[language + ".edit"]
													+ '</button>';
												if (buttonPerList
														.indexOf('editBrandClick') == -1) {
													e = "";
												}
												var d = '<button type="button" class="btn btn-default btn-operate" onclick="deleteBrandClick(\''
													+ row.id
													+ '\', \''
													+ brand_name
													+ '\')">'
													+ lang[language + ".delete"]
													+ '</button>';
												if (buttonPerList
														.indexOf('deleteBrandClick') == -1) {
													d = "";
												}
												return e + "<span style='margin-left:5px'/>" + d;
											}
										} ]
							});
		}

		function deleteBrandClick(id, name) {
			face_confirm(lang[language + ".confirmDel"], name);

			//点击确定事件
			$("#confirmButton")
					.click(
							function() {
								$("#confirmModal").modal('hide');
								$
										.ajax({
											type : 'post',
											url : '${pageContext.request.contextPath}/brandController/delBrand',
											data : {
												id : id
											},
											dataType : 'json',
											success : function(result) {
												if (result.status == "success") {
													initBrandInput("#searchbrand");
													$('#table').bootstrapTable(
															'refresh');
													face_succ(lang[language
															+ ".delSucc"]);
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

		function doSearch() {
			var data = {
				url : "${pageContext.request.contextPath}/brandController/brandList?search="
						+ $("#searchbrand").val()
			};
			$('#table').bootstrapTable('refresh', data);
		}
	</script>
</body>
</html>
