import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class SampleTest {

    @Test
    public void getWeatherDeatils() {
        RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city/";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.request(Method.GET, "/Ongole");
        String responseBody = response.getBody().asString();
        System.out.println("Response body is" + responseBody);
        int statusCode = response.getStatusCode();
        System.out.println("Status code is:" + statusCode);
        Assert.assertEquals(statusCode, 200);
        String statusLine = response.getStatusLine();
        System.out.println("Status line is :" + statusLine);
        Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
        //validate json response
        Assert.assertEquals(responseBody.contains("Ongole"),true);
        JsonPath jsonPath= response.jsonPath();
        System.out.println(jsonPath.get("City"));
        System.out.println(jsonPath.get());
    }

    @Test @Ignore
    void getGoogleMap() {
        RestAssured.baseURI = "https://maps.googleapis.com";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.request(Method.GET, "/maps/api/place/nearbysearch/xml?location=-33.8670522,151.1957362&radius=1500&type=supermarket&key=AIzaSyBjGCE3VpLU4lgTqSTDmHmJ2HoELb4Jy1s");
        String responseBody = response.getBody().asString();
        System.out.println("Response body is" + responseBody);
        String contentType = response.header("Content-Type");
        System.out.println("content type is:" + contentType);
        Assert.assertEquals(contentType, "application/xml; charset=UTF-8");
        String encoding = response.header("content-encoding");
        System.out.println("Encoding is:"+ encoding);
        Assert.assertEquals(encoding,"gzip");
        Headers allHeaders = response.headers();
        for( Header header:allHeaders){
          System.out.println(header.getName()+ "   "+ header.getValue());
        }


    }
}
