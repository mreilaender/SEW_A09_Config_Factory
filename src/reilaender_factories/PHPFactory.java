package reilaender_factories;

import reilaender_elements.Element;
import reilaender_elements.PHPAdapter;
import reilaender_elements.PHPClassname;
import reilaender_elements.PHPDSN;
import reilaender_elements.PHPUser;

public class PHPFactory implements ConfigFactory {

	@Override
	public Element getConfigElement(String element, String value) {
		switch(element) {
			case "adapter":
				return new PHPAdapter(value);
		
			case "classname":
				return new PHPClassname(value);
			
			case "dsn":
				return new PHPDSN(value);
				
			case "user":
				return new PHPUser(value);
			
			default:
				return null;
		}
	}
}