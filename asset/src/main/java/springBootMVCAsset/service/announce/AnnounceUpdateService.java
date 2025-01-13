package springBootMVCAsset.service.announce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpSession;
import springBootMVCAsset.command.AnnounceCommand;
import springBootMVCAsset.domain.AnnounceDTO;
import springBootMVCAsset.mapper.AnnounceMapper;

@Service
public class AnnounceUpdateService {
	@Autowired
	AnnounceMapper announceMapper;
	public void execute(AnnounceCommand announceCommand, Model model, HttpSession sesseion) {
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
		
		announceMapper.announceUpdate(dto);
	}
}
