package module4;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Logger {
	private String event_name,event_type,message,status,location,create_by;

	public Logger( String event_type, String message, String status, String location) {
		super();
		this.event_name = "Module4_From dbWareHouse To dbMart";
		this.event_type = event_type;
		this.message = message;
		this.status = status;
		this.location = location;
		this.create_by = "Ngoc Thao";
	}

	public Logger() {
		super();
	}

	public String getEvent_name() {
		return event_name;
	}

	public void setEvent_name(String event_name) {
		this.event_name = event_name;
	}

	public String getEvent_type() {
		return event_type;
	}

	public void setEvent_type(String event_type) {
		this.event_type = event_type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCreate_by() {
		return create_by;
	}

	public void setCreate_by(String create_by) {
		this.create_by = create_by;
	}
	public static void insertLog(Logger l) {
		String sql="INSERT INTO LOGS(event_name,event_type,message,status,location,create_by) VALUES (?,?,?,?,?,?);";
		try(Connection connection=ConnectionDb.getConnection("db.control.url", "db.control.username", "db.control.pass")) {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, l.getEvent_name());
			preparedStatement.setString(2, l.getEvent_type());
			preparedStatement.setString(3, l.getMessage());
			preparedStatement.setString(4, l.getStatus());
			preparedStatement.setString(5, l.getLocation());
			preparedStatement.setString(6, l.getCreate_by());
			int a=preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	@Override
	public String toString() {
		return "Logger [event_name=" + event_name + ", event_type=" + event_type + ", message=" + message + ", status="
				+ status + ", location=" + location + ", create_by=" + create_by + "]";
	}


}
