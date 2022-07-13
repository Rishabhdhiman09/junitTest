package junitPoc;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ContactManager {
	
	Map<String, Contact> map = new ConcurrentHashMap<>();
	
	public void addContact(String firstName, String lastName, String phoneNumber) {
		
		Contact c = new Contact(firstName, lastName, phoneNumber);
		validateContact(c);
		checkIfAlreadyExist(c);
		map.put(generateKey(c), c);
		
	}
	
	public Collection<Contact> getAllContacts(){
		return map.values();
	}

	private String generateKey(Contact c) {
		// TODO Auto-generated method stub
		return c.getFirstName()+c.getLastName();
	}

	private void checkIfAlreadyExist(Contact c) {
		// TODO Auto-generated method stub
		if (map.containsKey(generateKey(c)))
			throw new RuntimeException("already exist");
		
		
	}

	private void validateContact(Contact c) {
		c.validateFirstName();
		c.validateLastName();
		c.validatePhoneNumber();
		
	}
}
