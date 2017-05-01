package gamedata.composition;

import gamedata.compositiongen.MoveAxisUserData;

public class MoveHorizontalUserData extends MoveAxisUserData {
	public MoveHorizontalUserData(){
		super();
	}
	
	public MoveHorizontalUserData(String posButton, String negButton, Integer mySensitivity){
		super(posButton, negButton, mySensitivity);
	}
}
