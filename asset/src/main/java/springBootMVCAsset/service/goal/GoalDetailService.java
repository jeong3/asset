package springBootMVCAsset.service.goal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import springBootMVCAsset.domain.GoalDTO;
import springBootMVCAsset.mapper.GoalMapper;

@Service
public class GoalDetailService {
	@Autowired
	GoalMapper goalMapper;
	public void execute(String goalNum, Model model) {
		GoalDTO dto = goalMapper.goalDetail(goalNum);
		model.addAttribute("dto", dto);
	}
}
