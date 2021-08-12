package jsonproject;

import static org.junit.Assert.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class ParseDataTests {

    private final static String JSON_DATA = "[{\n" +
            "\t\"Name\": \"Site 1\",\n" +
            "\t\"AlarmColor\": -1671296,\n" +
            "\t\"Id\": 8,\n" +
            "\t\"Parameters\": [{\n" +
            "\t\t\"Key\": \"Name\",\n" +
            "\t\t\"Value\": \"Site 1\"\n" +
            "\t}, {\n" +
            "\t\t\"Key\": \"Peak Power\",\n" +
            "\t\t\"Value\": \"6410.88\"\n" +
            "\t}, {\n" +
            "\t\t\"Key\": \"Description\",\n" +
            "\t\t\"Value\": \"6410.88 kW\"\n" +
            "\t}, {\n" +
            "\t\t\"Key\": \"Situation\",\n" +
            "\t\t\"Value\": \"England\"\n" +
            "\t}, {\n" +
            "\t\t\"Key\": \"Panel Degradation Correction Coefficient\",\n" +
            "\t\t\"Value\": \"0.7\"\n" +
            "\t}, {\n" +
            "\t\t\"Key\": \"Temperature Correction Coefficient\",\n" +
            "\t\t\"Value\": \"-0.41\"\n" +
            "\t}],\n" +
            "\t\"DatasourcesCount\": 0,\n" +
            "\t\"_alertIcon\": \"Communications\",\n" +
            "\t\"ElementCount\": 640,\n" +
            "\t\"UniqueID\": \"87111c51-08df-4b29-85c5-43803a994bdd\"\n" +
            "}, {\n" +
            "\t\"Name\": \"Site 2\",\n" +
            "\t\"AlarmColor\": -256,\n" +
            "\t\"Id\": 4,\n" +
            "\t\"Parameters\": [{\n" +
            "\t\t\"Key\": \"Name\",\n" +
            "\t\t\"Value\": \"Site 2\"\n" +
            "\t}, {\n" +
            "\t\t\"Key\": \"Peak Power\",\n" +
            "\t\t\"Value\": \"4010.16\"\n" +
            "\t}, {\n" +
            "\t\t\"Key\": \"Nominal Power\",\n" +
            "\t\t\"Value\": \"3355\"\n" +
            "\t}, {\n" +
            "\t\t\"Key\": \"Description\",\n" +
            "\t\t\"Value\": \"4010.160 kW\"\n" +
            "\t}, {\n" +
            "\t\t\"Key\": \"Panel Degradation Correction Coefficient\",\n" +
            "\t\t\"Value\": \"0.7\"\n" +
            "\t}, {\n" +
            "\t\t\"Key\": \"Temperature Correction Coefficient\",\n" +
            "\t\t\"Value\": \"-0.41\"\n" +
            "\t}],\n" +
            "\t\"DatasourcesCount\": 0,\n" +
            "\t\"_alertIcon\": \"Warning\",\n" +
            "\t\"ElementCount\": 514,\n" +
            "\t\"UniqueID\": \"20fb99ae-b940-484a-b3f2-2e7d619b952a\"\n" +
            "}, {\n" +
            "\t\"Name\": \"Site 3\",\n" +
            "\t\"AlarmColor\": -256,\n" +
            "\t\"Id\": 10,\n" +
            "\t\"Parameters\": [{\n" +
            "\t\t\"Key\": \"Name\",\n" +
            "\t\t\"Value\": \"Site 3\"\n" +
            "\t}, {\n" +
            "\t\t\"Key\": \"Peak Power\",\n" +
            "\t\t\"Value\": \"7257.6\"\n" +
            "\t}, {\n" +
            "\t\t\"Key\": \"Nominal Power\",\n" +
            "\t\t\"Value\": \"5250\"\n" +
            "\t}, {\n" +
            "\t\t\"Key\": \"Description\",\n" +
            "\t\t\"Value\": \"7.257,6 kW\"\n" +
            "\t}, {\n" +
            "\t\t\"Key\": \"Panel Degradation Correction Coefficient\",\n" +
            "\t\t\"Value\": \"0.7\"\n" +
            "\t}, {\n" +
            "\t\t\"Key\": \"Temperature Correction Coefficient\",\n" +
            "\t\t\"Value\": \"-0.41\"\n" +
            "\t}],\n" +
            "\t\"DatasourcesCount\": 0,\n" +
            "\t\"_alertIcon\": \"Communications\",\n" +
            "\t\"ElementCount\": 753,\n" +
            "\t\"UniqueID\": \"e4429e5a-60ef-437e-8759-2d84574a5289\"\n" +
            "}, {\n" +
            "\t\"Name\": \"Site 4\",\n" +
            "\t\"AlarmColor\": -256,\n" +
            "\t\"Id\": 11,\n" +
            "\t\"Parameters\": [{\n" +
            "\t\t\"Key\": \"Name\",\n" +
            "\t\t\"Value\": \"Site 4\"\n" +
            "\t}, {\n" +
            "\t\t\"Key\": \"Peak Power\",\n" +
            "\t\t\"Value\": \"20930.4\"\n" +
            "\t}, {\n" +
            "\t\t\"Key\": \"Description\",\n" +
            "\t\t\"Value\": \"20930.4 kW\"\n" +
            "\t}, {\n" +
            "\t\t\"Key\": \"Panel Degradation Correction Coefficient\",\n" +
            "\t\t\"Value\": \"0.7\"\n" +
            "\t}, {\n" +
            "\t\t\"Key\": \"Temperature Correction Coefficient\",\n" +
            "\t\t\"Value\": \"-0.41\"\n" +
            "\t}],\n" +
            "\t\"DatasourcesCount\": 0,\n" +
            "\t\"_alertIcon\": \"Warning\",\n" +
            "\t\"ElementCount\": 1938,\n" +
            "\t\"UniqueID\": \"bb0a0777-4c50-40ec-82ff-659fa33004a6\"\n" +
            "}]";


    private final static String QUERY_ONE = "\n INSERT INTO input (UniqueID, AlarmColor, Id, DatasourcesCount, _alertIcon, ElementCount, Name)" +
            "\n VALUES ('87111c51-08df-4b29-85c5-43803a994bdd', '-1671296', '8', '0', 'Communications', '640', 'Site 1');\n"
            +
            "\n INSERT INTO parameters (pName, NominalPower, PeakPower, Description, Situation, PanelDegradationCorrectionCoefficient, TemperatureCorrectionCoefficient)" +
            "\n VALUES ('Site 1', ' ', '6410.88', '6410.88 kW', 'England', '0.7', '-0.41');\n";


    private final static String QUERY_LAST = "\n INSERT INTO input (UniqueID, AlarmColor, Id, DatasourcesCount, _alertIcon, ElementCount, Name)" +
            "\n VALUES ('bb0a0777-4c50-40ec-82ff-659fa33004a6', '-256', '11', '0', 'Warning', '1938', 'Site 4');\n"
            +
            "\n INSERT INTO parameters (pName, NominalPower, PeakPower, Description, Situation, PanelDegradationCorrectionCoefficient, TemperatureCorrectionCoefficient)" +
            "\n VALUES ('Site 4', ' ', '20930.4', '20930.4 kW', ' ', '0.7', '-0.41');\n";


    private final static String QUERY_ALL = "\n INSERT INTO input (UniqueID, AlarmColor, Id, DatasourcesCount, _alertIcon, ElementCount, Name)" +
            "\n VALUES ('87111c51-08df-4b29-85c5-43803a994bdd', '-1671296', '8', '0', 'Communications', '640', 'Site 1');\n" +

            "\n INSERT INTO parameters (pName, NominalPower, PeakPower, Description, Situation, PanelDegradationCorrectionCoefficient, TemperatureCorrectionCoefficient)" +
            "\n VALUES ('Site 1', ' ', '6410.88', '6410.88 kW', 'England', '0.7', '-0.41');\n" +

            "\n INSERT INTO input (UniqueID, AlarmColor, Id, DatasourcesCount, _alertIcon, ElementCount, Name)" +
            "\n VALUES ('20fb99ae-b940-484a-b3f2-2e7d619b952a', '-256', '4', '0', 'Warning', '514', 'Site 2');\n" +

            "\n INSERT INTO parameters (pName, NominalPower, PeakPower, Description, Situation, PanelDegradationCorrectionCoefficient, TemperatureCorrectionCoefficient)" +
            "\n VALUES ('Site 2', '3355', '4010.16', '4010.160 kW', ' ', '0.7', '-0.41');\n" +

            "\n INSERT INTO input (UniqueID, AlarmColor, Id, DatasourcesCount, _alertIcon, ElementCount, Name)" +
            "\n VALUES ('e4429e5a-60ef-437e-8759-2d84574a5289', '-256', '10', '0', 'Communications', '753', 'Site 3');\n" +

            "\n INSERT INTO parameters (pName, NominalPower, PeakPower, Description, Situation, PanelDegradationCorrectionCoefficient, TemperatureCorrectionCoefficient)" +
            "\n VALUES ('Site 3', '5250', '7257.6', '7.257,6 kW', ' ', '0.7', '-0.41');\n" +

            "\n INSERT INTO input (UniqueID, AlarmColor, Id, DatasourcesCount, _alertIcon, ElementCount, Name)" +
            "\n VALUES ('bb0a0777-4c50-40ec-82ff-659fa33004a6', '-256', '11', '0', 'Warning', '1938', 'Site 4');\n" +

            "\n INSERT INTO parameters (pName, NominalPower, PeakPower, Description, Situation, PanelDegradationCorrectionCoefficient, TemperatureCorrectionCoefficient)" +
            "\n VALUES ('Site 4', ' ', '20930.4', '20930.4 kW', ' ', '0.7', '-0.41');\n";

        @Test
        public void parseFirstObjectFromJSON () throws FileNotFoundException, ParseException {

            ParseData p = new ParseData();

            JSONParser jsonParser = new JSONParser();

            Object obj = jsonParser.parse(JSON_DATA);

            JSONArray itemList = (JSONArray) obj;

            JSONObject object = (JSONObject) itemList.get(0);

            String actual = p.parseDataFromFile(object);

            String expected = QUERY_ONE;

            Assert.assertEquals(expected, actual);
        }

    @Test
    public void parseLastObjectFromJSON () throws FileNotFoundException, ParseException {

        ParseData p = new ParseData();

        JSONParser jsonParser = new JSONParser();

        Object obj = jsonParser.parse(JSON_DATA);

        JSONArray itemList = (JSONArray) obj;

        JSONObject object = (JSONObject) itemList.get(3);

        String actual = p.parseDataFromFile(object);

        String expected = QUERY_LAST;

        Assert.assertEquals(expected, actual);
    }

        @Test
        public void parseAllObjectsFromJSON () throws ParseException {

            String actual = "";

            ParseData p = new ParseData();

            JSONParser jsonParser = new JSONParser();

            Object obj = jsonParser.parse(JSON_DATA);

            JSONArray itemList = (JSONArray) obj;

            for(Object item : itemList)
            {
                actual += p.parseDataFromFile((JSONObject) item);
            }

            String expected = QUERY_ALL;
            Assert.assertEquals(expected, actual);

        }


}
