package springBootMVCAsset.service.deal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpSession;
import springBootMVCAsset.domain.AssetListDTO;
import springBootMVCAsset.domain.AuthInfoDTO;
import springBootMVCAsset.domain.DealDTO;
import springBootMVCAsset.mapper.DealMapper;

@Service
public class JaeTechDealListService {
	@Autowired
	DealMapper dealMapper;
	@Autowired
	AssetListService assetListService;
	public void execute(HttpSession session, Model model, String categoryType) {
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
		model.addAttribute("categoryType", categoryType);
	}
	public void execute2(String categoryType, Integer page, AssetListDTO assetListDTO, HttpSession session, Model model) {
		Integer limit = 5;
		
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		String memberNum = auth.getUserNum();
		String searchWord = assetListDTO.getSearchWord();
		AssetListDTO dto = assetListService.execute(page, limit, searchWord, memberNum);
		List <DealDTO> list = dealMapper.jaetechList(categoryType, dto);
		
		String categoryName = "재태크";
		Integer count = dealMapper.dealjaetechCount(memberNum, categoryName, categoryType);

		assetListService.execute(page, limit, count, searchWord, model);
		model.addAttribute("list", list);
		
		if(searchWord == null) searchWord = "";
		dto.setSearchWord(searchWord);
		model.addAttribute("assetListDTO", dto);
		
		// 멤버 아이디
		String memberId = auth.getUserId();
		model.addAttribute("memberId", memberId);
	}
}
