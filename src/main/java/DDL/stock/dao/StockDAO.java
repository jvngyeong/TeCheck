package DDL.stock.dao;

import DDL.domain.StockDTO;

public class StockDAO extends DataBaseInfo{
	public void stockInsert(StockDTO stockDTO) {
		con = getConnection();
		sql = "insert into stock(trade_date, timestamp, volume, price) values (sysdate, ?, ?, ?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, stockDTO.getTimestamp());
			pstmt.setInt(2, stockDTO.getVolume());
			pstmt.setInt(3, stockDTO.getPrice());
			int i = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			close();
		}
	}
}
