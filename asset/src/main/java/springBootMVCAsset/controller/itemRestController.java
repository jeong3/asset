package springBootMVCAsset.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import springBootMVCAsset.command.CartCommand;
import springBootMVCAsset.service.item.CartInsertService;

@RestController
@RequestMapping("item")
public class itemRestController {
	@Autowired
	CartInsertService cartInsertService;
	@RequestMapping("addCart")
	public String cartAdd(@RequestBody CartCommand cartCommand, HttpSession session) {
		System.out.println("dsds");
		String result = cartInsertService.execute(cartCommand, session);
		System.out.println(result+"결과");
		return result;
	}
}
