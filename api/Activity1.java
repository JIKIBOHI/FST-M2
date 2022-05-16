package Activities;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class Activity1 {

    final static String base_uri= "https://petstore.swagger.io/v2/pet";

    @Test(priority=1)
    public void addNewPet()
    {
        String requestBody= "{" + "\"id\": 77232," +"\"name\": \"Riley\"," + "\"status\": \"alive\"" +"}";

        Response response = given().contentType(ContentType.JSON).body(requestBody).when().
            post(base_uri);
        //assertion
        response.then().body("id",equalTo(77232));
        response.then().body("name",equalTo("Riley"));
        response.then().body("status",equalTo("alive"));
    }

    @Test(priority=2)
    public void getPetDetails()
    {
        Response response = given().contentType(ContentType.JSON).when()
                .pathParam("petId","77232")
                .get(base_uri+"/{petId}");
        //assertion
        response.then().body("id",equalTo(77232));
        response.then().body("name",equalTo("Riley"));
        response.then().body("status",equalTo("alive"));
    }

    @Test(priority=3)
    public void deletePetDetails()
    {
        Response response = given().contentType(ContentType.JSON).when()
                .pathParam("petId","77232")
                .delete(base_uri+"/{petId}");
        //assertion
        response.then().body("code",equalTo(200));
        response.then().body("message",equalTo("77232"));
    }
}
