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
public class BankDealListService {
	@Autowired
	DealMapper dealMapper;
	@Autowired
	AssetListService assetListService;
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
	public void execute2(Integer page, AssetListDTO assetListDTO, HttpSession session, Model model) {
		Integer limit = 5;
		
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		String memberNum = auth.getUserNum();
		String searchWord = assetListDTO.getSearchWord();
		AssetListDTO dto = assetListService.execute(page, limit, searchWord, memberNum);
		List <DealDTO> list = dealMapper.bankList(dto);
		
		String dealMethod = "checkCard";
		Integer count = dealMapper.dealCount(memberNum, dealMethod);
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
