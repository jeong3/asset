package springBootMVCAsset.service.goods;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import springBootMVCAsset.domain.GoodsDTO;
import springBootMVCAsset.mapper.GoodsMapper;

@Service
public class GoodsLoadMoreListService {
	@Autowired
	GoodsMapper goodsMapper;
	public void execute(String goodsKind, Integer page, String searchWord, Model model) {
		int limit = 4; 
		int startRow = ((page - 1) * limit) + 1; 
		int endRow = startRow + limit - 1; 
		List<GoodsDTO> list = goodsMapper.goodsLoadMoreSelect(1, endRow, searchWord, goodsKind);
		Integer count = goodsMapper.goodsKindSearchCount(searchWord, goodsKind);
		int maxPage = (int)((double) count / limit + 0.95);
		
			
		System.out.println("카운트"+count);
		System.out.println("리스트"+list);
		model.addAttribute("maxPage", maxPage);
		model.addAttribute("count", count);
		model.addAttribute("list", list);	
	}
}
