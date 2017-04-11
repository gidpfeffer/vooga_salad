package gamedata.testers;

import static org.junit.Assert.*;

import org.junit.Test;

import factories.PropertyFactory;
import gamedata.composition.AfflictStatusData;
import gamedata.composition.BaseDamageData;

public class PropertyFactoryTest {

	@Test
	public void test() {
		PropertyFactory p = new PropertyFactory();
		p.make("AfflictStatusProperty", new AfflictStatusData());
		p.make("BaseDamageProperty", new BaseDamageData());
		p.make("AfflictStatusProperty", new AfflictStatusData());
		p.make("AfflictStatusProperty", new AfflictStatusData());
		
	}

}
