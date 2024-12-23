package springBootMVCAsset.service.ipgo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import springBootMVCAsset.domain.IpgoDTO;
import springBootMVCAsset.mapper.IpgoMapper;

@Service
public class IpgoListService {
	@Autowired
	IpgoMapper ipgoMapper;
	public void execute(Model model) {
		List<IpgoDTO> list = ipgoMapper.ipgoSelectAll();
		model.addAttribute("list", list);
	}
}
