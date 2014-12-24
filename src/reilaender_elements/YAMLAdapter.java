package reilaender_elements;


public class YAMLAdapter implements Element {
	private String adapter;
	
	public YAMLAdapter(String adapter) {
		this.adapter = adapter;
	}
	@Override
	public String getFormat() {
		return "adapter: " + adapter;
	}
}