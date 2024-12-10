package me.huuthinh.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Module2 {

    public static void loadData(String csvFilePath) {
    	String host = "localhost";
	    String port = "3306";
	    String username = "root";
	    String password = "01217065661a100"; 
	    String dbStaging = "dbStaging";
    	String urlDBStaging = "jdbc:mysql://" + host + ":" + port + "/" + dbStaging+"?allowLoadLocalInfile=true&useUnicode=true&characterEncoding=utf8";
	    String loadQuery = "LOAD DATA LOCAL INFILE ? INTO TABLE staging_temp FIELDS TERMINATED BY ',' ENCLOSED BY '\"' LINES TERMINATED BY '\\n' IGNORE 1 ROWS;";
	    	
        try (Connection conn = DriverManager.getConnection(urlDBStaging, username, password);
        		 PreparedStatement pstmt = conn.prepareStatement(loadQuery)) {

        	pstmt.setString(1, csvFilePath);
        	pstmt.execute();
            System.out.println("Data loaded successfully from " + csvFilePath);

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String csvFilePath = "C:/Users/Thinh/Tai Lieu HTSV/DataWareHouse/laz_motobike_20241205_130314 (1).csv"; // Thay thế đường dẫn thực tế
        loadData(csvFilePath);
    }
}

