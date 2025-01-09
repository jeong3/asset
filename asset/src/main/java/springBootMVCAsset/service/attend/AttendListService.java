package springBootMVCAsset.service.attend;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import springBootMVCAsset.domain.AttendDTO;
import springBootMVCAsset.mapper.AttendMapper;

@Service
public class AttendListService {
	@Autowired
	AttendMapper attendMapper;
	public void execute(String empNum, Model model) {
		List<AttendDTO> list = attendMapper.AttendSelectOne(empNum);
		model.addAttribute("list", list);
		
	}

}
