package builders.infogen;

public class PropertyDescriptionGenerator {
	private static final String DESCRIPTIONS = "PropertyDescriptions";
	
	public static String getDescription(String propertyName){
		return DescriptionGenerator.getDescription(propertyName, ClassFinder.getClass(DESCRIPTIONS),
				new DataInfoGenerator());
	}
}
