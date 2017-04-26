package ui.player;

import java.util.HashMap;
import java.util.List;

import javafx.scene.image.Image;

public class User {
	
	private String username;
	private String password;
	private HashMap<String, List<Integer>> scores;
	private String avatar;
	private String email;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public HashMap<String, List<Integer>> getScores() {
		return scores;
	}
	public void setScores(HashMap<String, List<Integer>> scores) {
		this.scores = scores;
	}
	public String getProfilePicture() {
		return avatar;
	}
	public void setProfilePicture(String avatar) {
		this.avatar = avatar;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public User(String username, String password, String avatar, String email) {
		this.username = username;
		this.password = password;
		this.avatar = avatar;
		this.email = email;
		scores = new HashMap<String, List<Integer>>();
	}
	
}
