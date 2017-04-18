package ui.player.login;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class LoginGrid extends DataEntryGrid{

	private Map<Text, TextField> entryMap;
	private Map<String, TextField> dataMap;
	private ResourceBundle loginResource;
	private String css;
	
	public TextField getUsername() {
		//System.out.println(entryMap.get(loginResource.getString("username")).getText());
		return dataMap.get("username");
	}
	
	public TextField getPassword() {
		return dataMap.get("password");
	}
	
	
	@Override
	public Map<Text, TextField> getEntryMap() {
		return entryMap;
	}
	
	public LoginGrid(ResourceBundle resource, String css) {
		super(resource);
		loginResource = resource;
		this.css = css;
		entryMap = new LinkedHashMap<>();
		dataMap = new LinkedHashMap<>();
		addValues();
		addToGrid(entryMap);
	}
	
	public void addValues(){
		TextField username = new TextField();
		Text user = new Text(loginResource.getString("username"));
		user.setId("text");
		entryMap.put(user, username);
		dataMap.put("username", username);
		
		PasswordField password = new PasswordField();
		Text pass = new Text(loginResource.getString("password"));
		pass.setId("text");
		entryMap.put(pass, password);
		dataMap.put("password", password);
	}
}
