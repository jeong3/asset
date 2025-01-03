package springBootMVCAsset.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import springBootMVCAsset.domain.GoodsStockDTO;
import springBootMVCAsset.domain.InquireDTO;
import springBootMVCAsset.mapper.GoodsStockMapper;
import springBootMVCAsset.mapper.InquireMapper;
import springBootMVCAsset.service.goods.GoodsDetailViewService;

@Controller
@RequestMapping("corner")
public class CornerController {
	@Autowired
	GoodsStockMapper goodsStockMapper;
	@Autowired
	InquireMapper inquireMapper;
	@Autowired
	GoodsDetailViewService goodsDetailViewService;
	@RequestMapping("/goodsDescript")
	@ResponseBody
	public GoodsStockDTO goodsDescript(@RequestBody Map<String, String> map,
	                                   HttpServletResponse response, HttpSession session) {
	    // 서비스에서 데이터 조회
	    GoodsStockDTO dto = goodsStockMapper.goodsStockSelectOne(map.get("goodsNum"));
	    return dto; // 자동으로 JSON 형식으로 변환되어 클라이언트로 반환됩니다.
	}
	@RequestMapping("inquireList")
	public String inquireList(@ModelAttribute("goodsNum") String goodsNum
			,Model model) {
		//model.addAttribute("goodsNum", goodsNum);
		List<InquireDTO> list = inquireMapper.inquireList(goodsNum, null);
		model.addAttribute("list", list);
		model.addAttribute("newLineChar", "\n");
		return "thymeleaf/inquire/inquireList";
	}
}