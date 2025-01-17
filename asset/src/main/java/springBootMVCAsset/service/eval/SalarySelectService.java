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
		int sum = dto.getWorkPerformanceAbility() + dto.getWorkAttitude() + dto.getAttendStatus();
		int allowances = 0;
		
		if(sum > 20 ) {
			allowances = 500000;
		} else if(sum > 15) {
			allowances = 250000;
		} else {
			allowances = 70000;
		}
		int netSalary = dto.getSalary() + allowances + dto.getAttendCount() * 10000;
		 model.addAttribute("netSalary", netSalary);
		 
		model.addAttribute("allowances", allowances);
		
	}

}
