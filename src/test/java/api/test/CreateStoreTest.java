package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.StoreUseCases;
import api.endpoints.UserUseCases;
import api.payloads.user.CreateStore;
import api.payloads.user.User;
import io.restassured.response.Response;

public class CreateStoreTest {
	
	Faker faker;
	CreateStore create_store_payload;
	public Logger logger;
	
	
	@BeforeTest
	public void set_up()
	{
		faker = new Faker();
		create_store_payload = new CreateStore();
		logger = LogManager.getLogger(this.getClass());
		
		create_store_payload.setId(2);
		create_store_payload.setPetId(faker.idNumber().hashCode());
		create_store_payload.setQuantity(2);
		create_store_payload.setStatus("approved");
		create_store_payload.setComplete(true);
}
	@Test
	public void create_store_test() {
		
			
		Response response = StoreUseCases.create_store(create_store_payload);
		response.then().log().all();
		Assert.assertEquals(200, response.getStatusCode());
		Assert.assertNotNull("message", response);
}
}
