/**
 * 
 */
const formatter = new Intl.NumberFormat('ko-KR',{style : 'currency', currency : 'KRW'});

$(function() {
	memEmpInfo();
	memberList();
	empList();
	myInfo();
	//wishStatus();
});
// Accordion 
function myAccFunc() {
	var x = document.getElementById("demoAcc");
	if (x.className.indexOf("w3-show") == -1) {
		x.className += " w3-show";
	} else {
		x.className = x.className.replace(" w3-show", "");
	}
}



// Open and close sidebar
function w3_open() {
	document.getElementById("mySidebar").style.display = "block";
	document.getElementById("myOverlay").style.display = "block";
}

function w3_close() {
	document.getElementById("mySidebar").style.display = "none";
	document.getElementById("myOverlay").style.display = "none";
}

function InquireList(){
	$.ajax({
			type: "get",
			url: "/inquire/inquireList",
			dataType: "html",
			success: function(result) {
				$("#content").html(result);
			},
			error: function() {
				alert("서버 오류");
			}
		});
}
function InquireListEmp(){
	$.ajax({
			type: "get",
			url: "/inquire/InquireListEmp",
			dataType: "html",
			success: function(result) {
				$("#content").html(result);
			},
			error: function() {
				alert("서버 오류");
			}
		});
}
function InquireRegist() {
	$.ajax({
			type: "get",
			url: "/inquire/inquireRegist",
			dataType: "html",
			success: function(result) {
				$("#content").html(result);
			},
			error: function() {
				alert("서버 오류");
			}
		});
	}
	
	function InquireRegistPost() {
		var formData = JSON.stringify({
		    "inquireKind": $("#inquireKind").val(),
		    "goodsNum": $("#goodsNum").val(),
		    "inquireSubject": $("#inquireSubject").val(),
		    "inquireContents": $("#inquireContents").val()
		});

		$.ajax({
				type: "post",
				url: "/inquire/inquireRegist",
				data: formData,
				contentType: "application/json",
				dataType: "html",
				success: function(result) {
					
				},
				error: function() {
					alert("서버 오류");
				},
				complete:InquireList
			});
		}


function paymentDelete(element){
	if (confirm("정말 취소하시겠습니까?")) {
	var purchaseNum = $(element).data('purchaseNum');
		$.ajax({
			type: "post",
			url: "/purchase/paymentDelete",
			data: { "purchaseNum": purchaseNum },
			dataType: "json",
			success: function(result) {
				if(result == 200){
					alert("결제가 취소되었습니다.");
				}
			},
			error: function() {
				alert("결제 취소를 실패하였습니다. 다시 시도 후 문의해주세요.");
			},
			complete : memPurchaseList
		});
	}
}
function paymentOk(element){
	var purchaseNum = $(element).data('purchaseNum');
		$.ajax({
			type: "post",
			url: "/purchase/paymentOk",
			data: { "purchaseNum": purchaseNum },
			dataType: "html",
			success: function(result) {
				$("#content").html(result);
			},
			error: function() {
				alert("서버 오류");
			}
		});
	
}

function submitOrder() {
           const form = document.querySelector('form');
           const formData = new FormData(form);

           // AJAX 요청
           $.ajax({
               url: '/purchase/goodsOrder', // 서버의 처리 URL
               type: 'POST',
               data: JSON.stringify(Object.fromEntries(formData)), // 데이터를 JSON 형식으로 변환
               contentType: 'application/json; charset=UTF-8', // JSON 형식으로 전송
               success: function(result) {
                   // 서버 응답 처리
                   $("#content").html(result);
               },
               error: function(xhr, status, error) {
                   alert('주문 처리 중 오류가 발생했습니다.');
                   console.error(error);
               }
           });
       }

