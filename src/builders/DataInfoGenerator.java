package builders;

import gamedata.compositiongen.Data;

public class DataInfoGenerator extends TwoLevelInfoGenerator<Data>{
	private static final String DATA_PATH = "gamedata.composition";
	private static final String SUPER_DATA_PATH = "gamedata.compositiongen";
	
	public DataInfoGenerator() {
		super(DATA_PATH, SUPER_DATA_PATH);
	}
	
	@Override
	protected String simplifyName(Class<?> className) {
		return className.getSimpleName().replace("Data", "");
	}
	

}
