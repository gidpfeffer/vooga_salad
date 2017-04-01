package gameengine.actors.management;

public abstract class AbstractActorHandler <I, O> implements IActorHandler<I>{

	private IActor<O> actor;
	private IDecision<I,O> decision;
	
	public AbstractActorHandler(IActor<O> actor, IDecision<I,O> decision){
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
