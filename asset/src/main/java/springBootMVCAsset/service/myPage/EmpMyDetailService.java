package springBootMVCAsset.service.myPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpSession;
import springBootMVCAsset.domain.AuthInfoDTO;
import springBootMVCAsset.domain.EmployeeDTO;
import springBootMVCAsset.domain.MemberDTO;
import springBootMVCAsset.mapper.MyPageMapper;

@Service
public class EmpMyDetailService {
	@Autowired
	MyPageMapper myPageMapper;
	
	public void execute(HttpSession session, Model model) {
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		String empId = auth.getUserId();
		EmployeeDTO dto = myPageMapper.EmpMyDetail(empId);
		
		model.addAttribute("employeeCommand", dto);
		
	}
	
}
