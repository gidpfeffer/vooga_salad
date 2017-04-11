package facebookintegration;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.User;

public class Main {

	public static void main(String[] args){
		String key = "EAACEdEose0cBALQdZArJkPsgGs2jr8eNFCfIcSmm4aK0aDsiPZAz1Mz8xMRMbjhPh0Io4uxRmZCr388606FEoRaolZB6TMGH155VXy7aZCCIZB46WEMCAjMd2adi4LQwItgmsZAjxfOIjbDGoJFh6AV6zVWPEpEZBcHPUaACqMH89COgG7Bh8P3XSEigY4lUQOQZD";
		FacebookClient client = new DefaultFacebookClient(key);
		
		User me = client.fetchObject("me", User.class);
		
		System.out.println("Name: " + me.getName());
		System.out.println(me.getGender());
		System.out.println(me.getBirthday());
		System.out.println(me.getLink());
	}
}
