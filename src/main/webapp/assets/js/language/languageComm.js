//检查文档中需要替换的文字信息
var webPath;
function showLanguageText(){
	// 当前页
	$("[langtag^='langtag_']").each(function(a,b){		
		var tmp = language + '.' + $(this).attr('langtag').substr(8,$(this).attr('langtag').length);
		var t=this;
		if(t.tagName=='INPUT' && t.type=='button'){
			//t.value=lang[tmp];
			$(this).val(lang[tmp]);
		}else if(t.tagName=='INPUT'){
			//t.value=lang[tmp];
			$(this).val(lang[tmp]);
		}else if(t.tagName=='TEXTAREA' ){ 
			//t.value=lang[tmp];
			$(this).val(lang[tmp]);
		}else{
			//t.innerHTML=lang[tmp];
			$(this).html(lang[tmp]);
		}
	});
}

//动态加载js
function loadJs(src) {
    document.write('<script src="'+src+'" type="text/javascript"></script>');
}