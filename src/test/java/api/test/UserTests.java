package api.test;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserUseCases;
import api.payloads.user.User;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class UserTests {
	
	Faker faker;
	User user_payload;
	public Logger logger;
	
	
	@BeforeTest
	public void set_up()
	{
		faker = new Faker();
		user_payload = new User();
		logger = LogManager.getLogger(this.getClass());
		
		user_payload.setId(faker.idNumber().hashCode());
		user_payload.setUsername(faker.name().username());
		user_payload.setFirstName(faker.name().firstName());
		user_payload.setLastName(faker.name().lastName());
		user_payload.setPassword(faker.internet().password(5, 10));
		user_payload.setEmail(faker.internet().safeEmailAddress());
		user_payload.setPhone(faker.phoneNumber().cellPhone());
	}
	
	@Test
	public void create_user_and_get_user_test() {
		
			
		Response response = UserUseCases.create_user(user_payload);
		response.then().log().all();
		Assert.assertEquals(200, response.getStatusCode());
		Assert.assertNotNull("message", response);
		
		System.out.println(this.user_payload.getUsername());
		logger.info(response.asString());
		
		// Validate user is created
		Response get_user_response = UserUseCases.get_user(this.user_payload.getUsername());
		get_user_response.then().log().all();
		
		Assert.assertEquals(200, get_user_response.getStatusCode());
		
		// Get Response Body
		ResponseBody rsp_body = get_user_response.getBody();
		
		//convert response body to string
	     String json_string = rsp_body.asString();
	     
	   //JSON Representation from Response Body
	     JsonPath json_path = get_user_response.jsonPath();
	     
	   //Get value of Location Key
	      String first_name = json_path.get("firstName");
	      Assert.assertEquals(this.user_payload.getFirstName(), first_name);
		
		
	}
	
	@Test
	public void create_user_and_delete_user_test() {
		
		
		Response response = UserUseCases.create_user(user_payload);
		response.then().log().all();
		Assert.assertEquals(200, response.getStatusCode());
		Assert.assertNotNull("message", response);
		System.out.println(this.user_payload.getUsername());
		
		//Log the response 
		logger.info(response.asString());
		// Validate user is created
		Response get_user_response = UserUseCases.delete_user(this.user_payload.getUsername());
		get_user_response.then().log().all();
		
		Assert.assertEquals(200, get_user_response.getStatusCode());
		
	}


}