function memPurchaseList(){
	$.ajax({
		type:"get",
		url:"/purchase/myPurchase",
		dataType: "html",  // 응답을 HTML 형식으로 처리
        success: function(result) {
			$("#content").html(result);
        },
        error: function() {
            alert("주문정보를 불러오는데 실패했습니다.");
        }
	});
}	   
function empPurchaseList(){
	$.ajax({
		type:"get",
		url:"/purchase/purchaseList",
		dataType: "html",  // 응답을 HTML 형식으로 처리
        success: function(result) {
			$("#content").html(result);
        },
        error: function() {
            alert("주문정보를 불러오는데 실패했습니다.");
        }
	});
}	   
function purchaseRegist(cartNum){
	var cartNums = [];
		if(cartNum != null){
			cartNums.push(cartNum);
		} else{
			 $("input:checkbox[name=nums]:checked").each(function(){
					cartNums.push($(this).val());
			 });
		}
	$.ajax({
		type: "post",
		url: "/purchase/purchaseRegist",
		contentType: "application/json",
        data: JSON.stringify(cartNums), // JSON 형식의 데이터 전송
		success: function(result) {
			$("#content").html(result);
		},
		error: function() {
			alert("서버 오류가 발생했습니다.");
		},
	});
}
function deleteIpgo(ipgoNum) {
	if (confirm("정말 삭제하시겠습니까?")) {
		$.ajax({
			type: "get",
			url: "/ipgo/ipgoDelete",
			data: { ipgoNum: ipgoNum },
			success: function(response) {
				
			},
			error: function() {
				alert("서버 오류가 발생했습니다. 삭제할 수 없습니다.");
			},
			complete : goodsIpgoList
		});
	} else {
		goodsIpgoList();
	}
}
function buyNow(element){
	var goodsNum = $(element).data('goodsNum');  // 상품 번호
	var cartQty = "1";           // 수량

	   $.ajax({
	       type: "POST",
	       url: "/item/addCart",  // 서버에서 처리할 URL
	       contentType: "application/json",  // JSON 형식으로 데이터 전송
	       data: JSON.stringify({  // 데이터를 JSON 형식으로 변환하여 전송
	           "goodsNum": goodsNum,
	           "cartQty": cartQty
	       }),
	       dataType: "html",  // 응답을 HTML 형식으로 처리
	       success: function(result) {
				purchaseRegist(result);
	       },
	       error: function() {
	           alert("주문을 실패하였습니다.");
	       }
	   });
}

function addCart(element) {
	
    var goodsNum = $(element).data('goodsNum');  // 상품 번호
    var cartQty = $('#cartQty').val();           // 수량
	
    $.ajax({
        type: "POST",
        url: "/item/addCart",  // 서버에서 처리할 URL
        contentType: "application/json",  // JSON 형식으로 데이터 전송
        data: JSON.stringify({  // 데이터를 JSON 형식으로 변환하여 전송
            "goodsNum": goodsNum,
            "cartQty": cartQty
        }),
        dataType: "html",  // 응답을 HTML 형식으로 처리
        success: function(result) {
            if(result != null){
               if (confirm("장바구니에 추가되었습니다. 장바구니로 이동하시겠습니까?")) {
				cartList();
				}
            } else {
                alert("장바구니 추가에 실패했습니다.");
            }
        },
        error: function() {
            alert("장바구니 추가에 실패했습니다.");
        }
    });
}


function cartList(){
	$.ajax({
		type:"get",
		url:"/item/cartList",
		dataType:"html",
		success:function(result){
			$("#content").html(result);
		},
		error:function(){
			alert("로그인 후 이용해주세요");
		}
	});
}

function ipgoUpdate(element) {
	var ipgoNum = $(element).data('ipgoNum');
	$.ajax({
		type: "get",
		url: "/ipgo/ipgoUpdate",
		data: { "ipgoNum": ipgoNum },
		dataType: "html",
		success: function(result) {
			$("#content").html(result);
		},
		error: function() {
			alert("서버 오류");
		}
	});
}
function ipgoUpdateSubmit(event) {
	event.preventDefault(); // 기본 폼 제출 방지
	var formData = $("#ipgoForm").serialize();

	$.ajax({
		type: "post",
		url: "/ipgo/ipgoUpdate",
		data: formData,
		dataType: "html",
		success: function(result) {
			$("#content").html(result);
		},
		error: function() {
			alert("서버 오류");
		}
	});
}
function ipgoSubmit(event) {
	event.preventDefault(); // 기본 폼 제출 방지
	var formData = $("#ipgoForm").serialize();

	$.ajax({
		type: "post",
		url: "/ipgo/ipgoRegist",
		data: formData,
		dataType: "html",
		success: function(result) {
			$("#content").html(result);
		},
		error: function() {
			alert("서버 오류");
		}
	});
}

function ipgoRegist() {
	$.ajax({
		type: "get",
		url: "/ipgo/ipgoRegist",
		dataType: "html",
		success: function(result) {
			$("#content").html(result);
		},
		error: function() {
			alert("서버 오류");
		}
	});
}

function goodsIpgoList() {
	$.ajax({
		type: "get",
		url: "/ipgo/ipgoList",
		dataType: "html",
		success: function(result) {
			$("#content").html(result);
		},
		error: function() {
			alert("서버 오류");
		}
	});
}


