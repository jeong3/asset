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
	Date RegistDate;
	String goodsKind;
	MultipartFile goodsMainImage;
	MultipartFile goodsDetailImage[];
}
