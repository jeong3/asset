package springBootMVCAsset.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import springBootMVCAsset.command.GoodsCommand;
import springBootMVCAsset.service.AutoNumService;
import springBootMVCAsset.service.goods.GoodsRegistService;

@Controller
@RequestMapping("goods")
public class GoodsController {
	@Autowired
	AutoNumService autoNumService;
	@Autowired
	GoodsRegistService goodsRegistService;
	@GetMapping("goodsRegist")
	public String bookRegist(Model model) {
		String autoNum = autoNumService.execute("goods_", "goods_num", 7, "goods");
		GoodsCommand  goodsCommand = new GoodsCommand();
		goodsCommand.setGoodsNum(autoNum);
		model.addAttribute("goodsCommand", goodsCommand);
		return "thymeleaf/goods/goodsRegist";
	}
	@PostMapping("goodsRegist")
	public String goodsRegist(GoodsCommand goodsCommand, HttpSession session) {
		goodsRegistService.execute(goodsCommand, session);
		return "redirect:goodsList";
	}
}
