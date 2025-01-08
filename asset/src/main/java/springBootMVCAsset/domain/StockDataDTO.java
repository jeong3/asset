package springBootMVCAsset.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Alias("stockDataDTO")
@Data
public class StockDataDTO {
	Date date;
    double open;
    double high;
    double low;
    double close;
    double cumulativeVolume;
    double sum;
}
