package reilaender_elements;


public class INIDSN implements Element {
	private String dsn;
	
	public INIDSN(String dsn) {
		this.dsn = dsn;
	}
	@Override
	public String getFormat() {
		return "dsn = " + dsn;
	}
}