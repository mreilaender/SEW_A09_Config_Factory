package reilaender_elements;


public class INIUser implements Element {
	private String user;
	
	public INIUser(String user) {
		this.user = user;
	}
	@Override
	public String getFormat() {
		return "user = " + user;
	}
}