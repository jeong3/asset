/**
 * check.js
 */
$(function(){
	$("#userId").on("change keyup", function(){
		$.ajax({
			type: "post",
			url: "/login/userIdCheck",
			data: {"userId": $("#userId").val()},
			dataType: "text",
			success : function(result){
				if(result == "1"){
					$("#idCheck").text("사용중인 아이디입니다");
					$("#idCheck").css("color", "red");
				}else{
					$("#idCheck").text("사용가능한 아이디입니다");
					$("#idCheck").css("color", "blue");
				}
			},
			error:function(){
				alert("서버오류");
			}
		});
	});
});