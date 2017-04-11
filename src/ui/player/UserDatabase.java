package ui.player;

import java.util.ArrayList;
import java.util.Collection;

import com.thoughtworks.xstream.XStream;

public class UserDatabase {
	
	private Collection<User> database = new ArrayList<User>();
	
	public Collection<User> getDatabase(){
		return database;
	}
	
	public void addUser(User newUser) {
		database.add(newUser);
	}
	
	public void deleteUser(User delete) {
		database.remove(delete);
	}
}