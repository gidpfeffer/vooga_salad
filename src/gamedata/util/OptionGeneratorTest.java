package gamedata.util;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * Test OptionGenerator
 * @author maddiebriere
 *
 */

public class OptionGeneratorTest {

	@Test
	public void correctActors() {
		//TODO: Implement
	}
	
	@Test
	public void correctProperties(){
		List<String> properties = OptionGenerator.getPropertyTypes();
		assertEquals(properties.get(0), "AfflictStatus");
		Arrays.asList(properties).stream().forEach(p -> System.out.println(p));
	}

}
