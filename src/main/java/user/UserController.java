package user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {


	private UserService userService;
	private String string;
	
	public UserController(String username, String password) {
		this.userService = new UserService(username, password);
	}
	
	@GetMapping("/api/login")
	public boolean Login(String username, String password) {
		
		return UserService.ValidateLogin(username, password);
	
	}
	
	
}
