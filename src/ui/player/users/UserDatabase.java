package ui.player.users;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

public class UserDatabase {
	
	private Collection<User> database;
	private Passwords passwords;
	private User activeUser;
	
	public UserDatabase() {
		database = new ArrayList<User>();
		passwords = new Passwords();
		activeUser = new User();
		addUser(activeUser);
	}
	
	public User getActiveUser() {
		return activeUser;
	}
	
	public void setActiveUser(User user) {
		activeUser = user;
	}
	
	public Passwords getPasswords(){
		return passwords;
	}
	
	public Collection<User> getDatabase(){
		return database;
	}
	
	public void addUser(User newUser) {
		database.add(newUser);
	}
	
	public void deleteUser(User delete) {
		database.remove(delete);
	}
	
	public Iterator<User> getUsersInExpOrder() {
		return database.stream().sorted(new Comparator<User>() {
			@Override
			public int compare(User o1, User o2) {
				return Double.compare(o1.getExperience(), o2.getExperience());
			}
		}).iterator();
	}
	
}
