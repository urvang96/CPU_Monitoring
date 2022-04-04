package logger;

import java.io.IOException;

class Logger {

    public static void main( String[] args ) throws IOException {
        if( args.length == 0 ){
            System.out.println("Please provide a file path and rerun");
            return;
        }

	  //Create the looger instance and simulate the data generation
        Logging_system logger = new Logging_system(args[0]);
        logger.writeData();
    }
}