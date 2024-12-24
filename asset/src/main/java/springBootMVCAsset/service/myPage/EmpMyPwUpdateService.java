package springBootMVCAsset.service.myPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import springBootMVCAsset.domain.AuthInfoDTO;
import springBootMVCAsset.mapper.MyPageMapper;

@Service
public class EmpMyPwUpdateService {
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	MyPageMapper myPageMapper;
	
	public Integer execute(String oldPw, String newPw, String newPwCon, HttpSession session) {
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		String empId = auth.getUserId();
		if(passwordEncoder.matches(oldPw, auth.getUserPw())) {
			System.out.println(newPw);
			System.out.println(newPwCon);
			if(newPw.equals(newPwCon) ) {
				String empPw = passwordEncoder.encode(newPw);
				myPageMapper.empMyPwUpdate(empPw, empId);
				return 1;
			}else {
				return 0;
			}
		}else {
			return 0;
		}
	}
}
