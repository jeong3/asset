package springBootMVCAsset.service.budget;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import springBootMVCAsset.command.DealCommand;
import springBootMVCAsset.command.MemberCommand;
import springBootMVCAsset.domain.AuthInfoDTO;
import springBootMVCAsset.domain.BudgetDTO;
import springBootMVCAsset.mapper.BudgetMapper;

@Service
public class BudgetRegistService {
	@Autowired
	BudgetMapper budgetMapper;
	public void execute(MemberCommand memberCommand, String autoNum) {
		BudgetDTO dto = new BudgetDTO();
		dto.setBudgetNum(autoNum);
		dto.setMemberNum(memberCommand.getMemberNum());
		budgetMapper.budgetRegist(dto);
	}
}
