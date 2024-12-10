package dbwarehouse;

import java.sql.Timestamp;

public class MotobikeDimW {
	private int id;//1
	private String model_name;
	private String color;//3
	private String name;//2
	private String engine_capacity;//4
	private String engine_type;
	private String transmission_type;
	private String features;//5
	private String image_url;//6
	private String status;//7
	private Timestamp create_at;//8
	public MotobikeDimW(int id, String model_name, String color, String name, String engine_capacity, String engine_type,
			String transmission_type, String features, String image_url, String status, Timestamp create_at) {
		super();
		this.id = id;
		this.model_name = model_name;
		this.color = color;
		this.name = name;
		this.engine_capacity = engine_capacity;
		this.engine_type = engine_type;
		this.transmission_type = transmission_type;
		this.features = features;
		this.image_url = image_url;
		this.status = status;
		this.create_at = create_at;
	}
	public MotobikeDimW() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getModel_name() {
		return model_name;
	}
	public void setModel_name(String model_name) {
		this.model_name = model_name;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEngine_capacity() {
		return engine_capacity;
	}
	public void setEngine_capacity(String engine_capacity) {
		this.engine_capacity = engine_capacity;
	}
	public String getEngine_type() {
		return engine_type;
	}
	public void setEngine_type(String engine_type) {
		this.engine_type = engine_type;
	}
	public String getTransmission_type() {
		return transmission_type;
	}
	public void setTransmission_type(String transmission_type) {
		this.transmission_type = transmission_type;
	}
	public String getFeatures() {
		return features;
	}
	public void setFeatures(String features) {
		this.features = features;
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
		return "MotobikeDim [id=" + id + ", model_name=" + model_name + ", color=" + color + ", name=" + name
				+ ", engine_capacity=" + engine_capacity + ", engine_type=" + engine_type + ", transmission_type="
				+ transmission_type + ", features=" + features + ", image_url=" + image_url + ", status=" + status
				+ ", create_at=" + create_at + "]";
	}
	
	


}
