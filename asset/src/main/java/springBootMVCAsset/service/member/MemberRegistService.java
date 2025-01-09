package springBootMVCAsset.service.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import springBootMVCAsset.command.MemberCommand;
import springBootMVCAsset.domain.MemberDTO;
import springBootMVCAsset.mapper.CouponMapper;
import springBootMVCAsset.mapper.MemberMapper;

@Service
public class MemberRegistService {
	@Autowired
	MemberMapper memberMapper;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	CouponMapper couponMapper; 
	public void execute(MemberCommand memberCommand) {
		MemberDTO dto = new MemberDTO();
		dto.setMemberGender(memberCommand.getMemberGender());
		dto.setMemberAddr(memberCommand.getMemberAddr());
		dto.setMemberAddrDetail(memberCommand.getMemberAddrDetail());
		dto.setMemberBirth(memberCommand.getMemberBirth());
		dto.setMemberEmail(memberCommand.getMemberEmail());
		dto.setMemberId(memberCommand.getMemberId());
		dto.setMemberName(memberCommand.getMemberName());
		dto.setMemberNum(memberCommand.getMemberNum());
		dto.setMemberPhone(memberCommand.getMemberPhone());
		dto.setMemberPost(memberCommand.getMemberPost());
		
		String encodePw = passwordEncoder.encode(memberCommand.getMemberPw());
		dto.setMemberPw(encodePw);
		memberMapper.memberinsert(dto);
		// 회원가입시 쿠폰 지금
		couponMapper.couponInsert(memberCommand.getMemberNum());
	}
}
