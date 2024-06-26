package tests;

import base.BaseTest;
import com.qa.jp.client.RestClient;
import com.qa.jp.constants.APIHttpStatus;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

public class GetUsersTest extends BaseTest {

    String userEndpoint;

    @BeforeMethod
    public void getUserSetup() {
        restClient = new RestClient(prop, baseURI);
        userEndpoint=baseURI+prop.getProperty("getPostEndpoint");
    }

    //code smell: sonarQube
    @Test(enabled = true)
    public void getAllUsersTest() {
        restClient.get(USER_ENDPOINT, false, true)
                .then().log().all()
                .assertThat().statusCode(APIHttpStatus.OK_200.getCode());

    }

    //code smell: sonarQube
    @Test(enabled = true)
    public void validatePostsSchema() {
        restClient.get(USER_ENDPOINT, false, true)
                .then().log().all()
                .body(matchesJsonSchemaInClasspath("schema/getUser.json"))
                .assertThat().statusCode(APIHttpStatus.OK_200.getCode());

    }

    @Test()
    public void getUserWithQueryParamsTest() {
        Map<String, Object> queryParams = new HashMap<String, Object>();
        queryParams.put("id", "1");


        restClient.get(USER_ENDPOINT, queryParams, null, false, true)
                .then().log().all()
                .assertThat().statusCode(APIHttpStatus.OK_200.getCode())
                .assertThat()
                .body("findAll{it.id='1'}.id[0]",equalTo("1"));
    }


}
