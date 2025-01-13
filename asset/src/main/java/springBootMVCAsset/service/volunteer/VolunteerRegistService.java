package springBootMVCAsset.service.volunteer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import springBootMVCAsset.command.VolunteerCommand;
import springBootMVCAsset.domain.VolunteerDTO;
import springBootMVCAsset.mapper.VolunteerMapper;

@Service
public class VolunteerRegistService {
	@Autowired
	VolunteerMapper volunteerMapper;
	public void execute(VolunteerCommand volunteerCommand, HttpSession session) {
		VolunteerDTO dto = new VolunteerDTO();
		dto.setReceiptNum(volunteerCommand.getReceiptNum());
		dto.setVolunteerBirth(volunteerCommand.getVolunteerBirth());
		dto.setAnnounceNum(volunteerCommand.getAnnounceNum());
		dto.setVolunteerEmail(volunteerCommand.getVolunteerEmail());
		dto.setVolunteerEngname(volunteerCommand.getVolunteerEngname());
		dto.setVolunteerId(volunteerCommand.getVolunteerId());
		dto.setVolunteerName(volunteerCommand.getVolunteerName());
		dto.setVolunteerPhone(volunteerCommand.getVolunteerPhone());
		dto.setVolunteerPw(volunteerCommand.getVolunteerPw());
		System.out.println("디티오 : " + dto);
		
		volunteerMapper.volunteerInsert(dto);
	}
}
