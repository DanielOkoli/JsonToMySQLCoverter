package jsonproject;

import
        org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.Iterator;
import java.util.Scanner;

public class MyFileReader {


    private static final String PARAMETER_TABLE = "\n CREATE TABLE IF NOT EXISTS parameters("
            + "\n pName Name VARCHAR(255) PRIMARY KEY,"
            + "\n NominalPower INT,"
            + "\n PeakPower DECIMAL (4, 2),"
            + "\n Description VARCHAR(255),"
            + "\n Situation VARCHAR(255),"
            + "\n PanelDegradationCorrectionCoefficient DECIMAL (4, 1),"
            + "\n TemperatureCorrectionCoefficient DECIMAL (4, 2) "
            + "\n );";


    private static final String INPUT_TABLE = "\n CREATE TABLE IF NOT EXISTS input("
            + "\n UniqueID VARCHAR(255) PRIMARY KEY,"
            + "\n AlarmColor INT,"
            + "\n Id INT,"
            + "\n DatasourcesCount INT,"
            + "\n alertIcon VARCHAR(255),"
            + "\n ElementCount INT"
            + "\n Name VARCHAR(255),"
            + "\n FOREIGN KEY (Name) REFERENCES parameters (pName) "
            + "\n );";


    public static void main(String[] args) throws FileNotFoundException {

        JSONParser jsonParser = new JSONParser();
        ParseData p = new ParseData();

        try (FileReader reader = new FileReader(args[0]))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray itemList = (JSONArray) obj;

            String query = " ";

            //Create MySQL file
            File file = new File(args[1]);

            //Iterate over JSON objects
            for(Object item : itemList)
            {
                //Create query statements for each JSON Object
                query += p.parseDataFromFile((JSONObject) item);
            }

            //Create output.txt if filename doesn't exist
            if (file.createNewFile())
            {
                System.out.println("File created: " + file.getName());

                // creates a FileWriter Object
                FileWriter writer = new FileWriter(file);

                //Write MySQL query string to output.txt
                writer.write(INPUT_TABLE + System.lineSeparator());

                writer.write(PARAMETER_TABLE + System.lineSeparator());

                writer.write( query + System.lineSeparator());

                writer.close();

            }
            else
            {
                System.out.println("File already exists.");
            }

        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
    }

}


