package springBootMVCAsset.service.myPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import springBootMVCAsset.domain.AuthInfoDTO;
import springBootMVCAsset.mapper.MyPageMapper;

@Service
public class MemberMyPwUpdateService {
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	MyPageMapper myPageMapper;
	
	public void execute(String oldPw, String newPw, String newPWCon, HttpSession session) {
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
	}
}
