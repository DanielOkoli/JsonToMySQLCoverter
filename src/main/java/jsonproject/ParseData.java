package jsonproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.text.ParagraphView;
import javax.xml.crypto.Data;

public class ParseData {

    private String pQuery = "";
    private String iQuery = "";

    public String parseDataFromFile(JSONObject object) {

        //MySQL column variables
        String pName = " ";
        String PeakPower = " ";
        String Description = " ";
        String Situation = " ";
        String PanelDegradationCorrectionCoefficient = " ";
        String TemperatureCorrectionCoefficient = " ";
        String NominalPower = " ";

        //Get Name object within list
        String Name = (String) object.get("Name");

        //Get AlarmColor object within list
        Long AlarmColor = (Long) object.get("AlarmColor");

        //Get Id object within list
        Long Id = (Long) object.get("Id");

        //Get DataSourcesCount object within list
        Long DatasourcesCount = (Long) object.get("DatasourcesCount");

        //Get _alertIcon object within list
        String _alertIcon = (String) object.get("_alertIcon");

        //ElementCount object within list
        Long ElementCount = (Long) object.get("ElementCount");

        //Get UniqueID object within list
        String UniqueID = (String) object.get("UniqueID");

        ////Get parameters array within list
        JSONArray parameters = (JSONArray) object.get("Parameters");

        //Iterate over parameters array
        for (int j = 0; j < parameters.size(); j++)
        {
            JSONObject pObject = (JSONObject) parameters.get(j);

            //Get key object
            String Key = (String) pObject.get("Key");

            //Get value object
            String Value = (String) pObject.get("Value");

            if (Key.equals("Name"))
            {
                //Get name object
                pName = Value;
            }
            else if (Key.equals("Peak Power"))
            {
                //Get peak power object
                PeakPower = Value;
            }
            else if (Key.equals("Nominal Power"))
            {
                //Get nominal power object
                NominalPower = Value;
            }
            else if (Key.equals("Description"))
            {
                //Get description object
                Description = Value;
            }
            else if (Key.equals("Situation"))
            {
                //Get situation object
                Situation = Value;
            }
            else if(Key.equals("Panel Degradation Correction Coefficient"))
            {
                //Get panel degradation correction coefficient object
                PanelDegradationCorrectionCoefficient = Value;
            }
            else if(Key.equals("Temperature Correction Coefficient"))
            {
                //Get temperature correction coefficient object
                TemperatureCorrectionCoefficient = Value;
            }

        }

        // the mysql insert statements for parameter and input table
        pQuery = "\n INSERT INTO input (UniqueID, AlarmColor, Id, DatasourcesCount, _alertIcon, ElementCount, Name)"
                + "\n VALUES ('" + UniqueID + "', '" + AlarmColor + "', '" + Id + "', '" + DatasourcesCount + "', '" + _alertIcon + "', '" + ElementCount  +  "', '" + Name + "');";

        iQuery = "\n INSERT INTO parameters (pName, NominalPower, PeakPower, Description, Situation, PanelDegradationCorrectionCoefficient, TemperatureCorrectionCoefficient)"
                + "\n VALUES ('" + pName + "', '" + NominalPower + "', '" +  PeakPower + "', '" + Description + "', '" + Situation + "', '" + PanelDegradationCorrectionCoefficient + "', '" + TemperatureCorrectionCoefficient + "');";

        return pQuery + "\n" +  iQuery + "\n";

    }

}



