package springBootMVCAsset.service.ipgo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springBootMVCAsset.mapper.IpgoMapper;

@Service
public class IpgoDeleteService {
	@Autowired
	IpgoMapper ipgoMapper;
	public void execute(String ipgoNum) {
		ipgoMapper.ipgoDelete(ipgoNum);
	}
}
