import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import utils.RandomDataGenerators;

import java.io.FileNotFoundException;
import java.io.IOException;

import static data.JSONOjectValidations.validateUserProperties;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class GetTests {

    //GET response, validate statuscode and content type
    public Response getResponse(String endPoint) {
        Response response =
                given()
                        .when()
                        .get(endPoint)
                        .then()
                        .statusCode(200)
                        .contentType("application/json")
                        .extract()
                        .response();
        return response;
    }

    @Test
    public void validateInstructionsEndpoint(){
        String response = getResponse("http://bpdts-test-app-v2.herokuapp.com/instructions")
                .prettyPrint();
        assertThat("{\n" +
                        "    \"todo\": \"Create a short automated test for this API. Check that the data returned by the API is valid, and that " +
                        "ensure that each valid operation can be successfully called for each endpoint. Once you've built the tests, push the answer " +
                        "to Github or Gitlab, and send us a link. \"\n" +
                        "}",
                is(response) );
    }

    @Test
    public void testAllUsersEndpoint() throws IOException {
        getResponse("http://bpdts-test-app-v2.herokuapp.com/users").prettyPrint();

    }

    @Test
    public void testSingleUserByIdEndpoint() throws FileNotFoundException {
        int userId = new RandomDataGenerators().generateUserId();
        Response res = getResponse("http://bpdts-test-app-v2.herokuapp.com/user/"+userId);

        JsonPath jsonPath = res.jsonPath();
        validateUserProperties(jsonPath);
        assertThat(userId, is(jsonPath.getInt("id")));


    }


    @Test
    public void testUsersByCityEndpoint() {
        String city = new RandomDataGenerators().retrieveCity();
        getResponse("http://bpdts-test-app-v2.herokuapp.com/city/"+city+"/users").prettyPrint();
    }

}
