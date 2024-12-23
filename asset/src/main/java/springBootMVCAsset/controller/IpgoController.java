package springBootMVCAsset.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import springBootMVCAsset.command.IpgoCommand;
import springBootMVCAsset.domain.GoodsDTO;
import springBootMVCAsset.mapper.GoodsMapper;
import springBootMVCAsset.service.AutoNumService;
import springBootMVCAsset.service.ipgo.IpgoDeleteService;
import springBootMVCAsset.service.ipgo.IpgoListService;
import springBootMVCAsset.service.ipgo.IpgoRegistService;

@Controller
@RequestMapping("goodsIpgo")
public class IpgoController {
	@Autowired
	GoodsMapper goodsMapper;
	@Autowired
	AutoNumService autoNumService;
	@Autowired
	IpgoRegistService ipgoRegistService;
	@Autowired
	IpgoListService ipgoListService;
	@Autowired
	IpgoDeleteService ipgoDeleteService;
	@GetMapping("ipgoList")
	public String ipgoList(Model model) {
		ipgoListService.execute(model);
		return "thymeleaf/ipgo/ipgoList";
	}
	@GetMapping("ipgoRegist")
	public String ipgoRegist(Model model) {
		String autoNum = autoNumService.execute("ipgo_", "ipgo_num", 6, "ipgo");
		IpgoCommand ipgoCommand =  new IpgoCommand();
		ipgoCommand.setIpgoNum(autoNum);
		model.addAttribute("ipgoCommand", ipgoCommand);
		return "thymeleaf/ipgo/ipgoRegist";
	}
	@PostMapping("ipgoRegist") 
	public String ipgoRegist(IpgoCommand ipgoCommand, HttpSession session) {
		System.out.println("ipgoCommand: " + ipgoCommand.getIpgoNum());
		ipgoRegistService.execute(ipgoCommand, session);
		System.out.println("ipgoCommand: " + ipgoCommand.getIpgoNum());
		return "redirect:ipgoList";
	}
	@GetMapping("goodsItem")
	public String goodsItem(Model model) {
	    // 예제: 상품 데이터를 데이터베이스에서 가져오기
	    List<GoodsDTO> goodsList = goodsMapper.goodsSelectAll();
	    model.addAttribute("list", goodsList);
	    return "thymeleaf/goods/goodsItem";
	}
	@GetMapping("ipgoDelete")
	public String ipgoDelete(@RequestParam("ipgoNum") String ipgoNum) {
		System.out.println("Deleting IPGO record with IPGO_NUM: " + ipgoNum); 
		ipgoDeleteService.execute(ipgoNum);
		return "redirect:ipgoList";
	}
}
