package module4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Propertiess {
	List<String> list=new ArrayList<String>();
	 String name_wh = "";
	 String url_wh = "";
	 String username_wh = "";
	 String pass_wh ="";
	 String name_m = "";
	 String url_m = "";
	 String username_m = "";
	 String pass_m = "";
	public Propertiess() throws ClassNotFoundException {
		list=loadProperties();
		this.url_wh=list.get(0);
		this.username_wh=list.get(1);
		this.pass_wh=list.get(2);
		this.url_m=list.get(3);
		this.username_m=list.get(4);
		this.pass_m=list.get(5);
		
		
	}

	public static List<String> loadProperties() throws ClassNotFoundException {
		List<String> list=new ArrayList<String>();
		Connection connection;
		String className = "com.mysql.cj.jdbc.Driver";
		Class.forName(className);
		String sql="SELECT * FROM DBCONTROL;";
		try {
			connection = ConnectionDb.getConnection("db.control.url", "db.control.username", "db.control.pass");
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				String url=resultSet.getString("url");
				String username=resultSet.getString("username");
				String pass=resultSet.getString("pass");
				list.add(url);
				list.add(username);
				list.add(pass);
				
			}
		} catch (Exception e) {
		
		}
		return list;
				
	}

}
