package springBootMVCAsset.command;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class GoodsCommand {
	String goodsNum;
	String goodsName;
	String goodsContents;
	Integer goodsPrice;
	Date registDate;
	String goodsKind;
	String videoUrl;
	MultipartFile mainImage;
	MultipartFile detailImage[];
	
	
}
