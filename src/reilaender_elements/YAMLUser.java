package reilaender_elements;


public class YAMLUser implements Element {
	private String user;
	
	public YAMLUser(String user) {
		this.user = user;
	}
	@Override
	public String getFormat() {
		return "user: " + user;
	}
}