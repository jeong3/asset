package springBootMVCAsset.service.announce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springBootMVCAsset.mapper.AnnounceMapper;

@Service
public class AnnounceDeleteService {
	@Autowired
	AnnounceMapper announceMapper;
	public void execute(String announceNum) {
		announceMapper.announceDelete(announceNum);
	}
}
