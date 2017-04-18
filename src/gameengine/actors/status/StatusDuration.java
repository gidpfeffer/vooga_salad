package gameengine.actors.status;

public class StatusDuration {

	private Integer myDuration;
	private Integer lifeLength;
	
	public StatusDuration(int dur) {
		myDuration = dur;
		lifeLength = 0;
	}
	
	public boolean stillOn() {
		return (lifeLength++)<myDuration;
	}
	
	public Integer getLifeLength() {
		return lifeLength;
	}
}
