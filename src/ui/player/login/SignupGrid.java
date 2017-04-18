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
	private String css;
	
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
	
	public SignupGrid(ResourceBundle resource, String css){
		super(resource);
		loginResource = resource;
		entryMap = new LinkedHashMap<>();
		dataMap = new LinkedHashMap<>();
		addValues();
		addToGrid(entryMap);
		this.css = css;
	}
	
	public void addValues(){
		TextField username = new TextField();
		Text user = new Text(loginResource.getString("username"));
		user.setId("text");
		entryMap.put(user, username);
		dataMap.put("username", username);
		
		//addTextField(loginResource.getString("username"), new TextField());
		addTextField(loginResource.getString("password"), new PasswordField());
		addTextField(loginResource.getString("reenter"), new PasswordField());
		addTextField(loginResource.getString("email"), new TextField());
		addTextField(loginResource.getString("reemail"), new TextField());
	}
	
	private void addTextField(String s, TextField field){
		TextField textfield = field;
		Text text = new Text(s);
		entryMap.put(text, textfield);
		dataMap.put("username", textfield);
		text.setId("text");
	}
}