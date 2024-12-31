package springBootMVCAsset.service.budget;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import springBootMVCAsset.command.DealCommand;
import springBootMVCAsset.domain.AuthInfoDTO;
import springBootMVCAsset.domain.BudgetDTO;
import springBootMVCAsset.mapper.BudgetMapper;

@Service
public class BudgetRegistService {
	@Autowired
	BudgetMapper budgetMapper;
	public void execute(DealCommand dealCommand, HttpSession session
			, String categoryName, String autoNum) {
		BudgetDTO dto = new BudgetDTO();
		dto.setBudgetNum(autoNum);
		dto.setBudgetPrice(dealCommand.getDealPrice());
		dto.setCategoryName(categoryName);
		dto.setDealMethod(dealCommand.getDealMethod());
		
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		String memberNum = auth.getUserNum();
		dto.setMemberNum(memberNum);
		budgetMapper.budgetInsert(dto);
	}
}
