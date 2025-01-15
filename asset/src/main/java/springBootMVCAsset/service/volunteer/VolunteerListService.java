package springBootMVCAsset.service.volunteer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import springBootMVCAsset.domain.VolunteerDTO;
import springBootMVCAsset.mapper.AnnounceMapper;
import springBootMVCAsset.mapper.VolunteerMapper;

@Service
public class VolunteerListService {
	@Autowired
	AnnounceMapper announceMapper;
	@Autowired
	VolunteerMapper volunteerMapper;
	public void execute(Model model) {
		List<VolunteerDTO> list = volunteerMapper.volunteerSelectAll();
		model.addAttribute("list", list);
		System.out.println("지원자 : " + list);
	}
}
