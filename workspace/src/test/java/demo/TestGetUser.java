 package demo;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TestGetUser {
	
	@Test
	public void test_get_user() {
		
		String url = "https://reqres.in/api/users?page=2";
		
		Response response = RestAssured.get(url);
		
		System.out.println(response.getStatusCode());
		System.out.println(response.getTime());
		System.out.println(response.getBody().asString());
		
		int actual_status_code = 200;
		
		Assert.assertEquals(actual_status_code, 200);
		
	} 
	

}
