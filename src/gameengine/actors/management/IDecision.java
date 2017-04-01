package gameengine.actors.management;

public interface IDecision <I, O>{
	
	public O decide(I input);

}
