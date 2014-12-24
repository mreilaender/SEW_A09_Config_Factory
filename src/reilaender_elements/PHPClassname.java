package reilaender_elements;


public class PHPClassname implements Element {
	private String classname;
	
	public PHPClassname(String classname) {
		this.classname = classname;
	}
	@Override
	public String getFormat() {
		return "'classname' => '" + classname + "'";
	}
}