function addWish(element) {
	var goodsNum = $(element).data('goodsNum');
	$.ajax({
		type: "get",
		url: "/wish",
		data: { "goodsNum": goodsNum },
		dataType: "json",
		success: function(result) {
			if (result == 1) {
				alert("찜하기");

			} else if (result == 0) {
				alert("찜하기 취소");
			}

		},
		error: function() {
			alert("서버 오류");
		}
	});
}


function searchGoods(element) {
	const searchWord = $("#searchWord").val(); // 입력된 검색어 가져오기
	var category = null;
	if (element instanceof HTMLElement) category = $(element).data('category');
	$.ajax({
		type: "POST",
		url: "/",
		data: { "searchWord": searchWord, "category": category }, // 데이터 객체로 전송
		dataType: "html",
		success: function(result) {
			$("#searchResult").html(result);
		},
		error: function() {
			alert("검색에 실패했습니다. 다시 시도해주세요.");
		}
	});
}


function fileDel(btn, orgFile, storeFile, kind) {

	$.ajax({
		type: "post",
		url: "/file/fileDel",
		data: { "orgFile": orgFile, "storeFile": storeFile },
		dataType: "text",
		success: function(result) {
			if (result == 1) {
				$(btn).html("⤾");
				if (kind == 'main') {
					$("#main").css("display", "none");
					$("#mainFile").html("<input type='file' name='goodsMainImage'/>")
				}
				
			} else {
				$(btn).html("<span class='w3-bar-item w3-button w3-white w3-xlarge w3-right'>×</span>");
				if (kind == 'main') {
					$("#main").css("display", "");
					$("#mainFile").css("display", "none");
				}
			}

		},
		error: function() {
			alert("서버오류");
		}
	});
}


function fileChk() {
	let fileInput = $("input[name = 'goodsMainImage']");
	if (fileInput.length > 0 && !fileInput[0].files.length) {
		alert("파일 선택해주세요");
		return false;
	}
}
function goodsUpdateSubmit(element) {
	var goodsNum = $(element).data('goodsNum');
	$.ajax({
		type: "post",
		url: "/goods/goodsUpdate",
		dataType: "html",
		success: function(result) {
			alert(result);
		},
		error: function() {
			alert("서버오류");
		}
	});
}

function goodsDelete(element) {
	if (confirm("정말 삭제하시겠습니까?")) {
		var goodsNum = $(element).data('goodsNum');
		$.ajax({
			type: "get",
			url: "/goods/goodsDelete",
			data: { "goodsNum": goodsNum },
			dataType: "html",
			success: function(result) {


			},
			error: function() {
				alert("서버오류");
			},
			complete : goodsList
		});
	}else{
		goodsList();
	}
}
function goodsUpdate(element) {
	var goodsNum = $(element).data('goodsNum');
	$.ajax({
		type: "get",
		url: "/goods/goodsUpdate",
		data: { "goodsNum": goodsNum },
		dataType: "html",
		success: function(result) {
			$("#content").html(result);
		},
		error: function() {
			alert("서버오류");
		}
	});
}
function goodsDetail(element) {
	var goodsNum = null;

	goodsNum = $(element).data('goodsNum');

	$.ajax({
		type: "get",
		url: "/goods/goodsDetail",
		data: { "goodsNum": goodsNum },
		dataType: "html",
		success: function(result) {
			$("#content").html(result);
		},
		error: function() {
			alert("서버오류");
		}
	});
}
function goodsRegist(input) {
	var category = null;

	let categoryValue = null;

	// 입력값 처리
	if (typeof input === "string") {
		// input이 문자열일 경우
		categoryValue = input;
	} else if (input instanceof HTMLElement) {
		// input이 element일 경우
		categoryValue = $(input).data('category');
	}

	if (!categoryValue) {
		alert("유효하지 않은 카테고리 값입니다.");
		return;
	}

	// 카테고리 매핑
	if (categoryValue === "KPOP") {
		category = "KP_";
	} else if (categoryValue === "POP/ROCK") {
		category = "PR_";
	} else if (categoryValue === "CLASSIC") {
		category = "CL_";
	} else {
		category = "LP_";
	}
	$.ajax({
		type: "get",
		url: "/goods/goodsRegist",
		data: { "category": category },
		dataType: "html",
		success: function(result) {
			$("#content").html(result);
		},
		error: function() {
			alert("서버 오류");
		}
	});
}
function goodsList(element) {
	// 모든 a 태그에서 'w3-light-grey' 클래스와 아이콘을 제거
	$('#demoAcc a').removeClass('w3-light-grey').find('i').remove();

	// 클릭한 a 태그에 'w3-light-grey' 클래스를 추가하고 아이콘을 앞에 추가
	$(element).addClass('w3-light-grey');
	$(element).prepend('<i class="fa fa-caret-right w3-margin-right"></i>');

	// 클릭한 항목의 카테고리를 콘솔에 출력 (optional)
	var category = $(element).data('category');
	console.log(category);  // 예시로 콘솔에 출력
	$.ajax({
		type: "get",
		url: "/goods/goodsList",
		data: { "category": category },
		dataType: "html",
		success: function(result) {

			$("#content").html(result);
		},
		error: function() {
			alert("상품이 존재하지 않습니다.");
		}


	});
}


