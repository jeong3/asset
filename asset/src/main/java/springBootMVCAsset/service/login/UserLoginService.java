package springBootMVCAsset.service.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
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
	public void execute(LoginCommand loginCommand, BindingResult result, HttpSession session, HttpServletResponse response) {
		AuthInfoDTO auth = loginMapper.LoginSelectOne(loginCommand.getUserId());
		System.out.println(loginCommand+"커맨드");
		if(auth != null) {
			System.out.println("아이디가 존재합니다.");
			
			//본문(Command)의 비밀번호, 암호문(DB)의의 비밀번호
			if(passwordEncoder.matches(loginCommand.getUserPw(), auth.getUserPw())) { 
				System.out.println("비밀번호가 일치합니다.");
				session.setAttribute("auth", auth);
				
				if(loginCommand.isIdStore() == true) {
					System.out.println(loginCommand.isIdStore()+"아이디저장");
					Cookie IdStoreCookie = new Cookie("userId", auth.getUserId());
					IdStoreCookie.setMaxAge(60*60*24*30);
					IdStoreCookie.setPath("/");
					IdStoreCookie.setSecure(true);
					response.addCookie(IdStoreCookie);
					System.out.println(IdStoreCookie.getName()+"아이디저장");
				} else {
					Cookie IdStoreCookie = new Cookie("userId", "");
					IdStoreCookie.setMaxAge(0);
					IdStoreCookie.setPath("/");
					response.addCookie(IdStoreCookie);
				}
			
				if(loginCommand.isAutoLogin() == true) {
					System.out.println(loginCommand.isAutoLogin()+"자동로그인");
					Cookie autoCookie = new Cookie("autoLogin", auth.getUserId());
					autoCookie.setMaxAge(60*60*24*30);
					autoCookie.setPath("/");
					autoCookie.setSecure(true);
					response.addCookie(autoCookie);
					System.out.println(autoCookie+"자동로그인");
				} 
				
				
				
			} else {
				if(loginCommand.getUserPw() != null) {
				result.rejectValue("userPw", "loginCommand.userPw", "비밀번호가 일치하지않습니다.");
				System.out.println("비밀번호가 일치하지않습니다.");
				}
			}
		} else {
			if(loginCommand.getUserId() != null) {
			result.rejectValue("userId", "loginCommand.userId", "이메일인증이 안됐거나 아이디가 존재하지 않습니다.");
			System.out.println("아이디가 존재하지 않습니다.");
			}
		}
		
		
		
	}
}
