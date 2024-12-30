package springBootMVCAsset.command;

import java.util.Date;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data
public class DealCommand {
	String dealNum;
	String memberNum;
	
	@NotEmpty(message = "거래 금액을 입력해주세요")
	Integer dealPrice;
	
	String dealContents;
	
	@NotEmpty(message = "거래 수단을 입력해주세요")
	String dealMethod;
	
	@NotNull(message = "거래 날짜를 입력해주세요")
	Date dealDate;
	
	String categoryName;
	
	@NotEmpty(message = "거래 카테고리를 선택해주세요")
	String categoryType;
}
