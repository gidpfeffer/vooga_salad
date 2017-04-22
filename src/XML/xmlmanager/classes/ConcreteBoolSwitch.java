package XML.xmlmanager.classes;

import XML.xmlmanager.interfaces.BoolSwitch;

public class ConcreteBoolSwitch implements BoolSwitch{

	private boolean state;
	
	public ConcreteBoolSwitch(boolean startState){
		this.state = startState;
	}

	@Override
	public void setTrue() {
		state = true;
	}

	@Override
	public void setFalse() {
		state = false;
	}

	@Override
	public boolean getState() {
		return state;
	}
	
	
	
	
	
}

