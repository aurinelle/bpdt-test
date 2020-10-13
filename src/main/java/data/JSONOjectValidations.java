package data;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.path.json.JsonPath;
import org.json.JSONObject;

import java.io.StringWriter;

public class JSONOjectValidations {

    public static JSONObject validateUserProperties(JsonPath jsonPath) {

        try {
            UserModelSchema UserModelSchema = new UserModelSchema();
            UserModelSchema.setId(jsonPath.get("id"));
            UserModelSchema.setFirst_name(jsonPath.get("first_name"));
            UserModelSchema.setLast_name(jsonPath.get("last_name"));
            UserModelSchema.setEmail(jsonPath.get("email_name"));
            UserModelSchema.setIp_address(jsonPath.get("ip_address"));
            UserModelSchema.setLatitude(jsonPath.get("latitude"));
            UserModelSchema.setLongitude(jsonPath.get("longitude"));
            UserModelSchema.setCity(jsonPath.get("city"));
            ObjectMapper objectMapper = new ObjectMapper();
            StringWriter stringEmp = new StringWriter();
            objectMapper.writeValue(stringEmp, UserModelSchema);
            System.out.println(stringEmp);
        } catch (Exception e) {
            System.out.println(e);
        }
        return new JSONObject();
    }
}
