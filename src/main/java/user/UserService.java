package user;

import java.util.ArrayList;
import java.util.List;

import customer.Customer;

public class UserService {

	
	private  List<User> users = new ArrayList<>();
	
	public UserService(String username, String password) {
		users.add( new User(username, password));
	}
	
	
	public  boolean ValidateLogin(String username, String password) {
		boolean flag = false;
		for(int i = 0; i < users.size();i++) {
			if((users.get(i).getUsername().equals(username)) && (users.get(i).getPassword().equals(password))) flag = true;
		}
		
		return flag;
	}
	//We have to check all the information about the customer is added. Only field that can be left empty is "description"
	public  boolean addCustomer(String userName, String name, String email, String nameCompany, String position, String date, String procedure,
			String description) {
		boolean flag = true;
		//System.out.println("Hola desde crear usuario");
		if(name == null || name.isEmpty() || name.trim().isEmpty()) flag = false;
		if(email == null || email.isEmpty() || email.trim().isEmpty()) flag = false;
		if(nameCompany == null || nameCompany.isEmpty() || nameCompany.trim().isEmpty()) flag = false;
		if(position == null || position.isEmpty() || position.trim().isEmpty()) flag = false;
		if(date == null || date.isEmpty() || date.trim().isEmpty()) flag = false;
		if(procedure == null || procedure.isEmpty() || procedure.trim().isEmpty()) flag = false;
		if(flag) {
			for(int i = 0; i < users.size(); i++) {
				String temp = users.get(i).getUsername();
				if(temp.equals(userName)) {
					User tempUser = users.get(i);
					Customer tempCustomer = new Customer(name,email,nameCompany, position, date, procedure, description);
					tempUser.setCustomers(new Customer(name,email,nameCompany, position, date, procedure, description));
					users.set(i, tempUser);
			
				}
			}
		}
		
		return flag;
	}
	public  List<Customer> getCustomers(String userName){
		for(int i = 0; i < users.size(); i++) {
			if(users.get(i).getUsername().equals(userName)) {
				return users.get(i).getCustomers();
			}
		}
		return null;
	}
	public  int numberOfCustomers(String userName) {
		int counter = 0;
		for(int i = 0; i < users.size(); i++) {
			if(users.get(i).getUsername().equals(userName)) {
				counter = users.get(i).getCustomers().size();
			}
		}
		return counter;
	}
}
