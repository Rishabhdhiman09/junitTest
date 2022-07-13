package junitPoc;

public class Contact {
	private String firstName;
	private String lastName;
	private String phoneNumber;
	
	
	public Contact(String firstName, String lastName, String phoneNumber) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public void validatePhoneNumber() {
		// TODO Auto-generated method stub
		if (this.phoneNumber.isBlank())
			throw new RuntimeException("invalid");
		
		if (this.phoneNumber.length() != 10) {
            throw new RuntimeException("Phone Number Should be 10 Digits Long");
        }
        if (!this.phoneNumber.matches("\\d+")) {
            throw new RuntimeException("Phone Number Contain only digits");
        }
        if (!this.phoneNumber.startsWith("0")) {
            throw new RuntimeException("Phone Number Should Start with 0");
        }
	}
	public void validateFirstName() {
		// TODO Auto-generated method stub
		if (this.firstName.isBlank())
			throw new RuntimeException("invalid");
	}
	public void validateLastName() {
		// TODO Auto-generated method stub
		if (this.lastName.isBlank())
			throw new RuntimeException("invalid");
	}
	
	
}
