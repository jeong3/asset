package springBootMVCAsset.service.news;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import springBootMVCAsset.domain.AuthInfoDTO;
import springBootMVCAsset.mapper.NewsMapper;

@Service
public class SaveRegistService {
	@Autowired
	NewsMapper newsMapper;
	public String execute(String newsNum, HttpSession session) {
		AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
		String memberNum = auth.getUserNum();
		newsMapper.newsSaveUpdate(newsNum, memberNum);
		String saveDate = newsMapper.newsSaveSelect(newsNum, memberNum);
		return saveDate;
	}

}
