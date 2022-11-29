package user;

import java.util.ArrayList;
import java.util.List;

public class UserService {

	
	private static List<User> users = new ArrayList<>();
	
	public UserService(String username, String password) {
		users.add( new User(username, password));
	}
	
	
	public static boolean ValidateLogin(String username, String password) {
		boolean flag = false;
		for(int i = 0; i < users.size();i++) {
			if((users.get(i).getUsername().equals(username)) && (users.get(i).getPassword().equals(password))) flag = true;
		}
		
		return flag;
		
		
	}
}
