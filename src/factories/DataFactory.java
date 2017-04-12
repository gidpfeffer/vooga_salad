package factories;

import java.util.ArrayList;
import java.util.Arrays;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

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
		Class<?>[] toRet = Arrays.asList(args).stream().map(p -> p.getClass()).toArray(Class[]::new);
		//TODO: Replace with pretty lambda
		for(int i=0; i<toRet.length; i++){
			if(toRet[i].equals(ArrayList.class)){
				toRet[i]=List.class;//more general
			}
			System.out.println(toRet[i]);
		}
		return toRet;
	}

}
