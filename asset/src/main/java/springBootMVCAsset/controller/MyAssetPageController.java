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
import springBootMVCAsset.domain.SearchDTO;
import springBootMVCAsset.service.AutoNumService;
import springBootMVCAsset.service.budget.BudgetDetailService;
import springBootMVCAsset.service.budget.BudgetUpdateService;
import springBootMVCAsset.service.deal.DealDeleteService;
import springBootMVCAsset.service.deal.DealDetailService;
import springBootMVCAsset.service.deal.DealListService;
import springBootMVCAsset.service.deal.DealRegistService;
import springBootMVCAsset.service.deal.DealUpdateService;
import springBootMVCAsset.service.deal.DealsDeleteService;

@Controller
@RequestMapping("myAsset")
public class MyAssetPageController {
	@Autowired
	AutoNumService autoNumService;
	@Autowired
	DealRegistService dealRegistService;
	@Autowired
	BudgetUpdateService budgetUpdateService;
	@Autowired
	BudgetDetailService budgetDetailService;
	@Autowired
	DealListService dealListService;
	@Autowired
	DealDetailService dealDetailService;
	@Autowired
	DealUpdateService dealUpdateService;
	@Autowired
	DealsDeleteService dealsDeleteService;
	@Autowired
	DealDeleteService dealDeleteService;
	
	@GetMapping("myAssetPage")
	public String myAssetPage(Model model, HttpSession session) {
		budgetDetailService.execute(model, session);
		return "thymeleaf/myAsset/myAssetPage";
	}
	
	@GetMapping("dealChoose")
	public String dealChoose() {
		return "thymeleaf/myAsset/dealChoose";
	}
	
	@GetMapping("dealRegist")
	public String dealRegist(@RequestParam(value = "categoryName", required = false) String categoryName, Model model) {
		String autoNum = autoNumService.execute("deal_", "deal_num", 6, "deal");
		DealCommand dealCommand = new DealCommand();
		dealCommand.setDealNum(autoNum);
		dealCommand.setCategoryName(categoryName);
		model.addAttribute("dealCommand", dealCommand);
		return "thymeleaf/myAsset/dealRegist";
	}
	
	@PostMapping("dealRegist")
	public String dealRegist(@Validated DealCommand dealCommand, BindingResult result
			, HttpSession session, @RequestParam(value = "categoryName", required = false) String categoryName) {
		if(result.hasErrors()) {
			return "thymeleaf/myAsset/dealRegist";
		}
		dealRegistService.execute(dealCommand, session, categoryName);
		budgetUpdateService.execute(session);
		return "redirect:myAssetPage";
	}
	
	@GetMapping("dealList")
	public String dealList(@RequestParam(value="page", required = false, defaultValue="1") Integer page,
			SearchDTO searchDTO, Model model, HttpSession session) {
		dealListService.execute(page, searchDTO, model, session);
		return "thymeleaf/myAsset/dealList";
	}
	
	@GetMapping("dealDetail")
	public String dealDetail(@RequestParam("dealNum") String dealNum, Model model) {
		dealDetailService.execute(dealNum, model);
		return "thymeleaf/myAsset/dealDetail";
	}
	
	@GetMapping("dealUpdate")
	public String dealUpdate(@RequestParam("dealNum") String dealNum, Model model) {
		dealDetailService.execute(dealNum, model);
		return "thymeleaf/myAsset/dealUpdate";
	}
	
	@PostMapping("dealUpdate")
	public String dealUpdate(@Validated DealCommand dealCommand, BindingResult result
			, HttpSession session, @RequestParam("categoryName") String categoryName) {
		if(result.hasErrors()) {
			return "thymeleaf/myAsset/dealUpdate";
		}
		dealUpdateService.execute(dealCommand, categoryName);
		budgetUpdateService.execute(session);
		
		return "redirect:dealDetail?dealNum=" + dealCommand.getDealNum();
	}
	@RequestMapping("dealsDelete")
	public String dealDelete(@RequestParam(value = "dealNums", required = false) String dealNums[]
			, HttpSession session) {
		if (dealNums == null || dealNums.length == 0) {
            // 파라미터가 없으면 "dealList" 페이지 반환
            return "redirect:dealList";
        }
		dealsDeleteService.execute(dealNums);
		budgetUpdateService.execute(session);
		return "redirect:dealList";
	}
	
	@GetMapping("dealDelete")
	public String dealDelete(@RequestParam("dealNum") String dealNum
			, HttpSession session) {
		dealDeleteService.execute(dealNum);
		budgetUpdateService.execute(session);
		return "redirect:dealList";
	}
}