function memberList() {
	$("#memberList").click(function() {
		memberList1();
	});
}
function memberList1() {
	$.ajax({
		type: "get",
		url: "/member/memberList",
		dataType: "html",
		success: function(result) {

			$("#content").html(result);
		},
		error: function() {
			alert("서버 오류");
		}


	});
}
function myInfo(element) {
	const memberNum = $(element).data('memberNum');
	if (memberNum == null) {
		return; // memberNum이 없으면 함수 종료
	}

	// 여기서부터는 memberNum이 있을 때만 실행
	$.ajax({
		type: "get",
		url: "/member/memberDetail",
		data: { "memberNum": memberNum },
		dataType: "html",
		success: function(result) {
			$("#content").html(result); // 결과를 HTML에 삽입
		},
		error: function() {
			alert("서버 오류 디테일"); // 오류 시 알림
		}
	});
}
function empMyInfo(element) {
	const empNum = element.getAttribute("data-emp-num");
	if (empNum == null) {
		return; // empNum이 없으면 함수 종료
	}

	// 여기서부터는 empNum이 있을 때만 실행
	$.ajax({
		type: "get",
		url: "/emp/empDetail",
		data: { "empNum": empNum },
		dataType: "html",
		success: function(result) {
			$("#content").html(result); // 결과를 HTML에 삽입
		},
		error: function() {
			alert("서버 오류 디테일"); // 오류 시 알림
		}
	});
}
function memEmpInfo() {
	$(document).on("click", "#myInfo", function() {
		$.ajax({
			type: "get",
			url: "/user/userDetail",
			dataType: "html",
			success: function(result) {
				$("#content").html(result); // 결과를 HTML에 삽입
			},
			error: function() {
				alert("로그인을 다시 해주세요"); // 오류 시 알림
			}
		});
	});
}

document.addEventListener("DOMContentLoaded", function() {
	const urlParams = new URLSearchParams(window.location.search);

	// URL에서 memberNum 또는 empNum 추출
	const memberNum = urlParams.get("memberNum");
	const empNum = urlParams.get("empNum");
	const category = urlParams.get("category");

	if (memberNum) {
		const dummyElement = document.createElement("div"); // 가짜 DOM 요소 생성
		dummyElement.setAttribute("data-member-num", memberNum); // element에 memberNum 값 추가
		myInfo(dummyElement); // myInfo 함수 호출
	}

	if (empNum) {
		const dummyElement = document.createElement("div"); // 가짜 DOM 요소 생성
		dummyElement.setAttribute("data-emp-num", empNum); // element에 empNum 값 추가
		empMyInfo(dummyElement); // empMyInfo 함수 호출
	}

});



function deleteMember(memberNum) {
	if (confirm("정말 삭제하시겠습니까?")) {
		$.ajax({
			type: "POST",
			url: "/member/memberDelete", // 삭제 엔드포인트
			data: { memberNum: memberNum }, // 삭제할 멤버 ID 전달
			success: function(response) {
				if (response == "success") {
					
				} else {
					alert("삭제를 취소했습니다.");
				}
			},
			error: function() {
				alert("서버 오류가 발생했습니다. 삭제할 수 없습니다.");
			},
			complete: memberList1
		});
	}else{
		memberList1();
	}
}
function deleteEmp(empNum) {
	if (confirm("정말 삭제하시겠습니까?")) {
		$.ajax({
			type: "POST",
			url: "/emp/empDelete", // 삭제 엔드포인트
			data: { empNum: empNum }, // 삭제할 직원 ID 전달
			success: function(response) {
				if (response == "success") {
					
				} else {
					alert("삭제를 취소했습니다.");
				}
			},
			error: function() {
				alert("서버 오류가 발생했습니다. 삭제할 수 없습니다.");
			},
			complete: empList1
		});
	}else{
		empList1();
	}
}

function empList() {
	$("#empList").click(function() {
		empList1();
	});
}
function empList1() {
	$.ajax({
		type: "get",
		url: "/emp/empList",
		dataType: "html",
		success: function(result) {
			$("#content").html(result);
		},
		error: function() {
			alert("서버 오류");
		}


	});
}

function memberRegist() {
	location.href = "/member/memberRegist";
}



