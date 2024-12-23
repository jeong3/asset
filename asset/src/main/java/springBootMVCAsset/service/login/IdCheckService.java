package springBootMVCAsset.service.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springBootMVCAsset.mapper.LoginMapper;

@Service
public class IdCheckService {
	@Autowired
	LoginMapper loginMapper;
	
	public Integer execute(String userId) {
		return loginMapper.userIdCheck(userId);
	}
}
