import lombok.NoArgsConstructor;
import model.Patient;

import java.util.Scanner;

@NoArgsConstructor
public class ConsoleManipulation {

    private ConsoleService consoleService = new ConsoleService();
    Scanner scanner = new Scanner(System.in);

    public void startConsole(){
        welcomeMessage();
        for(;;) {
            optionsMessage();

            switch (getOptionFromInput()){
                case 1:
                    try {
                        for (Patient patient : consoleService.getAllPatients())
                            System.out.println(patient);
                    }catch(Exception ex){
                        System.out.println("ERROR:" + ex.getMessage());
                    }
                    break;
                case 2:
                    try{
                        consoleService.savePatient(getPatientFromConsole());
                    }catch(Exception ex){
                        System.out.println("ERROR:" + ex.getMessage());
                    }
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Nieznana Opcja");
            }
        }
    }

    void welcomeMessage(){
        System.out.println("Witaj. Co chcesz wykonac?");
    }

    void optionsMessage(){
        System.out.println("1. Wyswietl zakazonych pacjentow");
        System.out.println("2. Dodaj pacjenta");
        System.out.println("0. Wyjscie");
    }

    int getOptionFromInput() {
        try {
            return scanner.nextInt();
        }catch (Exception ex) {
            return 404;
        }
    }

    Patient getPatientFromConsole(){
        System.out.println("Imie: ");
        String name = scanner.next();
        System.out.println("Nazwisko: ");
        String surname = scanner.next();
        System.out.println("Wiek: ");
        int age = scanner.nextInt();
        System.out.println("Data pozytywnego testu (yyyy-mm-dd): ");
        String testDate = scanner.next();
        System.out.println("Email: ");
        String email = scanner.next();

            return Patient.builder()
                    .name(name)
                    .surname(surname)
                    .age(age)
                    .positive_test_date(testDate)
                    .email(email)
                    .build();
    }

}
