package Activities;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class Activity2 {
    final static String base_uri = "https://petstore.swagger.io/v2/user";

    @Test(priority=1)
    public void addNewUser() throws IOException {
        FileInputStream inputJSON = new FileInputStream("src/test/java/activities/inputFile.json");
        String requestBody = new String(inputJSON.readAllBytes());

        Response response = given().contentType(ContentType.JSON)
                .body(requestBody).when().post(base_uri);
        inputJSON.close();

        //assertion
        response.then().body("code",equalTo(200));
        response.then().body("message",equalTo("11001"));
    }

    @Test(priority=2)
    public void getUserDetails()
    {
        File outputJSON = new File("src/test/java/Activities/inputFile.json");
        Response response = given().contentType(ContentType.JSON).when().
                pathParam("username","JB").when().
                get(base_uri+"/{username}");
        String resBody = response.getBody().asPrettyString();

        try{
            outputJSON.createNewFile();
            FileWriter wr = new FileWriter(outputJSON.getPath());
            wr.write(resBody);
            wr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //assertion
        response.then().body("id",equalTo(11001));
        response.then().body("username",equalTo("JB"));
        response.then().body("firstName",equalTo("Jiki"));
        response.then().body("lastName",equalTo("Bohidar"));
        response.then().body("email",equalTo("jiki.bohidar@mail.com"));
        response.then().body("password",equalTo("password123"));
        response.then().body("phone",equalTo("9999999999"));
    }

    @Test(priority=3)
    public void deleteUser()
    {
        Response response = given().contentType(ContentType.JSON).when().
                pathParam("username","JB").when().
                delete(base_uri+"/{username}");

        //assertion
        response.then().body("code",equalTo(200));
        response.then().body("message",equalTo("JB"));
    }
}
