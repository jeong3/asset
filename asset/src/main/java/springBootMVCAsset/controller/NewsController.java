package springBootMVCAsset.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import springBootMVCAsset.command.NewsCommand;
import springBootMVCAsset.command.NewsCommentCommend;
import springBootMVCAsset.mapper.NewsMapper;
import springBootMVCAsset.service.AutoNumService;
import springBootMVCAsset.service.news.CommentRegistService;
import springBootMVCAsset.service.news.MyNewsListService;
import springBootMVCAsset.service.news.NewsDeleteService;
import springBootMVCAsset.service.news.NewsDetailService;
import springBootMVCAsset.service.news.NewsListService;
import springBootMVCAsset.service.news.NewsLoadMoreList;
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
	@Autowired
	CommentRegistService commentRegistService;
	@Autowired
	NewsMapper newsMapper;
	@Autowired
	NewsLoadMoreList newsLoadMoreList;
	@Autowired
	MyNewsListService myNewsListService;
	
	@GetMapping("newsList")
	public String newsList(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
			@RequestParam(value = "searchWord", required = false) String searchWord, Model model) {
		newsListService.execute(page, searchWord, model);
		return "thymeleaf/news/newsList";
	}
	@RequestMapping("loadMoreNewsList")
	public String loadMoreNewsList(int page, String searchWord, Model model) {
		System.out.println("검색"+page + searchWord);
		newsLoadMoreList.execute(page,searchWord, model);
		return "thymeleaf/news/loadMoreNewsList";
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
	public String newsDetail(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
			@RequestParam(value = "searchWord", required = false) String searchWord, String newsNum, Model model, HttpSession session) {
		newsListService.execute(page, searchWord, model);
		if(session == null) session = null;
		newsDetailService.execute(newsNum, model, session);
		return "thymeleaf/news/newsDetail";
	}
	@GetMapping("newsUpdate")
	public String newsUpdate(String newsNum, Model model, HttpSession session) {
		newsDetailService.execute(newsNum, model, session);
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
	
	@RequestMapping("comment")
	public String comment(NewsCommentCommend newsCommentCommend, HttpSession session) {
		commentRegistService.execute(newsCommentCommend, session);
		return "redirect:newsDetail?newsNum="+newsCommentCommend.getNewsNum();
	}
	@GetMapping("commentDelete")
	public String commentDelete(NewsCommentCommend newsCommentCommend) {
		System.out.println("코맨트커맨드"+newsCommentCommend);
		newsMapper.commentDelete(newsCommentCommend.getCommentNum());
		return "redirect:newsDetail?newsNum="+newsCommentCommend.getNewsNum();
	}
	@GetMapping("myNewsList")
	public String myNewsList(Model model , HttpSession session) {
		myNewsListService.execute(model, session);
		return "thymeleaf/news/myNewsList";
	}
	
	

	
	
}
