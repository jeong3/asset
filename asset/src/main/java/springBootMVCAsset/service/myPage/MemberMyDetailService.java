package springBootMVCAsset.service.myPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpSession;
import springBootMVCAsset.domain.AuthInfoDTO;
import springBootMVCAsset.domain.MemberDTO;
import springBootMVCAsset.mapper.MyPageMapper;

@Service
public class MemberMyDetailService {
	@Autowired
	MyPageMapper myPageMapper;
	public void execute(HttpSession session, Model model) {
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		String memberId = auth.getUserId();
		MemberDTO dto = myPageMapper.memberMyDetail(memberId);
		model.addAttribute("memberCommand", dto);
	}
}
