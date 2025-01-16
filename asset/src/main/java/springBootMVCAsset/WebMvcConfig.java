package springBootMVCAsset;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
	@Autowired
	InteceptorConfig inteceptorConfig;
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//로그인 하지 않아도 되는 페이지 저장
		List<String> excludeList = new ArrayList<String>();
		excludeList.add("/news/**/*");
		excludeList.add("/login/**/*");
		excludeList.add("/goods/**/*");
		excludeList.add("/static/**/*");
		excludeList.add("/announce/**/*");
		excludeList.add("/inquire/**/*");
		excludeList.add("/volunteer/**/*");
		excludeList.add("/stock/**/*");
		excludeList.add("/kakao-login");
		excludeList.add("/naver-login");
		registry.addInterceptor(inteceptorConfig)
				.addPathPatterns("/**/*") // 모두 차단
				.excludePathPatterns(excludeList); // 허용할 주소
	}
}
