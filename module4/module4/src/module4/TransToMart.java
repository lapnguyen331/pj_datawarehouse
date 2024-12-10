package module4;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dbmart.Fact;

public class TransToMart {
	Connection con_dbwarehouse;
	Connection con_dbmart;
	String url_wh;

	public TransToMart() throws ClassNotFoundException, SQLException, IOException {
	//	Propertiess propertiess=new Propertiess();
//		List<String> list=new ArrayList<String>();
//		Connection connection=null;
//		String className = "com.mysql.cj.jdbc.Driver";
//		Class.forName(className);
//		String sql="SELECT * FROM DBCONTROL;";
//		try {
//			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbcontrol", "root", "");
//			PreparedStatement preparedStatement=connection.prepareStatement(sql);
//			ResultSet resultSet=preparedStatement.executeQuery();
//			while(resultSet.next()) {
//				String url=resultSet.getString("url");
//				String username=resultSet.getString("username");
//				String pass=resultSet.getString("pass");
//				
//			}
//		} catch (Exception e) {
//		
//		}
		

		this.con_dbwarehouse = ConnectionDb.getConnection("db.warehouse.url", "db.warehouse.username",
				"db.warehouse.pass");
//		this.con_dbwarehouse = ConnectionDb.getConnection(propertiess.url_wh, propertiess.username_wh,
//				propertiess.pass_wh);
		this.con_dbmart = ConnectionDb.getConnection("db.mart.url", "db.mart.username", "db.mart.pass");

	}
	public void getProperties() throws ClassNotFoundException {
		
	}

