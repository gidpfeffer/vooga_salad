package factories;

import java.util.Arrays;

import util.VoogaException;

/**
 * Factory for creation of actors
 * 
 * @author maddiebriere
 *
 */

public abstract class ActorFactory<A> extends AbstractFactory<A>{

	private static final String PATH = "gameengine.actors.";
	
	public ActorFactory() {
		super(PATH);
	}

	@Override
	protected A failResponse() {
		try {
			throw new VoogaException("Reflection Error: No such property/actor");
		} catch (VoogaException e) {
			//TODO: Error Catching
		}
		return null;
	}
	
	private Class getClass(String name){
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
		String [] classNames =  Arrays.asList(args)
				.stream()
				.map(p -> p.getClass().getName().replace("Data", ""))
				.toArray(String[]::new);
		return Arrays.asList(classNames)
				.stream()
				.map(p -> getClass(p))
				.toArray(Class[]::new);
	}

}
