package gamedata.util;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import gamedata.FieldData;

import java.lang.reflect.Field;

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
		System.out.println("Properties");
		Arrays.asList(properties).stream().forEach(p -> System.out.println(p));
	}
	
	@Test 
	public void correctPropertyArgs(){
		Map<String, List<FieldData>> options = OptionGenerator.getPropertyTypesWithArgs();
		for(String s: options.keySet()){
			System.out.println(s + " ");
			for(FieldData f: options.get(s)){
				System.out.println(f.getMyType() + " " + f.getMyName());
			}
			System.out.println();
		}
	}

}
