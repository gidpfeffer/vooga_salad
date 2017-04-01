package gameengine.actors.management;

public interface IActorHandler <I>{
	
	public void handle(I input);
	
	public void updateHandler();

}
