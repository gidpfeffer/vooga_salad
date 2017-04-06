package factories;

import gameengine.actors.properties.IActProperty;

public class PropertyFactory extends AbstractFactory <IActProperty> {

	public PropertyFactory(String path) {
		super(path);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String generateObjectType(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected IActProperty failResponse() {
		// TODO Auto-generated method stub
		return null;
	}

}
