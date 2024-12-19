package springBootMVCAsset.service.goods;

import java.io.File;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springBootMVCAsset.domain.GoodsDTO;
import springBootMVCAsset.mapper.GoodsMapper;

@Service
public class GoodsDeleteService {
	@Autowired
	GoodsMapper goodsMapper;
	public void execute(String goodsNum) {
		// 먼저 삭제할 파일의 정보를 자져옵니다.
		GoodsDTO dto = goodsMapper.goodsSelectOne(goodsNum);
		int i = goodsMapper.goodsDelete(goodsNum);
		//파일 삭제코드를 추가합니다. 
		if(i > 0) {
			//1. 디렉터리 경로
			URL resource = getClass().getClassLoader().getResource("static/images");
			String fileDir = resource.getFile();
			// 2. 파일 객체 생성
			File file =  new File(fileDir+"/"+dto.getMainStoreImage());
			// 3. 삭제
			if(file.exists()) file.delete();
			if(dto.getDetailImage() != null) {
				for(String fileName : dto.getDetailStoreImage().split("/")) {
					// 2. 파일 객체 생성
					file =  new File(fileDir+"/"+fileName);
					// 3. 삭제
					if(file.exists()) file.delete();
				}
			}
		}
	}
}