package springBootMVCAsset.service.goods;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import springBootMVCAsset.domain.GoodsDTO;
import springBootMVCAsset.domain.StartEndPageDTO;
import springBootMVCAsset.mapper.GoodsMapper;
import springBootMVCAsset.service.StartEndPageService;

@Service
public class GoodsListService {
	@Autowired
	GoodsMapper goodsMapper;
	@Autowired
	StartEndPageService startEndPageService;
	public void execute(String goodsKind, int page, String searchWord, Model model) {
	
		int limit = 4;
		StartEndPageDTO sepDTO = startEndPageService.execute(page, searchWord, limit);
		System.out.println("searchWord1 : " + sepDTO);
		//List<GoodsDTO> list = goodsMapper.allSelect(sepDTO, goodsKind);
		int startRow = sepDTO.getStartRow();
		int endRow = sepDTO.getEndRow();
		searchWord = sepDTO.getSearchWord();
		List<GoodsDTO> list = goodsMapper.allSelect(startRow, endRow, null, goodsKind);
		System.out.println("안녕1");
		
		
		
		Integer count = goodsMapper.goodsKindCount(goodsKind);
		startEndPageService.execute(page, limit, count, searchWord, list, model);
	}
}
