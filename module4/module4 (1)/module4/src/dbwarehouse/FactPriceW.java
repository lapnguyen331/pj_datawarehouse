package dbwarehouse;

public class FactPriceW {
	private int fact_price_id;
	private int motobike_id;
	private int price_id;
	private int date_sk;
	private int source_id;
	private int brand_id;
	private int type_id;
	private float price_value;
	public FactPriceW(int fact_price_id, int motobike_id, int price_id, int date_sk, int source_id, int brand_id,
			int type_id, float price_value) {
		super();
		this.fact_price_id = fact_price_id;
		this.motobike_id = motobike_id;
		this.price_id = price_id;
		this.date_sk = date_sk;
		this.source_id = source_id;
		this.brand_id = brand_id;
		this.type_id = type_id;
		this.price_value = price_value;
	}
	public FactPriceW() {
		super();
	}
	public int getFact_price_id() {
		return fact_price_id;
	}
	public void setFact_price_id(int fact_price_id) {
		this.fact_price_id = fact_price_id;
	}
	public int getMotobike_id() {
		return motobike_id;
	}
	public void setMotobike_id(int motobike_id) {
		this.motobike_id = motobike_id;
	}
	public int getPrice_id() {
		return price_id;
	}
	public void setPrice_id(int price_id) {
		this.price_id = price_id;
	}
	public int getDate_sk() {
		return date_sk;
	}
	public void setDate_sk(int date_sk) {
		this.date_sk = date_sk;
	}
	public int getSource_id() {
		return source_id;
	}
	public void setSource_id(int source_id) {
		this.source_id = source_id;
	}
	public int getBrand_id() {
		return brand_id;
	}
	public void setBrand_id(int brand_id) {
		this.brand_id = brand_id;
	}
	public int getType_id() {
		return type_id;
	}
	public void setType_id(int type_id) {
		this.type_id = type_id;
	}
	public float getPrice_value() {
		return price_value;
	}
	public void setPrice_value(float price_value) {
		this.price_value = price_value;
	}
	@Override
	public String toString() {
		return "FactPriceW [fact_price_id=" + fact_price_id + ", motobike_id=" + motobike_id + ", price_id=" + price_id
				+ ", date_sk=" + date_sk + ", source_id=" + source_id + ", brand_id=" + brand_id + ", type_id="
				+ type_id + ", price_value=" + price_value + "]";
	}
	

	
}