	public List<Fact> extract_transform(Connection connection) {
		SendingMail.sendMail("Thông báo từ hệ thống Warehouse", "Module 4 started");
		System.out.println("bắt đầu");
		List<Fact> facts = new ArrayList<Fact>();
//		String query = "SELECT  p.motobike_id ,m.name, MAX(d.full_date) AS latest_full_date, \r\n"
//				+ "       t.type_name, b.brand_name, pr.price\r\n"
//				+ "FROM factprice p\r\n"
//				+ "JOIN datedim d ON p.date_sk = d.date_sk\r\n"
//				+ "JOIN typedim t ON p.type_id = t.type_id\r\n"
//				+ "JOIN branddim b ON p.brand_id = b.brand_id\r\n"
//				+ "JOIN pricedim pr ON p.price_id = pr.price_id\r\n"
//				+ "JOIN motobikedim m ON p.motobike_id = m.id\r\n"
//				+ "GROUP BY p.motobike_id"
//				+ "ORDER BY p.motobike_id;\r\n"
//				+ ";";
//		
		String query = "SELECT p.motobike_id, MAX(d.full_date) AS latest_full_date " + "FROM factprice p "
				+ "JOIN datedim d ON p.date_sk = d.date_sk " + "GROUP BY p.motobike_id " + "ORDER BY p.motobike_id";

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Fact fact = new Fact();
				int motobikeId = resultSet.getInt("motobike_id");
				Date latestFullDate = resultSet.getDate("latest_full_date");
//				String name = resultSet.getString("name");
//				String typeName = resultSet.getString("type_name");
//				String brandName = resultSet.getString("brand_name");
//			float price_cr = resultSet.getFloat("price");
				fact.setId_vehicle(motobikeId);
//				fact.setName(name);
//				fact.setBrand(brandName);
//				fact.setType(typeName);
//				fact.setPrice_current((double) price_cr);
				fact.setDate(latestFullDate);
				facts.add(fact);
			}

		} catch (SQLException e) {
			Logger.insertLog(new Logger(EventType.Extract.toString(), "Extract_Transform data from Warehouse failed",
					Status.fail.toString(), TransToMart.class.getName()));
			SendingMail.sendMail("Thông báo từ hệ thống Warehouse", "Extract_Transform data from Warehouse failed");
		}
		String query3 = "SELECT f.motobike_id,m.name " + "FROM factprice f "
				+ "JOIN motobikedim m ON f.motobike_id = m.id " + "GROUP BY f.motobike_id";

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query3);

			// Execute query
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				for (Fact fact : facts) {
					if (rs.getInt("motobike_id") == fact.getId_vehicle()) {
						String name = rs.getString("name");
						fact.setName(name);
						break;
					}
				}

			}

		} catch (SQLException e) {
			Logger.insertLog(new Logger(EventType.Extract.toString(), "Extract_Transform data from Warehouse failed",
					Status.fail.toString(), TransToMart.class.getName()));
			SendingMail.sendMail("Thông báo từ hệ thống Warehouse", "Extract_Transform data from Warehouse failed");
		}
		String query4 = "SELECT f.motobike_id,b.brand_name " + "FROM factprice f "
				+ "JOIN branddim b ON f.brand_id = b.brand_id " + "GROUP BY f.motobike_id";

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query4);

			// Execute query
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				for (Fact fact : facts) {
					if (rs.getInt("motobike_id") == fact.getId_vehicle()) {
						String name = rs.getString("brand_name");
						fact.setBrand(name);
						break;
					}
				}

			}

		} catch (SQLException e) {
			Logger.insertLog(new Logger(EventType.Extract.toString(), "Extract_Transform data from Warehouse failed",
					Status.fail.toString(), TransToMart.class.getName()));
			SendingMail.sendMail("Thông báo từ hệ thống Warehouse", "Extract_Transform data from Warehouse failed");
		}
		String query5 = "SELECT f.motobike_id,t.type_name " + "FROM factprice f "
				+ "JOIN typedim t ON f.type_id = t.type_id " + "GROUP BY f.motobike_id";

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query5);

			// Execute query
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				for (Fact fact : facts) {
					if (rs.getInt("motobike_id") == fact.getId_vehicle()) {
						String name = rs.getString("type_name");
						fact.setType(name);
						break;
					}
				}

			}

		} catch (SQLException e) {
			Logger.insertLog(new Logger(EventType.Extract.toString(), "Extract_Transform data from Warehouse failed",
					Status.fail.toString(), TransToMart.class.getName()));
			SendingMail.sendMail("Thông báo từ hệ thống Warehouse", "Extract_Transform data from Warehouse failed");
		}

		String query1 = "SELECT f.motobike_id, p.price,AVG(p.price) AS avg_price, MIN(p.price) AS min_price, MAX(p.price) AS max_price "
				+ "FROM factprice f " + "JOIN PriceDim p ON f.price_id = p.price_id " + "GROUP BY f.motobike_id";

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query1);

			// Execute query
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				for (Fact fact : facts) {
					if (rs.getInt("motobike_id") == fact.getId_vehicle()) {
						double avgPrice = rs.getDouble("avg_price");
						double minPrice = rs.getDouble("min_price");
						double maxPrice = rs.getDouble("max_price");
						Float price = rs.getFloat("price");
						fact.setPrice_current((double) price);
						fact.setPrice_avg(avgPrice);
						fact.setPrice_max(maxPrice);
						fact.setPrice_min(minPrice);
						break;
					}
				}

			}

		} catch (SQLException e) {
			Logger.insertLog(new Logger(EventType.Extract.toString(), "Extract_Transform data from Warehouse failed",
					Status.fail.toString(), TransToMart.class.getName()));
			SendingMail.sendMail("Thông báo từ hệ thống Warehouse", "Extract_Transform data from Warehouse failed");
		}

		return facts;
	}

	public void load(Connection wh, Connection mart) throws SQLException {
		List<Fact> facts = extract_transform(wh);
		if (facts.isEmpty()) {
			Logger.insertLog(new Logger(EventType.Load.toString(), "Loading data to dbMart failed",
					Status.fail.toString(), TransToMart.class.getName()));
			SendingMail.sendMail("Thông báo từ hệ thống Warehouse", "Loading data to dbMart failed");
			return;
		}
		String delete = "DELETE FROM FACT";
		String sql = "INSERT INTO fact (id_vehicle, name, brand, type, price_current, price_min, price_max, price_avg, date)\r\n"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
		try {
		
		try (Statement statement = mart.createStatement()) {
			statement.executeUpdate(delete);
		}
	
			PreparedStatement preparedStatement = mart.prepareStatement(sql);
			for (Fact b : facts) {
				preparedStatement.setInt(1, b.getId_vehicle());
				preparedStatement.setString(2, b.getName());
				preparedStatement.setString(3, b.getBrand());
				preparedStatement.setString(4, b.getType());
				preparedStatement.setDouble(5, b.getPrice_current());
				preparedStatement.setDouble(6, b.getPrice_min());
				preparedStatement.setDouble(7, b.getPrice_max());
				preparedStatement.setDouble(8, b.getPrice_avg());
				preparedStatement.setDate(9, (Date) b.getDate());
				preparedStatement.addBatch();
			}
			preparedStatement.executeBatch();
			Logger.insertLog(new Logger(EventType.Load.toString(), "Successful", Status.success.toString(),
					TransToMart.class.getName()));
			SendingMail.sendMail("Thông báo từ hệ thống Warehouse", "Module 4 successful");
			System.out.println("kêt thúc");
		 } catch (Exception e) {
		        try {
		        	Logger.insertLog(new Logger(EventType.Load.toString(),"Loading data to dbMart failed", Status.fail.toString(),TransToMart.class.getName()));
					SendingMail.sendMail("Thông báo từ hệ thống Warehouse","Loading data to dbMart failed");
		            mart.rollback(); // ROLLBACK nếu có lỗi
		            System.out.println("đã rollback");
		        } catch (Exception rollbackEx) {
		            rollbackEx.printStackTrace();
		        }
		        e.printStackTrace();
		 }
		
		
	}

	public static void main(String[] args)
			throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {

		TransToMart mart = new TransToMart();
		mart.load(mart.con_dbwarehouse, mart.con_dbmart);
		// mart.run(mart.con_dbwarehouse,mart.con_dbmart);
//		System.out.println(mart.con_dbmart);
//		System.out.println(mart.con_dbwarehouse);
//		mart.extract(mart.con_dbwarehouse);
//		for (BrandDimW b : mart.brandDimW_list) {
//			System.out.println(b);
//		}
//		for (DateDimW d : mart.dateDimW_list) {
//			System.out.println(d);
//		}
//		for (MotobikeDimW m : mart.MotobikeDimW_list) {
//			System.out.println(m);
//		}
//		for (PriceDimW p : mart.PriceDimW_list) {
//			System.out.println(p);
//		}
//		for (SourceDimW s : mart.SourceDimW_list) {
//			System.out.println(s);
//		}
//		for (TypeDimW t : mart.TypeDimW_list) {
//			System.out.println(t);
//		}
//		for (FactPriceW f : mart.factPriceW_list) {
//			System.out.println(f);
//		}
//		mart.transform();
//		mart.load(mart.con_dbmart);
//		System.out.println("oke");

	}

}
