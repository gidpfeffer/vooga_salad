package ui;

public class Container implements PartDelegate{

	@Override
	public void doSomething() {
		// TODO Auto-generated method stub
		
	}
	
	private void setupPart(){
		Part p = new Part(this);
	}

}
