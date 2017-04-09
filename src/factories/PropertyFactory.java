package factories;

import gameengine.actors.propertygen.IActProperty;

public class PropertyFactory extends AbstractFactory <IActProperty> {
	//TODO: Confirm package
	private static final String PATH = "gameengine.actors.properties";
	
	public PropertyFactory() {
		super(PATH);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String generateObjectType(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected IActProperty failResponse() {
		return null;
	}

}
