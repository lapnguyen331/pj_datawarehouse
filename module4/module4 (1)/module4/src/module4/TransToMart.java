package module4;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import dbmart.BrandDim;
import dbmart.DateDim;
import dbmart.MotobikeDim;
import dbmart.PriceDim;
import dbmart.PriceFact;
import dbmart.SourceDim;
import dbmart.TypeDim;
import dbwarehouse.BrandDimW;
import dbwarehouse.DateDimW;
import dbwarehouse.FactPriceW;
import dbwarehouse.MotobikeDimW;
import dbwarehouse.PriceDimW;
import dbwarehouse.SourceDimW;
import dbwarehouse.TypeDimW;

public class TransToMart {
	Connection con_dbwarehouse;
	Connection con_dbmart;
	static List<BrandDimW> brandDimW_list;
	static List<DateDimW> dateDimW_list;
	static List<FactPriceW> factPriceW_list;
	static List<MotobikeDimW> MotobikeDimW_list;
	static List<PriceDimW> PriceDimW_list;
	static List<SourceDimW> SourceDimW_list;
	static List<TypeDimW> TypeDimW_list;
	//
	static List<BrandDim> brandDim_list;
	static List<DateDim> dateDim_list;
	static List<PriceFact> factPrice_list;
	static List<MotobikeDim> MotobikeDim_list;
	static List<PriceDim> PriceDim_list;
	static List<SourceDim> SourceDim_list;
	static List<TypeDim> TypeDim_list;

	public TransToMart() throws ClassNotFoundException, SQLException {
		brandDimW_list = new ArrayList<BrandDimW>();
		dateDimW_list = new ArrayList<DateDimW>();
		factPriceW_list = new ArrayList<FactPriceW>();
		MotobikeDimW_list = new ArrayList<MotobikeDimW>();
		PriceDimW_list = new ArrayList<PriceDimW>();
		SourceDimW_list = new ArrayList<SourceDimW>();
		TypeDimW_list = new ArrayList<TypeDimW>();
		//
		brandDim_list = new ArrayList<BrandDim>();
		dateDim_list = new ArrayList<DateDim>();
		factPrice_list = new ArrayList<PriceFact>();
		MotobikeDim_list = new ArrayList<MotobikeDim>();
		PriceDim_list = new ArrayList<PriceDim>();
		SourceDim_list = new ArrayList<SourceDim>();
		TypeDim_list = new ArrayList<TypeDim>();
		String className = "com.mysql.cj.jdbc.Driver";
		String dbWH_name = "dbwarehouse";
		String dbMart_name = "dbmart";
		String serverName = "localhost";
		String port = "3306";
		String user = "root";
		String pass = "";
		Class.forName(className);
		String url_wh = "jdbc:mysql://" + serverName + ":" + port + "/" + dbWH_name;
		String url_mart = "jdbc:mysql://" + serverName + ":" + port + "/" + dbMart_name;
		this.con_dbwarehouse = DriverManager.getConnection(url_wh, user, pass);
		this.con_dbmart = DriverManager.getConnection(url_mart, user, pass);

	}

