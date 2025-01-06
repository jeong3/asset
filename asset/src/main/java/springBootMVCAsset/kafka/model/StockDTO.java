package springBootMVCAsset.kafka.model;

import lombok.Data;

@Data
public class StockDTO {
	String dealTime;
	String itemCode;
	String dealType;
	int executionPrice;
	int dealVolume;
	int cumulativeDealVolume;
	
}
