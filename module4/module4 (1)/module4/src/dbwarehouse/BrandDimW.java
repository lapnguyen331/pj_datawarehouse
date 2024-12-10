package dbwarehouse;

public class BrandDimW {
	private int id;
	private String name;
	public BrandDimW(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public BrandDimW() {
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
	@Override
	public String toString() {
		return "BrandDim [id=" + id + ", name=" + name + "]";
	}
	

}
