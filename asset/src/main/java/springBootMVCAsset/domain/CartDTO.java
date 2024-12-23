package springBootMVCAsset.domain;

import java.util.Date;
import org.apache.ibatis.type.Alias;
import lombok.Data;
import springBootMVCAsset.domain.CartDTO;

@Data
@Alias("cartDTO")
public class CartDTO {
	String goodsNum;
	String memberNum;
	Integer cartNum;
	Date cartDate;
	Integer cartQty;
	
}

