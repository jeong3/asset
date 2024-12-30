package springBootMVCAsset.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import springBootMVCAsset.domain.InquireDTO;
import springBootMVCAsset.mapper.InquireMapper;
import springBootMVCAsset.service.goods.GoodsDetailViewService;

@Controller
@RequestMapping("corner")
public class CornerController {
	@Autowired
	InquireMapper inquireMapper;
	@Autowired
	GoodsDetailViewService goodsDetailViewService;
	@RequestMapping("/goodsDescript")
	public void goodsDescript(@RequestBody Map<String, String> map,
			Model model, HttpServletResponse response, HttpSession session) {
		goodsDetailViewService.execute(map.get("goodsNum"), model, response,session);
		System.out.println("Response1: " + model.asMap());	
	}
	@RequestMapping("inquireList")
	public String inquireList(@ModelAttribute("goodsNum") String goodsNum
			,Model model) {
		//model.addAttribute("goodsNum", goodsNum);
		List<InquireDTO> list = inquireMapper.inquireList(goodsNum, null);
		model.addAttribute("list", list);
		model.addAttribute("newLineChar", "\n");
		return "thymeleaf/inquire/inquireList";
	}
}