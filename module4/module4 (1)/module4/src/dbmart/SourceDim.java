package dbmart;

public class SourceDim {
	private int source_id;
	private String source_name;
	private String source_url;
	public SourceDim(int source_id, String source_name, String source_url) {
		super();
		this.source_id = source_id;
		this.source_name = source_name;
		this.source_url = source_url;
	}
	public SourceDim() {
		super();
	}
	public int getSource_id() {
		return source_id;
	}
	public void setSource_id(int source_id) {
		this.source_id = source_id;
	}
	public String getSource_name() {
		return source_name;
	}
	public void setSource_name(String source_name) {
		this.source_name = source_name;
	}
	public String getSource_url() {
		return source_url;
	}
	public void setSource_url(String source_url) {
		this.source_url = source_url;
	}
	@Override
	public String toString() {
		return "SourceDim [source_id=" + source_id + ", source_name=" + source_name + ", source_url=" + source_url
				+ "]";
	}
	

}
