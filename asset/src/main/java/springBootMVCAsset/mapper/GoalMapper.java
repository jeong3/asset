package springBootMVCAsset.mapper;

import org.apache.ibatis.annotations.Mapper;

import springBootMVCAsset.domain.GoalDTO;

@Mapper
public interface GoalMapper {
	public void goalRegist1(GoalDTO dto); // 저축
	public void goalRegist2(GoalDTO dto); // 소비
}