	public void extract(Connection connection) {

		String sql_brand = "SELECT * FROM BRANDDIM";
		String sql_date = "SELECT * FROM DATEDIM";
		String sql_factprice = "SELECT * FROM FACTPRICE";
		String sql_motobike = "SELECT * FROM MOTOBIKEDIM";
		String sql_price = "SELECT * FROM PRICEDIM";
		String sql_source = "SELECT * FROM SOURCEDIM";
		String sql_type = "SELECT * FROM TYPEDIM";
		try {
			PreparedStatement preparedStatement_brand = connection.prepareStatement(sql_brand);
			PreparedStatement preparedStatement_date = connection.prepareStatement(sql_date);
			PreparedStatement preparedStatement_factprice = connection.prepareStatement(sql_factprice);
			PreparedStatement preparedStatement_motobike = connection.prepareStatement(sql_motobike);
			PreparedStatement preparedStatement_price = connection.prepareStatement(sql_price);
			PreparedStatement preparedStatement_source = connection.prepareStatement(sql_source);
			PreparedStatement preparedStatement_type = connection.prepareStatement(sql_type);
			ResultSet resultSet_brand = preparedStatement_brand.executeQuery();
			ResultSet resultSet_date = preparedStatement_date.executeQuery();
			ResultSet resultSet_factprice = preparedStatement_factprice.executeQuery();
			ResultSet resultSet_motobike = preparedStatement_motobike.executeQuery();
			ResultSet resultSet_price = preparedStatement_price.executeQuery();
			ResultSet resultSet_source = preparedStatement_source.executeQuery();
			ResultSet resultSet_type = preparedStatement_type.executeQuery();

			while (resultSet_brand.next()) {

				int id_brand = resultSet_brand.getInt("brand_id");
				String name_brand = resultSet_brand.getString("brand_name");
				BrandDimW brandDimW = new BrandDimW();
				brandDimW.setId(id_brand);
				brandDimW.setName(name_brand);
				brandDimW_list.add(brandDimW);
			}
			while (resultSet_date.next()) {
				int date_sk = resultSet_date.getInt("date_sk");
				Date full_date = resultSet_date.getDate("full_date");
				int day_since_2010 = resultSet_date.getInt("day_since_2010");
				int month_since_2010 = resultSet_date.getInt("month_since_2010");
				String day_of_week = resultSet_date.getString("day_of_week");
				String calendar_month = resultSet_date.getString("calendar_month");
				int calendar_year = resultSet_date.getInt("calendar_year");
				String calendar_year_month = resultSet_date.getString("calendar_year_month");
				int day_of_month = resultSet_date.getInt("day_of_month");
				int day_of_year = resultSet_date.getInt("day_of_year");
				int week_of_year_sunday = resultSet_date.getInt("week_of_year_sunday");
				String year_week_sunday = resultSet_date.getString("year_week_sunday");
				Date week_sunday_start = resultSet_date.getDate("week_sunday_start");
				int week_of_year_monday = resultSet_date.getInt("week_of_year_monday");
				String year_week_monday = resultSet_date.getString("year_week_monday");
				Date week_monday_start = resultSet_date.getDate("week_monday_start");
				String holiday = resultSet_date.getString("holiday");
				String day_type = resultSet_date.getString("day_type");
				DateDimW dateDimW = new DateDimW();
				dateDimW.setDate_sk(date_sk);
				dateDimW.setFull_date(full_date);
				dateDimW.setDay_since_2010(day_since_2010);
				dateDimW.setMonth_since_2010(month_since_2010);
				dateDimW.setDay_of_week(day_of_week);
				dateDimW.setCalendar_month(calendar_month);
				dateDimW.setCalendar_year(calendar_year);
				dateDimW.setDay_of_month(day_of_month);
				dateDimW.setDay_of_year(day_of_year);
				dateDimW.setWeek_of_year_sunday(week_of_year_sunday);
				dateDimW.setWeek_sunday_start(week_sunday_start);
				dateDimW.setWeek_of_year_monday(week_of_year_monday);
				dateDimW.setWeek_monday_start(week_monday_start);
				dateDimW.setHoliday(holiday);
				dateDimW.setDay_type(day_type);
				dateDimW_list.add(dateDimW);
			}
			while (resultSet_motobike.next()) {
				int id_motobike = resultSet_motobike.getInt("id");
				String name = resultSet_motobike.getString("name");
				String color = resultSet_motobike.getString("color");
				String engine_capacity = resultSet_motobike.getString("engine_capacity");
				String features = resultSet_motobike.getString("features");
				String image_url = resultSet_motobike.getString("image_url");
				String status = resultSet_motobike.getString("status");
				Timestamp create_at = resultSet_motobike.getTimestamp("create_at");
				MotobikeDimW motobikeDimW = new MotobikeDimW();
				motobikeDimW.setId(id_motobike);
				motobikeDimW.setName(name);
				motobikeDimW.setColor(color);
				motobikeDimW.setEngine_capacity(engine_capacity);
				motobikeDimW.setFeatures(features);
				motobikeDimW.setImage_url(image_url);
				motobikeDimW.setStatus(status);
				motobikeDimW.setCreate_at(create_at);
				MotobikeDimW_list.add(motobikeDimW);
			}
			while (resultSet_price.next()) {
				int price_id = resultSet_price.getInt("price_id");
				String price_range = resultSet_price.getString("price_range");
				float price = resultSet_price.getFloat("price");
				PriceDimW priceDimW = new PriceDimW();
				priceDimW.setPrice_id(price_id);
				priceDimW.setPrice_range(price_range);
				priceDimW.setPrice(price);
				PriceDimW_list.add(priceDimW);
			}
			while (resultSet_source.next()) {
				int source_id = resultSet_source.getInt("source_id");
				String source_name = resultSet_source.getString("source_name");
				String source_url = resultSet_source.getString("source_url");
				SourceDimW sourceDimW = new SourceDimW();
				sourceDimW.setSource_id(source_id);
				sourceDimW.setSource_name(source_name);
				sourceDimW.setSource_url(source_url);
				SourceDimW_list.add(sourceDimW);
			}
			while (resultSet_type.next()) {
				int type_id = resultSet_type.getInt("type_id");
				String type_name = resultSet_type.getString("type_name");
				TypeDimW typeDimW = new TypeDimW();
				typeDimW.setType_id(type_id);
				typeDimW.setType_name(type_name);
				TypeDimW_list.add(typeDimW);
			}
			while (resultSet_factprice.next()) {
				int fact_price_id = resultSet_factprice.getInt("fact_price_id");
				int motobike_id = resultSet_factprice.getInt("motobike_id");
				int price_id = resultSet_factprice.getInt("price_id");
				int date_sk = resultSet_factprice.getInt("date_sk");
				int source_id = resultSet_factprice.getInt("source_id");
				int brand_id = resultSet_factprice.getInt("brand_id");
				int type_id = resultSet_factprice.getInt("type_id");
				float price_value = resultSet_factprice.getFloat("price_value");
				FactPriceW factPriceW = new FactPriceW();
				factPriceW.setFact_price_id(fact_price_id);
				factPriceW.setMotobike_id(motobike_id);
				factPriceW.setPrice_id(price_id);
				factPriceW.setDate_sk(date_sk);
				factPriceW.setSource_id(source_id);
				factPriceW.setBrand_id(brand_id);
				factPriceW.setType_id(type_id);
				factPriceW.setPrice_value(price_value);
				factPriceW_list.add(factPriceW);
			}
		} catch (SQLException e) {
			System.out.println("brand");
			e.printStackTrace();
		}

	}

