package ui.player;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;


//tweaked code from Veera @ https://veerasundar.com/blog/2010/09/storing-passwords-in-java-web-application/
public class Passwords {

	private Map<String, String> database = new HashMap<>();
	public static final String SALT = "anngelyque";

	public Boolean existingUserCheck(String username) {
		return database.containsKey(username);
	}
	
	public void signup(String username, String password) {
		String hashedPassword = SALTandHashPassword(password);
		database.put(username, hashedPassword);
	}

	public Boolean login(String username, String password) {
		Boolean isAuthenticated = false;
		String hashedPassword = SALTandHashPassword(password);

		String storedPasswordHash = database.get(username);
		if(hashedPassword.equals(storedPasswordHash)) isAuthenticated = true;
		return isAuthenticated;
	}

	private String SALTandHashPassword(String password){
		String saltedPassword = SALT + password;
		String hashedPassword = generateHash(saltedPassword);
		return hashedPassword;
	}
	
	public String generateHash(String input) {
		StringBuilder hash = new StringBuilder();

		try {
			MessageDigest sha = MessageDigest.getInstance("SHA-1");
			byte[] hashedBytes = sha.digest(input.getBytes());
			char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
					'a', 'b', 'c', 'd', 'e', 'f' };
			for (int idx = 0; idx < hashedBytes.length; idx++) {
				byte b = hashedBytes[idx];
				hash.append(digits[(b & 0xf0) >> 4]);
				hash.append(digits[b & 0x0f]);
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			System.out.println("Hashing Error");
			// handle error here.
		}

		return hash.toString();
	}

}