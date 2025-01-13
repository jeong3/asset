package springBootMVCAsset.service.eval;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import springBootMVCAsset.domain.EmpSalaryDTO;
import springBootMVCAsset.mapper.EvalMapper;

@Service
public class SalarySelectService {
	@Autowired
	EvalMapper evalMapper;
	public void execute(String empNum, Model model) {
		EmpSalaryDTO dto = evalMapper.salarySelectOne(empNum);
		model.addAttribute("dto", dto);
		
	}

}
