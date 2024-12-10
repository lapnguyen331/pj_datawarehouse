package me.huuthinh.test.module3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MainTest {

	private static Connection stagingConnection;
	private static Connection newConnection;
	private static Connection controlConnection;

	public static void main(String[] args) {
		try {
			connectionToDatabase();
		} catch(Exception ex) {
			ex.printStackTrace();
			sendToLog(EventType.Load, "Loading table failed", Status.fail,"Database");
			//Throw log
			return;
		}
		
		TransformToStaging action1 = new TransformToStaging(stagingConnection);
		Module3Ver2 action2 = new Module3Ver2(stagingConnection,newConnection);
		Aggregation action3 = new Aggregation(newConnection);
		try {
			action1.start();	
		} catch(Exception ex) {
			ex.printStackTrace();
			sendToLog(EventType.Transform, "Loading Staging table failed", Status.fail,"Staging");
			return;
		}
			action2.start();
		
		try {
			action3.start();
		} catch(Exception ex) {
			ex.printStackTrace();
			sendToLog(EventType.Aggregate, "Update Aggregation table failed", Status.fail,"Aggregation");
			return;
		}
		sendToLog(EventType.Load, "Module3 Successful", Status.success,"Module3");
		closeDataBase();
	}
	
	public static void sendToLog(EventType type, String message, Status status, String location) {
		Logger.insertLog(new Logger(type.toString(),message, status.toString(),location),controlConnection);
	}

	private static void closeDataBase() {
		try {
			stagingConnection.close();
			newConnection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private static void connectionToDatabase() throws SQLException {
		String host = "localhost";
	     String port = "3306";
	     String username = "root";
	     String password = "01217065661a100";
	     
	     String dbStaging = "dbstaging";
	     String dbNew = "dbwarehouse";
	     String dbControl = "dbcontrol";
	     
	     
	     String urlDBStaging = "jdbc:mysql://" + host + ":" + port + "/" + dbStaging+"?useUnicode=true&characterEncoding=utf8";
	     String urlDBNew = "jdbc:mysql://" + host + ":" + port + "/" + dbNew+"?useUnicode=true&characterEncoding=utf8";
	     String urlDBControl = "jdbc:mysql://" + host + ":" + port + "/" + dbControl+"?useUnicode=true&characterEncoding=utf8";
	     
	    
	     stagingConnection = DriverManager.getConnection(urlDBStaging, username, password);
	     newConnection = DriverManager.getConnection(urlDBNew, username, password);
	     controlConnection = DriverManager.getConnection(urlDBControl, username, password);
	             
	     
	}
	
	
	
}
