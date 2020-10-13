package utils;

import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.Random;

import static io.restassured.RestAssured.given;

public class RandomDataGenerators {

    private Response getResponse(String endPoint) {

        Response response =
                given()
                        .when()
                        .get(endPoint)
                        .then()
                        .extract()
                        .response();
        return response;
    }


    //retrieve all IDs from Users endpoint and select random ID for reuse as User endpoint params
    public int generateUserId() {
        ArrayList<Integer> listOfUserIDs = getResponse("http://bpdts-test-app-v2.herokuapp.com/users").jsonPath().get("id");
        return new Random().nextInt(listOfUserIDs.size());
    }

    //retrieve city from User endpoint for reuse as city endpoint params
    public String retrieveCity() {
        String city = getResponse("http://bpdts-test-app-v2.herokuapp.com/user/"+generateUserId()).jsonPath().get("city").toString();
        return city;
    }

}
