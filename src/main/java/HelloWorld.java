import BaseClasses.BaseMethods;

import java.util.ArrayList;
import java.util.Scanner;

public class HelloWorld {

    public static ArrayList<String> getInputs() {
        Scanner scanner = new Scanner(System.in);
        String departure_location, arrival_location, flight_date, flight_class, output_pref;

        ArrayList<String> inputs = new ArrayList<>();

        System.out.print("Enter Departure location: ");
        departure_location = scanner.nextLine();
        System.out.print("Enter Arrival location: ");
        arrival_location = scanner.nextLine();
        System.out.print("Enter flight date: ");
        flight_date = scanner.nextLine();
        System.out.print("Enter flight class: ");
        flight_class = scanner.nextLine();
        System.out.print("Enter Output Preference: ");
        output_pref = scanner.nextLine();

        inputs.add(departure_location);
        inputs.add(arrival_location);
        inputs.add(flight_date);
        inputs.add(flight_class);
        inputs.add(output_pref);

        return inputs;
    }

    public static void main(String[] args) {
        ArrayList<String> inputs = getInputs();
        BaseMethods baseMethods = new BaseMethods(5 , "A:\\Java\\assets\\csv" , inputs);
        baseMethods.init();
    }
}
