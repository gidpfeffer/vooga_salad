package factories;

public class TowerFactory extends ActorFactory<Tower>{
	
	public TowerFactory() {
		super();
	}

	@Override
	protected String generateObjectType(String name) {
		return "Tower";
	}

}
