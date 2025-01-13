package springBootMVCAsset.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import springBootMVCAsset.domain.VolunteerDTO;

@Mapper
public interface VolunteerMapper {
	public void volunteerInsert(VolunteerDTO dto);
	public List<VolunteerDTO> volunteerSelectAll();
	public void updateResult(@Param("receiptNum") String receiptNum, 
							 @Param("documentResult") String documentResult);
	public void updateInterviewResult(@Param("receiptNum") String receiptNum, 
                     				  @Param("interviewResult") String interviewResult);
	public VolunteerDTO findByReceiptNum(@Param("receiptNum") String receiptNum);
	public VolunteerDTO findByVolunteerId(String volunteerId);
	public Integer idCheckSelectOne(String volunteerId);
	public String selectAnnounceNum(String volunteerId);
}
