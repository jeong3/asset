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
public class NewsRegistService {
	@Autowired
	NewsMapper newsMapper;
	public void execute(NewsCommand newsCommand, HttpSession session) {
		AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
		NewsDTO dto = new NewsDTO();
		BeanUtils.copyProperties(newsCommand, dto);
		dto.setEmpNum(auth.getUserNum());
		
		URL resource = getClass().getClassLoader().getResource("static/Images");
		System.out.println("resource : " + resource);
		String filrDir = resource.getFile();
		//String filrDir = "C:/Users/misolaptop1/eclipse-workspace/real_time_data_process_20240708/springBootMVCShopping/target/classes/static/upload";
		MultipartFile mf = newsCommand.getImage();
		String originalFile = mf.getOriginalFilename();

		String extension = originalFile.substring(originalFile.lastIndexOf("."));
	
		String storeName = UUID.randomUUID().toString().replace("-", "");
		String storeFileName = storeName + extension;
	
		File file = new File(filrDir + "/" + storeFileName);
		try {
			mf.transferTo(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		dto.setImage(originalFile);
		dto.setStoreImage(storeFileName);
		System.out.println(dto);
		newsMapper.newsInsert(dto);
	}

}
