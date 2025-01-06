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
	public void execute(Integer page, Model model) {
		int limit = 4; 
		int startRow = ((page - 1) * limit) + 1; 
		int endRow = startRow + limit - 1; 
		List<GoodsDTO> list = goodsMapper.goodsLoadMoreSelect(1, endRow);
		Integer count = goodsMapper.goodsCount();
		int maxPage = (int)((double) count / limit + 0.95);
		model.addAttribute("maxPage", maxPage);
		model.addAttribute("list", list);
		
	}
}
