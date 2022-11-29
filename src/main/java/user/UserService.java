package user;

import java.util.ArrayList;
import java.util.List;

public class UserService {

	
	private List<User> users = new ArrayList<>();
	
	public UserService(String username, String password) {
		users.add( new User(username, password));
	}
	
	
	public static boolean ValidateLogin(String username, String password) {
		//Implement
		return true;
		
	}
}
