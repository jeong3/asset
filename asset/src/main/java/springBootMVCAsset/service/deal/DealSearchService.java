package springBootMVCAsset.service.deal;

import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import springBootMVCAsset.domain.SearchDTO;

@Service
public class DealSearchService {
	public SearchDTO execute(int page, int limit, 
			String categoryName, String categoryType, 
			String dealMethod, Date dealStartDate, 
			Date dealEndDate, String searchWord, String memberNum
			) {
		int startRow = ((page - 1) * limit) + 1; // 11
		int endRow = startRow + limit - 1 ; // 20
		SearchDTO searchDTO = new SearchDTO(startRow, endRow, categoryName, categoryType,dealMethod, dealStartDate, dealEndDate, searchWord, memberNum);
		return searchDTO;
	}
	
	public void execute(int page, int limit, int count, String searchWord, Model model) {
		int limitPage = 10;
		int startPageNum = (int)((double) page / limitPage + 0.95 -1) * limitPage + 1;
		int endPageNum = startPageNum + limitPage - 1;
		int maxPage = (int)((double)count / limit + 0.95); //2
		if(endPageNum > maxPage) {
			endPageNum = maxPage;
		}
		if(count == 0) {
			maxPage = 1;
			endPageNum = 1;
		}
		
		if(searchWord == null) searchWord = "";
		model.addAttribute("searchWord", searchWord);
		model.addAttribute("page", page);
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("endPageNum", endPageNum);
		//model.addAttribute("count", count);
		model.addAttribute("maxPage", maxPage);
	}
}
