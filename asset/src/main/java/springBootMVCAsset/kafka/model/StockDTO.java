package springBootMVCAsset.kafka.model;

import java.util.Date;

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
