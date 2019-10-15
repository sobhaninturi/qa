import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class dataDrivenTest_AddEmployee {

    @Test(dataProvider = "empdataprovider")
    void postNewEmployee(String ename, String esalary, String eage) {
        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
        RequestSpecification httpRequest = RestAssured.given();
        JSONObject request = new JSONObject();
        request.put("name", ename);
        request.put("salary", esalary);
        request.put("age", eage);
        httpRequest.header("Content-Type", "application/json");
        httpRequest.body(request.toJSONString());
        Response response = httpRequest.request(Method.POST, "/create");
        String body = response.getBody().asString();
        System.out.println("Body is: " + body);
        int code = response.getStatusCode();
        System.out.println("Code is:" + code);
        Assert.assertEquals(code, 200);
    }

    @DataProvider(name = "empdataprovider")
    Object [][] getEmpData() {
        String empdata[][] = {{"abc", "2000", "20"}, {"xyz", "3000", "30"}, {"pqr", "4000", "40"}};
        return (empdata);
    }
}
