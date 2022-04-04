package logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Logging_system {

    int servers = 1000;
    int cpus = 2;
    File logFile;
    Set ips = new HashSet<>();

    public Logging_system( String path ) {
        this.logFile = new File( path );
    }

    public void writeData() throws IOException {

        try {

		//Create a new file at the given path
            this.logFile.createNewFile();

		//Generate server IPs
            generateIPs();

		//Rnadom CPU usage
            Random randNum = new Random();


		//File Writer to genrate log file
            FileWriter writer = new FileWriter(this.logFile.getAbsolutePath());
            Object[] ip = ips.toArray();

            //Date formatter to convert in between Date object and String
            SimpleDateFormat date24 = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		//24hour time data
            String start = "2022-04-03 00:00";
            String end = "2022-04-03 23:59";

            Date startTime = date24.parse(start);
            Date endTime = date24.parse(end);

		//Get every miute of the day
            while( startTime.compareTo(endTime) <= 0){
		    //Get each server IP
                for( int ind = 0; ind < this.servers; ++ind ) {
			  //Get each CPU
                    for( int index = 0; index < this.cpus; ++index ) {
                        writer.write(startTime.getTime() + " " + ip[ind] + " " + index + " " + randNum.nextInt(100) + "\n" );
                    }
                }

		    //Increase the minue by one
                startTime.setMinutes(startTime.getMinutes()+1);
            }

            writer.close();
        }
        catch (IOException e){
            System.out.println("Error occured: " + e);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    //Generate 1000 unique IPs and store in a set
    public void generateIPs() {
        Random num = new Random();
        while( ips.size() < servers ) {
            ips.add("192.168." + num.nextInt(255) +"." + num.nextInt(255));
        }
    }
}
