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

	import domain.StudentDomainModel;
	import javafx.beans.property.SimpleStringProperty;
	import javafx.beans.property.StringProperty;

	public class Student_Test {
			
		private static StudentDomainModel Student1;
		private static UUID Student1UUID = UUID.randomUUID();			
		
		@BeforeClass
		public static void StudentInstance() throws Exception{
			
			Date Student1Birth = null;

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			
			 Student1 = new StudentDomainModel();
			 
			try {
				Student1Birth = dateFormat.parse("1994-11-27");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Student1.setStudentID(Student1UUID);
			Student1.setFirstName("Mingkun");
			Student1.setMiddleName("a");
			Student1.setLastName("Chen");

			
		}
		
		@After
		public void tearDown() throws Exception {
			StudentDomainModel stu;
			stu = StudentDAL.getStudent(Student1UUID);
			StudentDAL.deleteStudent(stu.getStudentID());
		}
		
		@Test
		public void addStudentTest(){
			StudentDomainModel stu;
			stu = StudentDAL.getStudent(Student1UUID);
			StudentDAL.addStudent(stu);
			assertTrue(stu.getStudentID()==Student1UUID);
		}
		
		@Test
		public void deleteStudentTest(){
			StudentDomainModel stu;
			stu = StudentDAL.getStudent(Student1UUID);
			StudentDAL.addStudent(stu);
			
			StudentDAL.deleteStudent(stu.getStudentID());
			assertNull(stu);
		}
			
		@Test 
		public void updateStudentTest(){
			final String firstName = "John";
			StudentDomainModel stu;
			stu = StudentDAL.getStudent(Student1UUID);
			StudentDAL.addStudent(stu);
			
			stu.setFirstName(firstName);
			StudentDAL.updateStudent(stu);
			
			assertTrue(stu.getFirstName()==firstName);
			
		}



}
