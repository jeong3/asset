/**
 * 
 */
$(function(){
	$("#checkBoxs").click(function(){
		if($("#checkBoxs").prop("checked")){
			$("input:checkbox[name=nums]").prop("checked",true);
		} else{
			$("input:checkbox[name=nums]").prop("checked",false);
		}
		prodChk();
	});
	$("input:checkbox[name=nums]").click(function(){
		var tot = $("input:checkbox[name=nums]").length;
		var cnt = $("input:checkbox[name=nums]:checked").length;
		if (tot == cnt) $("#checkBoxs").prop("checked", true);
		else $("#checkBoxs").prop("checked", false);
		prodChk();
	});
	
});

$(function(){
	$("#dealCheckBoxs").click(function(){
		if($("#dealCheckBoxs").prop("checked")){
			$("input:checkbox[name=dealNums]").prop("checked",true);
		} else{
			$("input:checkbox[name=dealNums]").prop("checked",false);
		}
		prodChk();
	});
	$("input:checkbox[name=dealNums]").click(function(){
		var tot = $("input:checkbox[name=dealNums]").length;
		var cnt = $("input:checkbox[name=dealNums]:checked").length;
		if (tot == cnt) $("#dealCheckBoxs").prop("checked", true);
		else $("#dealCheckBoxs").prop("checked", false);
		prodChk();
	});
	
});

$(function(){
	$("#goalCheckBoxs").click(function(){
		if($("#goalCheckBoxs").prop("checked")){
			$("input:checkbox[name=goalNums]").prop("checked",true);
		} else{
			$("input:checkbox[name=goalNums]").prop("checked",false);
		}
		prodChk();
	});
	$("input:checkbox[name=goalNums]").click(function(){
		var tot = $("input:checkbox[name=goalNums]").length;
		var cnt = $("input:checkbox[name=goalNums]:checked").length;
		if (tot == cnt) $("#goalCheckBoxs").prop("checked", true);
		else $("#goalCheckBoxs").prop("checked", false);
		prodChk();
	});
	
});