package io.rest_assured.json_path;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class NewTest {
	public static String url = "http://demo4032024.mockable.io/apitest";
	@Test
	public void apiTest() {	 
		getResponseBody();
		getResponseStatus();
	}

	private static RestAssured given() {
		// TODO Auto-generated method stub
		return null;
	}

	public static void getResponseBody(){
		RestAssured.baseURI = url;
		Response response = null;

		try {
			response = RestAssured.given()
					.when()
					.get(url);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Response :" + response.asString());
		System.out.println("Status Code :" + response.getStatusCode());


		//Creation of JsonPath object
		JsonPath jsonPathValidator = response.jsonPath();
		List<Integer> element = jsonPathValidator.get("employeeData.age");
		int age = element.get(0);
		int status  = jsonPathValidator.get("status");
		
		String message =jsonPathValidator.get("message");
		
		List<String> element1 =  jsonPathValidator.get("employeeData.role");
		String role = element1.get(0); 
		element1 = jsonPathValidator.get("employeeData.dob");
		String dob = element1.get(0); 
		element1 = jsonPathValidator.get("employeeData.company");
		String company = element1.get(0); 



		if(age==25 && role.contains("QA Automation Developer") && message.contains("data retrieved successful") && dob.contains("25-02-1994"))
			System.out.println("age,role,dob - Pass");

		if(status==200)
			System.out.println("Status - Pass");    

		if(!company.equals("ABC Infotech"))
			System.out.println("Company - Fail");

	}

	private static RestAssured when() {
		// TODO Auto-generated method stub
		return null;
	}

	public static void getResponseStatus(){
		given();
		int statusCode= RestAssured.when().get(url).getStatusCode();
		System.out.println("The response status is "+statusCode);

		given();
		RestAssured.when().get(url).then().assertThat().statusCode(200);
	}
}
