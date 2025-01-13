package springBootMVCAsset.mapper;

import org.apache.ibatis.annotations.Mapper;

import springBootMVCAsset.domain.VolunteerDTO;

@Mapper
public interface VolunteerMapper {
	public void volunteerInsert(VolunteerDTO dto);
}
