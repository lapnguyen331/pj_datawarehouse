package dbmart;

public class PriceFact {
	private int fact_price_id;
	private MotobikeDim motobike_id;
	private PriceDim price_id;
	private SourceDim source_id;
	private BrandDim brand_id;
	private TypeDim type_id;
	private double price_value;
	private DateDim date_sk;
	public PriceFact(int fact_price_id, MotobikeDim motobike_id, PriceDim price_id, SourceDim source_id,
			BrandDim brand_id, TypeDim type_id, double price_value, DateDim date_sk) {
		super();
		this.fact_price_id = fact_price_id;
		this.motobike_id = motobike_id;
		this.price_id = price_id;
		this.source_id = source_id;
		this.brand_id = brand_id;
		this.type_id = type_id;
		this.price_value = price_value;
		this.date_sk = date_sk;
	}
	public PriceFact() {
		super();
	}
	public int getFact_price_id() {
		return fact_price_id;
	}
	public void setFact_price_id(int fact_price_id) {
		this.fact_price_id = fact_price_id;
	}
	public MotobikeDim getMotobike_id() {
		return motobike_id;
	}
	public void setMotobike_id(MotobikeDim motobike_id) {
		this.motobike_id = motobike_id;
	}
	public PriceDim getPrice_id() {
		return price_id;
	}
	public void setPrice_id(PriceDim price_id) {
		this.price_id = price_id;
	}
	public SourceDim getSource_id() {
		return source_id;
	}
	public void setSource_id(SourceDim source_id) {
		this.source_id = source_id;
	}
	public BrandDim getBrand_id() {
		return brand_id;
	}
	public void setBrand_id(BrandDim brand_id) {
		this.brand_id = brand_id;
	}
	public TypeDim getType_id() {
		return type_id;
	}
	public void setType_id(TypeDim type_id) {
		this.type_id = type_id;
	}
	public double getPrice_value() {
		return price_value;
	}
	public void setPrice_value(double price_value) {
		this.price_value = price_value;
	}
	public DateDim getDate_sk() {
		return date_sk;
	}
	public void setDate_sk(DateDim date_sk) {
		this.date_sk = date_sk;
	}
	@Override
	public String toString() {
		return "PriceFact [fact_price_id=" + fact_price_id + ", motobike_id=" + motobike_id + ", price_id=" + price_id
				+ ", source_id=" + source_id + ", brand_id=" + brand_id + ", type_id=" + type_id + ", price_value="
				+ price_value + ", date_sk=" + date_sk + "]";
	}
	
}