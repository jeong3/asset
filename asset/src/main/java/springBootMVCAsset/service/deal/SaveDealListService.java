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
public class SaveDealListService {
	@Autowired
	DealMapper dealMapper;
	public void execute(HttpSession session, Model model) {
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		String memberNum = auth.getUserNum();
		//List <DealDTO> list = dealMapper.assetDealList(memberNum);
		//model.addAttribute("saveList", list);
		Integer totalSave = dealMapper.totalSavePrice(null, memberNum);
		Integer totalYegeumPrice = dealMapper.totalSavePrice("예금", memberNum);
		Integer totalJuckgeumPrice = dealMapper.totalSavePrice("적금", memberNum);
		Integer totalSaveGitaPrice = dealMapper.totalSavePrice("기타", memberNum);
		model.addAttribute("totalSave", totalSave);
		model.addAttribute("totalYegeumPrice", totalYegeumPrice);
		model.addAttribute("totalJuckgeumPrice", totalJuckgeumPrice);
		model.addAttribute("totalSaveGitaPrice", totalSaveGitaPrice);
	}
}
