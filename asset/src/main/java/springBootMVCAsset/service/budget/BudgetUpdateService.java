package springBootMVCAsset.service.budget;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import springBootMVCAsset.command.DealCommand;
import springBootMVCAsset.domain.AuthInfoDTO;
import springBootMVCAsset.mapper.BudgetMapper;

@Service
public class BudgetUpdateService {
	@Autowired
	BudgetMapper budgetMapper;
	public void execute(HttpSession session, DealCommand dealCommand) {
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		String memberNum = auth.getUserNum();
		budgetMapper.budgetUpdate(memberNum);
	}
}
