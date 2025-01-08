package springBootMVCAsset.service.goods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpSession;
import springBootMVCAsset.domain.GoodsDTO;
import springBootMVCAsset.mapper.GoodsMapper;

@Service
public class GoodsDetailService {
	@Autowired
	GoodsMapper goodsMapper;
	public void execute(String goodsNum, Model model, HttpSession session) {
		int endPrice = goodsMapper.selectEndPrice();
		double sale = 1.0;
		if(endPrice > 55000) {
			sale = 0.95;
		} else if(endPrice > 60000) {
			sale = 0.9;
		} else {
			sale = 1.0;
		}
		model.addAttribute("sale", sale);
		GoodsDTO dto = goodsMapper.goodsSelectOne(goodsNum);
		model.addAttribute("dto", dto);
	}
}
