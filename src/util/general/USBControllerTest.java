package util.general;

import net.java.games.input.*;

public class USBControllerTest {

 	public static void main(String[] args) { 
		System.out.println("JInput version: " + Version.getVersion()); 
		ControllerEnvironment ce = ControllerEnvironment.getDefaultEnvironment(); 
		Controller[] cs = ce.getControllers(); 
		Controller myController = cs[0];
		for (int i = 0; i < cs.length; i++) {
			System.out.println(i + ". " + cs[i].getType() + ", " + cs[i].getPortNumber() ); 
 		}
 	}
}