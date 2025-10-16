package com.framework.tests.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserApiTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://reqres.in/api";
    }

    @Test(description = "Verify GET request for list of users")
    public void testGetListUsers() {
        Response res = RestAssured
                .given()
                .when()
                .get("/users?page=2")
                .then()
                .extract()
                .response();

        Assert.assertEquals(res.getStatusCode(), 200, "Expected status code 200");
        Assert.assertTrue(res.getBody().asString().contains("data"), "Response should contain 'data'");
        Assert.assertTrue(res.jsonPath().getList("data").size() > 0, "User list should not be empty");
    }

    @Test(description = "Verify GET request for single user")
    public void testGetSingleUser() {
        Response res = RestAssured
                .given()
                .when()
                .get("/users/2")
                .then()
                .extract()
                .response();

        Assert.assertEquals(res.getStatusCode(), 200, "Expected status code 200");
        Assert.assertEquals(res.jsonPath().getInt("data.id"), 2, "User ID should be 2");
        Assert.assertNotNull(res.jsonPath().getString("data.email"), "Email should not be null");
        Assert.assertTrue(res.getBody().asString().contains("support"), "Response should contain 'support' info");
    }

    @Test(description = "Verify POST request for creating new user")
    public void testCreateUser() {
        String body = "{ \"username\": \"Khizer Ahmed\", \"email\": \"khizer.ahmed@reqres.in\", \"password\": \"Khizer123\"}";
        Response res = RestAssured
                .given()
                .header("x-api-key", "reqres-free-v1") 
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/users")
                .then()
                .extract()
                .response();
                
        Assert.assertEquals(res.getStatusCode(), 201, "Expected status code 201");
        Assert.assertEquals(res.jsonPath().getString("username"), "Khizer Ahmed", "Username should match");
        Assert.assertEquals(res.jsonPath().getString("email"), "khizer.ahmed@reqres.in", "Email should match");
        Assert.assertNotNull(res.jsonPath().getString("id"), "User ID should be generated");
        Assert.assertNotNull(res.jsonPath().getString("createdAt"), "createdAt timestamp should be present");
    }
}
