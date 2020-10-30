import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import model.Patient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ConsoleService {
    private ApiConsumer apiConsumer;
    private Gson gson;

    public ConsoleService() {
        try {
            gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            apiConsumer = new ApiConsumer();
        } catch (Exception ex) {

        }
    }

    public List<Patient> getAllPatients() {
        List<Patient> patients = new ArrayList<>();
        JSONArray jsonArray = apiConsumer.getPatients();

        for (int i = 1; i < jsonArray.length(); i++) {
            try {
                patients.add(gson.fromJson(jsonArray.get(i).toString(), Patient.class));
            } catch (Exception ex) {

            }
        }
        return patients;
    }

    public void savePatient(Patient patientFromConsole) {
        String [] date = patientFromConsole.getPositive_test_date().split("-");

        patientFromConsole.setPositive_test_date(LocalDate.of(Integer.valueOf(date[0]),Integer.valueOf(date[1]),Integer.valueOf(date[2])).toString());
        apiConsumer.savePatient(gson.toJson(patientFromConsole));
    }
}
