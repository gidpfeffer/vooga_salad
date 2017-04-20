package ui.handlers;

import ui.player.users.User;

public interface LoginHandler {
	
	public User getActiveUser();
	
	public void showProfile();
}
