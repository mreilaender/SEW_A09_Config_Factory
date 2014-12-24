package reilaender_elements;


public class XMLAdapter implements Element {
	private String adapter;
	
	public XMLAdapter(String adapter) {
		this.adapter = adapter;
	}
	@Override
	public String getFormat() {
		return "<adapter>" + adapter + "</adapter>";
	}
}