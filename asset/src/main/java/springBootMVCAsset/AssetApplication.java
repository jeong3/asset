package springBootMVCAsset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import springBootMVCAsset.service.EmailSendService;
import springBootMVCAsset.service.goods.GoodsListService;
@Controller
@SpringBootApplication
public class AssetApplication {
	@Autowired
	GoodsListService goodsListService;
	public static void main(String[] args) {
		SpringApplication.run(AssetApplication.class, args);
	}
	@GetMapping("/")
	public String index(@RequestParam(value = "goodsKind1", required = false, defaultValue = "강의") String goodsKind1,
	                    @RequestParam(value = "goodsKind2", required = false, defaultValue = "도서") String goodsKind2,
	                    @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
	                    @RequestParam(value = "searchWord", required = false) String searchWord,
	                    Model model) {
	    model.addAttribute("goodsKind1", goodsKind1);
	    model.addAttribute("goodsKind2", goodsKind2);
	    goodsListService.execute(goodsKind1, page, searchWord, model);
	    model.addAttribute("lectureList", model.getAttribute("list"));
	    goodsListService.execute(goodsKind2, page, searchWord, model);
	    model.addAttribute("bookList", model.getAttribute("list"));
	    return "thymeleaf/index";
	}

	@GetMapping("/mailling")
	public String mail() {
		return "thymeleaf/email";
	}
	@Autowired
	EmailSendService emailSendService;
	@PostMapping("/mailling")
	public String mail(String fromEmail
			,String toEmail, String subject
			,String contents) {
		emailSendService.mailSend(fromEmail, toEmail, subject, contents);
		return "redirect:/";
	}
}
