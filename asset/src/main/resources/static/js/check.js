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
	
	$("#newPwCon").on("change keyup", function(){
			$.ajax({
				type: "post",
				url: "/myPage/newPwCheck",
				data: {"newPw": $("#newPw").val(),
					"newPwCon": $("#newPwCon").val()},
				dataType: "text",
				success : function(result){
					if(result == "1"){
						$("#pwCheck").text("비밀번호가 일치합니다.");
						$("#pwCheck").css("color", "blue");
					}else{
						$("#pwCheck").text("비밀번호가 일치하지않습니다.");
						$("#pwCheck").css("color", "red");
					}
				},
				error:function(){
					alert("서버오류");
				}
			});
		});
});