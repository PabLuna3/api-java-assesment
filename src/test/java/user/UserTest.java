package user;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.api.assessment.pack.contact.Contact;
import com.api.assessment.pack.user.User;
import com.api.assessment.pack.user.UserService;


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
	void createOpportunity() {
		
		UserService validUser =  new UserService("pablo@solera", "haSvd12863tx");
		//addCustomer has to receive all the information about the customer, and the information about the first Contact.
		assertEquals(0, validUser.numberOfCustomers("pablo@solera"));
		assertEquals(true, validUser.addCustomer("pablo@solera", "Antonio", "antonio@email","solera", "HR", "29-11-2022", "Phone Call", "Talked about new hardware"));
		assertEquals("Antonio", validUser.getCustomers("pablo@solera").get(0).getName());
		
	}
	@Test
	void createOpportunityEmptyFields(){
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
	//In the case there is a new opportunity with the same email as a current opportunity/customer, 
	//we add the contact to the existing opportunity/customer.
	//In this test, we have one opportunity with one contact. After a new call to addCustomer,
	// that customer will have 2 contacts.
	@Test
	void createCustomerWithSameEmail() {
		UserService validUser =  new UserService("pablo@solera", "haSvd12863tx");
		validUser.addCustomer("pablo@solera", "Antonio", "antonio@email","solera", "HR", "29-11-2022", "Phone Call", "Talked about new hardware");
		assertEquals(1, validUser.getCustomers("pablo@solera").get(0).numberOfContacts());
		validUser.addCustomer("pablo@solera", "Antonio", "antonio@email","solera", "HR", "30-11-2022", "Phone Call", "Talked about prices");
		assertEquals(2, validUser.getCustomers("pablo@solera").get(0).numberOfContacts());
	}
	
	@Test
	void returnUserGivenValidUserName() {
		//For test purposes, anytime we create a UserService, an user with the username "pablo@solera" and password "azul" is created.
		
		UserService validService = new UserService();
		User testUser = validService.getUser("pablo@solera");
		assertEquals("pablo@solera", testUser.getUsername());
		assertEquals("azul", testUser.getPassword());
		assertEquals(0, testUser.getCustomers().size());
	}
	@Test
	void returnUserGivenInvalidUserName() {
		UserService validService = new UserService();
		User testUser = validService.getUser("maria@solera");
		assertEquals(null, testUser);
	}
	
	@Test
	void returnOpportunitiesListGivenUser() {
		UserService validService = new UserService();
		assertEquals(0,validService.getOpportunities("pablo@solera").size());
		
		validService.addCustomer("pablo@solera", "Antonio", "antonio@email","solera", "HR", "29-11-2022", "Phone Call", "Talked about new hardware");
		assertEquals(1,validService.getOpportunities("pablo@solera").size());
	}
	
	@Test
	void changeOpportunityStatusToClient() {
		UserService validService = new UserService();
		
		validService.addCustomer("pablo@solera", "Antonio", "antonio@email","solera", "HR", "29-11-2022", "Phone Call", "Talked about new hardware");
		assertEquals(true, validService.turnOpportunityIntoClient("pablo@solera", "antonio@email"));
	}
	
	@Test
	void changeOpportunityStatusToClientGivenInvalidEmail() {
		UserService validService = new UserService();
		
		validService.addCustomer("pablo@solera", "Antonio", "antonio@email","solera", "HR", "29-11-2022", "Phone Call", "Talked about new hardware");
		assertEquals(false, validService.turnOpportunityIntoClient("pablo@solera", "isabel@email"));
	}
	
	@Test
	void returnClientsListGivenUser() {
		UserService validService = new UserService();
		assertEquals(0,validService.getClients("pablo@solera").size());
		
		validService.addCustomer("pablo@solera", "Antonio", "antonio@email","solera", "HR", "29-11-2022", "Phone Call", "Talked about new hardware");
		validService.turnOpportunityIntoClient("pablo@solera", "antonio@email");
		assertEquals(1,validService.getClients("pablo@solera").size());
	}
	
	@Test
	void addContactToValidOpportunity() {
		UserService validService = new UserService();
		validService.addCustomer("pablo@solera", "Antonio", "antonio@email","solera", "HR", "29-11-2022", "Phone Call", "Talked about new hardware");
		
		
		assertEquals(true,validService.addContact("pablo@solera", "antonio@email", "31-12-2022", "Video Conference", "Talked about coffee"));
		assertEquals(2,validService.getCustomer("pablo@solera", "antonio@email").getContacts().size());
	}
	
	@Test
	void addContactToInvalidOpportunity() {
		UserService validService = new UserService();
		validService.addCustomer("pablo@solera", "Antonio", "antonio@email","solera", "HR", "29-11-2022", "Phone Call", "Talked about new hardware");
		
		
		assertEquals(false,validService.addContact("pablo@solera", "antonio", "31-12-2022", "Video Conference", "Talked about coffee"));
		assertEquals(1,validService.getCustomer("pablo@solera", "antonio@email").getContacts().size());
	}
	
	@Test
	void deleteContactGivenValidCustomer() {
		UserService validService = new UserService();
		validService.addCustomer("pablo@solera", "Antonio", "antonio@email","solera", "HR", "29-11-2022", "Phone Call", "Talked about new hardware");
		validService.addContact("pablo@solera", "antonio@email", "31-12-2022", "Video Conference", "Talked about coffee");
		Contact tempContact = new Contact("29-11-2022", "Phone Call", "Talked about new hardware");
		assertEquals(true,validService.deleteContact("pablo@solera", "antonio@email", tempContact));
		assertEquals(1, validService.getCustomer("pablo@solera", "antonio@email").getContacts().size());
		
	}
	@Test
	void deleteContactGivenInvalidCustomer() {
		UserService validService = new UserService();
		validService.addCustomer("pablo@solera", "Antonio", "antonio@email","solera", "HR", "29-11-2022", "Phone Call", "Talked about new hardware");
		validService.addContact("pablo@solera", "antonio@email", "31-12-2022", "Video Conference", "Talked about coffee");
		Contact tempContact = new Contact("29-11-2022", "Phone Call", "Talked about new hardware");
		assertEquals(false,validService.deleteContact("pablo@solera", "sara@email", tempContact));
		assertEquals(2, validService.getCustomer("pablo@solera", "antonio@email").getContacts().size());
	}
	
	@Test
	void deleteContactGivenInvalidContact() {
		UserService validService = new UserService();
		validService.addCustomer("pablo@solera", "Antonio", "antonio@email","solera", "HR", "29-11-2022", "Phone Call", "Talked about new hardware");
		validService.addContact("pablo@solera", "antonio@email", "31-12-2022", "Video Conference", "Talked about coffee");
		Contact tempContact = new Contact("2-11-2022", "Phone Call", "Talked about new hardware");
		assertEquals(false,validService.deleteContact("pablo@solera", "antonio@email", tempContact));
		assertEquals(2, validService.getCustomer("pablo@solera", "antonio@email").getContacts().size());
	}
	
	@Test
	void deleteCustomerGivenValidCustomer() {
		UserService validService = new UserService();
		validService.addCustomer("pablo@solera", "Antonio", "antonio@email","solera", "HR", "29-11-2022", "Phone Call", "Talked about new hardware");
		assertEquals(true,validService.deleteCustomer("pablo@solera", "antonio@email"));
		assertEquals(0, validService.getCustomers("pablo@solera").size());
	}
	@Test
	void deleteCustomerGivenInvalidCustomer() {
		UserService validService = new UserService();
		validService.addCustomer("pablo@solera", "Antonio", "antonio@email","solera", "HR", "29-11-2022", "Phone Call", "Talked about new hardware");
		assertEquals(false,validService.deleteCustomer("pablo@solera", "sara@email"));
		assertEquals(1, validService.getCustomers("pablo@solera").size());
	}
	
	@Test
	void deleteCustomerAfterDeletingLastContact() {
		UserService validService = new UserService();
		validService.addCustomer("pablo@solera", "Antonio", "antonio@email","solera", "HR", "29-11-2022", "Phone Call", "Talked about new hardware");
		Contact tempContact = new Contact("29-11-2022", "Phone Call", "Talked about new hardware");
		validService.deleteContact("pablo@solera", "antonio@email", tempContact);
		assertEquals(0, validService.getCustomers("pablo@solera").size());
	}
}
