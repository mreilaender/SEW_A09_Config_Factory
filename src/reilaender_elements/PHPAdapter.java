package reilaender_elements;


public class PHPAdapter implements Element {
	private String adapter;
	public PHPAdapter(String adapter) {
		this.adapter = adapter;
	}
	@Override
	public String getFormat() {
		return "'adapter' => '" + adapter + "'";
	}
}