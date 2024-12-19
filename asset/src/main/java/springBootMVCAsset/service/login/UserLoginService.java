package springBootMVCAsset.service.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import jakarta.servlet.http.HttpSession;
import springBootMVCAsset.command.LoginCommand;
import springBootMVCAsset.domain.AuthInfoDTO;
import springBootMVCAsset.mapper.LoginMapper;

@Service
public class UserLoginService {
	@Autowired
	LoginMapper loginMapper;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public void execute(LoginCommand loginCommand
			, BindingResult result
			, HttpSession session) {
		AuthInfoDTO auth = loginMapper.login(loginCommand.getUserId());
		if(auth != null) {
			if(passwordEncoder.matches(loginCommand.getUserPw(), auth.getUserPw())) {
				session.setAttribute("auth", auth);
			}else {
				result.rejectValue("userPw", "loginCommand.userPw"
						, "비밀번호가 틀렸습니다.");
			}
		}else {
			result.rejectValue("userId", "loginCommand.userId"
					, "아이디가 존재하지 않습니다.");
		}
	}
}
