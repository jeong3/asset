package springBootMVCAsset.service.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springBootMVCAsset.command.MemberCommand;
import springBootMVCAsset.domain.MemberDTO;
import springBootMVCAsset.mapper.MemberMapper;

@Service
public class MemberUpdateService {
	@Autowired
	MemberMapper memberMapper;
	public void execute(MemberCommand memberCommand) {
		MemberDTO dto = new MemberDTO();
		dto.setMemberAddr(memberCommand.getMemberAddr());
		dto.setMemberAddrDetail(memberCommand.getMemberAddrDetail());
		dto.setMemberBirth(memberCommand.getMemberBirth());
		dto.setMemberEmail(memberCommand.getMemberEmail());
		dto.setMemberGender(memberCommand.getMemberGender());
		dto.setMemberId(memberCommand.getMemberId());
		dto.setMemberName(memberCommand.getMemberName());
		dto.setMemberNum(memberCommand.getMemberNum());
		dto.setMemberPhone(memberCommand.getMemberPhone());
		dto.setMemberPost(memberCommand.getMemberPost());
		System.out.println(dto);
		memberMapper.memberUpdate(dto);
	}
}
