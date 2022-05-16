import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.*;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.oauth2;

public class RestAssured {
    RequestSpecification reqSpec;
    String sshKey;
    int sshKeyId;

    @BeforeClass
    public void setUp()
    {
        reqSpec=new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setAuth(oauth2( "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"))
                .setBaseUri("https://api.github.com")
                .build();

        sshKey= "ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQC190b2k6dxHr+MIhRVJDmltEyZMMUE5KuRBgprfzEm/hrzZZQemPgrRxBVmvb1rFhzqZxuW7ZootMLiW08MvLqJuP4OnXmfPrRdZ1Cf6GmiclpetmZYK/mLYXxbI5wdvySoGfw5x1m1GKutWBr57IBcMaEhDcGn2cJuKKH0DN0ylkjLsvALjuFmbF35JB7QFJzwrxdX/jIay8WMWLc8Ivao/qm1THr6PG/0OGhLxIUSCxZlf2OOMMyiA70L9fVvqJUbI2Erf8iVLHFyySM7202HrSD9Y8Sq3Fnjc9wsDSWsePUb5hXJGcO8wDzkGQ1lriH8eQZAuDHLFvyNJezeizd";

    }

    @Test(priority=1)
    public void postKey() {
        // Create JSON request
        String reqBody = "{\"title\": \"TestAPIKey\",  "
                + "\"key\": \""+sshKey+"\" }";
        Response response=given().spec(reqSpec).body(reqBody).when().post("/user/keys");
        String resBody= response.getBody().asPrettyString();
        System.out.println(resBody);
        System.out.println(response.getStatusCode());
        sshKeyId=response.then().extract().path("id");
        System.out.println(sshKeyId);

        Assert.assertEquals(response.getStatusCode(), 201, "Not a correct status");

    }


    @Test(priority=2)
    public void getKey() {
        Response response =
                given().spec(reqSpec).when()
                        .get("/user/keys");
        System.out.println(response.asPrettyString());
        Assert.assertEquals(response.getStatusCode(), 200, "Not a correct status");

    }

    @Test(priority=3)
    public void deleteKey() {
        Response response =
                given().spec(reqSpec)// Set headers.when()
                        .delete("/user/keys/"+sshKeyId); // Send DELETE request
        System.out.println(response.asPrettyString());
        Assert.assertEquals(response.getStatusCode(), 204, "Not a correct status");


    }

}
