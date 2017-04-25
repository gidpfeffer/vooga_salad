package facebookintegration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.json.JsonObject;
import com.restfb.types.FacebookType;

import facebookintegration.interfaces.MasterFacebookUser;
import javafx.scene.image.Image;

public class Authenticator implements MasterFacebookUser{
	private String domain, appID, authURL;
	private WebDriver driver;
	private FacebookClient client;
	private boolean authenticated;
	
	
	public Authenticator(){
		domain = "http://www.cs.duke.edu/courses/compsci308/spring17/classwork/";
		appID = "164672860722038";
		authURL = "https://graph.facebook.com/oauth/authorize?type=user_agent&client_id="+appID+
				"&redirect_uri="+domain+"&scope=email,user_about_me,user_friends,user_birthday," + 
				"user_posts,user_photos,public_profile,manage_pages,publish_actions";
		System.setProperty("webdriver.chrome.driver", "lib/chromedriver");
		driver = new ChromeDriver();
	}
	
	private Image getProfilePicFromClient(){
		JsonObject picture = 
			      client.fetchObject("me/picture", 
				      JsonObject.class, Parameter.with("redirect","false"));
			
			String url = picture.get("data").asObject().get("url").asString();
			return extractImageViewFromURL(url);
	}
	
	private Image extractImageViewFromURL(String url){
		return new Image(url);
	}

	@Override
	public boolean isAuthenticated() {
		return authenticated;
	}

	@Override
	public void authenticate() {
		String accessToken;
		driver.get(authURL);
		
		while(true){
			if(!driver.getCurrentUrl().contains("facebook.com")){
				String url = driver.getCurrentUrl();
				accessToken = url.replaceAll(".*#access_token=(.+)&.*", "$1");
				driver.quit();
				break;
			}
		}
		client = new DefaultFacebookClient(accessToken, Version.LATEST);
		authenticated = true;
	}

	@Override
	public String postWithoutVoogaLink(String toPost) {
		FacebookType response = client.publish("me/feed", FacebookType.class, Parameter.with("message", toPost));
		return "fb.com/" + response.getId();
	}

	@Override
	public String postWithVoogaLink(String toPost) {
		return postWithoutVoogaLink(toPost + " Download I<3Singletons today" + 
				"at https://coursework.cs.duke.edu/CompSci308_2017Spring/voogasalad_ilovesingletons");
	}

	@Override
	public Image getProfilePicture() {
		return getProfilePicFromClient();
	}
}
