package me.huuthinh.test.module3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Aggregation {

	private Connection newConnection;
	
	public Aggregation(Connection newConnection) {
		this.newConnection = newConnection;
	}
	
	public void start() throws SQLException {
		updateAggregationTables();
		
	}
	  public void updateAggregationTables() throws SQLException {
		  
		  String updatePriceTrendAggregation = "INSERT INTO pricetrendaggregation (date_sk, average_price, min_price, max_price) SELECT date_sk, AVG(pricedim.price), MIN(pricedim.price), MAX(pricedim.price) FROM factprice JOIN pricedim ON factprice.price_id = pricedim.price_id GROUP BY date_sk ON DUPLICATE KEY UPDATE average_price = VALUES(average_price), min_price = VALUES(min_price), max_price = VALUES(max_price);";
		  
		  String updateAveragePriceBySource = "INSERT INTO averagepricebysource (source_id, average_price) SELECT source_id, AVG(pricedim.price) FROM factprice JOIN pricedim ON factprice.price_id = pricedim.price_id WHERE source_id IS NOT NULL GROUP BY source_id ON DUPLICATE KEY UPDATE average_price = VALUES(average_price);";
				  String updateAveragePriceByBrand = "INSERT INTO averagepricebybrand (brand_id, average_price) SELECT brand_id, AVG(pricedim.price) FROM factprice JOIN pricedim ON factprice.price_id = pricedim.price_id WHERE brand_id IS NOT NULL GROUP BY brand_id ON DUPLICATE KEY UPDATE average_price = VALUES(average_price);";
	            try (PreparedStatement stmt = newConnection.prepareStatement(updateAveragePriceBySource)) {
	            	int rowsInserted =  stmt.executeUpdate();
	                int rowsInserted2 = stmt.getUpdateCount();
	                System.out.println("AveragePriceBySource cap nhat voi " + rowsInserted + " cot duoc chen.");
	                System.out.println("AveragePriceBySource cap nhat voi " + rowsInserted2 + " cot duoc chen.");
	            }

	            try (PreparedStatement stmt = newConnection.prepareStatement(updatePriceTrendAggregation)) {
	                stmt.executeUpdate();
	                int rowsInserted = stmt.getUpdateCount();
	                System.out.println("PriceTrendAggregation cap nhat voi " + rowsInserted + " cot duoc chen.");
	            }


	            try (PreparedStatement stmt = newConnection.prepareStatement(updateAveragePriceByBrand)) {
	                stmt.executeUpdate();
	                int rowsInserted = stmt.getUpdateCount();
	                System.out.println("AveragePriceByBrand cap nhat voi " + rowsInserted + " cot duoc chen.");
	            }
	        
	    }

	
}
