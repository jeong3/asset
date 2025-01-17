package springBootMVCAsset.service.deal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpSession;
import springBootMVCAsset.domain.AssetListDTO;
import springBootMVCAsset.domain.AuthInfoDTO;
import springBootMVCAsset.domain.DealDTO;
import springBootMVCAsset.domain.StartEndPageDTO;
import springBootMVCAsset.mapper.DealMapper;
import springBootMVCAsset.service.StartEndPageService;

@Service
public class CashDealListService {
	@Autowired
	DealMapper dealMapper;
	@Autowired
	AssetListService assetListService;
	// 현금 내역 3개까지만
	public void execute(String dealMethodValue, HttpSession session, Model model) {
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		String memberNum = auth.getUserNum();
		//List <DealDTO> list = dealMapper.assetDealList(dealMethodValue, memberNum).stream().limit(3).collect(Collectors.toList());
		List <DealDTO> list = dealMapper.assetDealList(dealMethodValue, memberNum);
		while (list.size() < 4) {
			list.add(new DealDTO());
		}
		if (list.size() > 4) {
			list = list.subList(0, 3);
	    }
		model.addAttribute("cashList", list);
	}
	
	// 현금 전체 내역
	public void execute2(Integer page, AssetListDTO assetListDTO, HttpSession session, Model model) {
		Integer limit = 5;
		
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		String memberNum = auth.getUserNum();
		String searchWord = assetListDTO.getSearchWord();
		AssetListDTO dto = assetListService.execute(page, limit, searchWord, memberNum);
		List <DealDTO> list = dealMapper.cashList(dto);
		
		String dealMethod = "cash";
		Integer count = dealMapper.dealCount(memberNum);
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
