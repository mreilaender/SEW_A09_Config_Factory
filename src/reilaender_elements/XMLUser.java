package reilaender_elements;


public class XMLUser implements Element {
	private String user;
	
	public XMLUser(String user) {
		this.user = user;
	}
	@Override
	public String getFormat() {
		// TODO Auto-generated method stub
		return "<user>" + user + "</user>";
	}	
}