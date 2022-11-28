package user;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {


	private UserService userService;
	
	
	public UserController(String username, String password) {
		this.userService = new UserService(username, password);
	}
	
	
	
	
	
}
