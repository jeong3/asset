package springBootMVCAsset.controller;

import java.io.PrintWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import springBootMVCAsset.command.CartCommand;
import springBootMVCAsset.domain.AuthInfoDTO;
import springBootMVCAsset.service.item.CartInsertService;
import springBootMVCAsset.service.item.CartListService;
import springBootMVCAsset.service.item.GoodsCartDelsService;

@Controller
@RequestMapping("item")
public class ItemController {
	@Autowired
	CartListService cartListService;
	@Autowired
	GoodsCartDelsService goodsCartDelsService;
	@Autowired
	CartInsertService cartInsertService;
	@GetMapping("cartList")
	public String cartList(Model model, HttpSession session) {
		cartListService.execute(model, session);
		return "thymeleaf/item/cartList";
	}
	@GetMapping("cartDel")
	public String cartDel(String goodsNums[] , HttpSession session) {
		goodsCartDelsService.execute(goodsNums, session);
		return "redirect:cartList";
	}
	@GetMapping("buyItem")
	public String buyItem(CartCommand cartCommand, HttpSession session, 
	        HttpServletResponse response, @RequestParam(required = false) String fromPage, String goodsKind) {

	    // 세션에서 로그인 정보를 가져옴
	    AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");

	    // 로그인되지 않은 경우 알림 후 로그인 페이지로 리다이렉트
	    if (auth == null) {
	        String str = "<script>"
	                + "alert('로그인이 필요합니다.');"
	                + "location.href='/login/login';"  // 로그인 페이지로 리다이렉트
	                + "</script>";
	        try {
	            response.setContentType("text/html; charset=utf-8");
	            PrintWriter out = response.getWriter();
	            out.print(str);
	            out.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;  // 리다이렉트 처리 이미 script 안에서 처리됨
	    }

	    System.out.println("cartCommand: " + cartCommand);
	    String result = cartInsertService.execute(cartCommand, session);
	    response.setContentType("text/html; charset=utf-8");
	    PrintWriter out;

	    // 직원일 경우
	    if (result.equals("900")) {
	        String str = "<script>"
	                + "alert('관리자는 구매할 수 없습니다.');";

	        // fromPage가 null이면 상품 상세 페이지로, 아니면 상품 리스트 페이지로 리다이렉트
	        if ("list".equals(fromPage)) {
	            str += "location.href='/goods/goodsList/" + goodsKind + "';";
	        } else {
	            str += "location.href='/goods/goodsDetail?goodsNum=" + cartCommand.getGoodsNum() + "';";
	        }
	        str += "</script>";
	        try {
	            out = response.getWriter();
	            out.print(str);
	            out.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;  // 리다이렉트 처리 이미 script 안에서 처리됨
	    }

	    // 정상적인 구매일 경우
	    if (result.equals("000")) {
	        return "redirect:/";
	    }

	    return "redirect:/purchase/goodsBuy?nums=" + cartCommand.getGoodsNum();
	}


}
