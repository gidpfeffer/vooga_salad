package gamestatus;

public interface WriteableGameStatus {
	void addExperience(double exp);
	void addMoney(double mon);
	void spendMoney(double mon);
	void loseLife();
	void gainLife();
}
