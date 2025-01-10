package springBootMVCAsset.service.deal;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import springBootMVCAsset.domain.AssetListDTO;

@Service
public class AssetListService {
	public AssetListDTO execute(int page, int limit, 
			String searchWord, String memberNum) {
		int startRow = ((page - 1) * limit) + 1; // 11
		int endRow = startRow + limit - 1 ; // 20
		AssetListDTO assetListDTO = new AssetListDTO(startRow, endRow, searchWord, memberNum);
		return assetListDTO;
	}
	public void execute(int page, int limit, int count, String searchWord, Model model) {
		int limitPage = 10;
		int startPageNum = (int)((double) page / limitPage + 0.95 -1) * limitPage + 1;
		int endPageNum = startPageNum + limitPage - 1;
		int maxPage = (int)((double)count / limit + 0.95); //2
		if(endPageNum > maxPage) {
			endPageNum = maxPage;
		}
		
		//if(searchWord == null) searchWord = "";
		//model.addAttribute("searchWord", searchWord);
		model.addAttribute("page", page);
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("endPageNum", endPageNum);
		//model.addAttribute("count", count);
		model.addAttribute("maxPage", maxPage);
	}
}
