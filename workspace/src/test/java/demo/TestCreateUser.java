package demo;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.json.simple.JSONObject;

import io.restassured.http.ContentType;


public class TestCreateUser{
	
	@Test
	public void create_user() {
		
		baseURI = "https://reqres.in/api/";
		
		JSONObject request = new JSONObject();
		request.put("name", "GK");
		request.put("job", "Team Lead");
		
		System.out.println(request.toJSONString());

		String json_String = request.toJSONString();
		
		given().
			header("Content-Type","application/json").
			contentType(ContentType.JSON).
			accept(ContentType.JSON).
			body(json_String).
		when().
			post("/users").
		then().
			statusCode(201).body("name",equalTo("GK"), "job",equalTo("Team Lead")).
			log().all();
	} 
	
}



