package com.maveric.project.test;

import static org.hamcrest.CoreMatchers.equalToObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.apache.http.HttpStatus;
import org.hamcrest.CoreMatchers;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maveric.project.pojos.Employee;
import com.maveric.project.pojos.EmployeeCsvPojo;
import com.maveric.project.pojos.EmployeePojo;
import com.opencsv.bean.CsvToBeanBuilder;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class RestAPITest {

	@BeforeClass
	public static void setUpEnv() {
		RestAssured.baseURI = "https://dummy.restapiexample.com/api/v1";
	}

	@Ignore
	@Test(testName = "Get all the Employees Details")
	public <employees> void test1() throws JsonMappingException, JsonProcessingException  {
//		// Normal Respone
//		Response respone = RestAssured.get("/employees");
//		Assert.assertEquals(respone.getStatusCode(), 200);

		// validate respone
		String responseBody = RestAssured.get("/employees").then().assertThat().statusCode(200).log().body().toString();
		System.out.println(responseBody);
		
		ObjectMapper objectMapper = new ObjectMapper();
		EmployeePojo employee = objectMapper.readValue(responseBody, EmployeePojo.class);
		
		System.out.println("Name: " + employee.getName());
		System.out.println("Salary: " + employee.getSalary());
		System.out.println("Age: " + employee.getAge());
      
	}		
		
		
		
	
    
	@Test(testName = "Get all the Employees Details")
	public void test2() {

		RestAssured.get("/employee/1").then().assertThat().statusCode(200).body("employee_name", equalToObject("Tiger Nixon"));
	}
    
   
	@Test(testName = "Add new Employees Details")
	public void test4() {
       
		String payload="{name:test,salary:123,age:23}";
		
		RestAssured.given()
		.contentType(ContentType.JSON)
		.body(payload)
		.post("/create")
		.then()
		.assertThat()
		.statusCode(HttpStatus.SC_CREATED)
		.and()
		.body("meassage",equalToObject("Successfully! Record has been added."));
		
		

	}
	@Ignore
	@Test(testName = "Add new Employees Details")
	public void test5() {
       
		HashMap<String, String> payLoad= new HashMap<>();
		payLoad.put("name", "Satish");
		payLoad.put("salart", "111");
		payLoad.put("age", "40");
		
		
		 RestAssured.given()
		.contentType(ContentType.JSON)
		.body(payLoad)
		.post("/create")
		.then()
		.assertThat()
		.and()
		.body(CoreMatchers.containsString("Succesfully"));
		

	}
	@Ignore
	@Test(testName = "Add new Employees Details using employee.json file")
	public void test7() throws DatabindException, IOException   {
       
		ObjectMapper mapper = new ObjectMapper();
		
		FileInputStream stream = new FileInputStream(new File("./TestData/EmployeeData.json"));
		EmployeePojo payLoad = mapper.readValue(stream,EmployeePojo.class);
		
		RestAssured.given()
		.contentType(ContentType.JSON)
		.body(payLoad)
		.post("/create")
		.then()
		.assertThat().body("message",equalToObject("Successfully! Record has been added."));

	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test(testName = "Add new employee details using employee csv file")
	public void test8() throws DatabindException, IOException {
		String uri = "/create";
 
		/*
		 * FileReader reader = new FileReader("./TestData/EmployeeMockData.csv");
		 * CsvToBeanBuilder<EmployeeCsvPojo> beanBuilder = new CsvToBeanBuilder(reader);
		 * beanBuilder = beanBuilder.withType(EmployeeCsvPojo.class);
		 * CsvToBean<EmployeeCsvPojo> toBean = beanBuilder.build();
		 * List<EmployeeCsvPojo> beans = toBean.parse();
		 */
 
		List<EmployeeCsvPojo> beans = new CsvToBeanBuilder(new FileReader("./TestData/EmployeeMockData.csv")) // example of builder pattern
				.withType(EmployeeCsvPojo.class).build().parse();
 
		  RestAssured.given() 
		  .contentType(ContentType.JSON) .
		  body(beans) .
		  post(uri)
		  .then() 
		  .assertThat().
		  body("message",  equalToObject("Successfully! Record has been added."));
	}
	
	@Test(testName="update employee details")
	public void test9() {
		  Employee updatedEmployee = new Employee("John Doe",7500, 31);
		
		 RestAssured.given()
		.contentType(ContentType.JSON)
		.body(updatedEmployee)
		.put("/employees/1")
		.then()
		.assertThat()
		.statusCode(200)
		.and()
		.body("meassage",equalToObject("Successfully! Record has been updated."));
		
	}
	
	
	@AfterClass
	public static void tearDownEnv() {
		RestAssured.reset();
	}
}
