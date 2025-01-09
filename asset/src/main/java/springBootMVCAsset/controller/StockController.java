package springBootMVCAsset.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import springBootMVCAsset.kafka.MyWebSocketClient;
import springBootMVCAsset.service.stock.StockListService;

@Controller
@RequestMapping("/stock")
public class StockController {
	

	@GetMapping("/stockList")
	public String stock (Model model) {
		
		return "thymeleaf/stock/stock";
	}
}
