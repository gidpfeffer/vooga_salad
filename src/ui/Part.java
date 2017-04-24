package ui;

public class Part {
	PartDelegate myDelegate;
	
	public Part(PartDelegate del){
		myDelegate = del;
	}
	
	//example of how Part can notify Container to run a method
	private void randomMethod(){
		myDelegate.doSomething();
	}
	
}
