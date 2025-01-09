package springBootMVCAsset.service.announce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import springBootMVCAsset.command.AnnounceCommand;
import springBootMVCAsset.domain.AnnounceDTO;
import springBootMVCAsset.mapper.AnnounceMapper;

@Service
public class AnnounceRegistService {

	@Autowired
	AnnounceMapper announceMapper;
	public void execute(AnnounceCommand announceCommand, HttpSession session) {
		AnnounceDTO dto = new AnnounceDTO();
		dto.setAnnounceContents(announceCommand.getAnnounceContents());
		dto.setAnnounceNum(announceCommand.getAnnounceNum());
		dto.setAnnounceName(announceCommand.getAnnounceName());
		dto.setAnnounceDate(announceCommand.getAnnounceDate());
		dto.setDepartmentNum(announceCommand.getDepartmentNum());
		dto.setEndDate(announceCommand.getEndDate());
		dto.setInterviewAddr(announceCommand.getInterviewAddr());
		dto.setInterviewDate(announceCommand.getInterviewDate());
		dto.setResultDate(announceCommand.getResultDate());
		dto.setStartDate(announceCommand.getStartDate());
		
		announceMapper.announceInsert(dto);

	}
}
