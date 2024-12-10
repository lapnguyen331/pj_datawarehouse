package dbwarehouse;

public class PriceDimW {
	private int price_id;
	private String price_range;
	private float price;
	public PriceDimW(int price_id, String price_range, float price) {
		super();
		this.price_id = price_id;
		this.price_range = price_range;
		this.price = price;
	}
	public PriceDimW() {
		super();
	}
	public int getPrice_id() {
		return price_id;
	}
	public void setPrice_id(int price_id) {
		this.price_id = price_id;
	}
	public String getPrice_range() {
		return price_range;
	}
	public void setPrice_range(String price_range) {
		this.price_range = price_range;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "PriceDim [price_id=" + price_id + ", price_range=" + price_range + ", price=" + price + "]";
	}
	

}
