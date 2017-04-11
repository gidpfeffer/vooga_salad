package facebookintegration;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.User;

public class Main {

	public static void main(String[] args){
		String key = "EAACEdEose0cBANJoTluzIoMlF2YdVCyyeCY8J8VbH7xMDgQkoZCn6cWkfDh0ZAY0ZCba2HsWBsGImvsRIC83ZBlitqn9aXOdtNOZBaogDlZAlyf7zG9IN2RZBCETHVJ1CpGYsMd4LZBUf0Ex3yPk5zgqdDkTB2EOsji9FwKlSY5ZBvnFGCRKoIVTkMnxAPuPgV84ZD";
		FacebookClient client = new DefaultFacebookClient(key);
		
		User me = client.fetchObject("me", User.class);
		
		System.out.println("Name: " + me.getName());
		System.out.println(me.getGender());
		System.out.println(me.getBirthday());
		System.out.println(me.getLink());
	}
}
