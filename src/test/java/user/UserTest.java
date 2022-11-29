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
	
	@Test
	void createCustomer() {
		
		UserService validUser =  new UserService("pablo@solera", "haSvd12863tx");
		//addCustomer has to receive all the information about the customer, and the information about the first Contact.
		assertEquals(0, validUser.numberOfCustomers("pablo@solera"));
		assertEquals(true, validUser.addCustomer("pablo@solera", "Antonio", "antonio@email","solera", "HR", "29-11-2022", "Phone Call", "Talked about new hardware"));
		assertEquals("Antonio", validUser.getCustomers("pablo@solera").get(0).getName());
		
	}
	@Test
	void createCustomerEmptyFields(){
		UserService validUser =  new UserService("pablo@solera", "haSvd12863tx");
		validUser.addCustomer("pablo@solera", "Antonio", "antonio@email","solera", "HR", "29-11-2022", "Phone Call", "Talked about new hardware");
		assertEquals(false, validUser.addCustomer("pablo@solera", "", "antonio@email", "solera", "HR", "29-11-2022", "Phone Call", "Talked about new hardware"));
		assertEquals(false, validUser.addCustomer("pablo@solera", "Antonio", "", "solera", "HR", "29-11-2022", "Phone Call", "Talked about new hardware"));
		assertEquals(false, validUser.addCustomer("pablo@solera", "Antonio", "antonio@email", "", "HR", "29-11-2022", "Phone Call", "Talked about new hardware"));
		assertEquals(false, validUser.addCustomer("pablo@solera", "Antonio", "antonio@email", "solera", "", "29-11-2022", "Phone Call", "Talked about new hardware"));
		assertEquals(false, validUser.addCustomer("pablo@solera", "Antonio", "antonio@email", "solera", "HR", "", "Phone Call", "Talked about new hardware"));
		assertEquals(false, validUser.addCustomer("pablo@solera", "Antonio", "antonio@email", "solera", "HR", "29-11-2022", "", "Talked about new hardware"));
		assertEquals(1, validUser.numberOfCustomers("pablo@solera"));
		assertEquals(true, validUser.addCustomer("pablo@solera", "Manolo", "manolo@email", "solera", "HR", "29-11-2022", "Phone Call", ""));
		
	}
}
