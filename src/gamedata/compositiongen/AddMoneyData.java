package gamedata.compositiongen;

public class AddMoneyData extends ActData {
	private Double money;
	
	public AddMoneyData(){
		this(0.0);
	}
	
	public AddMoneyData(Double myMoney){
		this.money = myMoney;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double myMoney) {
		this.money = myMoney;
	}
	
	
}

