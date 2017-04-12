package gamedata.testers;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import builders.DataGenerator;
import builders.OptionGenerator;
import gamedata.FieldData;
import gamedata.compositiongen.Data;

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
	
	@Test
	public void correctTypeProperties(){
		List<String> properties = OptionGenerator.getGeneralPropertyTypes();
		assertEquals(properties.get(0), "Status");
		System.out.println("General Properties");
		Arrays.asList(properties).stream().forEach(p -> System.out.println(p));
	}
	
	
	@Test
	public void correctSubtypes(){
		List<String> properties = OptionGenerator.getSubcategories("Shoot");
		//assertEquals(properties.get(0), "");
		assertEquals(properties.get(0), "ShootTargetFar");
		System.out.println("Shoot Properties");
		Arrays.asList(properties).stream().forEach(System.out::println);
	}
	
	@Test
	public void getName(){
		Data d = DataGenerator.makeData("LimitedHealth", 10.0);
		assertEquals("LimitedHealth", OptionGenerator.getName(d));
	}
	
	@Test 
	public void getFields(){
		System.out.println("\n");
		Data d = DataGenerator.makeData("LimitedHealth", 10.0);
		Map<String, Object> values = OptionGenerator.getFields(d);
		for(String s: values.keySet()){
			System.out.println(s + " " + values.get(s));
		}
		System.out.println("\n");
		//assertEquals(10.0, o);
	}

}
