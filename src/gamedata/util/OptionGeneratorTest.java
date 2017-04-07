package gamedata.util;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class OptionGeneratorTest {

	@Test
	public void correctActors() {
		//TODO: Implement
	}
	
	@Test
	public void correctProperties(){
		String [] properties = OptionGenerator.getPropertyTypes();
		assertEquals(properties[0], "AfflictStatus");
		Arrays.asList(properties).stream().forEach(p -> System.out.println(p));
	}

}
