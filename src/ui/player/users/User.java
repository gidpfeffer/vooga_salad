package ui.player.users;

public class User implements WriteableUser{
	
	private String username;
	private String password;
	private Double money;
	private String avatar;
	private String email;
	private Integer level;
	private Double experience;
	private String rank;
	private String last;
	private String most;
	
	public String getMostPlayed() {
		return most;
	}
	public void setMostPlayed(String most) {
		this.most = most;
	}
	public String getLastPlayed() {
		return last;
	}
	public void setLastPlayed(String last) {
		this.last = last;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public Double getExperience() {
		return experience;
	}
	public void setExperience(double experience) {
		this.experience = experience;
	}
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
	public Double getHighScore() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
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
		this.experience = 0.0;
		this.level = 1;
		this.rank = "Novice";
		this.last = "";
		this.money = 0.0;
	}
	
}
