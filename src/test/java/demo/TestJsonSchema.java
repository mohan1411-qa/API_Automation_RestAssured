package demo;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;
import org.testng.annotations.Test;

public class TestJsonSchema {
	
	
	@Test
	public void test_list_user_json_schema() {
		
		baseURI = "https://reqres.in/api/";
		
		given().
			get("users?page=2").
		then().
			statusCode(200)
			.assertThat()
			.body(matchesJsonSchemaInClasspath("list_user_schema.json"))
			.statusCode(200);
		
	} 

}
