package api.test;


import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserUseCases;
import api.payloads.user.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

import com.github.javafaker.Faker;

public class UserDataDrivenTest {
	
	Faker faker;
	
	
	@Test(dataProvider="Data", dataProviderClass=DataProviders.class)
	public void create_multiple_user(String fname,String lname,String email, String password, String phone_number) {
		
		faker = new Faker();
		User user_payload = new User();
		
		user_payload.setId(faker.idNumber().hashCode());
		user_payload.setUsername(faker.name().username());
		user_payload.setFirstName(fname);
		user_payload.setLastName(lname);
		user_payload.setPassword(password);
		user_payload.setEmail(email);
		user_payload.setPhone(phone_number);
		
		Response response = UserUseCases.create_user(user_payload);
		response.then().log().all();
		Assert.assertEquals(200, response.getStatusCode());
		
		// Validate user is created
		Response get_user_response = UserUseCases.get_user(user_payload.getUsername());
		get_user_response.then().log().all();
		
		Assert.assertEquals(200, get_user_response.getStatusCode());
		Assert.assertEquals(0, 0);
		
		
	}

}
