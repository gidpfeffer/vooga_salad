package ui.handlers;

import ui.player.users.User;

public interface LoginHandler {
	
	public User getActiveUser();
	public void showProfile();
	public void returnToMain();
	public void gotoSignupPage();
	public Boolean login(String username, String password);
	public User findUser(String username);
	public void setActiveUser(User user);
	public void gotoAuth();
	public void gotoGameSelector();
	public void gotoReviews();
	public void setCornerProfileCard(User user);
}
