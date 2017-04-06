package gameengine.actors.status;

/**
 * @author sarahzhou
 * Status in which the afflicted actor shoots at (damages) their own side a certain percentage of the time 
 */
public class Confused implements IStatus {
	
	private static final double CONFUSED_PERCENTAGE = 0.5;

	@Override
	public void afflict(Integer id) {
		
	}

}
