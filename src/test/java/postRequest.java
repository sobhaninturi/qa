import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class postRequest {

    @Test @Ignore
    void Registration() {
        RestAssured.baseURI = "http://restapi.demoqa.com/customer";
        RequestSpecification httpRequest = RestAssured.given();
        JSONObject requestParams = new JSONObject();
        requestParams.put("FirstName", "tsaa");
        requestParams.put("LastName", "satua");
        requestParams.put("UserName", "sattusa");
        requestParams.put("Password", "ssauta");
        requestParams.put("Email", "rasat@gmail.com");
        httpRequest.header("Content_Type","application/json");
        httpRequest.body(requestParams.toJSONString());
        Response response = httpRequest.request(Method.POST,"/register");
        int statusCode = response.getStatusCode();
        System.out.println("Status code is:" + statusCode);
        Assert.assertEquals(statusCode, 201);
        String successCode = response.jsonPath().get("SuccessCode");
        Assert.assertEquals(successCode, "OPERATION_SUCCESS");


    }

    @Test
    void AutherizationTest() {
        RestAssured.baseURI = "http://restapi.demoqa.com/authentication/CheckForAuthentication";
        PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
        authScheme.setUserName("ToolsQA");
        authScheme.setPassword("TestPassword");
        RestAssured.authentication = authScheme;
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.request(Method.GET, "/");
        int statusCode = response.getStatusCode();
        System.out.println("Status code is: " + statusCode);
        Assert.assertEquals(statusCode, 200);
        String body = response.getBody().asString();
        System.out.println("Response body is: " + body);


    }


}

