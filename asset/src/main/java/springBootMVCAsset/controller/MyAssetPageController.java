package springBootMVCAsset.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("myAsset")
public class MyAssetPageController {
	
	@GetMapping("myAssetPage")
	public String myAssetPage() {
		return "thymeleaf/myAsset/myAssetPage";
	}
	
	@GetMapping("dealRegist")
	public String dealRegist() {
		return "thymeleaf/myAsset/dealRegist";
	}
}
