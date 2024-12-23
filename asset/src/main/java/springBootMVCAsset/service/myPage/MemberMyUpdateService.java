package springBootMVCAsset.service.myPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import springBootMVCAsset.command.MemberCommand;
import springBootMVCAsset.domain.MemberDTO;
import springBootMVCAsset.mapper.MyPageMapper;

@Service
public class MemberMyUpdateService {
	@Autowired
	MyPageMapper myPageMapper;
	
	public void execute(MemberCommand memberCommand,
			HttpSession session) {
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
		myPageMapper.memberMyUpdate(dto);
	}
}
