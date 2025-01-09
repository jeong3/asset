package springBootMVCAsset.service.deal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpSession;
import springBootMVCAsset.domain.AuthInfoDTO;
import springBootMVCAsset.domain.DealDTO;
import springBootMVCAsset.mapper.DealMapper;

@Service
public class BankDealListService {
	@Autowired
	DealMapper dealMapper;
	public void execute(String dealMethodValue, HttpSession session, Model model) {
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		String memberNum = auth.getUserNum();
		List <DealDTO> list = dealMapper.assetDealList(dealMethodValue, memberNum);
		while (list.size() < 3) {
			list.add(new DealDTO());
		}
		if (list.size() > 3) {
			list = list.subList(0, 3);
	    }
		model.addAttribute("bankList", list);
	}
}
