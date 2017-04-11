package factories;

import gameengine.actors.propertygen.IActProperty;
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
