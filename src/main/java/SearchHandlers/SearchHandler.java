package SearchHandlers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class SearchHandler {

    private ArrayList<HashMap<String, String>> flightDetails;
    ArrayList<String> inputs;

    public SearchHandler(ArrayList<HashMap<String, String>> flightDetails , ArrayList<String> inputs) {
        this.flightDetails = flightDetails;
        this.inputs = inputs;
    }

    private boolean checkDatesDifference(String date1 , String date2) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date d1 = simpleDateFormat.parse(date1);
        Date d2 = simpleDateFormat.parse(date2);
        long difference_In_Time = d2.getTime() - d1.getTime();
        return difference_In_Time >= 0;
    }

    private void handleSingleHashMap(HashMap<String, String> oneDetail) throws ParseException {
        if(oneDetail.get("DEP_LOC").equals(inputs.get(0))
                && oneDetail.get("ARR_LOC").equals(inputs.get(1))
                && oneDetail.get("SEAT_AVAILABILITY").equals("Y")
                && oneDetail.get("CLASS").equals(inputs.get(3))
        ) {
            try {
                boolean validDates = checkDatesDifference(inputs.get(2), oneDetail.get("VALID_TILL"));
                if(validDates) {
                    System.out.println(oneDetail.get("FLIGHT_NO"));
                }
            } catch (Exception exception) {
                throw exception;
            }
        }
    }

    public void handleSearch() throws ParseException {
        for(HashMap<String,String> flight: flightDetails) {
            handleSingleHashMap(flight);
        }
    }
}
