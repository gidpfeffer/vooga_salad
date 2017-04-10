package ui.player.login;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class SignupGrid extends DataEntryGrid{

	private Map<Text, TextField> entryMap;
	private ResourceBundle loginResource;
	
	public TextField getUsername() {
		return entryMap.get("username");
	}
	
	public TextField getPassword() {
		return entryMap.get("password");
	}
	
	public TextField getRePassword() {
		return entryMap.get("repassword");
	}
	
	public TextField getEmail() {
		return entryMap.get("email");
	}
	
	public TextField getReEmail() {
		return entryMap.get("reemail");
	}
	
	@Override
	public Map<Text, TextField> getEntryMap() {
		return entryMap;
	}
	
	public SignupGrid(ResourceBundle resource){
		super(resource);
		loginResource = resource;
		entryMap = new LinkedHashMap<>();
		addValues();
		addToGrid(entryMap);
	}
	
	public void addValues(){
		entryMap.put(new Text(loginResource.getString("username")), new TextField());
		entryMap.put(new Text(loginResource.getString("password")), new PasswordField());
		entryMap.put(new Text(loginResource.getString("reenter")), new PasswordField());
		entryMap.put(new Text(loginResource.getString("email")), new TextField());
		entryMap.put(new Text(loginResource.getString("reemail")), new TextField());
	}
	
}