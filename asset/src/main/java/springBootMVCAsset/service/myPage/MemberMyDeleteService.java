package springBootMVCAsset.service.myPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import springBootMVCAsset.domain.AuthInfoDTO;
import springBootMVCAsset.mapper.MyPageMapper;

@Service
public class MemberMyDeleteService {
	@Autowired
	MyPageMapper myPageMapper;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public void execute(String memberPw, HttpSession session) {
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		if(passwordEncoder.matches(memberPw, auth.getUserPw())) {
			myPageMapper.memberMyDelete(auth.getUserId());
		}
	}
}
