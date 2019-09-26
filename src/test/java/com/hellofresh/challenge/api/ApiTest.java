package com.hellofresh.challenge.api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hellofresh.challenge.constants.IEndPoint;
import com.hellofresh.challenge.extent.reports.ExtentTestManager;
import com.hellofresh.challenge.pojo.GroupKt;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class ApiTest {
	
	private static RequestSpecification groupKt_requestSpec;
	private static ResponseSpecification groupKt_resonseSpec;

	@DataProvider
	public static Object[][] countryAndAlphaCode() {
		return new Object[][] { { "United States", "US", "USA" },
								{ "Delaware", "DE", "DEL" }, 
								{ "United Kingdom", "GB", "GBR" } };
	}
	
	@BeforeClass
	public static void setupMain()
	{  
	
		groupKt_requestSpec = new RequestSpecBuilder().
				setBaseUri(IEndPoint.GROUPKT_BASE_URI).
				setBasePath(IEndPoint.GROUPKT_BASE_PATH).
				addHeader("Content-Type", "application/json").
				addHeader("Accept", "application/json").
				build();
		
		
		
		groupKt_resonseSpec= new ResponseSpecBuilder().
				expectStatusCode(200).
				build();
	}
	

	@Test(dataProvider = "countryAndAlphaCode")
	public void getAllCountries(String expectedPlaceName,String expectedAlpha2_code,String expectedAlpha3_code) {
		ExtentTestManager.startTest("Get All countries - "+expectedPlaceName,
				"Get all countries and validate that US, DE and GB were returned in the response");
		
		 given().
	 		spec(groupKt_requestSpec).
	 	when().
  			get(IEndPoint.GROUPKT_GET_ALL).
  		then().
  			spec(groupKt_resonseSpec).
  			body("RestResponse.result.alpha2_code", equalTo(expectedAlpha2_code));
	}

	@Test(dataProvider = "countryAndAlphaCode")
	public void getEachCountry(String expectedPlaceName,String expectedAlpha2_code,String expectedAlpha3_code) {
		ExtentTestManager.startTest("Get Each Country - "+expectedPlaceName,
				"Get each country (US, DE and GB) individually and validate the response");
		
	
		 given().
		 		spec(groupKt_requestSpec).
		 		pathParam("alpha2_code", expectedAlpha2_code).
		 when().
         		get("/iso2code/{alpha2_code}").
         then().
         		spec(groupKt_resonseSpec).
         		body("RestResponse.result.name", equalTo(expectedPlaceName),
         				"RestResponse.result.alpha2_code", equalTo(expectedAlpha2_code),
         					"RestResponse.result.alpha3_code", equalTo(expectedAlpha3_code));
	
	

	}

	@Test()
	public void tryInexistentCountry() {

		// ExtentReports Description
		ExtentTestManager.startTest("Inexistent Country - XX",
				"Get information for inexistent countries and validate the response");
		List<String> message=
		 given().
		 		spec(groupKt_requestSpec).
		 		pathParam("alpha2_code", "XX").
	 	when().
  				get(IEndPoint.GROUPKT_ISO_2_CODE).
  		then().
  				spec(groupKt_resonseSpec).
  				extract().
  				path("RestResponse.messages");
		 
		  for(String msg: message) {
			  Assert.assertEquals(msg, "No matching country found for requested code [xx].");
	        }	

	}
	
	
	@Test(dataProvider = "countryAndAlphaCode")
	public void postNewCountry(String placeName,String alpha2_code,String alpha3_code) throws Exception {

		ExtentTestManager.startTest("Post New Country - "+placeName,
				" validate new country addition using POST");
		
		GroupKt payload = new GroupKt();
		payload.setName(placeName);
		payload.setAlpha2_code(alpha2_code);
		payload.setAlpha3_code(alpha3_code);
		
		given().
				spec(groupKt_requestSpec).
				contentType(ContentType.JSON).				
	 	when().
	 			body(payload).
  				post(IEndPoint.GROUPKT_POST).
  		then().
  				statusCode(HttpStatus.SC_CREATED);

	}
	
}
