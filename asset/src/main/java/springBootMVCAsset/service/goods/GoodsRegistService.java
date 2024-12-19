package springBootMVCAsset.service.goods;

import java.io.File;
import java.net.URL;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;
import springBootMVCAsset.command.GoodsCommand;
import springBootMVCAsset.domain.GoodsDTO;
import springBootMVCAsset.mapper.GoodsMapper;

@Service
public class GoodsRegistService {
	@Autowired
	GoodsMapper goodsMapper;
	public void execute(GoodsCommand goodsCommand, HttpSession session) {
		GoodsDTO dto = new GoodsDTO();
		dto.setGoodsContents(goodsCommand.getGoodsContents());
		dto.setGoodsName(goodsCommand.getGoodsName());
		dto.setGoodsNum(goodsCommand.getGoodsNum());
		dto.setGoodsPrice(goodsCommand.getGoodsPrice());
		dto.setGoodsKind(goodsCommand.getGoodsKind());
		//AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		//String empNum = employeeMapper.getEmpNum(auth.getUserId());
		//dto.setEmpNum(empNum);
		
		URL resource = getClass().getClassLoader().getResource("static/upload");
		System.out.println("resource : " + resource);
		String filrDir = resource.getFile();
		//String filrDir = "C:/Users/misolaptop1/eclipse-workspace/real_time_data_process_20240708/springBootMVCShopping/target/classes/static/upload";
		MultipartFile mf = goodsCommand.getGoodsMainImage();
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
	
		dto.setGoodsMainImage(originalFile);
		dto.setGoodsMainStoreImage(storeFileName);
	
		if(!goodsCommand.getGoodsDetailImage()[0].getOriginalFilename().isEmpty()) {
			String originalTotal = ""; 
			String storeTotal = "";
			for(MultipartFile mpf : goodsCommand.getGoodsDetailImage()) {
				originalFile = mpf.getOriginalFilename();//오류
				extension = originalFile.substring(originalFile.lastIndexOf("."));
				storeName = UUID.randomUUID().toString().replace("-", "");
				storeFileName = storeName + extension;
				file = new File(filrDir + "/" + storeFileName);
				try {
					mpf.transferTo(file);
				} catch (Exception e) {
					e.printStackTrace();
				}
				originalTotal += originalFile + "/";
				storeTotal += storeFileName +"/";
			}
			dto.setGoodsDetailImage(originalTotal);
			dto.setGoodsDetailStoreImage(storeTotal);
		}
		goodsMapper.goodsInsert(dto);
		
	}
}