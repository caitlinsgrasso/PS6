package base;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import domain.PersonDomainModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person_Test {
		
	private static PersonDomainModel person1;
	private static UUID person1UUID = UUID.randomUUID();			
	
	@BeforeClass
	public static void personInstance() throws Exception{
		
		Date person1Birth = null;

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		 person1 = new PersonDomainModel();
		 
		try {
			person1Birth = dateFormat.parse("1994-11-27");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		person1.setPersonID(person1UUID);
		person1.setFirstName("Mingkun");
		person1.setMiddleName("a");
		person1.setLastName("Chen");
		person1.setBirthday(person1Birth);
		person1.setCity("Elkton");
		person1.setStreet("702 Stone Gate Blvd");
		person1.setPostalCode(21921);
		
	}
	
	@After
	public void tearDown() throws Exception {
		PersonDomainModel per;
		per = PersonDAL.getPerson(person1UUID);
		PersonDAL.deletePerson(per.getPersonID());
	}
	
	@Test
	public void addPersonTest(){
		PersonDomainModel per;
		per = PersonDAL.getPerson(person1UUID);
		PersonDAL.addPerson(per);
		assertTrue(per.getPersonID()==person1UUID);
	}
	
	@Test
	public void deletePersonTest(){
		PersonDomainModel per;
		per = PersonDAL.getPerson(person1UUID);
		PersonDAL.addPerson(per);
		
		PersonDAL.deletePerson(per.getPersonID());
		assertNull(per);
	}
		
	@Test 
	public void updatePersonTest(){
		final String firstName = "John";
		PersonDomainModel per;
		per = PersonDAL.getPerson(person1UUID);
		PersonDAL.addPerson(per);
		
		per.setFirstName(firstName);
		PersonDAL.updatePerson(per);
		
		assertTrue(per.getFirstName()==firstName);
		
	}

}
