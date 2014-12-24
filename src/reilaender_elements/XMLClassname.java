package reilaender_elements;


public class XMLClassname implements Element {
	private String classname;
	
	public XMLClassname(String classname) {
		this.classname = classname;
	}
	@Override
	public String getFormat() {
		return "<classname>" + classname + "</classname>";
	}
}