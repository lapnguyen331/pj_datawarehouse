package module4;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionDb {
	public static Properties loadConfig() throws IOException {
		  Properties properties = new Properties();
		  try (FileInputStream input = new FileInputStream("src\\module4\\config_4.properties")) {
			  properties.load(input);
		  }catch (Exception e) {
			Logger.insertLog(new Logger(EventType.Load.toString(),"Loading file config4.properties failed", Status.fail.toString(),ConnectionDb.class.getName()));
			SendingMail.sendMail("Thông báo từ hệ thống Warehouse","Loading file config4.properties failed");
		}
	return properties;
}
	
	
	public static Connection getConnection(String url,String userName,String pass) throws ClassNotFoundException, SQLException, IOException {
		Connection connection=null;
		String className = "com.mysql.cj.jdbc.Driver";
		Class.forName(className);
		try {
			connection = DriverManager.getConnection(loadConfig().getProperty(url), loadConfig().getProperty(userName), loadConfig().getProperty(pass));
		} catch (Exception e) {
			switch (url) {
			case "db.warehouse.url": {
				Logger.insertLog(new Logger(EventType.Load.toString(),"Connecting dbWareHouse failed", Status.fail.toString(),TransToMart.class.getName()));
				SendingMail.sendMail("Thông báo từ hệ thống Warehouse","Connecting dbWareHouse failed");
				break;
			}
			case "db.mart.url": {
				Logger.insertLog(new Logger(EventType.Load.toString(),"Connecting dbMart failed", Status.fail.toString(),TransToMart.class.getName()));
				SendingMail.sendMail("Thông báo từ hệ thống Warehouse","Connecting dbMart failed");
				break;
			}
//			case "db.control.url": {
//				Logger.insertLog(new Logger(EventType.Load.toString(),"Connecting dbWareHouse failed", Status.fail.toString(),TransToMart.class.getName()));
//				SendingMail.sendMail("Thông báo từ hệ thống Warehouse","Connecting dbWareHouse failed");
//				break;
//			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + url);
				
			}
		}
		
		return connection;
	}
	public static void main(String[] args) {
		Logger.insertLog(new Logger(EventType.Load.toString(),"Loading file config4.properties failed", Status.fail.toString(),ConnectionDb.class.getName()));

	}

}
