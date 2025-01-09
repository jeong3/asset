package springBootMVCAsset.service.deal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpSession;
import springBootMVCAsset.domain.AuthInfoDTO;
import springBootMVCAsset.mapper.DealMapper;

@Service
public class JaeTechDealListService {
	@Autowired
	DealMapper dealMapper;
	public void execute(HttpSession session, Model model) {
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		String memberNum = auth.getUserNum();
		Integer totalJaeTech = dealMapper.totalJaetechPrice(null, memberNum);
		Integer totalFundPrice = dealMapper.totalJaetechPrice("펀드", memberNum);
		Integer totalToojaPrice = dealMapper.totalJaetechPrice("투자", memberNum);
		Integer totalJusickPrice = dealMapper.totalJaetechPrice("주식", memberNum);
		Integer totalJaeTechGitaPrice = dealMapper.totalJaetechPrice("기타", memberNum);
		model.addAttribute("totalJaeTech", totalJaeTech);
		model.addAttribute("totalFundPrice", totalFundPrice);
		model.addAttribute("totalToojaPrice", totalToojaPrice);
		model.addAttribute("totalJusickPrice", totalJusickPrice);
		model.addAttribute("totalJaeTechGitaPrice", totalJaeTechGitaPrice);
	}
}