	public void transform() {
		for (BrandDimW b : this.brandDimW_list) {
			int id = b.getId();
			String name = b.getName();
			BrandDim brandDim = new BrandDim();
			brandDim.setId(id);
			brandDim.setName(name);
			brandDim_list.add(brandDim);
		}
		for (DateDimW d : dateDimW_list) {
			DateDim dateDim = new DateDim();
			dateDim.setDate_sk(d.getDate_sk());
			dateDim.setFull_date(d.getFull_date());
			dateDim.setDay_since_2010(d.getDay_since_2010());
			dateDim.setMonth_since_2010(d.getMonth_since_2010());
			dateDim.setDay_of_week(d.getDay_of_week());
			dateDim.setCalendar_month(d.getCalendar_month());
			dateDim.setCalendar_year(d.getCalendar_year());
			dateDim.setDay_of_month(d.getDay_of_month());
			dateDim.setDay_of_year(d.getDay_of_year());
			dateDim.setWeek_of_year_sunday(d.getWeek_of_year_sunday());
			dateDim.setWeek_sunday_start(d.getWeek_sunday_start());
			dateDim.setWeek_of_year_monday(d.getWeek_of_year_monday());
			dateDim.setWeek_monday_start(d.getWeek_monday_start());
			int holiday;
			if (d.getHoliday().equalsIgnoreCase("weekday")) {
				holiday = 0;
			} else {
				holiday = 1;
			}
			dateDim.setHoliday(holiday);
			int day_type;
			if (d.getDay_type().equalsIgnoreCase("weekend")) {
				day_type = 0;
			} else {
				day_type = 1;
			}
			dateDim.setDay_type(day_type);
			dateDim_list.add(dateDim);
		}
		for (MotobikeDimW m : MotobikeDimW_list) {
			MotobikeDim motobikeDim = new MotobikeDim();
			motobikeDim.setId(m.getId());
			motobikeDim.setName(m.getName());
			motobikeDim.setColor(m.getColor());
			motobikeDim.setEngine_capacity(m.getEngine_capacity());
			motobikeDim.setImage_url(m.getImage_url());
			motobikeDim.setStatus(m.getStatus());
			motobikeDim.setCreate_at(m.getCreate_at());
			MotobikeDim_list.add(motobikeDim);
		}
		for (PriceDimW p : PriceDimW_list) {
			PriceDim priceDim = new PriceDim();
			priceDim.setPrice_id(p.getPrice_id());
			priceDim.setZone(p.getPrice_range());
			double price = (double) p.getPrice();
			priceDim.setPrice(price);
			PriceDim_list.add(priceDim);
		}
		for (SourceDimW s : SourceDimW_list) {
			SourceDim sourceDim = new SourceDim();
			sourceDim.setSource_id(s.getSource_id());
			sourceDim.setSource_name(s.getSource_name());
			sourceDim.setSource_url(s.getSource_url());
			SourceDim_list.add(sourceDim);
		}
		for (TypeDimW t : TypeDimW_list) {
			TypeDim typeDim = new TypeDim();
			typeDim.setType_id(t.getType_id());
			typeDim.setType_name(t.getType_name());
			TypeDim_list.add(typeDim);
		}
		for (FactPriceW f : factPriceW_list) {

		}

	}

