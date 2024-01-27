package demo;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class TestUpdateUser {
	
	@Test
	public void test_update_user() {
		
		baseURI = "https://reqres.in/api/";
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("name", "Ravi");
		jsonObj.put("job", "Team Lead");
		
		String jsonString = jsonObj.toString();
		
		given().
			accept(ContentType.JSON).
			header("Content-Type","application/json").
			body(jsonString).
			contentType(ContentType.JSON).
		when().
			put("/users/2").then().statusCode(204).body("name", equalTo("Ravi"));
		
	}
	
	@Test
	public void test_delete_user() {
		
		baseURI = "https://reqres.in/api/";
		
		
		given().
			delete("/users/2").then().statusCode(203);
		
	}
	

}
