package dbmart;

import java.sql.Timestamp;

public class MotobikeDim {
	private int id;
	private String name;
	private String color;
	private String engine_capacity;
	private String image_url;
	private String status;
	private Timestamp create_at;
	public MotobikeDim(int id, String name, String color, String engine_capacity, String image_url,
			String status, Timestamp create_at) {
		super();
		this.id = id;
		this.name = name;
		this.color = color;
		this.engine_capacity = engine_capacity;
		this.image_url = image_url;
		this.status = status;
		this.create_at = create_at;
	}
	public MotobikeDim() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getEngine_capacity() {
		return engine_capacity;
	}
	public void setEngine_capacity(String engine_capacity) {
		this.engine_capacity = engine_capacity;
	}
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Timestamp getCreate_at() {
		return create_at;
	}
	public void setCreate_at(Timestamp create_at) {
		this.create_at = create_at;
	}
	@Override
	public String toString() {
		return "MotobikeDim [id=" + id + ", name=" + name + ", color=" + color + ", engine_capacity=" + engine_capacity
				+ ", image_url=" + image_url + ", status=" + status + ", create_at="
				+ create_at + "]";
	}
	


}
