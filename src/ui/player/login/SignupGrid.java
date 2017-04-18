package ui.player.login;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class SignupGrid extends DataEntryGrid{

	private Map<Text, TextField> entryMap;
	private Map<String, TextField> dataMap;
	private ResourceBundle loginResource;
	
	public TextField getUsername() {
		return dataMap.get("username");
	}
	
	public TextField getPassword() {
		return dataMap.get("password");
	}
	
	public TextField getRePassword() {
		return dataMap.get("repassword");
	}
	
	public TextField getEmail() {
		return dataMap.get("email");
	}
	
	public TextField getReEmail() {
		return dataMap.get("reemail");
	}
	
	@Override
	public Map<Text, TextField> getEntryMap() {
		return entryMap;
	}
	
	public SignupGrid(ResourceBundle resource){
		super(resource);
		loginResource = resource;
		entryMap = new LinkedHashMap<>();
		dataMap = new LinkedHashMap<>();
		addValues();
		addToGrid(entryMap);
	}
	
	public void addValues(){
		TextField username = new TextField();
		entryMap.put(new Text(loginResource.getString("username")), username);
		dataMap.put("username", username);
		
		PasswordField password = new PasswordField();
		entryMap.put(new Text(loginResource.getString("password")), password);
		dataMap.put("password", password);
		
		PasswordField reenter = new PasswordField();
		entryMap.put(new Text(loginResource.getString("reenter")), reenter);
		dataMap.put("repassword", reenter);
		
		TextField email = new TextField();
		entryMap.put(new Text(loginResource.getString("email")), email);
		dataMap.put("email", email);
		
		TextField reemail = new TextField();
		entryMap.put(new Text(loginResource.getString("reemail")), reemail);
		dataMap.put("reemail", reemail);
	}
}