package springBootMVCAsset.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import springBootMVCAsset.command.GoodsCommand;
import springBootMVCAsset.service.AutoNumService;
import springBootMVCAsset.service.goods.CouponListService;
import springBootMVCAsset.service.goods.GoodsDeleteService;
import springBootMVCAsset.service.goods.GoodsDetailService;
import springBootMVCAsset.service.goods.GoodsDetailViewService;
import springBootMVCAsset.service.goods.GoodsListService;
import springBootMVCAsset.service.goods.GoodsLoadMoreListService;
import springBootMVCAsset.service.goods.GoodsRegistService;
import springBootMVCAsset.service.goods.GoodsUpdateService;

@Controller
@RequestMapping("goods")
public class GoodsController {
	@Autowired
	CouponListService couponListService;
	@Autowired
	AutoNumService autoNumService;
	@Autowired
	GoodsRegistService goodsRegistService;
	@Autowired
	GoodsListService goodsListService;
	@Autowired
	GoodsDetailService goodsDetailService;
	@Autowired
	GoodsUpdateService goodsUpdateService;
	@Autowired
	GoodsDeleteService goodsDeleteService;
	@Autowired
	GoodsLoadMoreListService goodsLoadMoreListService;
	@GetMapping("goodsRegist")
	public String bookRegist(Model model) {
		String autoNum = autoNumService.execute("goods_", "goods_num", 7, "goods");
		GoodsCommand  goodsCommand = new GoodsCommand();
		goodsCommand.setGoodsNum(autoNum);
		model.addAttribute("goodsCommand", goodsCommand);
		return "thymeleaf/goods/goodsRegist";
	}
	@RequestMapping("loadMoreGoodsList")
	public String loadMoreGoodsList(String goodsKind, int page, String searchWord, Model model) {
		goodsLoadMoreListService.execute(goodsKind, page, searchWord, model);
		System.out.println("검색 : " + searchWord);
		return "thymeleaf/goods/goodsLoadMoreList";
	}
	@PostMapping("goodsRegist")
	public String goodsRegist(GoodsCommand goodsCommand, HttpSession session) {
		goodsRegistService.execute(goodsCommand, session);
		return "redirect:/";
	}
	@GetMapping("goodsList/{goodsKind}")
	public String goodsList(@PathVariable String goodsKind, @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
			@RequestParam(value = "searchWord", required = false) String searchWord, Model model) {
	    System.out.println("Received goodsKind: " + goodsKind);  // 디버깅용
	    model.addAttribute("goodsKind", goodsKind);
	    System.out.println("searchWord1 : " + searchWord);
	    goodsListService.execute(goodsKind, page, searchWord, model);
	    return "thymeleaf/goods/goodsList";
	}
	@GetMapping("study")
	public String index(@RequestParam(value = "goodsKind", required = false, defaultValue = "강의") String goodsKind,
	                    @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
	                    @RequestParam(value = "searchWord", required = false) String searchWord,
	                    Model model) {
	    model.addAttribute("goodsKind", goodsKind);
	    goodsListService.execute(goodsKind, page, searchWord, model);
	    model.addAttribute("bookList", model.getAttribute("list"));
	    return "thymeleaf/goods/studyList";
	}
	
	/*
	@GetMapping("goodsDetail")
	public String goodsDetail(@RequestParam("goodsNum") String goodsNum
			,Model model, HttpSession session) {
		session.removeAttribute("fileList");
		goodsDetailService.execute(goodsNum, model, session);
		return "thymeleaf/goods/goodsDetail";
	}
	*/
	@GetMapping("goodsUpdate") 
	public String goodsUpdate(@RequestParam("goodsNum") String goodsNum
			,Model model, HttpSession session) {
		goodsDetailService.execute(goodsNum, model, session);
		return "thymeleaf/goods/goodsUpdate";
	}
	@PostMapping("goodsUpdate")
	public String goodsUpdate(GoodsCommand goodsCommand, HttpSession session, Model model) {
		goodsUpdateService.execute(goodsCommand, session, model);
		return "redirect:goodsDetail?goodsNum=" + goodsCommand.getGoodsNum();
	}
	@RequestMapping("goodsDelete")
	public String goodsDel(@RequestParam ("goodsNum") String goodsNum) {
		goodsDeleteService.execute(goodsNum);
		return "redirect:/"; //PathVariable인 경우에는 주소 앞에 .. 을 꼭해줘야 합니다.
	}
	@Autowired
	GoodsDetailViewService goodsDetailViewService;
	@GetMapping("goodsDetail")
	public String goodsInfo(
			@RequestParam("goodsNum") String goodsNum,Model model
			, HttpServletResponse response, HttpSession session) {	
		goodsDetailViewService.execute(goodsNum, model, response, session);
		return "thymeleaf/goods/goodsDetail";
	}
	@GetMapping("couponList")
	public String couponList(HttpSession session, Model model) {
		couponListService.execute(session, model);
		return "thymeleaf/goods/couponList";
	}
}
