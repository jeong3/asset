package springBootMVCAsset.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("ipgoDTO")
public class IpgoDTO {
	String ipgoNum;
	Integer ipgoQty;
	Date ipgoDate;
	Integer ipgoPrice;
	String empNum;
	String goodsNum;
}
