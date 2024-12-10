package me.huuthinh.test.module3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

public class TransformToStaging {

	
	private Connection stagingConnection;
	
	public TransformToStaging(Connection stagingConnection) {
		this.stagingConnection = stagingConnection;
	}
	
	public void start() {
		transformToStaging();
	}
	
	private void transformToStaging(){
		try {
			stagingConnection.setAutoCommit(false);
			clearDataFromStaging();//Xóa dữ liệu cũ từ staging
            String selectQuery = "SELECT * FROM staging_temp";
            try (PreparedStatement selectStmt = stagingConnection.prepareStatement(selectQuery);
                 ResultSet resultSet = selectStmt.executeQuery()) {

                String insertQuery = "INSERT INTO staging(" +
                        "model_name, type, color, price, price_range, brand, version, name, " +
                        "engine_capacity, engine_type, transmission_type, features, image_url, " +
                        "source_url, source_pid, source_SkuId, source_name, status, create_at) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                try (PreparedStatement insertStmt = stagingConnection.prepareStatement(insertQuery)) {
                    while (resultSet.next()) {
                        
                        insertStmt.setString(1, resultSet.getString("model_name"));
                        insertStmt.setString(2, resultSet.getString("type"));
                        insertStmt.setString(3, resultSet.getString("color"));

                       
                        String priceText = resultSet.getString("price");
                        String price = null;
                        if (priceText != null && !priceText.isEmpty()) {
                            price = priceText.replaceAll("[^0-9,]", "");//Loại bỏ các kí tự không phải số
                            if (price.contains(",")) {
                                if (price.lastIndexOf(",") > price.lastIndexOf(".") || !price.contains(".")) {
                                    price = price.replace(",", ".");
                                } else {
                                    price = price.replace(",", "");
                                }
                            }
                        }
                        
                        insertStmt.setFloat(4, price != null ? Float.parseFloat(price.replace(".", "")) : null);

                        insertStmt.setString(5, resultSet.getString("price_range"));
                        insertStmt.setString(6, resultSet.getString("brand"));
                        insertStmt.setString(7, resultSet.getString("version"));
                        insertStmt.setString(8, resultSet.getString("name"));
                        insertStmt.setString(9, resultSet.getString("engine_capacity"));
                        insertStmt.setString(10, resultSet.getString("engine_type"));
                        insertStmt.setString(11, resultSet.getString("transmission_type"));
                        insertStmt.setString(12, resultSet.getString("features"));
                        insertStmt.setString(13, resultSet.getString("image_url"));
                        insertStmt.setString(14, resultSet.getString("source_url"));

                        
                        String sourcePidText = resultSet.getString("source_pid");
                        Integer sourcePid = (sourcePidText != null && !sourcePidText.trim().equals("")) ? Integer.parseInt(sourcePidText) : 0;
                        insertStmt.setObject(15, sourcePid);//Cho giá trị 0 nếu không có source_pid

                        insertStmt.setString(16, resultSet.getString("source_SkuId"));
                        insertStmt.setString(17, resultSet.getString("source_name"));
                        insertStmt.setString(18, resultSet.getString("status"));

                        
                        String createAtText = resultSet.getString("create_at");
                        Timestamp createAt = createAtText != null ? Timestamp.valueOf(createAtText) : null;
                        insertStmt.setObject(19, createAt);

                        
                        insertStmt.addBatch();
                    }
                   
                    insertStmt.executeBatch();
                }
            }
            stagingConnection.commit();
            System.out.println("Hoan thanh cap nhat du lieu tu staging_temp qua staging");
		} catch(Exception ex) {
            	ex.printStackTrace();
        }
	}

	private void clearDataFromStaging() {
		try {
            //Xóa dữ liệu từ staging
            String selectQuery = "DELETE FROM staging";
            try (PreparedStatement selectStmt = stagingConnection.prepareStatement(selectQuery);){
            	selectStmt.executeUpdate();
            }
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
}
