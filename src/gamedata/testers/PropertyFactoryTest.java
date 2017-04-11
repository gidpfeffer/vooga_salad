package gamedata.testers;

import static org.junit.Assert.*;

import org.junit.Test;

import factories.PropertyFactory;
import gamedata.composition.AfflictStatusData;
import gamedata.composition.BaseDamageData;
import gamedata.composition.DamageableData;
import gamedata.composition.ImmuneHealthData;
import gamedata.composition.LimitedHealthData;
import gamedata.composition.MoveWithSetPathData;
import gamedata.composition.ShootTargetFarData;
import types.BasicActorType;

public class PropertyFactoryTest {

	@Test
	public void test() {
		PropertyFactory p = new PropertyFactory();
		assertNotEquals(p.make("AfflictStatusProperty", new AfflictStatusData()),null);
		assertNotEquals(p.make("BaseDamageProperty", new BaseDamageData(10, BasicActorType.Tower)), null);
		assertNotEquals(p.make("DamageableProperty", new DamageableData(10, 10, BasicActorType.Tower)),null);
		assertNotEquals(p.make("ImmuneHealthProperty", new ImmuneHealthData()), null);
		assertNotEquals(p.make("LimitedHealthProperty", new LimitedHealthData(10)), null);
		assertNotEquals(p.make("MoveWithSetPathProperty", new MoveWithSetPathData(null, 10)), null);
		assertNotEquals(p.make("ShootTargetFarProperty", new ShootTargetFarData(10, 10, BasicActorType.Tower, 10)), null);
	}

}
