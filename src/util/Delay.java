package util;

public class Delay {

	private final int delayMod = 60;

	private Integer actionDelay;
	private Integer steps;

	public Delay(int actionsPerStep) {
		actionDelay = delayMod/actionsPerStep;// * delayMod;
		steps = 0;
	}

	public boolean delayAction() {
		return (steps++ % actionDelay == 0);
	}

}
