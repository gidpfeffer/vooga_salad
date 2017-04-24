package factories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
	
	protected Class<?>[] getClasses(Object... args) {
		Class<?>[] toRet = new Class[args.length];
		for(int i=0; i<args.length; i++){
			if(args[i].getClass().equals(ArrayList.class))
				toRet[i]=List.class;
			else
				toRet[i] = args[i].getClass();
			System.out.println(toRet[i]);
		}
		return toRet;
	}

}
