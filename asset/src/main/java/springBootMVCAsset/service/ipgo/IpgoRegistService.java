package springBootMVCAsset.service.ipgo;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import springBootMVCAsset.command.IpgoCommand;
import springBootMVCAsset.domain.IpgoDTO;
import springBootMVCAsset.mapper.IpgoMapper;

@Service
public class IpgoRegistService {
	@Autowired
	IpgoMapper ipgoMapper;
	public void execute(IpgoCommand ipgoCommand, HttpSession session) {
		IpgoDTO dto = new IpgoDTO();
		BeanUtils.copyProperties("ipgoCommand", dto);
		ipgoMapper.ipgoRegist(dto);
		
	}
}
