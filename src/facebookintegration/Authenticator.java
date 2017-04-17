package facebookintegration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.json.JsonObject;
import com.restfb.types.FacebookType;
import com.restfb.types.Page;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Authenticator {
	private String domain, appID, authURL;
	private WebDriver driver;
	private FacebookClient client;
	
	
	public Authenticator(){
		domain = "http://www.cs.duke.edu/courses/compsci308/spring17/classwork/";
		appID = "164672860722038";
		authURL = "https://graph.facebook.com/oauth/authorize?type=user_agent&client_id="+appID+
				"&redirect_uri="+domain+"&scope=email,user_about_me,user_friends,user_birthday," + 
				"user_posts,user_photos,public_profile,manage_pages,publish_actions";
		
		System.setProperty("webdriver.chrome.driver", "lib/chromedriver");
		driver = new ChromeDriver();
	}
	
	public void setUp(){
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
	}
	
	public ImageView getProfilePicFromClient(){
		JsonObject picture = 
			      client.fetchObject("me/picture", 
				      JsonObject.class, Parameter.with("redirect","false"));
			
			String url = picture.get("data").asObject().get("url").asString();
			return extractImageViewFromURL(url);
	}
	
	private ImageView extractImageViewFromURL(String url){
		ImageView imageView = new ImageView(new Image(url));
		return imageView;
	}
	
	public String postToTimeline(String toPost){
		final Page page = client.fetchObject("me", Page.class);
		FacebookType response = client.publish("me/feed", FacebookType.class, Parameter.with("message", toPost));
		return "fb.com/" + response.getId();
	}
}
