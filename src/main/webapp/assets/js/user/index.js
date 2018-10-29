$(function(){
	// 初始化用户菜单
	$.ajax({  
       	type : 'post',
       	data : { userid : cUserSessionId },
		url : 'getUserMenu',
		success : function(data) {
			result = data;
			treeData = "<li class='nav-header'> <div class='dropdown profile-element'> <a data-toggle='dropdown' class='dropdown-toggle' href='#'> <span class='clear'> <span class='block m-t-xs' style='font-size: 20px;'> <strong class='font-bold'>portal-demo</strong> </span> </span> </a> </div> <div class='logo-element'>Portal</div> </li>";
			treeData = treeData + allnode(1);
			$("#side-menu").html(treeData);
		}
  	});
	
	//递归出来的值
	function allnode(index) {
		var childNum = 0;
		for (var row = 1; row < result.length; row++) {
			if (result[row].ptype == 0){
				if (result[row].flevel == index){
					if (result[row].state == 0) {
						tempValue = tempValue
								+ "<li> <a class='J_menuItem' href='${pageContext.request.contextPath}"
								+ result[row].url + "'> <i class='fa "
								+ result[row].icon
								+ "'></i> <span class='nav-label'>"
								+ result[row].cn_name + "</span> </a> </li>";
					} else if (result[row].state == 1) {
						if(haschild(result[row].id) == 0){
							childNum++;
							tempValue = tempValue
							+ "<li> <a class='J_menuItem' href='${pageContext.request.contextPath}"
							+ result[row].url + "'> <i class='fa "
							+ result[row].icon
							+ "'></i> <span class='nav-label'>"
							+ result[row].cn_name + "</span> </a> </li>";
							if(haschild(index) == childNum){
								tempValue = tempValue + "</ul></li>";
							}
						} else {
							tempValue = tempValue + "<li> <a href='#'> <i class='fa "
									+ result[row].icon
									+ "'></i> <span class='nav-label'>"
									+ result[row].cn_name + "</span> <span class='fa arrow'></span> </a> <ul class='nav nav-second-level'>";
							//tempValue = tempValue + allnode(result[row].id);
						}
					}
				} else {
					continue;
				}
			} else {// 按钮权限
				buttonPer = buttonPer + result[row].url + "|";
			}
		}
		return tempValue;
	}
	
	function haschild(index){
		var count = 0;
		for (var row = 1; row < result.length; row++) {
			if (result[row].ptype == 0){
				if (result[row].flevel == index) {
					count++;
				}
			}
		}
		return count;
	}
	
    //菜单点击
    $(".J_menuItem").on('click',function(){
        var url = $(this).attr('href');
        $("#J_iframe").attr('src',url);
        return false;
    });
    
});