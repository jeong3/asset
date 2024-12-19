package springBootMVCAsset.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import springBootMVCAsset.command.IpgoCommand;
import springBootMVCAsset.service.AutoNumService;
import springBootMVCAsset.service.ipgo.IpgoRegistService;

@Controller
@RequestMapping("goodsIpgo")
public class IpgoController {
	@Autowired
	AutoNumService autoNumService;
	@Autowired
	IpgoRegistService ipgoRegistService;
	@GetMapping("ipgoList")
	public String ipgoList() {
		return "thymeleaf/ipgo/ipgoList";
	}
	@GetMapping("ipgoRegist")
	public String ipgoRegist(Model model) {
		String autoNum = autoNumService.execute("ipgo_", "ipgo_num", 9, "ipgo");
		IpgoCommand ipgoCommand =  new IpgoCommand();
		ipgoCommand.setIpgoNum(autoNum);
		model.addAttribute("ipgoCommand", ipgoCommand);
		return "thymeleaf/ipgo/ipgoRegist";
	}
	@PostMapping("ipgoRegist") 
	public String ipgoRegist(IpgoCommand ipgoCommand, HttpSession session) {
		ipgoRegistService.execute(ipgoCommand, session);
		return "redirect:ipgoList";
	}
	@GetMapping("goodsItem")
	   public String goodsItem() {
	      return "thymeleaf/goods/goodsItem";
	 }
}
