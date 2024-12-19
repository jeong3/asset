package springBootMVCAsset.service.goods;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;
import springBootMVCAsset.command.GoodsCommand;
import springBootMVCAsset.domain.FileDTO;
import springBootMVCAsset.domain.GoodsDTO;
import springBootMVCAsset.mapper.GoodsMapper;

@Service
public class GoodsUpdateService {
	@Autowired
	GoodsMapper goodsMapper;

	public void execute(GoodsCommand goodsCommand, HttpSession session, Model model) {

		GoodsDTO dto = new GoodsDTO();
		BeanUtils.copyProperties(goodsCommand, dto);
		URL resource = getClass().getClassLoader().getResource("static/images");
		String fileDir = resource.getFile();
		System.out.println(fileDir);
		if (goodsCommand.getMainImage() != null) {
			MultipartFile mf = goodsCommand.getMainImage();
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
			dto.setMainImage(originalFile);
			dto.setMainStoreImage(storeFileName);
		}
		String originalTotal = "";
		String storeTotal = "";
		if (!goodsCommand.getDetailImage()[0].getOriginalFilename().isEmpty()) {
			for (MultipartFile mf : goodsCommand.getDetailImage()) {
				String originalFile = mf.getOriginalFilename();
				String extension = originalFile.substring(originalFile.lastIndexOf("."));
				String storeName = UUID.randomUUID().toString().replace("-", "");
				String storeFileName = storeName + extension;
				File file = new File(fileDir + "/" + storeFileName);
				try {
					mf.transferTo(file);
				} catch (Exception e) {
					e.printStackTrace();
				}
				originalTotal += originalFile + "/";
				storeTotal += storeFileName + "/";
			}
		}
		List<FileDTO> list = (List<FileDTO>) session.getAttribute("fileList");
		GoodsDTO goodsDTO = goodsMapper.goodsSelectOne(goodsCommand.getGoodsNum());
		if (goodsDTO.getDetailImage() != null) {
			List<String> dbOrg = new ArrayList<>(Arrays.asList(goodsDTO.getDetailImage().split("[/`]")));
			List<String> dbStore = new ArrayList<>(Arrays.asList(goodsDTO.getDetailStoreImage().split("[/`]")));
			if (list != null) {
				for (FileDTO fdto : list) {
					for (String img : dbOrg) {
						if (fdto.getOrgFile().equals(img)) {
							dbOrg.remove(fdto.getOrgFile());
							dbStore.remove(fdto.getStoreFile());
							break;
						}
					}
				}
			}
			for (String img : dbOrg)
				originalTotal += img + "/";
			for (String img : dbStore)
				storeTotal += img + "/";
		}
		dto.setDetailStoreImage(storeTotal);
		dto.setDetailImage(originalTotal);

		int i = goodsMapper.goodsUpdate(dto);
		if (i > 0) {
			if (list != null) {
				for (FileDTO fd : list) {
					File file = new File(fileDir + "/" + fd.getStoreFile());
					if (file.exists())
						file.delete();
				}
			}
		}
	}
}


