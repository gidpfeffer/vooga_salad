package factories;

import java.util.ArrayList;
import java.util.Arrays;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import gameengine.actors.propertygen.Property;

public class PropertyFactory extends AbstractFactory <Property> {
	//TODO: Confirm package
	private static final String PATH = "gameengine.actors.properties.";
	
	public PropertyFactory() {
		super(PATH);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String generateObjectType(String name) {
		return name;
	}

	@Override
	protected Property failResponse() {
		return null;
	}


}
