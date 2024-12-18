package springBootMVCAsset.command;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class MemberCommand {
	String memberNum;
	
	@NotEmpty(message = "아이디를 입력해주세요.")
	String memberId;
	
	@Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$",
			message = "영문자와 숫자 그리고 특수문자가 포함된 8글자 이상")
	String memberPw;
	
	@NotBlank(message = "비밀번호 확인을 입력해주세요")
	String memberPwCon;
	
	@NotBlank(message = "이름을 입력해주세요")
	String memberName;
	
	@NotBlank(message = "주소를 입력해주세요")
	String memberAddr;
	String memberAddrDetail;
	
	@NotBlank(message = "우편번호를 입력해주세요")
	String memberPost;
	
	Date memberRegist;
	String gender;
	
	@NotBlank(message = "연락처를 입력해주세요")
	@Size(min = 11, max = 23)
	String memberPhone;
	
	@NotBlank(message = "이메일을 입력해주세요")
	String memberEmail;
	
	@NotNull(message = "생년월일을 입력해주세요")
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	Date memberBirth;

	public String getMemberNum() {
		return memberNum;
	}

	public void setMemberNum(String memberNum) {
		this.memberNum = memberNum;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberPw() {
		return memberPw;
	}

	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}

	public String getMemberPwCon() {
		return memberPwCon;
	}

	public void setMemberPwCon(String memberPwCon) {
		this.memberPwCon = memberPwCon;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberAddr() {
		return memberAddr;
	}

	public void setMemberAddr(String memberAddr) {
		this.memberAddr = memberAddr;
	}

	public String getMemberAddrDetail() {
		return memberAddrDetail;
	}

	public void setMemberAddrDetail(String memberAddrDetail) {
		this.memberAddrDetail = memberAddrDetail;
	}

	public String getMemberPost() {
		return memberPost;
	}

	public void setMemberPost(String memberPost) {
		this.memberPost = memberPost;
	}

	public Date getMemberRegist() {
		return memberRegist;
	}

	public void setMemberRegist(Date memberRegist) {
		this.memberRegist = memberRegist;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMemberPhone() {
		return memberPhone;
	}

	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}

	public String getMemberEmail() {
		return memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	public Date getMemberBirth() {
		return memberBirth;
	}

	public void setMemberBirth(Date memberBirth) {
		this.memberBirth = memberBirth;
	}
	
	
}
