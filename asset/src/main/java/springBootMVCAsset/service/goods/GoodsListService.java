package springBootMVCAsset.service.goods;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import springBootMVCAsset.domain.GoodsDTO;
import springBootMVCAsset.mapper.GoodsMapper;

@Service
public class GoodsListService {
	@Autowired
	GoodsMapper goodsMapper;
	public void execute(String goodsKind, Model model) {
		List<GoodsDTO> list = goodsMapper.goodsAllSelect(goodsKind);
		model.addAttribute("list", list);
	}
}
