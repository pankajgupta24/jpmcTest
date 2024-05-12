package com.qa.jp.client;


import com.qa.jp.constants.APIHttpStatus;
import com.qa.jp.frameworkexception.APIFrameworkException;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class RestClient {


    private RequestSpecBuilder specBuilder;

    private Properties prop;
    private String baseURI;

    private boolean isAuthorizationHeaderAdded = false;


    public RestClient(Properties prop, String baseURI) {
        specBuilder = new RequestSpecBuilder();
        this.prop = prop;
        this.baseURI = baseURI;
    }


    public void addAuthorizationHeader() {
        if (!isAuthorizationHeaderAdded) {
            specBuilder.addHeader("Authorization", "Bearer " + prop.getProperty("tokenId"));
            isAuthorizationHeaderAdded = true;
        }
    }

    private void setRequestContentType(String contentType) {//json-JSON-Json
        switch (contentType.toLowerCase()) {
            case "json":
                specBuilder.setContentType(ContentType.JSON);
                break;
            case "xml":
                specBuilder.setContentType(ContentType.XML);
                break;
            case "text":
                specBuilder.setContentType(ContentType.TEXT);
                break;
            case "multipart":
                specBuilder.setContentType(ContentType.MULTIPART);
                break;

            default:
                System.out.println("plz pass the right content type....");
                throw new APIFrameworkException("INVALIDCONTENTTYPE");
        }
    }


    private RequestSpecification createRequestSpec(boolean includeAuth) {
        specBuilder.setBaseUri(baseURI);
        if (includeAuth) {
            addAuthorizationHeader();
        }

        return specBuilder.build();
    }

    private RequestSpecification createRequestSpec(Map<String, String> headersMap, boolean includeAuth) {
        specBuilder.setBaseUri(baseURI);
        if (includeAuth) {
            addAuthorizationHeader();
        }
        if (headersMap != null) {
            specBuilder.addHeaders(headersMap);
        }
        return specBuilder.build();
    }

    private RequestSpecification createRequestSpec(Map<String, String> headersMap, Map<String, Object> queryParams, boolean includeAuth) {
        specBuilder.setBaseUri(baseURI);
        if (includeAuth) {
            addAuthorizationHeader();
        }
        if (headersMap != null) {
            specBuilder.addHeaders(headersMap);
        }
        if (queryParams != null) {
            specBuilder.addQueryParams(queryParams);
        }
        return specBuilder.build();
    }


    private RequestSpecification createRequestSpec(Object requestBody, String contentType, boolean includeAuth) {
        specBuilder.setBaseUri(baseURI);
        if (includeAuth) {
            addAuthorizationHeader();
        }
        setRequestContentType(contentType);

        if (requestBody != null) {
            specBuilder.setBody(requestBody);
        }
        return specBuilder.build();
    }

    private RequestSpecification createRequestSpec(Object requestBody, String contentType, Map<String, String> headersMap, boolean includeAuth) {
        specBuilder.setBaseUri(baseURI);
        if (includeAuth) {
            addAuthorizationHeader();
        }
        setRequestContentType(contentType);
        if (headersMap != null) {
            specBuilder.addHeaders(headersMap);
        }
        if (requestBody != null) {
            specBuilder.setBody(requestBody);
        }
        return specBuilder.build();
    }


    //Http Methods Utils:
    public Response get(String serviceUrl, boolean includeAuth, boolean log) {
        if (log) {
            return RestAssured.given(createRequestSpec(includeAuth)).log().all()
                    .when()
                    .get(serviceUrl);
        }
        return RestAssured.given(createRequestSpec(includeAuth)).when().get(serviceUrl);

    }

    public Response get(String serviceUrl, Map<String, String> headersMap, boolean includeAuth, boolean log) {

        if (log) {
            return RestAssured.given(createRequestSpec(headersMap, includeAuth)).log().all()
                    .when()
                    .get(serviceUrl);
        }
        return RestAssured.given(createRequestSpec(headersMap, includeAuth)).when().get(serviceUrl);
    }

    public Response get(String serviceUrl, Map<String, Object> queryParams, Map<String, String> headersMap, boolean includeAuth, boolean log) {

        if (log) {
            return RestAssured.given(createRequestSpec(headersMap, queryParams, includeAuth)).log().all()
                    .when()
                    .get(serviceUrl);
        }
        return RestAssured.given(createRequestSpec(headersMap, queryParams, includeAuth)).when().get(serviceUrl);
    }


    public Response post(String serviceUrl, String contentType, Object requestBody, boolean includeAuth, boolean log) {
        if (log) {
            return RestAssured.given(createRequestSpec(requestBody, contentType, includeAuth)).log().all()
                    .when()
                    .post(serviceUrl);
        }
        return RestAssured.given(createRequestSpec(requestBody, contentType, includeAuth))
                .when()
                .post(serviceUrl);
    }


    public Response post(String serviceUrl, String contentType, Object requestBody, Map<String, String> headersMap, boolean includeAuth, boolean log) {
        if (log) {
            return RestAssured.given(createRequestSpec(requestBody, contentType, headersMap, includeAuth)).log().all()
                    .when()
                    .post(serviceUrl);
        }
        return RestAssured.given(createRequestSpec(requestBody, contentType, headersMap, includeAuth))
                .when()
                .post(serviceUrl);
    }


    }



