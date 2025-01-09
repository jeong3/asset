package springBootMVCAsset.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import springBootMVCAsset.command.AnnounceCommand;
import springBootMVCAsset.domain.AuthInfoDTO;
import springBootMVCAsset.domain.DepartmentDTO;
import springBootMVCAsset.mapper.EmployeeMapper;
import springBootMVCAsset.service.AutoNumService;
import springBootMVCAsset.service.announce.AnnounceRegistService;

@Controller
@RequestMapping("announce")
public class AnnounceController {
	@Autowired
	EmployeeMapper employeeMapper;
	@Autowired
	AutoNumService autoNumService;
	@Autowired
	AnnounceRegistService announceRegistService;
	@GetMapping("announceList")
	public String announceList() {
		return "thymeleaf/announce/announceList";
	}
	@GetMapping("announceRegist") 
	public String announceRegist(Model model, HttpSession session) {
		String autoNum = autoNumService.execute("announce_", "announce_num", 10, "announce");
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		String empNum = auth.getUserNum();
		model.addAttribute("empNum", empNum);
		AnnounceCommand  announceCommand = new AnnounceCommand();
		announceCommand.setAnnounceNum(autoNum);
		model.addAttribute("announceCommand", announceCommand);
		return "thymeleaf/announce/announceRegist";
	}
	@PostMapping("announceRegist")
	public String announceRegist(AnnounceCommand announceCommand, HttpSession session) {
		announceRegistService.execute(announceCommand, session);
		return "redirect:announceList";
	}
	@GetMapping("announceDepartmentItem")
	public String announceDepartmentItem(Model model) {
	    // 예제: 상품 데이터를 데이터베이스에서 가져오기
	    List<DepartmentDTO> list = employeeMapper.departmentEmployeeSelectAll();
	    model.addAttribute("list", list);
	    return "thymeleaf/announce/announceDepartmentItem";
	}
}
