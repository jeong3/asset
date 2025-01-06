package springBootMVCAsset.kafka.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import springBootMVCAsset.kafka.StockA3;

public class StockDAO  {
	String jdbcURL;
    String jdbcDriver;
    Connection con;
    PreparedStatement pstmt;
    ResultSet rs;
    String sql;
    
    public StockDAO() {
       jdbcURL = "jdbc:oracle:thin:@172.16.110.141:1521:xe";
       jdbcDriver = "oracle.jdbc.driver.OracleDriver";
    }
    public Connection getConnection() {
       Connection co = null;
       try {
          Class.forName(jdbcDriver);
          co = DriverManager.getConnection(jdbcURL,"asset","1234");
       } catch (Exception e) {
          e.printStackTrace();
       }
       return co;
    }
	public void saveToDatabase(StockDTO dto) {
		con = getConnection();
	    sql = "INSERT INTO stock_data (deal_time, item_code, deal_type, execution_price, deal_volume, cumulative_deal_volume) VALUES (?, ?, ?, ?, ?, ?)";
	    try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getDealTime());
			pstmt.setString(2, dto.getItemCode());
			pstmt.setString(3, "S");
			pstmt.setInt(4, dto.getExecutionPrice());
			pstmt.setString(5, dto.getDealVolume());
			pstmt.setString(6, dto.getCumulativeDealVolume());
			int i = pstmt.executeUpdate();
			System.out.println(i + "행 삽입");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
	}
	
	public void close() {
	      if (rs != null)
	         try {
	            rs.close();
	         } catch (Exception e) {
	         }
	      if (pstmt != null)
	         try {
	            pstmt.close();
	         } catch (Exception e) {
	         }
	      if (con != null)
	         try {
	            con.close();
	         } catch (Exception e) {
	         }
	   }
}
