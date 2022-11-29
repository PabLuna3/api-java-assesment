package user;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


/**
 * 
 * @author Pablo.Luna
 * test cases for the API Controller using TTD method.
 */
class UserTest {

	@Test
	void Login() {
		//A valid username in the system will be "pablo@solera" with a password "haSvd12863tx."
		
		UserService validUser =  new UserService("pablo@solera", "haSvd12863tx");
		
		assertEquals(false, validUser.ValidateLogin("pablo@soltera", "1234"));
		assertEquals(true, validUser.ValidateLogin("pablo@solera", "haSvd12863tx"));
	}

	
	@Test
	void LoginUserNameEmpty() {
		//A valid username in the system will be "pablo@solera" with a password "haSvd12863tx."
		
		UserService validUser =  new UserService("pablo@solera", "haSvd12863tx");
		
		assertEquals(false, validUser.ValidateLogin("", "haSvd12863tx"));
	}
	
	@Test
	void LoginPasswordEmpty() {
		//A valid username in the system will be "pablo@solera" with a password "haSvd12863tx."
		
		UserService validUser =  new UserService("pablo@solera", "haSvd12863tx");
		
		assertEquals(false, validUser.ValidateLogin("pablo@solera", ""));
	}
}
