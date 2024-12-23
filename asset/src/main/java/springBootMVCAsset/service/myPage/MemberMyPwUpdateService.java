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
	
	public Integer execute(String oldPw, String newPw, String newPwCon, HttpSession session) {
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		if(passwordEncoder.matches(oldPw, auth.getUserPw())) {
			System.out.println(newPw);
			System.out.println(newPwCon);
			if(newPw.equals(newPwCon) ) {
				String pw = passwordEncoder.encode(newPw);
				myPageMapper.memberMyPwUpdate(pw, auth.getUserId());
				return 1;
			}else {
				return 0;
			}
		}else {
			return 0;
		}
	}
}
