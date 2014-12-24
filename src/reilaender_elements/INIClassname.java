package reilaender_elements;


public class INIClassname implements Element {
	private String classname;
	
	public INIClassname(String classname) {
		this.classname = classname;
	}
	@Override
	public String getFormat() {
		return "classname = " + classname;
	}
}