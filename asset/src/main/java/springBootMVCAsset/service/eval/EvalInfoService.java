package springBootMVCAsset.service.eval;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import springBootMVCAsset.domain.EvalEmployeeDTO;
import springBootMVCAsset.mapper.EvalMapper;

@Service
public class EvalInfoService {
	@Autowired
	EvalMapper evalMapper;
	public void execute(String empNum, Model model) {
		EvalEmployeeDTO dto = evalMapper.EvalSelectOne(empNum);
		System.out.println("디티오"+dto);
		model.addAttribute("dto", dto);
		
	}

}
