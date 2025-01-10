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
	public void execute2(String categoryType, Integer page, AssetListDTO assetListDTO, HttpSession session, Model model) {
		Integer limit = 5;
		
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		String memberNum = auth.getUserNum();
		String searchWord = assetListDTO.getSearchWord();
		AssetListDTO dto = assetListService.execute(page, limit, searchWord, memberNum);
		List <DealDTO> list = dealMapper.saveList(categoryType, dto);
		Integer count = dealMapper.dealCount();
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
