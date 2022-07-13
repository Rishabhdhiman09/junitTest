package junitPoc;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

public class ContactManagerTest {
	
	ContactManager cm;
	
	@BeforeAll
	public static void setupAll() {
		System.out.println("Should print before all test");
	}
	
	@BeforeEach
	public void setup() {
		cm = new ContactManager();
	}
	@Test
	public void shouldCreateContact() {
		
		
		cm.addContact("Rishabh", "Dhiman", "0123456789");
		Assertions.assertFalse(cm.getAllContacts().isEmpty());
		Assertions.assertEquals(1, cm.getAllContacts().size());
		
	}
	
	@Test
	@DisplayName("Should not add contact when firstname is null")
	public void shouldThrowRunTimeExceptionWhenFirstNameIsNull() {
		
		Assertions.assertThrows(
				RuntimeException.class, ()-> {
					cm.addContact(null, "Dhiman", "0123456789");
				});
				
	}
	
	@Test
	@DisplayName("Should not add contact when lastname is null")
	public void shouldThrowRunTimeExceptionWhenLastNameIsNull() {
		
		Assertions.assertThrows(
				RuntimeException.class, ()-> {
					cm.addContact("Rishabh", null, "0123456789");
				});
				
	}
	
	@Test
	@DisplayName("Should not add contact when phone number is null")
	public void shouldThrowRunTimeExceptionWhenPhoneNumberIsNull() {
		
		Assertions.assertThrows(
				RuntimeException.class, ()-> {
					cm.addContact("Rishabh", "Dhiman", null);
				});
				
	}
	
	@AfterEach
	public void teardown() {
		System.out.println("Should print after each test");
	}
	
	@AfterAll
	public static void teardownAll() {
		System.out.println("Should print after all test");
	}
	
	@Test
	@DisplayName("Should create contact only on windows")
	@EnabledOnOs(value = OS.WINDOWS, disabledReason = "Only work on windows")
	public void shouldOnlyWorkOnWindows() {
		
		cm.addContact("Rishabh", "Dhiman", "0123456789");
		Assertions.assertFalse(cm.getAllContacts().isEmpty());
		Assertions.assertEquals(1, cm.getAllContacts().size());
		
	}
	
	// assumptions: it is used if there is condion in which if test case fails then 
	// also we dont want to throw error we want to abort that test case
	@Test
	@DisplayName("Should create contact only on TEST")
	public void shouldTestOnAssumedValue() {
		Assumptions.assumeTrue("TEST".equals("PROD"));
		cm.addContact("Rishabh", "Dhiman", "0123456789");
		Assertions.assertFalse(cm.getAllContacts().isEmpty());
		Assertions.assertEquals(1, cm.getAllContacts().size());
		
	}
	

	
	
	
	
	
	
	@Nested
	class RepeatedNestedTest{
		
		@DisplayName("Repeat Contact creation test 5 times")
		@RepeatedTest(value = 5, 
		name = "Repeating contact creation test {currentRepetition} of {totalRepetitions}")
		public void shouldTestMultipleTimes() {
			cm.addContact("Rishabh", "Dhiman", "0123456789");
			Assertions.assertFalse(cm.getAllContacts().isEmpty());
			Assertions.assertEquals(1, cm.getAllContacts().size());
			
		}
	}
	
	@Nested
	class ParameterizedNestedTest{
		@DisplayName("Parameterized test with value source")
		@ParameterizedTest
		@ValueSource(strings = {"0123456789", "0121212121"})
		public void shouldParametrizedTest(String phoneNumber) {
			cm.addContact("Rishabh", "Dhiman", phoneNumber);
			Assertions.assertFalse(cm.getAllContacts().isEmpty());
			Assertions.assertEquals(1, cm.getAllContacts().size());
			
		}
		
		@DisplayName("Parameterized test with method source")
		@ParameterizedTest
		@MethodSource("phoneNumbers")
		public void shouldParametrizedTestWithMethodSource(String phoneNumber) {
			cm.addContact("Rishabh", "Dhiman", phoneNumber);
			Assertions.assertFalse(cm.getAllContacts().isEmpty());
			Assertions.assertEquals(1, cm.getAllContacts().size());
			
		}
		
		private static List<String> phoneNumbers(){  // method must be static
			return Arrays.asList("0123456789", "0121212121");
		}
		
		@DisplayName("Parameterized test with csv source")
		@ParameterizedTest
		@CsvSource({"0123456789", "0121212121"})
		public void shouldParametrizedTestWithCsvSource(String phoneNumber) {
			cm.addContact("Rishabh", "Dhiman", phoneNumber);
			Assertions.assertFalse(cm.getAllContacts().isEmpty());
			Assertions.assertEquals(1, cm.getAllContacts().size());
			
		}
		
		
		@DisplayName("Parameterized test with csv file source")
		@ParameterizedTest
		@CsvFileSource(resources = "/data.csv")
		public void shouldParametrizedTestWithCsvFileSource(String phoneNumber) {
			cm.addContact("Rishabh", "Dhiman", phoneNumber);
			Assertions.assertFalse(cm.getAllContacts().isEmpty());
			Assertions.assertEquals(1, cm.getAllContacts().size());
			
		}
	}
}