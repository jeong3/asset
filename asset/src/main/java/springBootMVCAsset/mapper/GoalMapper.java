package springBootMVCAsset.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import springBootMVCAsset.domain.GoalDTO;

@Mapper
public interface GoalMapper {
	public void goalRegist1(GoalDTO dto); // 저축
	public void goalRegist2(GoalDTO dto); // 소비
	
	public List <GoalDTO> goalRunList(String memberNum); // 진행중인 목표 리스트
	public Integer goalRunCount(String memberNum);
	
	public List <GoalDTO> goalFinishList(String memberNum); // 완료된 목표 리스트
	public Integer goalFinishCount(String memberNum);
	
	public GoalDTO goalDetail(String goalNum);
}
