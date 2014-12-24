package reilaender_elements;


public class INIAdapter implements Element {
	private String adapter;
	
	public INIAdapter(String adapter) {
		this.adapter = adapter;
	}
	@Override
	public String getFormat() {
		return "adapter = "+adapter;
	}
}