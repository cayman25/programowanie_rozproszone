import com.goebl.david.Response;
import com.goebl.david.Webb;
import com.google.gson.Gson;
import model.api.TokenApiResponse;
import org.json.JSONArray;

import java.net.MalformedURLException;
import java.net.URL;

public class ApiConsumer {

    private URL apiUrl = new URL("http://localhost:8080");
    private Webb webb;
    private Gson gson;
    private String accessToken;

    public ApiConsumer() throws MalformedURLException {
        webb = Webb.create();
        gson = new Gson();
        accessToken = setAccessToken();
    }

    public JSONArray getPatients(){
        Response<JSONArray> response = webb.get(apiUrl+"/patients")
                .header("Authorization","Bearer " + accessToken)
                .ensureSuccess()
                .connectTimeout(5000)
                .readTimeout(5000)
                .asJsonArray();

        return response.getBody();
    }

    public Response<String> savePatient(String patientJson) {
        return webb.post(apiUrl+"/patient/add")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer " + accessToken)
                .body(patientJson)
                .ensureSuccess()
                .connectTimeout(5000)
                .readTimeout(5000)
                .asString();
    }

    private String getBodyFromTokenEndpoint(){
        return webb.post("https://login.microsoftonline.com/146ab906-a33d-47df-ae47-fb16c039ef96/oauth2/v2.0/token")
                .body("TO CHANGE)
                .header("Content-Type","application/x-www-form-urlencoded")
                .asString().getBody();
    }

    private String setAccessToken(){
        TokenApiResponse tokenApiResponse = gson.fromJson(getBodyFromTokenEndpoint(), TokenApiResponse.class);
        return tokenApiResponse.getAccess_token();
    }


}
