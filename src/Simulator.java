package src;

import java.io.BufferedReader; //gnl
import java.io.FileReader;  //file.open()
import java.io.File;
import java.io.FileWriter;  
import java.io.IOException; //exception for the try catch
import java.io.PrintWriter; //print to file import
import java.io.FileOutputStream; 
import java.io.PrintStream;
import java.io.FileNotFoundException;

public class Simulator {

    public static class InvalidInputException extends Exception {
        public InvalidInputException(String message) {
            super(message);
        }
    }

    private static int cycles = 0;
    private static String line;
    private static BufferedReader br;
    private static WeatherTower tower;

    private static void parseScenario(File file) throws IOException, InvalidInputException {
        try {
            String[] stuff;

            tower = new WeatherTower();
            br = new BufferedReader(new FileReader(file));

            //checking first line for cycles
            cycles = Integer.parseInt(br.readLine());
            if (cycles <= 0) {
                throw new InvalidInputException("Invalid cycle count: " + cycles);
            }

            while ((line = br.readLine()) != null){
                stuff = line.split("\\s+");

                if (stuff.length != 5) {
                    throw new InvalidInputException("Invalid input format: " + line);
                }

                String type = stuff[0];
                String name = stuff[1];
                int longitude = Integer.parseInt(stuff[2]);
                int latitude = Integer.parseInt(stuff[3]);
                int height = Integer.parseInt(stuff[4]);

                if (longitude < 0 || latitude < 0 || height < 0) {
                    throw new InvalidInputException("Invalid coordinates or height for " + name);
                }

                Coordinates coords = new Coordinates(longitude, latitude, height);
                AircraftFactory.newAircraft(type, name, coords).registerTower(tower);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + file.getName());
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Number format error: " + e.getMessage());
        } finally {
            if (br != null) {
                br.close();
            }
        }
    }

    public static void main(String[] args) {
        if (args.length < 1){
            System.out.println("No scenario file provided.");
            return;
        }
        
        try {
            FileOutputStream fos = new FileOutputStream("simulator.txt");
            PrintStream fileout = new PrintStream(fos);

            System.setOut(fileout); //to set all my system.out.println to write in the .txt

            try {
                parseScenario(new File(args[0]));
            } catch (InvalidInputException e) {
                System.out.println("Error: " + e.getMessage());
                return;
            }

            while (cycles-- > 0){
                tower.changeWeather();
            }
        
            fileout.close();
            fos.close();
        } catch(IOException e) {
            System.out.println("Error writing to output file");
            e.printStackTrace();
        }
        
    }
}