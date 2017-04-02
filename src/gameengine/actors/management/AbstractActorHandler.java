package gameengine.actors.management;

public abstract class AbstractActorHandler <I, O> implements IActorHandler<I>{

	private Actor<O> actor;
	private IDecision<I,O> decision;
	
	public AbstractActorHandler(Actor<O> actor, IDecision<I,O> decision){
		this.actor = actor;
		this.decision = decision;
	}
	
	@Override
	public void handle(I input) {
		actor.act(decision.decide(input));	
	}
	
	@Override
	abstract public void updateHandler();

}
