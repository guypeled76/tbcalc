package com.gremlin.apps;

import java.io.Console;
import java.io.File;
import java.util.Scanner;

import com.gremlin.engine.Expression;
import com.gremlin.engine.Processor;

/**
 * @author Tom Everett
 */
class Main {
   public static void main(String[] args)  throws Exception  {
      
        // If there is a valid extenrnal console
        if(System.console() != null) {
                
            // Start interacting with the user
            System.out.println("Enter expressions:");

            // Create the input scanner
            Scanner scanner = new Scanner(System.in);

            // The arithmetic processor
            Processor processor = new Processor();
            
            // The current line from the console
            String line = null;

            boolean exit = false;

            // Read next line and process or exit
            while(!exit && ((line = readLine(scanner)) != null))
            {
                // Execute line
                Expression result = processor.execute(line);

                // If is not an empty expression
                if(result != null && !result.isEmpty())
                {
                    // If is an exit expression
                    if(result.isExit()) {
                        exit = true;
                    // If is a debug expression
                    } else if(result.isDebug()){

                        // Write the processor state
                        System.out.println(processor.debug());
                    } else {
                        // Print the value of the evaluated expression
                        System.out.println(result);
                    }
                }
            } 

            // Write the processor state
            System.out.println(processor.debug());

        } else {
            System.out.println("Please provide an extenrnal console that supports aninput stream.");
            return;
        }



   }

    /**
     * Reads the next line from the user.
     * @return A single line.
     */
    private static String readLine(Scanner scanner) {
        
        // Print the input sign
        System.out.print(">> ");

        if(!scanner.hasNext()) {
            return null;
        }

        

        // Wait for next line
        String line = scanner.nextLine();
        if(line != null) {
            // normalize line
            line = line.trim();
        }

        // If is an empty string
        if(line != "") {
            return line;
        }
        
        return null;
    }
}