package springBootMVCAsset.service.deal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpSession;
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
	StartEndPageService startEndPageService;
	// 현금 내역 3개까지만
	public void execute(String dealMethodValue, HttpSession session, Model model) {
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		String memberNum = auth.getUserNum();
		//List <DealDTO> list = dealMapper.assetDealList(dealMethodValue, memberNum).stream().limit(3).collect(Collectors.toList());
		List <DealDTO> list = dealMapper.assetDealList(dealMethodValue, memberNum);
		while (list.size() < 3) {
			list.add(new DealDTO());
		}
		if (list.size() > 3) {
			list = list.subList(0, 3);
	    }
		model.addAttribute("cashList", list);
	}
	
	// 현금 전체 내역
	public void execute2(String searchWord, Integer page, String dealMethodValue, HttpSession session, Model model) {
		Integer limit = 5;
		StartEndPageDTO sepDTO = startEndPageService.execute(page, searchWord, limit);
		
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		String memberNum = auth.getUserNum();
		
		//List <DealDTO> list = dealMapper.assetAllDealList(searchWord, page, dealMethodValue, memberNum, searchWord);
		
		
	}
}
