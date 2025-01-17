package springBootMVCAsset.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import springBootMVCAsset.command.DealCommand;
import springBootMVCAsset.domain.AssetListDTO;
import springBootMVCAsset.domain.SearchDTO;
import springBootMVCAsset.service.AutoNumService;
import springBootMVCAsset.service.budget.BudgetDetailService;
import springBootMVCAsset.service.budget.BudgetUpdateService;
import springBootMVCAsset.service.deal.BankDealListService;
import springBootMVCAsset.service.deal.CashDealListService;
import springBootMVCAsset.service.deal.CreditListService;
import springBootMVCAsset.service.deal.CreditPayService;
import springBootMVCAsset.service.deal.DealDeleteService;
import springBootMVCAsset.service.deal.DealDetailService;
import springBootMVCAsset.service.deal.DealListService;
import springBootMVCAsset.service.deal.DealRegistService;
import springBootMVCAsset.service.deal.DealTotalService;
import springBootMVCAsset.service.deal.DealUpdateService;
import springBootMVCAsset.service.deal.DealsDeleteService;
import springBootMVCAsset.service.deal.JaeTechDealListService;
import springBootMVCAsset.service.deal.SaveDealListService;

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
	
	@Autowired
	CashDealListService cashDealListService;
	@Autowired
	BankDealListService bankDealListService;
	@Autowired
	SaveDealListService saveDealListService;
	@Autowired
	JaeTechDealListService jaeTechDealListService;
	@Autowired
	CreditListService creditListService;
	@Autowired
	CreditPayService creditPayService;
	@Autowired
	DealTotalService dealTotalService;
	
	@GetMapping("myAssetPage")
	public String myAssetPage(Model model, HttpSession session) {
		budgetDetailService.execute(model, session);
		dealTotalService.execute(session, model);
		return "thymeleaf/myAsset/myAssetPage";
	}
	// 거래 등록
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
	// 거래 내역 조회
	@GetMapping("dealList")
	public String dealList(@RequestParam(value="page", required = false, defaultValue="1") Integer page,
			SearchDTO searchDTO, Model model, HttpSession session) {
		dealListService.execute(page, searchDTO, model, session);
		return "thymeleaf/myAsset/dealList";
	}
	// 거래 영수증
	@GetMapping("dealDetail")
	public String dealDetail(@RequestParam("dealNum") String dealNum, Model model
			, HttpServletRequest request) {
		dealDetailService.execute(dealNum, model);
		 String referrer = request.getHeader("Referer"); // 이전 페이지 URL
	     model.addAttribute("previousPage", referrer);  
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
			, HttpSession session
			, @RequestHeader(value = "Referer", required = false) String referer) {
		if (dealNums == null || dealNums.length == 0) {
            // 파라미터가 없으면 "dealList" 페이지 반환
            return "redirect:dealList";
        }
		dealsDeleteService.execute(dealNums);
		budgetUpdateService.execute(session);
		if (referer != null && !referer.isEmpty()) {
	        return "redirect:" + referer;
	    } else {
	        return "redirect:dealList"; // 기본 경로 설정
	    }
	}
	
	@RequestMapping("dealDelete")
	public String dealDelete(@RequestParam("dealNum") String dealNum
			, HttpSession session
			, @RequestParam("pastLocation") String pastLocation) {
		dealDeleteService.execute(dealNum);
		budgetUpdateService.execute(session);
		//String decodedLocation = URLDecoder.decode(pastLocation, StandardCharsets.UTF_8);
		return "redirect:" + pastLocation;
	}
	
	// 내 자산 리스트
	@GetMapping("assetList")
	public String assetList(HttpSession session, Model model) {
		cashDealListService.execute("cash", session, model);
		bankDealListService.execute("checkCard", session, model);
		budgetDetailService.execute(model, session);
		saveDealListService.execute(session, model, null);
		jaeTechDealListService.execute(session, model, null);
		return "thymeleaf/myAsset/assetList";
	}
	
	@GetMapping("cashList")
	public String cashList(@RequestParam(value="page", required = false, defaultValue="1") Integer page
			, AssetListDTO assetListDTO
			, HttpSession session, Model model) {
		cashDealListService.execute2(page, assetListDTO, session, model);
		budgetDetailService.execute(model, session);
		return "thymeleaf/myAsset/cashList";
	}
	
	@GetMapping("bankList")
	public String bankList(@RequestParam(value="page", required = false, defaultValue="1") Integer page
			, AssetListDTO assetListDTO
			, HttpSession session, Model model) {
		bankDealListService.execute2(page, assetListDTO, session, model);
		budgetDetailService.execute(model, session);
		return "thymeleaf/myAsset/bankList";
	}
	
	@RequestMapping("saveList")
	public String saveList(@RequestParam(value="page", required = false, defaultValue="1") Integer page
			, @RequestParam(value="categoryType", required = false) String categoryType
			, AssetListDTO assetListDTO
			, HttpSession session, Model model) {
		System.out.println("카테고리"+categoryType);
		if(categoryType == null) {
			saveDealListService.execute2(null, page, assetListDTO, session, model);
		}
		if(categoryType != null) {
			saveDealListService.execute2(categoryType, page, assetListDTO, session, model);
		}
		
		saveDealListService.execute(session, model, null);
		
		return "thymeleaf/myAsset/saveList";
	}
	
	@RequestMapping("jaetechList")
	public String jaetechList(@RequestParam(value="page", required = false, defaultValue="1") Integer page
			, @RequestParam(required = false) String categoryType
			, AssetListDTO assetListDTO
			, HttpSession session, Model model) {
		jaeTechDealListService.execute2(categoryType, page, assetListDTO, session, model);
		jaeTechDealListService.execute(session, model, categoryType);
		return "thymeleaf/myAsset/jaetechList";
	}
	
	@GetMapping("creditList")
	public String creditList(@RequestParam(value="page", required = false, defaultValue="1") Integer page
			, AssetListDTO assetListDTO
			, HttpSession session, Model model) {
		creditListService.execute(page, assetListDTO, session, model);
		budgetDetailService.execute(model, session);
		return "thymeleaf/myAsset/creditList";
	}
	
}
