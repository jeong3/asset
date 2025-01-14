package springBootMVCAsset.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import springBootMVCAsset.domain.GoalDTO;

@Mapper
public interface GoalMapper {
	public void goalRegist1(GoalDTO dto); // 저축
	public void goalRegist2(GoalDTO dto); // 소비
	public List <GoalDTO> goalRunList(String memberNum);
	public Integer goalRunCount(String memberNum);
}
