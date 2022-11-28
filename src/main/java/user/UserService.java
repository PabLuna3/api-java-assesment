package user;

public class UserService {

	
	private User user;
	
	public UserService(String username, String password) {
		user = new User(username, password);
	}
	
	
	public boolean ValidateLogin(String username, String password) {
		
		//return ((user.getUsername().equals(username)) && (user.getPassword().equals(password))) ? true : false;
		return true;
		
	}
}
