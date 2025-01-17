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
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		String memberNum = auth.getUserNum();
	   
		iniPayReturnService.execute(request);
		 System.out.println("auth이니페이리턴서비스"+ auth);
		couponMapper.couponDelete(couponNum);
		String purchaseNum = (String) request.getAttribute("orderNumber");
		System.out.println("구매 번호 : " + purchaseNum);
		PaymentDTO dto = purchaseMapper.paymentSelectOne(purchaseNum);
		String totalprice = dto.getTotalprice();
		System.out.println("총 가격 : " + totalprice) ;
		model.addAttribute("purchaseNum", purchaseNum);
		model.addAttribute("totalprice", totalprice);
		
		session.setAttribute("auth", auth);
		
		int total = Integer.parseInt(totalprice);
		if(total > 50000) {
			couponMapper.addCoupon(memberNum);
		}
		
		System.out.println("auth바이피니쉬드페이지전"+ auth);
		return "thymeleaf/purchase/buyfinished";
	}
}
