package springBootMVCAsset.kafka.model;

import lombok.Data;

@Data
public class StockDTO {
	String dealTime;
	String itemCode;
	String dealType;
	Integer executionPrice;
	String dealVolume;
	String cumulativeDealVolume;
	
}
