package reilaender_elements;


public class XMLDSN implements Element {
	private String dsn;
	
	public XMLDSN(String dsn) {
		this.dsn = dsn;
	}
	@Override
	public String getFormat() {
		return "<dsn>" + dsn + "</dsn>";
	}
}