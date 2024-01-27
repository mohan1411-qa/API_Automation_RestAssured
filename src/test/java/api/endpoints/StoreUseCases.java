package api.endpoints;

import static io.restassured.RestAssured.*;

import java.util.ResourceBundle;

import api.payloads.user.CreateStore;
import api.payloads.user.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class StoreUseCases {
	
	public static ResourceBundle get_url() {
		
		ResourceBundle routes = ResourceBundle.getBundle("routes");
		return routes;
		
	}

	public static Response create_store(CreateStore payload) {
		
		String create_pet_url = get_url().getString("place_order_pet");
		
		System.out.println(payload);
		
		Response response = given().log().all().accept(ContentType.JSON).contentType(ContentType.JSON).header("Content-Type","application/json").body(payload).when()
				.post(create_pet_url);
		System.out.println(response);

		return response;

	}
}