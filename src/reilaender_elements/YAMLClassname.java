package reilaender_elements;


public class YAMLClassname implements Element {
	private String classname;
	
	public YAMLClassname(String classname) {
		this.classname = classname;
	}
	@Override
	public String getFormat() {
		return "classname: " + classname;
	}
}