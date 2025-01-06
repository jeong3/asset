package springBootMVCAsset.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import springBootMVCAsset.command.CartCommand;
import springBootMVCAsset.service.item.CartInsertService;
import springBootMVCAsset.service.item.CartQtyDownService;
import springBootMVCAsset.service.item.GoodsCartDelsService;

@RestController
@RequestMapping("item")
public class itemRestController {
	@Autowired
	CartInsertService cartInsertService;
	@Autowired
	GoodsCartDelsService goodsCartDelsService;
	@Autowired
	CartQtyDownService cartQtyDownService;
	@PostMapping("addCart")
	public String cartAdd(@RequestBody CartCommand cartCommand, HttpSession session) {
		System.out.println("CartCommand GoodsNum: " + cartCommand.getGoodsNum());
		System.out.println("CartCommand CartQty: " + cartCommand.getCartQty());
		String result = cartInsertService.execute(cartCommand, session);
		System.out.println(result+"결과");
		return result;
	}
	@PostMapping("cartDels")
	public String cartDels(@RequestBody String goodsNums[],  HttpSession session ) {
		return goodsCartDelsService.execute(goodsNums, session);
	}
	@PostMapping("cartQtyDown")
	public void cartQtyDown(@RequestParam String goodsNum, HttpSession session )  {
		cartQtyDownService.execute(goodsNum, session);
	}
}
