package springBootMVCAsset.service.find;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import springBootMVCAsset.command.PwChangeCommand;
import springBootMVCAsset.mapper.FindMapper;

@Service
public class PwChangeService {
	@Autowired
	FindMapper findMapper;
	@Autowired
	PasswordEncoder passwordEncoder;
	public Integer execute(PwChangeCommand pwChangeCommand) {
	    if(pwChangeCommand.getNewPw().equals(pwChangeCommand.getNewPwCon())) {
	        if(pwChangeCommand.getGrade().equals("mem")) {
	            String pw = passwordEncoder.encode(pwChangeCommand.getNewPw());
	            findMapper.pwMemberChange(pw, pwChangeCommand.getUserNum());
	        } else if(pwChangeCommand.getGrade().equals("emp")) {
	            String pw = passwordEncoder.encode(pwChangeCommand.getNewPw());
	            findMapper.pwEmployeeChange(pw, pwChangeCommand.getUserNum());
	        }
	        return 1;
	    } else {
	        return 0;
	    }
	}

}
