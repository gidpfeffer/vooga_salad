package factories;

import gamedata.compositiongen.Data;

/**
 * Data object factory, used in DataGenerator
 * 
 * TODO: Consolidate shared code
 * 
 * @author maddiebriere
 *
 */

public class DataFactory extends AbstractFactory <Data> {
	//TODO: Confirm package
	private static final String PATH = "gamedata.composition.";
	
	public DataFactory() {
		super(PATH);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String generateObjectType(String name) {
		return name;
	}

	@Override
	protected Data failResponse() {
		return null;
	}

}
