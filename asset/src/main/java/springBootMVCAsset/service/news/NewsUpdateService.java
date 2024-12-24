package springBootMVCAsset.service.news;

import java.io.File;
import java.net.URL;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;
import springBootMVCAsset.command.NewsCommand;
import springBootMVCAsset.domain.AuthInfoDTO;
import springBootMVCAsset.domain.NewsDTO;
import springBootMVCAsset.mapper.NewsMapper;

@Service
public class NewsUpdateService {
	@Autowired
	NewsMapper newsMapper;
	public void execute(NewsCommand newsCommand, HttpSession session) {
		AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
		NewsDTO dto = new NewsDTO();
		BeanUtils.copyProperties(newsCommand, dto);
		dto.setUpdateEmpNum(auth.getUserNum());

		URL resource = getClass().getClassLoader().getResource("static/images");
		String fileDir = resource.getFile();
		System.out.println(fileDir);
		if (newsCommand.getImage() != null) {
			MultipartFile mf = newsCommand.getImage();
			String originalFile = mf.getOriginalFilename();
			String extension = originalFile.substring(originalFile.lastIndexOf("."));
			String storeName = UUID.randomUUID().toString().replace("-", "");
			String storeFileName = storeName + extension;
			System.out.println("storeFileName : " + storeFileName);
			File file = new File(fileDir + "/" + storeFileName);
			try {
				mf.transferTo(file);
			} catch (Exception e) {
				e.printStackTrace();
			}
			dto.setImage(originalFile);
			dto.setStoreImage(storeFileName);
		}
		newsMapper.newsUpdate(dto);
		
	}

}
