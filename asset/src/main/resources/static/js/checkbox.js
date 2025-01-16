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
	$("#runGoalCheckBoxs").click(function(){
		if($("#runGoalCheckBoxs").prop("checked")){
			$("input:checkbox[name=runGoalNums]").prop("checked",true);
		} else{
			$("input:checkbox[name=runGoalNums]").prop("checked",false);
		}
		prodChk();
	});
	$("input:checkbox[name=runGoalNums]").click(function(){
		var tot = $("input:checkbox[name=runGoalNums]").length;
		var cnt = $("input:checkbox[name=runGoalNums]:checked").length;
		if (tot == cnt) $("#runGoalCheckBoxs").prop("checked", true);
		else $("#runGoalCheckBoxs").prop("checked", false);
		prodChk();
	});
	
});

$(function(){
	$("#finishGoalCheckBoxs").click(function(){
		if($("#finishGoalCheckBoxs").prop("checked")){
			$("input:checkbox[name=finishGoalNums]").prop("checked",true);
		} else{
			$("input:checkbox[name=finishGoalNums]").prop("checked",false);
		}
		prodChk();
	});
	$("input:checkbox[name=runGoalNums]").click(function(){
		var tot = $("input:checkbox[name=finishGoalNums]").length;
		var cnt = $("input:checkbox[name=finishGoalNums]:checked").length;
		if (tot == cnt) $("#finishGoalCheckBoxs").prop("checked", true);
		else $("#finishGoalCheckBoxs").prop("checked", false);
		prodChk();
	});
	
});