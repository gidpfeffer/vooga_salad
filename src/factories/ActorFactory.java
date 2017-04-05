package factories;

import java.util.ArrayList;
import java.util.Arrays;

import gameengine.actors.management.Actor;
import util.VoogaException;

/**
 * Factory for creation of actors
 * 
 * @author maddiebriere
 *
 */

public class ActorFactory extends AbstractFactory<Actor>{

	private static final String PATH = "gameengine.actors.";
	
	public ActorFactory() {
		super(PATH);
	}

	@Override
	protected Actor failResponse() {
		try {
			throw new VoogaException("Reflection Error: No such property/actor");
		} catch (VoogaException e) {
			//TODO: Error Catching
		}
		return null;
	}
	
	private Class<?> getClass(String name){
		try {
			return Class.forName(name);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
		}
		return null;
	}
	
	//Get classes from data objects
	@Override
	protected Class<?>[] getClasses(Object... args) {
		//Convert Data objects to Property names
		Class <?>ID = args[0].getClass();
		String [] classNames =  Arrays.asList(args)
				.stream()
				.filter(e -> !(e==args[0]))
				.map(p -> p.getClass().getName().replace("Data", "Property"))
				.toArray(String[]::new);
		Class<?>[] remaining = Arrays.asList(classNames)
				.stream()
				.map(p -> getClass(p))
				.toArray(Class[]::new);
		ArrayList<Class<?>> classes = new ArrayList<Class<?>>();
		classes.add(ID);
		classes.addAll(Arrays.asList(remaining));
		Class<?> [] toRet = new Class[remaining.length+1];
		classes.toArray(toRet);
		return toRet;
	}

	@Override
	protected String generateObjectType(String name) {
		return name;
	}

}
