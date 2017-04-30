package facebookintegration.interfaces;

public interface FacebookPoster {
	
	String postWithoutVoogaLink(String toPost);
	
	String postWithVoogaLink(String toPost);

}
