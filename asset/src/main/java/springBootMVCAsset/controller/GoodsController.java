package springBootMVCAsset.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import springBootMVCAsset.command.GoodsCommand;
import springBootMVCAsset.service.goods.GoodsRegistService;

@Controller
//@RequestMapping("goods")
public class GoodsController {
	@Autowired
	GoodsRegistService goodsRegistService;
	@GetMapping("bookRegist")
	public String bookRegist() {
		return "thymeleaf/goods/goodsRegist";
	}
	@GetMapping("studyRegist")
	public String studyRegist() {
		return "thymeleaf/goods/goodsRegist";
	}
	@PostMapping("goodsRegist")
	public String goodsRegist(GoodsCommand goodsCommand) {
		goodsRegistService.execute(goodsCommand);
		return "redirect:goodsList";
	}
}
