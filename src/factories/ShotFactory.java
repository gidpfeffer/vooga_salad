package factories;

public class ShotFactory extends ActorFactory<Tower>{
	
	public TowerFactory() {
		super();
	}

	@Override
	protected String generateObjectType(String name) {
		return "Tower";
	}

}