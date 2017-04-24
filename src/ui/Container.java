package ui;

public class Container implements PartDelegate{

	@Override
	public void doSomething() {
		//does something
	}
	
	private void setupPart(){
		Part p = new Part(this); 
	}

}
