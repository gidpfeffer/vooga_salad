package gameengine.actors.status;

/**
 * @author sarahzhou
 * Afflicted actor loses a set amount of HP at a specified time interval
 */
public class Poisoned implements IStatus{
	
	public final static int HP_LOST = 10;
	public final static int TIME_INTERVAL = 5;

	@Override
	public void afflict(Integer id) {
		// TODO Auto-generated method stub
		
	}

}
