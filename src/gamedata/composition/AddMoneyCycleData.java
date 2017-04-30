package gamedata.composition;

import gamedata.compositiongen.AddMoneyData;

public class AddMoneyCycleData extends AddMoneyData  {
	private Integer frequency;

	public AddMoneyCycleData(){
		this(0, 0.0);
	}
	
	public AddMoneyCycleData(Integer myFrequency, Double myMoney) {
		super(myMoney);
		this.frequency = myFrequency;
	}

	public Integer getFrequency() {
		return frequency;
	}

	public void setFrequency(Integer frequency) {
		this.frequency = frequency;
	}

	
	
	
}
