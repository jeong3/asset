package springBootMVCAsset.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import springBootMVCAsset.command.DealCommand;
import springBootMVCAsset.service.AutoNumService;

@Controller
@RequestMapping("myAsset")
public class MyAssetPageController {
	@Autowired
	AutoNumService autoNumService;
	
	@GetMapping("myAssetPage")
	public String myAssetPage() {
		return "thymeleaf/myAsset/myAssetPage";
	}
	
	@GetMapping("dealChoose")
	public String dealChoose() {
		return "thymeleaf/myAsset/dealChoose";
	}
	
	@GetMapping("dealRegist")
	public String dealRegist(@RequestParam(value = "categoryName", required = false) String categoryName, Model model) {
		System.out.println("categoryName:!!!!!!!!!!!!!!"+ categoryName);
		String autoNum = autoNumService.execute("deal_", "deal_num", 6, "deal");
		DealCommand dealCommand = new DealCommand();
		dealCommand.setDealNum(autoNum);
		dealCommand.setCategoryName(categoryName);
		model.addAttribute("dealCommand", dealCommand);
		return "thymeleaf/myAsset/dealRegist";
	}
	
	@PostMapping("dealRegist")
	public String dealRegist(@Validated DealCommand dealCommand, BindingResult result) {
		if(result.hasErrors()) {
			return "thymeleaf/myAsset/dealRegist";
		}
		return "redirect:myAssetPage";
	}
	
}
