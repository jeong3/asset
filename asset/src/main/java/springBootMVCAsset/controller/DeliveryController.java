package springBootMVCAsset.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import springBootMVCAsset.mapper.DeliveryMapper;
import springBootMVCAsset.service.delivery.DeliveryInsertService;
import springBootMVCAsset.service.purchase.PurchaseListService;

@Controller
@RequestMapping("delivery")
public class DeliveryController {
	@Autowired
	DeliveryMapper deliveryMapper;
	@Autowired
	PurchaseListService purchaseListService;
	@Autowired
	DeliveryInsertService deliveryInsertService;
	@GetMapping("deliveryRegist")
	public String deliveryRegist(String purchaseNum
			,Model model, HttpSession session) {
		purchaseListService.execute(session, model, purchaseNum);
		model.addAttribute("newLineChar", "\n");
		return "thymeleaf/delivery/deliveryRegist"; 
	}
	@PostMapping("deliveryRegist")
	public String deliveryRegist(String purchaseNum
			,@RequestParam(value="deliveryNum" , required = false , defaultValue = "")String deliveryNum) {
		deliveryInsertService.execute(purchaseNum, deliveryNum);
		return "redirect:deliveryRegist?purchaseNum="+purchaseNum;
	}
	@GetMapping("deliveryStatus")
	public String deliveryStatus(String purchaseNum) {
		deliveryMapper.deliveryStatusUpdate(purchaseNum);
		return "redirect:/purchase/purchaseList";
	}
}
