import com.goebl.david.Response;
import com.goebl.david.Webb;
import model.Patient;
import org.json.JSONArray;

import java.net.MalformedURLException;
import java.net.URL;

public class ApiConsumer {

    private URL apiUrl = new URL("http://localhost:8080");
    private Webb webb;

    public ApiConsumer() throws MalformedURLException {
        webb = Webb.create();
    }

    public JSONArray getPatients(){
        Response<JSONArray> response = webb.get(apiUrl+"/patients")
                .ensureSuccess()
                .connectTimeout(5000)
                .readTimeout(5000)
                .asJsonArray();


        return response.getBody();
    }

    public Response<String> savePatient(String patientJson) {
        return webb.post(apiUrl+"/patient/add")
                .header("Content-Type","application/json")
                .body(patientJson)
                .ensureSuccess()
                .connectTimeout(5000)
                .readTimeout(5000)
                .asString();
    }
}
