package reilaender_factories;

import reilaender_elements.Element;
import reilaender_elements.INIAdapter;
import reilaender_elements.INIClassname;
import reilaender_elements.INIDSN;
import reilaender_elements.INIUser;

public class INIFactory implements ConfigFactory {
	@Override
	public Element getConfigElement(String element, String value) {
		switch(element) {
			case "adapter":
				return new INIAdapter(value);
			
			case "classname":
				return new INIClassname(value);
				
			case "dsn":
				return new INIDSN(value);
				
			case "user":
				return new INIUser(value);
				
			default:
				return null;
		}
	}
}