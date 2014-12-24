package reilaender_elements;


public class YAMLDSN implements Element {
	private String dsn;
	
	public YAMLDSN(String dsn) {
		this.dsn = dsn;
	}
	@Override
	public String getFormat() {
		// TODO Auto-generated method stub
		return "dsn: " + dsn;
	}
}