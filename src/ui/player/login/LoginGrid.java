package ui.player.login;


import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class LoginGrid extends DataEntryGrid{

	private Map<Text, TextField> entryMap;
	private ResourceBundle loginResource;
	
	public TextField getUsername() {
		//System.out.println(entryMap.get(loginResource.getString("username")).getText());
		return entryMap.get(loginResource.getString("username"));
	}
	
	public TextField getPassword() {
		return entryMap.get(loginResource.getString("password"));
	}
	
	
	@Override
	public Map<Text, TextField> getEntryMap() {
		return entryMap;
	}
	
	public LoginGrid(ResourceBundle resource) {
		super(resource);
		loginResource = resource;
		entryMap = new LinkedHashMap<>();
		addValues();
		addToGrid(entryMap);
	}
	
	public void addValues(){
		entryMap.put(new Text(loginResource.getString("username")), new TextField("hello"));
		entryMap.put(new Text(loginResource.getString("password")), new PasswordField());
	}
}
