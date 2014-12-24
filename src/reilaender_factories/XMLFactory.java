package reilaender_factories;

import reilaender_elements.Element;
import reilaender_elements.XMLAdapter;
import reilaender_elements.XMLClassname;
import reilaender_elements.XMLDSN;
import reilaender_elements.XMLUser;

public class XMLFactory implements ConfigFactory {

	@Override
	public Element getConfigElement(String element, String value) {
		switch(element) {
			case "adapter":
				return new XMLAdapter(value);
		
			case "classname":
				return new XMLClassname(value);
			
			case "dsn":
				return new XMLDSN(value);
			
			case "user":
				return new XMLUser(value);
			
			default:
				return null;
		}
	}
}