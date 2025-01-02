package springBootMVCAsset.service.budget;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpSession;
import springBootMVCAsset.domain.AuthInfoDTO;
import springBootMVCAsset.domain.BudgetDTO;
import springBootMVCAsset.mapper.BudgetMapper;

@Service
public class BudgetDetailService {
	@Autowired
	BudgetMapper budgetMapper;
	public void execute(Model model, HttpSession session) {
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		String memberNum = auth.getUserNum();
		BudgetDTO dto = budgetMapper.budgetDetail(memberNum);
		model.addAttribute("dto", dto);
	}
}