	public void load(Connection connection) {
	    String delete_brand = "DELETE FROM BRANDDIM";
	    String delete_date = "DELETE FROM DATEDIM";
	    String delete_motobike = "DELETE FROM MOTOBIKEDIM";
	    String delete_price = "DELETE FROM PRICEDIM";
	    String delete_priceagg = "DELETE FROM PRICEAGGRE";
	    String delete_pricefact = "DELETE FROM PRICEFACT";
	    String delete_source = "DELETE FROM SOURCEDIM";
	    String delete_type = "DELETE FROM TYPEDIM";
	    
	    String sql_brand = "INSERT INTO BRANDDIM (brand_id,brand_name) VALUES (?, ?);";
	    String sql_date = "INSERT INTO DATEDIM (date_sk, full_date, day_since_2010, month_since_2010, day_of_week, calendar_month, calendar_year, day_of_month, day_of_year, week_of_year_sunday, week_sunday_start, week_of_year_monday, week_monday_start, holiday, day_type) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?);";
	    String sql_motobike = "INSERT INTO MOTOBIKEDIM (id, name, color, engine_capacity, image_url, status) VALUES (?, ?, ?, ?, ?, ?);";
	    String sql_price = "INSERT INTO PRICEDIM (price_id, zone, price) VALUES (?, ?, ?);";
	    String sql_priceagg = "INSERT INTO PRICEAGGRE (date_sk, average_price, min_price,max_price) VALUES (?, ?, ?,?);";
	    String sql_pricefact = "INSERT INTO PRICEFACT (fact_price_id,motobike_id,price_id,source_id,brand_id,type_id,price_value,date_sk) VALUES (?, ?, ?,?,?,?,?,?);";
	    String sql_source = "INSERT INTO SOURCEDIM (source_id, source_name, source_url) VALUES (?, ?, ?);";
	    String sql_type = "INSERT INTO TYPEDIM (type_id, type_name) VALUES (?, ?);";
	    
	    try {
	        connection.setAutoCommit(false); 
	        
	        try (Statement statement = connection.createStatement()) {
	            statement.executeUpdate(delete_brand);
	            statement.executeUpdate(delete_date);
	            statement.executeUpdate(delete_motobike);
	            statement.executeUpdate(delete_price);
	            statement.executeUpdate(delete_priceagg);
	            statement.executeUpdate(delete_pricefact);
	            statement.executeUpdate(delete_source);
	            statement.executeUpdate(delete_type);
	        }

	        PreparedStatement preparedStatement_brand = connection.prepareStatement(sql_brand);
	        PreparedStatement preparedStatement_date = connection.prepareStatement(sql_date);
	        PreparedStatement preparedStatement_motobike = connection.prepareStatement(sql_motobike);
	        PreparedStatement preparedStatement_price = connection.prepareStatement(sql_price);
	        PreparedStatement preparedStatement_priceagg = connection.prepareStatement(sql_priceagg);
	        PreparedStatement preparedStatement_pricefact = connection.prepareStatement(sql_pricefact);
	        PreparedStatement preparedStatement_source = connection.prepareStatement(sql_source);
	        PreparedStatement preparedStatement_type = connection.prepareStatement(sql_type);

	        for (BrandDim b : brandDim_list) {
	            preparedStatement_brand.setInt(1, b.getId());
	            preparedStatement_brand.setString(2, b.getName());
	            preparedStatement_brand.addBatch();
	        }
	        preparedStatement_brand.executeBatch();

	        for (DateDim d : dateDim_list) {
	            preparedStatement_date.setInt(1, d.getDate_sk());
	            preparedStatement_date.setDate(2, d.getFull_date());
	            preparedStatement_date.setInt(3, d.getDay_since_2010());
	            preparedStatement_date.setInt(4, d.getMonth_since_2010());
	            preparedStatement_date.setString(5, d.getDay_of_week());
	            preparedStatement_date.setString(6, d.getCalendar_month());
	            System.out.println(d.getCalendar_month());
	            preparedStatement_date.setInt(7, d.getCalendar_year());
	            preparedStatement_date.setInt(8, d.getDay_of_month());
	            preparedStatement_date.setInt(9, d.getDay_of_year());
	            preparedStatement_date.setInt(10, d.getWeek_of_year_sunday());
	            preparedStatement_date.setDate(11, d.getWeek_sunday_start());
	            preparedStatement_date.setInt(12, d.getWeek_of_year_monday());
	            preparedStatement_date.setDate(13, d.getWeek_monday_start());
	            preparedStatement_date.setInt(14, d.getHoliday());
	            preparedStatement_date.setInt(15, d.getDay_type());
	            preparedStatement_date.addBatch();
	        }
	        preparedStatement_date.executeBatch();

	        for (MotobikeDim m : MotobikeDim_list) {
	            preparedStatement_motobike.setInt(1, m.getId());
	            preparedStatement_motobike.setString(2, m.getName());
	            preparedStatement_motobike.setString(3, m.getColor());
	            preparedStatement_motobike.setString(4, m.getEngine_capacity());
	            preparedStatement_motobike.setString(5, m.getImage_url());
	            preparedStatement_motobike.setString(6, m.getStatus());
	            //preparedStatement_motobike.setTimestamp(7, m.getCreate_at());
	            preparedStatement_motobike.addBatch();
	        }
	        preparedStatement_motobike.executeBatch();

	        for (PriceDim p : PriceDim_list) {
	            preparedStatement_price.setInt(1, p.getPrice_id());
	            preparedStatement_price.setString(2, p.getZone());
	            preparedStatement_price.setDouble(3, p.getPrice());
	            preparedStatement_price.addBatch();
	        }
	        preparedStatement_price.executeBatch();
	        for (SourceDim s : SourceDim_list) {
	            preparedStatement_source.setInt(1, s.getSource_id());
	            preparedStatement_source.setString(2, s.getSource_name());
	            preparedStatement_source.setString(3, s.getSource_url());
	            preparedStatement_source.addBatch();
	        }
	        preparedStatement_source.executeBatch();
	        for (TypeDim t : TypeDim_list) {
	            preparedStatement_type.setInt(1, t.getType_id());
	            preparedStatement_type.setString(2, t.getType_name());
	            preparedStatement_type.addBatch();
	        }
	        preparedStatement_type.executeBatch();
	        for (FactPriceW f : factPriceW_list) {
	            preparedStatement_pricefact.setInt(1, f.getFact_price_id());
	            preparedStatement_pricefact.setInt(2, f.getMotobike_id());
	            preparedStatement_pricefact.setInt(3, f.getPrice_id());
	            preparedStatement_pricefact.setInt(4, f.getSource_id());
	            preparedStatement_pricefact.setInt(5, f.getBrand_id());
	            preparedStatement_pricefact.setInt(6, f.getType_id());
	            preparedStatement_pricefact.setDouble(7, (double)f.getPrice_value());
	            preparedStatement_pricefact.setInt(8, f.getDate_sk());

	            preparedStatement_pricefact.addBatch();
	        }
	        preparedStatement_pricefact.executeBatch();

	        connection.commit();
	        System.out.println("load thành công");
	    } catch (Exception e) {
	        try {
	        	System.out.println("đã rollback");
	            connection.rollback(); // ROLLBACK nếu có lỗi
	        } catch (Exception rollbackEx) {
	            rollbackEx.printStackTrace();
	        }
	        e.printStackTrace();
	    }
	}


	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		TransToMart mart = new TransToMart();
		System.out.println(mart.con_dbmart);
		System.out.println(mart.con_dbwarehouse);
		mart.extract(mart.con_dbwarehouse);
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
		mart.transform();
		mart.load(mart.con_dbmart);
		System.out.println("oke");

	}

}
