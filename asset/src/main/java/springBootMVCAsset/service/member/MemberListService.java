package springBootMVCAsset.service.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import springBootMVCAsset.domain.MemberDTO;
import springBootMVCAsset.mapper.MemberMapper;

@Service
public class MemberListService {
	@Autowired
	MemberMapper memberMapper;
	public void execute(Model model) {
		List <MemberDTO> list = memberMapper.memberList();
		model.addAttribute("list", list);
	}
}
