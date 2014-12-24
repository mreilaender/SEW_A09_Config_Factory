package reilaender_factories;

import reilaender_elements.Element;
import reilaender_elements.YAMLAdapter;
import reilaender_elements.YAMLClassname;
import reilaender_elements.YAMLDSN;
import reilaender_elements.YAMLUser;

public class YAMLFactory implements ConfigFactory {

	@Override
	public Element getConfigElement(String element, String value) {
		switch(element) {
			case "adapter":
				return new YAMLAdapter(value);
	
			case "classname":
				return new YAMLClassname(value);
		
			case "dsn":
				return new YAMLDSN(value);
		
			case "user":
				return new YAMLUser(value);
		
			default:
				return null;
		}
	}
}