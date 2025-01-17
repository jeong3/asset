package springBootMVCAsset.service.find;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import springBootMVCAsset.mapper.FindMapper;
import springBootMVCAsset.mapper.MemberMapper;

@Service
public class FindPwService {
	@Autowired
	FindMapper findMapper;
	public void execute(String userId, String userPhone, Model model) {
		
	}
}
