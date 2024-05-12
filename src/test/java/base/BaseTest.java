package base;


import com.qa.jp.client.RestClient;
import com.qa.jp.configuration.ConfigurationManager;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.util.Properties;

public class BaseTest {
	
	//Service URLs:
	public static final String 	POST_ENDPOINT = "/posts";
	public static final String 	COMMENT_ENDPOINT = "/comments";
	public static final String 	USER_ENDPOINT = "/users";
	

	protected ConfigurationManager config;
	protected Properties prop;
	protected RestClient restClient;
	protected String baseURI;
	
	@Parameters({"baseURI"})
	@BeforeTest
	public void setUp(String baseURI) {
		
		RestAssured.filters(new AllureRestAssured());
		
		config = new ConfigurationManager();
		prop = config.initProp();
		this.baseURI = baseURI;


		baseURI= prop.getProperty("BaseURI");
		
		
	}
	
	
	

}
