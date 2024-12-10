package me.huuthinh.test.module3;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.IsoFields;
import java.util.ArrayList;
import java.util.List;

public class Module3Ver2 {
	
	private static Connection stagingConnection;
	private static Connection newConnection;
	private static String staging = "staging";
	
	Module3Ver2(Connection stagingConnection,Connection newConnection) {
		Module3Ver2.stagingConnection = stagingConnection;
		Module3Ver2.newConnection = newConnection;
	}
	
	
	public void start(){
		try {
			transformToDimTable();//Truyền dữ liệu qua dim table, từ staging table
		} catch(Exception ex) {
			MainTest.sendToLog(EventType.Transform, "Transform to table failed", Status.fail,"DimTable");
		}
		try {

			loadToFactTable();//Truyền dữ liệu qua dim table, từ staging table
		} catch(Exception ex) {
			MainTest.sendToLog(EventType.Load, "Load FactTable table failed", Status.fail,"FactTable");
		}
	}

	private void transformToDimTable() {
		insertToDateDim();
		insertToTypeDim();
		insertToBrandDim();
		insertToSourceDim();
		insertToPriceDim();
		insertToMotobikeDim();
		System.out.println("Hoan thanh doi du lieu qua dimension table");
	}

	private void loadToFactTable() {
		//Tính 
		int countrow = countFromTable(newConnection,"factprice");
		
		
		String query = "SELECT * FROM "+staging;
		try (PreparedStatement pstmt = stagingConnection.prepareStatement(query); ResultSet rs = pstmt.executeQuery()) {
			while(rs.next()) {
				countrow++;
				int[] values = {countrow,getMotobikeID(rs),getPriceDimID(rs),getDateSK(rs),getSourceID(rs),getBrandID(rs),getTypeID(rs)};
				if(this.recordFactTableExists(newConnection, values)) {
					continue;
				}
				String queryToFact = "INSERT INTO factprice(fact_price_id,motobike_id,price_id,date_sk,source_id,brand_id,type_id) VALUES(?,?,?,?,?,?,?);";
				 try (PreparedStatement pstmt2 = newConnection.prepareStatement(queryToFact)) {
					 for(int i = 0;i < values.length;i++) {
						 if(values[i] < 0) {
							 pstmt2.setNull(i+1, java.sql.Types.INTEGER);
						 } else {
							 pstmt2.setInt(i+1, values[i]);
						 }
					 }
					 pstmt2.executeUpdate();
				 }
				
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("Hoan thanh doi du lieu qua fact table");
	}

	private int countFromTable(Connection conn, String table) {
		String querycount = "SELECT COUNT(*) FROM "+table;
		 try (PreparedStatement pstmt = conn.prepareStatement(querycount)) {
			 ResultSet rs =  pstmt.executeQuery();
	            if (rs.next()) {
	                return rs.getInt(1);
	            }
		 } catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	private int getPriceDimID(ResultSet from) {
		String query = "SELECT price_id FROM pricedim WHERE price_range = ? AND ABS(price - ?) < 0.001 LIMIT 1;";
		
        try (PreparedStatement pstmt = newConnection.prepareStatement(query)) {
            pstmt.setString(1, from.getString("price_range")); 
            pstmt.setFloat(2, from.getFloat("price")); 
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("price_id"); 
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }

        return -1;
	}

	private int getTypeID(ResultSet from) {
		String query = "SELECT type_id FROM typedim WHERE type_name = ? LIMIT 1;";

	        try (PreparedStatement pstmt = newConnection.prepareStatement(query)) {
	            pstmt.setString(1, from.getString("type")); 
	            try (ResultSet rs = pstmt.executeQuery()) {
	                if (rs.next()) {
	                    return rs.getInt("type_id"); 
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace(); 
	        }

	        return -1; 
	}

	private int getBrandID(ResultSet from) {
		String query = "SELECT brand_id FROM branddim WHERE brand_name = ? LIMIT 1;";

        try (PreparedStatement pstmt = newConnection.prepareStatement(query)) {
            pstmt.setString(1, from.getString("brand")); 
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("brand_id"); 
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }

        return -1; 
	}

	private int getSourceID(ResultSet from) {
		
		String[] fields = {"source_pid","source_name","source_type","source_url","source_SkuID","description"};
		String check = "";
		for(String s : fields) {
			if(check.equals("")) {
				check += (s+" = ?");
				continue;
			}
			check+= (" AND "+s+" = ?");
		}
	
		String query = "SELECT source_id FROM sourcedim WHERE "+check+" LIMIT 1;";
        try (PreparedStatement pstmt = newConnection.prepareStatement(query)) {
        	for(int i = 0;i < fields.length;i++) {
        		try {
        			if(from.getString(fields[i]) == null) {
        				pstmt.setNull(i+1, java.sql.Types.VARCHAR);
        			} else {
        				pstmt.setString(i+1, from.getString(fields[i]));
        			}
        		} catch(Exception ex) {
        			pstmt.setString(i+1,"");
        			continue;
        		}	
            }
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("source_id"); 
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }

        return -1; 
	}

	private int getDateSK(ResultSet from) {
		String query = "SELECT date_sk FROM datedim WHERE full_date = ? LIMIT 1;";
		
        try (PreparedStatement pstmt = newConnection.prepareStatement(query)) {
        	LocalDate date = from.getDate("create_at").toLocalDate();
            pstmt.setString(1, Date.valueOf(date).toString()); 
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("date_sk"); 
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }

        return -1;
	}

	private int getMotobikeID(ResultSet from) {
		String[] fields = {"model_name","color","name","engine_capacity","engine_type","transmission_type","features","image_url","status"};
		
		String check = "";
		for(String s : fields) {
			if(check.equals("")) {
				check += (s+" = ?");
				continue;
			}
			check+= (" AND "+s+" = ?");
		}
	
		String query = "SELECT id FROM motobikedim WHERE "+check+" LIMIT 1;";
		
        try (PreparedStatement pstmt = newConnection.prepareStatement(query)) {
        	for(int i = 0;i < fields.length;i++) {
        		try {
        			pstmt.setString(i+1, from.getString(fields[i]));
        		} catch(Exception ex) {
        			pstmt.setString(i+1,"");
        			continue;
        		}	
            }
        	
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id"); 
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }

        return -1; 
	}

	private void insertToMotobikeDim() {
		String[] fields = {"model_name","color","name","engine_capacity","engine_type","transmission_type","features","image_url","status","create_at"};
		
		List<String> exists = new ArrayList<>();
		
		for (String column : fields) {
            if (doesColumnExist(stagingConnection,staging, column)) {
            	exists.add(column);
            }
        }
		if(!exists.isEmpty()) {
		String columnList = String.join(", ", exists);
	    String query = "SELECT DISTINCT " + columnList + " FROM "+staging;
	    try (PreparedStatement stmt = stagingConnection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
        	while (rs.next()) {
        		String[] values = new String[fields.length];

        		for (int i = 0;i < fields.length;i++) {
        			try {
        				values[i] = rs.getString(fields[i]);
        			} catch(Exception ex) {
        				values[i] = "";
        			}
        		}
                if(!recordExists(newConnection,"motobikedim",fields, values)) {
                	insertRecord(newConnection,"motobikedim",fields, values);
                }
            }
        
		
	
        } catch(Exception ex) {
        	ex.printStackTrace();
        }
	}
	}

	private void insertToPriceDim() {
		String query = "SELECT DISTINCT price_range, price FROM "+staging;
        try (PreparedStatement stmt = stagingConnection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
        	while (rs.next()) {
                String[] values = {rs.getString("price_range"),""+rs.getFloat("price")};
                String[] field = {"price_range","price"};
                if(!recordPriceDimExists(newConnection,values)) {
                insertRecord(newConnection,"pricedim",field, values);
                }
            }
        } catch(Exception ex) {
        	ex.printStackTrace();
        }
	}

	private void insertToSourceDim() {
		
		String[] fields = {"source_pid","source_name","source_type","source_url","source_SkuID","description"};
		
		List<String> exists = new ArrayList<>();
		
		for (String column : fields) {
            if (doesColumnExist(stagingConnection,staging, column)) {
            	exists.add(column);
            }
        }
		if(!exists.isEmpty()) {
		String columnList = String.join(", ", exists);
	    String query = "SELECT DISTINCT " + columnList + " FROM "+staging+" WHERE source_url IS NOT NULL AND source_url != ''";

		
        try (PreparedStatement stmt = stagingConnection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
        	while (rs.next()) {
        		String[] values = new String[fields.length];

        		for (int i = 0;i < fields.length;i++) {
        			try {
        				values[i] = rs.getString(fields[i]);
        			} catch(Exception ex) {
        				values[i] = "";
        			}
        		}
                if(!recordExists(newConnection,"sourcedim",fields, values)) {
                	insertRecord(newConnection,"sourcedim",fields, values);
                }
            }
        
		
	
        } catch(Exception ex) {
        	ex.printStackTrace();
        }
	}
	}

	private void insertToBrandDim() {
		String query = "SELECT DISTINCT brand FROM "+staging+" WHERE brand IS NOT NULL AND brand != ''";//Thêm where để tránh không ghi giá trị nếu null
        try (PreparedStatement stmt = stagingConnection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
        	while (rs.next()) {
                String brand = rs.getString("brand");
                String[] field = {"brand_name"};
                
                if(!recordExists(newConnection,"branddim",field,  brand)) {
                insertRecord(newConnection,"branddim",field,  brand);
                }
            }
        } catch(Exception ex) {
        	ex.printStackTrace();
        }
	}

	private void insertToTypeDim() {
		String query = "SELECT DISTINCT type FROM "+staging+" WHERE type IS NOT NULL AND type != ''";
		try (PreparedStatement stmt = stagingConnection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
        	while (rs.next()) {
                String type = rs.getString("type");
                String[] field = {"type_name"};
                if(!recordExists(newConnection,"typedim",field, type)) {
                insertRecord(newConnection,"typedim",field, type);
                }
            }
        } catch(Exception ex) {
        	ex.printStackTrace();
        }
	}
	private boolean recordExists(Connection connection,String table ,String[] name_value, String... data) throws SQLException {
		String check = "";
		
		for(String s : name_value) {
			if(check.equals("")) {
				check += (s+" = ?");
				continue;
			}
			check+= (" AND "+s+" = ?");
		}

		String query = "SELECT COUNT(*) FROM "+table+" WHERE "+check+";";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            for(int i = 0;i < data.length;i++) {
            	if(data[i] != null) {
            		stmt.setString(i+1, data[i]);
    			} else {
    				stmt.setNull(i+1, java.sql.Types.VARCHAR);
    			}
            }
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
	}
	private boolean recordPriceDimExists(Connection connection,String... data) throws SQLException {
		String query = "SELECT COUNT(*) FROM pricedim WHERE price_range = ? AND ABS(price - ?) < 0.001";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            for(int i = 0;i < data.length;i++) {
            	stmt.setString(i+1, data[i]);
            }
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
	}
	private boolean recordFactTableExists(Connection connection,int[] data) throws SQLException {
		
		String query = 
				"SELECT COUNT(*) "
				+ "FROM factprice "
				+ "WHERE motobike_id = ? OR (motobike_id IS NULL) "
				+ "AND price_id = ? OR (price_id IS NULL) "
				+ "AND date_sk = ?  OR (date_sk IS NULL) "
				+ "AND source_id = ? OR (source_id IS NULL) "
				+ "AND brand_id = ? OR (brand_id IS NULL) "
				+ "AND type_id = ? OR (type_id IS NULL) ";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            
        	//{countrow,getMotobikeID(rs),getPriceDimID(rs),getDateSK(rs),getSourceID(rs),getBrandID(rs),getTypeID(rs)};
			
        	
        	for(int i = 1;i < data.length;i++) {
        		if(data[i] < 0) {
        			stmt.setNull(i, java.sql.Types.INTEGER);//Thay giá trị null
        		} else {
        			stmt.setString(i, ""+data[i]);//Skip phần id;
        		}
            }
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
	}

	private static void insertRecord(Connection connection,String table ,String[] name_value, String... data) throws SQLException {
		String check = "";
		String values = "";
		for(String s : name_value) {
			if(check.equals("")) {
				check += s;
				values += "?";
				continue;
			} 
			check+= (", "+s);
			values+= (", ?");
		}	
		
		String query = "INSERT INTO "+table+" ("+check+") " +
                       "VALUES "+"("+values+") ";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
        	for(int i = 0;i < data.length;i++) {
            	stmt.setString(i+1, data[i]);
            }
            stmt.executeUpdate();
        }
    }
	
	private void insertToDateDim( ){
		String selectQuery = "SELECT DISTINCT create_at FROM "+staging;
        try (PreparedStatement selectStmt = stagingConnection.prepareStatement(selectQuery);
             ResultSet rs = selectStmt.executeQuery()) {

            String insertQuery = "INSERT INTO dateDim (full_date, day_since_2010, month_since_2010, day_of_week, " +
                    "calendar_month, calendar_year, calendar_year_month, day_of_month, day_of_year, " +
                    "week_of_year_sunday, year_week_sunday, week_sunday_start, " +
                    "week_of_year_monday, year_week_monday, week_monday_start, holiday, day_type) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement insertStmt = newConnection.prepareStatement(insertQuery)) {
                while (rs.next()) {
                    LocalDate date = rs.getDate("create_at").toLocalDate();
                    String querycheck = "SELECT COUNT(*) FROM datedim WHERE full_date = ?;";
                	
                	PreparedStatement checkPstmt = newConnection.prepareStatement(querycheck);
                	checkPstmt.setDate(1, Date.valueOf(date));
                	ResultSet s = checkPstmt.executeQuery();
                	//Kiem tra date co trung hay khong, neu co thi bo qua
                    if(s.next() && s.getInt(1) > 0) {
                    	continue;
                    }
                    // Phân tích ngày
                    LocalDate baseDate = LocalDate.of(2010, 1, 1);
                    int daySince2010 = (int) ChronoUnit.DAYS.between(baseDate, date);
                    int monthSince2010 = (date.getYear() - 2010) * 12 + date.getMonthValue();
                    String dayOfWeek = date.getDayOfWeek().name(); // Ví dụ: MONDAY
                    String calendarMonth = date.getMonth().name(); // Ví dụ: JANUARY
                    int calendarYear = date.getYear();
                    String calendarYearMonth = String.format("%04d%02d", calendarYear, date.getMonthValue());
                    int dayOfMonth = date.getDayOfMonth();
                    int dayOfYear = date.getDayOfYear();
                    int weekOfYearSunday = date.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
                    String yearWeekSunday = String.format("%04d%02d", calendarYear, weekOfYearSunday);
                    LocalDate weekSundayStart = date.with(DayOfWeek.SUNDAY);
                    int weekOfYearMonday = date.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
                    String yearWeekMonday = String.format("%04d%02d", calendarYear, weekOfYearMonday);
                    LocalDate weekMondayStart = date.with(DayOfWeek.MONDAY);
                    String holiday = (dayOfWeek.equals("SATURDAY") || dayOfWeek.equals("SUNDAY")) ? "Weekend" : "Weekday";
                    String dayType = holiday.equals("Weekend") ? "Holiday" : "Workday";

                    // Gán giá trị vào câu lệnh chèn
                    insertStmt.setDate(1, Date.valueOf(date));
                    insertStmt.setInt(2, daySince2010);
                    insertStmt.setInt(3, monthSince2010);
                    insertStmt.setString(4, dayOfWeek);
                    insertStmt.setString(5, calendarMonth);
                    insertStmt.setInt(6, calendarYear);
                    insertStmt.setString(7, calendarYearMonth);
                    insertStmt.setInt(8, dayOfMonth);
                    insertStmt.setInt(9, dayOfYear);
                    insertStmt.setInt(10, weekOfYearSunday);
                    insertStmt.setString(11, yearWeekSunday);
                    insertStmt.setDate(12, Date.valueOf(weekSundayStart));
                    insertStmt.setInt(13, weekOfYearMonday);
                    insertStmt.setString(14, yearWeekMonday);
                    insertStmt.setDate(15, Date.valueOf(weekMondayStart));
                    insertStmt.setString(16, holiday);
                    insertStmt.setString(17, dayType);

                    // Thực thi câu lệnh chèn
                    insertStmt.executeUpdate();
                }
                
            }
        }
     catch (SQLException e) {
        e.printStackTrace();
    }
	}

	
	public static boolean doesColumnExist(Connection conn,String tableName, String columnName) {
        String checkQuery = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS " +
                            "WHERE TABLE_NAME = ? AND COLUMN_NAME = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(checkQuery)) {

            pstmt.setString(1, tableName);
            pstmt.setString(2, columnName);

            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
	
	
}
