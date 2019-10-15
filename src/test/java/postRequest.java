import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class postRequest {

    @Test
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


}

