package springBootMVCAsset.command;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class IpgoCommand {
	String ipgoNum;
	String goodsNum;
	Integer ipgoQty;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date ipgoDate;
	Integer ipgoPrice;
}
