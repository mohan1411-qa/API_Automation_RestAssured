package api.endpoints;

/*
 Swagger URI -> https://petstore.swagger.io/
 Get User -> https://petstore.swagger.io/
 */

public class Routes {
	
	public static String base_url = "https://petstore.swagger.io/v2";
	
	// User modal api url
	public static String get_user_url = base_url + "/user/{username}";
	public static String post_user_url = base_url + "/user";
	public static String delete_user_url = base_url + "/user/{username}";
	public static String update_user_url = base_url + "/user/{username}";
	
	

}
