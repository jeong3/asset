package springBootMVCAsset.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import springBootMVCAsset.domain.AuthInfoDTO;
import springBootMVCAsset.domain.PaymentDTO;
import springBootMVCAsset.mapper.CouponMapper;
import springBootMVCAsset.mapper.MemberMapper;
import springBootMVCAsset.mapper.PurchaseMapper;
import springBootMVCAsset.service.purchase.INIstdpayPcReturn;

@Controller
@RequestMapping("payment")
public class PaymentController {
	@Autowired
	MemberMapper memberMapper;
	@Autowired
	PurchaseMapper purchaseMapper;
	@Autowired
	INIstdpayPcReturn iniPayReturnService;
	@Autowired
	CouponMapper couponMapper;
	@RequestMapping("INIstdpay_pc_return")
	public String payReturn(HttpServletRequest request, Model model, @RequestParam(required = false) String couponNum, HttpSession session) {
		
	   
		iniPayReturnService.execute(request);
		 
		couponMapper.couponDelete(couponNum);
		String purchaseNum = (String) request.getAttribute("orderNumber");
		System.out.println("구매 번호 : " + purchaseNum);
		PaymentDTO dto = purchaseMapper.paymentSelectOne(purchaseNum);
		String totalprice = dto.getTotalprice();
		System.out.println("총 가격 : " + totalprice) ;
		model.addAttribute("purchaseNum", purchaseNum);
		model.addAttribute("totalprice", totalprice);
		
		String memberNum =memberMapper.memberNumSelectToPurchase(purchaseNum);
		
		AuthInfoDTO auth = memberMapper.memberSelect(memberNum);
		session.setAttribute("auth", auth);
		int total = Integer.parseInt(totalprice);
		if(total > 50000) {
			couponMapper.addCoupon(memberNum);
		}
		return "thymeleaf/purchase/buyfinished";
	}
}
