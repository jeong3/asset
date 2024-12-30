package springBootMVCAsset.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import springBootMVCAsset.command.NewsCommand;
import springBootMVCAsset.service.AutoNumService;
import springBootMVCAsset.service.news.NewsDeleteService;
import springBootMVCAsset.service.news.NewsDetailService;
import springBootMVCAsset.service.news.NewsListService;
import springBootMVCAsset.service.news.NewsRegistService;
import springBootMVCAsset.service.news.NewsUpdateService;

@Controller
@RequestMapping("news")
public class NewsController {
	@Autowired
	AutoNumService autoNumService;
	@Autowired
	NewsRegistService newsRegistService;
	@Autowired
	NewsListService newsListService;
	@Autowired
	NewsDetailService newsDetailService;
	@Autowired
	NewsUpdateService newsUpdateService;
	@Autowired
	NewsDeleteService newsDeleteService;
	
	@GetMapping("newsList")
	public String newsList(Model model) {
		newsListService.execute(model);
		return "thymeleaf/news/newsList";
	}
	@GetMapping("newsRegist")
	public String newsRegist(NewsCommand newsCommand,Model model) {
		String newNum = autoNumService.execute("news_", "news_num", 6, "news");
		newsCommand.setNewsNum(newNum);
		model.addAttribute("newsCommand", newsCommand);
		return "thymeleaf/news/newsRegist";
	}
	@PostMapping("newsRegist")
	public String newsRegist(NewsCommand newsCommand, HttpSession session) {
		newsRegistService.execute(newsCommand, session);
		return "redirect:newsList";
	}
	@GetMapping("newsDetail")
	public String newsDetail(String newsNum, Model model, HttpSession session) {
		newsListService.execute(model);
		newsDetailService.execute(newsNum, model, session);
		return "thymeleaf/news/newsDetail";
	}
	@GetMapping("newsUpdate")
	public String newsUpdate(String newsNum, Model model, HttpSession session) {
		newsDetailService.execute(newsNum, model, null);
		return "thymeleaf/news/newsUpdate";
	}
	@PostMapping("newsUpdate")
	public String newsUpdate(NewsCommand newsCommand, HttpSession session) {
		newsUpdateService.execute(newsCommand, session);
		return "redirect:newsDetail?newsNum="+newsCommand.getNewsNum();
	}
	@GetMapping("newsDelete")
	public String newsDelete(String newsNum) {
		newsDeleteService.execute(newsNum);
		return "redirect:newsList";
	}
	
}
