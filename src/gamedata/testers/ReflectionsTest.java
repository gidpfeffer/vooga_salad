package gamedata.testers;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import gamedata.reflections.Reflections;

public class ReflectionsTest {

	@Test
	public void classesMatch() {
		Class[] classes = new Class[0];
		try {
			classes = Reflections.getClasses("gamedata.reflectionstest");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(classes[0].getName(), "gamedata.reflectionstest.A");
		assertEquals(classes[1].getName(), "gamedata.reflectionstest.B");
	}

}
