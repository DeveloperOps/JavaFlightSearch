package CsvHandlers;

import SearchHandlers.SearchHandler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

public class CsvHandler {

    String path;
    ArrayList<String> inputs;
    static int counter = 0;
    static ArrayList<HashMap<String, String>> flightDetails = new ArrayList<>();
    int numberOfFiles;
    public CsvHandler(String path , ArrayList<String> inputs , int numberOfFiles) {
        this.path = path;
        this.inputs = inputs;
        this.numberOfFiles = numberOfFiles;
        counter++;
    }
    public void readCsv() throws IOException {
        FileReader reader = new FileReader(this.path);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line;
        ArrayList<String> lines = new ArrayList<>();
        while ((line = bufferedReader.readLine()) != null) {
            lines.add(line);
        }
        ArrayList<String[]> finalList = new ArrayList<>();
        for(String i: lines) {
            String[] split = i.split("\\|");
            finalList.add(split);
        }
        try {
            convertToHashObject(finalList);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void convertToHashObject(ArrayList<String[]> finalList) throws ParseException {

        for(int i = 0; i < finalList.size(); i++) {
            if(i != 0) {
                HashMap<String, String> oneObject = new HashMap<>();
                oneObject.put("FLIGHT_NO" ,finalList.get(i)[0]);
                oneObject.put("DEP_LOC" ,finalList.get(i)[1]);
                oneObject.put("ARR_LOC" ,finalList.get(i)[2]);
                oneObject.put("VALID_TILL" ,finalList.get(i)[3]);
                oneObject.put("FLIGHT_TIME" ,finalList.get(i)[4]);
                oneObject.put("FLIGHT_DUR" ,finalList.get(i)[5]);
                oneObject.put("FARE" ,finalList.get(i)[6]);
                oneObject.put("SEAT_AVAILABILITY" ,finalList.get(i)[7]);
                oneObject.put("CLASS" ,finalList.get(i)[8]);
                flightDetails.add(oneObject);
            }
        }
        if(counter == numberOfFiles) {
            new SearchHandler(flightDetails ,this.inputs).handleSearch();
        }
    }
}
