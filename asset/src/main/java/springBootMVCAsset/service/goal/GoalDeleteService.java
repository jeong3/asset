package springBootMVCAsset.service.goal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springBootMVCAsset.mapper.GoalMapper;

@Service
public class GoalDeleteService {
	@Autowired
	GoalMapper goalMapper;
	public void execute(String goalNum) {
		goalMapper.goalDelete(goalNum);
	}
}
