package dbmart;

import java.util.Date;

public class Fact {
	int id_vehicle;
	String name,brand,type;
	double price_current,price_min,price_max,price_avg;
	Date date;
	public Fact(int id_vehicle, String name, String brand, String type, double price_current, double price_min,
			double price_max, double price_avg, Date date) {
		super();
		this.id_vehicle = id_vehicle;
		this.name = name;
		this.brand = brand;
		this.type = type;
		this.price_current = price_current;
		this.price_min = price_min;
		this.price_max = price_max;
		this.price_avg = price_avg;
		this.date = date;
	}
	public Fact() {
		super();
	}
	public int getId_vehicle() {
		return id_vehicle;
	}
	public void setId_vehicle(int id_vehicle) {
		this.id_vehicle = id_vehicle;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getPrice_current() {
		return price_current;
	}
	public void setPrice_current(double price_current) {
		this.price_current = price_current;
	}
	public double getPrice_min() {
		return price_min;
	}
	public void setPrice_min(double price_min) {
		this.price_min = price_min;
	}
	public double getPrice_max() {
		return price_max;
	}
	public void setPrice_max(double price_max) {
		this.price_max = price_max;
	}
	public double getPrice_avg() {
		return price_avg;
	}
	public void setPrice_avg(double price_avg) {
		this.price_avg = price_avg;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "Fact [id_vehicle=" + id_vehicle + ", name=" + name + ", brand=" + brand + ", type=" + type
				+ ", price_current=" + price_current + ", price_min=" + price_min + ", price_max=" + price_max
				+ ", price_avg=" + price_avg + ", date=" + date + "]";
	}
	
	
}