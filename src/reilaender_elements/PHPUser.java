package reilaender_elements;


public class PHPUser implements Element {
	private String user;
	
	public PHPUser(String user) {
		this.user = user;
	}
	@Override
	public String getFormat() {
		return "'user' => '" + user + "'";
	}
}