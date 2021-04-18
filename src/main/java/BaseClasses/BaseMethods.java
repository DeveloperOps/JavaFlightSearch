package BaseClasses;

import CsvHandlers.CsvHandler;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class BaseMethods {
    private final int time;
    private static String path;
    private static ArrayList<String> inputs;

    public BaseMethods(int time, String path , ArrayList<String> inputs) {
        BaseMethods.path = path;
        BaseMethods.inputs = inputs;
        this.time = time;
    }

    public void init() {
        try {
            final ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
            scheduledExecutorService.scheduleAtFixedRate(BaseMethods::readFiles , 0 , this.time , TimeUnit.MINUTES);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    public static void readFiles() {
        File folder = new File(BaseMethods.path);
        File[] listOfFiles = folder.listFiles();
        assert listOfFiles != null;

        for (File file : listOfFiles) {
            if (file.isFile()) {
                String currentPath = BaseMethods.path + "\\" + file.getName();
                try {
                    new CsvHandler(currentPath , inputs , listOfFiles.length).readCsv();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
