package springBootMVCAsset.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import springBootMVCAsset.domain.AttendDTO;

@Mapper
public interface AttendMapper {

	int AttendInsert(AttendDTO dto);

	int AttendUpdate(AttendDTO dto);

	int atWorkSelect(String empNum);

	AttendDTO AttendSelect(String empNum, String date);

}
