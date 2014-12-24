package reilaender_elements;


public class PHPDSN implements Element {
	private String dsn;
	
	public PHPDSN(String dsn) {
		this.dsn = dsn;
	}
	@Override
	public String getFormat() {
		return "'dsn' => '" + dsn + "'";
	}
}