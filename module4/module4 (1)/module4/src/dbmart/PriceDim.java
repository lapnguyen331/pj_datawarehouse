package dbmart;

public class PriceDim {
	private int price_id;
	private String zone;
	private double price;
	public PriceDim(int price_id, String zone, double price) {
		super();
		this.price_id = price_id;
		this.zone = zone;
		this.price = price;
	}
	public PriceDim() {
		super();
	}
	public int getPrice_id() {
		return price_id;
	}
	public void setPrice_id(int price_id) {
		this.price_id = price_id;
	}
	public String getZone() {
		return zone;
	}
	public void setZone(String zone) {
		this.zone = zone;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "PriceDim [price_id=" + price_id + ", zone=" + zone + ", price=" + price + "]";
	}
	

}
