package dbwarehouse;

public class SourceDimW {
	private int source_id;//1
	private int source_pid;
	private String source_name;//2
	private String source_type;
	private String source_url;//3
	private String source_SkuID;
	private String description;
	public SourceDimW(int source_id, int source_pid, String source_name, String source_type, String source_url,
			String source_SkuID, String description) {
		super();
		this.source_id = source_id;
		this.source_pid = source_pid;
		this.source_name = source_name;
		this.source_type = source_type;
		this.source_url = source_url;
		this.source_SkuID = source_SkuID;
		this.description = description;
	}
	public SourceDimW() {
		super();
	}
	public int getSource_id() {
		return source_id;
	}
	public void setSource_id(int source_id) {
		this.source_id = source_id;
	}
	public int getSource_pid() {
		return source_pid;
	}
	public void setSource_pid(int source_pid) {
		this.source_pid = source_pid;
	}
	public String getSource_name() {
		return source_name;
	}
	public void setSource_name(String source_name) {
		this.source_name = source_name;
	}
	public String getSource_type() {
		return source_type;
	}
	public void setSource_type(String source_type) {
		this.source_type = source_type;
	}
	public String getSource_url() {
		return source_url;
	}
	public void setSource_url(String source_url) {
		this.source_url = source_url;
	}
	public String getSource_SkuID() {
		return source_SkuID;
	}
	public void setSource_SkuID(String source_SkuID) {
		this.source_SkuID = source_SkuID;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "SourceDim [source_id=" + source_id + ", source_pid=" + source_pid + ", source_name=" + source_name
				+ ", source_type=" + source_type + ", source_url=" + source_url + ", source_SkuID=" + source_SkuID
				+ ", description=" + description + "]";
	}
	

}
