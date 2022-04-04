package query;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

class QueryTool {

    public static void main( String[] args ) throws IOException, ParseException {

        try{

            System.out.println(">Welcome to the Query Tool. Use this tool to query the log files and get the CPU usage " +
                    "for desired server. \n>Syntax for query is : QUERY IP cpu_id time_start time_end. " +
                    "\n>Time_start and time_end should be specified in the format YYYY-MM-DD HH:MM AA" +
                    "\n>Use `exit` command to close the tool.");

            //Check the input parameter
            if( args.length == 0 ){
                System.out.println("Please provide a file path and rerun");
                return;
            }

            System.out.println(">Intializing...");
            Generator gen = new Generator(args[0]);

            //Check if file exists on given path
            if( !gen.logFile.exists() ){
                System.out.println(">Incorrect file path or file does not exist. Rerun the tool.");
                return;
            }

            //Map the input file to convinent data structure. Use HashMap to get O(1) query results.
            gen.parse();

            //Use Scanner to get stdin
            Scanner input = new Scanner(System.in);

            System.out.print(">");

            //Loop till user inputs command
            while (input.hasNext() ) {
                String inData = input.nextLine();

                //If input is `exit` break the loop
                 if (inData.toUpperCase(Locale.ROOT).equals("EXIT")) return;
                 else{

                     String[] queryData = inData.split(" ");

                     //Check the `query` command syntax
                     if( !queryData[0].toUpperCase(Locale.ROOT).equals("QUERY") || queryData.length < 7 ){
                         System.out.print(">Incorrect query string");
                     }
                     else {

                         //Get query parameters
                         String queryIp = queryData[1];
                         String queryCpu = queryData[2];
                         String queryStart = queryData[3] + " " + queryData[4];
                         String queryEnd = queryData[5] + " " + queryData[6];

                         //Date formatter to convert in between Date object and String
                         SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");

                         //Convert input time to Date objects
                         Date startTime = formatter.parse(queryStart);
                         Date endTime = formatter.parse(queryEnd);

                         //Loop and get each minute between the start and end time
                         List<String> queryTimes = new ArrayList<>();
                         while( startTime.compareTo(endTime) <= 0 ) {
                             queryTimes.add(formatter.format(startTime));
                             startTime.setMinutes(startTime.getMinutes()+1);
                         }

                         //Build the query result
                         StringBuilder result = new StringBuilder();

                         //Loop through each minute and fetch the corresponding result from the map
                         for( String time : queryTimes ) {
                             Map<String, String[]> data = gen.parsedData.get(time);

                             //If the time does not exist in log files skip
                             if( data == null ) continue;

                             //If input IP is present in the map, fetch the desired data and add to result
                             if(data.containsKey(queryIp)){
                                 result.append("(");
                                 result.append(time);
                                 result.append(",");
                                 result.append(data.get(queryIp)[Integer.parseInt(queryCpu)-1]);
                                 result.append("%),");
                             }
                         }

                         //Format the result string and print in the tool
                         if( result.length() > 0 ) {
                             result.deleteCharAt(result.length()-1);
                             System.out.print(">" + result.toString() + "\n");
                         }
                         else{
                             System.out.println(">No data found");
                         }

                         result = new StringBuilder();

                     }

                     System.out.print(">");

                 }
            }
        }
        catch (Exception e){
            System.out.println("Error occured: " + e);
            e.printStackTrace();
        }
    }
}
