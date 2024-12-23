package springBootMVCAsset.domain;

import java.util.Date;
import org.apache.ibatis.type.Alias;
import lombok.Data;
import springBootMVCAsset.domain.CartDTO;

@Data
@Alias("cartDTO")
public class CartDTO {
	Integer cartNum;
	String memberNum;
	String goodsNum;
	Integer cartQty;
	Date cartDate;
}

