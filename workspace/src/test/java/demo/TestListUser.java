package demo;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import io.restassured.response.Response;

public class TestListUser  {
	
	@Test
	public void test_get_user() {
		
		String url = "https://reqres.in/api/users?page=2";
		
		Response response = get(url);
		
		System.out.println(response.getStatusCode());
		System.out.println(response.getTime());
		System.out.println(response.getBody().asString());
		
		int actual_status_code = 200;
		
		Assert.assertEquals(actual_status_code, 200);
		
	} 
	
	@Test
	public void test_list_user() {
		
		baseURI = "https://reqres.in/api/";
		
		given().
			get("users?page=2").
		then().
			statusCode(200).body("data[2].id", equalTo(9), "data[2].first_name", equalTo("Tobias")).
			log().all();
	
		
	} 
	

}