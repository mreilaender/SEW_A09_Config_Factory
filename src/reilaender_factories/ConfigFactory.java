package reilaender_factories;

import reilaender_elements.Element;

public interface ConfigFactory {
	public Element getConfigElement(String element, String value);
}