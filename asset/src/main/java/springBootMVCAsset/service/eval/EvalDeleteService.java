package springBootMVCAsset.service.eval;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springBootMVCAsset.mapper.EvalMapper;

@Service
public class EvalDeleteService {
	@Autowired
	EvalMapper evalMapper;
	public void execute(String empNum) {
		evalMapper.EvalDelete(empNum);
		
	}

}
