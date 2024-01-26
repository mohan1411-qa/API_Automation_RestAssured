package api.endpoints;

import static io.restassured.RestAssured.*;
import api.payloads.user.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserUseCases {

	public static Response create_user(User payload) {
		
		System.out.println(payload);
		
		Response response = given().log().all().accept(ContentType.JSON).contentType(ContentType.JSON).header("Content-Type","application/json").body(payload).when()
				.post(Routes.post_user_url);
		System.out.println(response);

		return response;

	}

	public static Response get_user(String user_name) {

		Response response = given().pathParam("username", user_name).when().get(Routes.get_user_url);
		
		return response;

	}

	public static Response delete_user(String user_name) {

		Response response = given().pathParam("username", user_name).when().delete(Routes.delete_user_url);

		return response;

	}

	public static Response update_user(User payload, String username) {

		Response response = given().accept(ContentType.JSON).contentType(ContentType.JSON).body(payload)
				.pathParam(username, username).when().post(Routes.post_user_url);

		return response;

	}

}
