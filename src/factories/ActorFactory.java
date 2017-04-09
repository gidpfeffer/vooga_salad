package factories;

import java.util.ArrayList;
import java.util.Arrays;

import gameengine.actors.MainActor;
import gameengine.actors.management.Actor;
import util.VoogaException;

/**
 * Factory for creation of actors
 * 
 * @author maddiebriere
 *
 */

public class ActorFactory extends AbstractFactory<MainActor>{

	private static final String PATH = "gameengine.actors.";
	
	public ActorFactory() {
		super(PATH);
	}

	@Override
	protected MainActor failResponse() {
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
		ArrayList<Class<?>> classes = new ArrayList<Class<?>>();
		classes.add(args[0].getClass());
		classes.add(args[1].getClass());
		classes.add(args[2].getClass());
		
		try {
			classes.add(Class.forName("gameengine.actors.propertygen.HealthProperty"));
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Class<?> [] remaining =  new Class[args.length-4];
		try {
			Arrays.fill(remaining, Class.forName("gameengine.actors.propertygen.Property"));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		classes.addAll(Arrays.asList(remaining));
		
		Class<?> [] toRet = new Class[args.length];
		classes.toArray(toRet);
		classes.stream().forEach(p -> System.out.println(p.getSimpleName()));
		return toRet;
	}

	@Override
	protected String generateObjectType(String name) {
		return "MainActor";
	}

}
