package query;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Generator {

    Map<String, Map<String, String[]>> parsedData;
    File logFile;

    public Generator( String path ) {
        parsedData = new HashMap<>();
        logFile = new File(path);
    }

    public void parse() throws FileNotFoundException {

        try {

		//Open the log file in read mode
            Scanner reader = new Scanner(logFile);

		//Fetch each line of file
            while (reader.hasNextLine()) {

                String line = reader.nextLine();
                String[] data = line.split(" ");

                //Format the unix timestamps to match the input query strings
                SimpleDateFormat date24 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                Date temp = new Date(Long.parseLong(data[0]));
                String convertedTime = date24.format(temp);


		    //Add the timestamp to the map
                if (!parsedData.containsKey( convertedTime )) {
                    parsedData.put(convertedTime, new HashMap<>());
                }

                Map<String, String[]> ipMap = parsedData.get(convertedTime);

		    //Add the IP address to the map
                if (!ipMap.containsKey(data[1])) {
                    ipMap.put(data[1], new String[2]);
                }

		    //Add both the CPU usage
                if (data[2].equals("0")) {
                    ipMap.get(data[1])[0] = data[3];
                } else {
                    ipMap.get(data[1])[1] = data[3];
                }
            }

            reader.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Error occured: " + e);
            e.printStackTrace();
        }

    }

}
