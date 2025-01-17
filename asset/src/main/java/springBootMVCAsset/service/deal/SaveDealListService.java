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
public class SaveDealListService {
	@Autowired
	DealMapper dealMapper;
	@Autowired
	AssetListService assetListService;
	public void execute(HttpSession session, Model model, String categoryType) {
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		String memberNum = auth.getUserNum();
		Integer totalSave = dealMapper.totalSavePrice(null, memberNum);
		Integer totalYegeumPrice = dealMapper.totalSavePrice("예금", memberNum);
		Integer totalJuckgeumPrice = dealMapper.totalSavePrice("적금", memberNum);
		Integer totalSaveGitaPrice = dealMapper.totalSavePrice("기타", memberNum);
		System.out.println("페이지이동");
		System.out.println(totalSave);
		System.out.println(totalYegeumPrice);
		System.out.println(totalJuckgeumPrice);
		System.out.println(totalSaveGitaPrice);
		model.addAttribute("totalSave", totalSave);
		model.addAttribute("totalYegeumPrice", totalYegeumPrice);
		model.addAttribute("totalJuckgeumPrice", totalJuckgeumPrice);
		model.addAttribute("totalSaveGitaPrice", totalSaveGitaPrice);
		if(categoryType == null) categoryType = "";
		model.addAttribute("categoryType", categoryType);
	}
	public void execute2(String categoryType, Integer page, AssetListDTO assetListDTO, HttpSession session, Model model) {
		Integer limit = 5;
		
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		String memberNum = auth.getUserNum();
		String searchWord = assetListDTO.getSearchWord();
		AssetListDTO dto = assetListService.execute(page, limit, searchWord, memberNum);
		List <DealDTO> list = dealMapper.saveList(categoryType, dto);
		
		String categoryName = "저축";
		Integer count = dealMapper.dealSaveCount(memberNum, categoryName, categoryType);
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
