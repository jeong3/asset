package springBootMVCAsset.service.volunteer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springBootMVCAsset.mapper.VolunteerMapper;

@Service
public class VolunteerIdCheckService {
	@Autowired
	VolunteerMapper volunteerMapper;
	public Integer execute(String volunteerId) {
		return volunteerMapper.idCheckSelectOne(volunteerId);
	}
}
