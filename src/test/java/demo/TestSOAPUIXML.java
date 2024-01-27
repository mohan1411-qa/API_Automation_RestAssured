package demo;

import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.matcher.RestAssuredMatchers;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class TestSOAPUIXML {
	
	@Test
	public void test_soap_xml() throws Exception {
		
		baseURI = "http://www.dneonline.com";
		
		File file = new File("./SOAPRequest/Add_XML.xml");
		
		if (file.exists()){
			System.out.println("File is present");
			
		}
		else {
			throw new Exception("File not exist");
		}
		
		FileInputStream fileinput = new FileInputStream(file);
		
		String req_body = IOUtils.toString(fileinput, "UTF-8");
		
		given()
			.contentType("text/xml")
			.accept(ContentType.XML)
			.body(req_body)
		.when()
			.post("calculator.asmx")
		.then()
			.statusCode(200).log().all()
			.and()
			.body("//*:DivideResult.text()", equalTo("1"))
			.assertThat().body(RestAssuredMatchers.matchesXsd("./Divide_XSD.xml"));	
	}

}
