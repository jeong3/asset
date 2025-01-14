package springBootMVCAsset.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import springBootMVCAsset.command.PurchaseCommand;
import springBootMVCAsset.mapper.PurchaseMapper;
import springBootMVCAsset.service.purchase.GoodsBuyService;
import springBootMVCAsset.service.purchase.GoodsOrderService;
import springBootMVCAsset.service.purchase.IniPayReqService;
import springBootMVCAsset.service.purchase.PurchaseListService;

@Controller
@RequestMapping("purchase")
public class PurchaseController {
	@Autowired
	GoodsBuyService goodsBuyService;
	@Autowired
	GoodsOrderService goodsOrderService;
	@Autowired
	IniPayReqService iniPayReqService;
	@Autowired
	PurchaseListService purchaseListService;
	@Autowired
	PurchaseMapper purchaseMapper;
	@RequestMapping("goodsBuy")
	public String goodsBuy(String nums[] , HttpSession session,Model model) {
		goodsBuyService.execute(nums, session, model);
		return "thymeleaf/purchase/goodsOrder";
	}
	@PostMapping("goodsOrder")
	public String goodsOrder(@RequestParam String couponNum, PurchaseCommand purchaseCommand, HttpSession session,
			Model model) {
		String purchaseNum = goodsOrderService.execute(purchaseCommand, session, model);
		System.out.println("쿠폰번호 : " + couponNum);	
		model.addAttribute("couponNum", couponNum);
		return "redirect:paymentOk?purchaseNum=" + purchaseNum + "&couponNum=" + couponNum;
	}
	@GetMapping("paymentOk")
	public String paymentOk(String purchaseNum, String couponNum, Model model) {
		try {
			iniPayReqService.execute(purchaseNum,couponNum, model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "thymeleaf/purchase/payment";
	}
	@GetMapping("orderList")
	public String orderList(HttpSession session, Model model) {
		purchaseListService.execute(session, model, null);
		return "thymeleaf/purchase/myPurchase";
	}
	@GetMapping("purchaseList")
	public String purchaseList(HttpSession session, Model model) {
		purchaseListService.execute(session, model, null);
		return "thymeleaf/purchase/purchaseList";
	}
	@GetMapping("purchaseOk")
	public String purchaseOk(String purchaseNum) {
		purchaseMapper.paymentStatusUpdate(purchaseNum);
		return "redirect:orderList";
	}
	@GetMapping("paymentDel")
	public String paymentDel(String purchaseNum) {
		purchaseMapper.paymentDel(purchaseNum);
		return "redirect:orderList";
	}
}
	