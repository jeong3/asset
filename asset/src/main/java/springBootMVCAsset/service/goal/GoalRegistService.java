package springBootMVCAsset.service.goal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import springBootMVCAsset.command.GoalCommand;
import springBootMVCAsset.domain.AuthInfoDTO;
import springBootMVCAsset.domain.GoalDTO;
import springBootMVCAsset.mapper.GoalMapper;
import springBootMVCAsset.service.AutoNumService;

@Service
public class GoalRegistService {
	@Autowired
	GoalMapper goalMapper;
	@Autowired
	AutoNumService autoNumService;
	
	public void execute(GoalCommand goalCommand
			, HttpSession session, String goalName) {
		GoalDTO dto = new GoalDTO();
		dto.setEndDate(goalCommand.getEndDate());
		dto.setStartDate(goalCommand.getStartDate());
		dto.setGoalContents(goalCommand.getGoalContents());
		dto.setGoalName(goalName);
		dto.setGoalNum(goalCommand.getGoalNum());
		dto.setGoalPrice(goalCommand.getGoalPrice());
		
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		String memberNum = auth.getUserNum();
		dto.setMemberNum(memberNum);
		
		String autoNum = autoNumService.execute("goal_", "goal_num", 6, "goal");
		dto.setGoalNum(autoNum);
		
		if(dto.getGoalName().equals("저축")){
			goalMapper.goalRegist1(dto);
		}else{
			goalMapper.goalRegist2(dto);
		}
	}
}
