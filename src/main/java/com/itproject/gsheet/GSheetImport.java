package com.itproject.gsheet;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.itproject.gsheet.GSheetDAO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GSheetImport
{
    //      To load the data from GSheet source:
    //      1: Instanciate a GSheet Controller
    //              GSheetController myGsheetController = new GSheetController();
    //      2:Instanciate a GSheetDAO object
    //              GSheetDAO myObjectDAO = new GSheetDAO();
    //      3: Call this getRows method to load the data in
    //              myObjectDAO = myGsheetController.getRows();
    //      3: To verify the data use toString         
    //              System.out.println(myObjectDAO.toString());

    private static final String APPLICATION_NAME = "Google Sheets API Java Quickstart";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";

    /**
     * Global instance of the scopes required. If modifying these scopes, delete
     * your previously saved tokens/ folder.
     */
    private static final List<String> SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS_READONLY);
    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";

    /**
     * Creates an authorized Credential object.
     *
     * @param HTTP_TRANSPORT The network HTTP Transport.
     * @return An authorized Credential object.
     * @throws IOException If the credentials.json file cannot be found.
     */
    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException
    {
        // Load client secrets.
        InputStream in = GSheetDAO.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null)
        {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }

    public GSheetDAO getRows() throws IOException, GeneralSecurityException
    {
        // Create the output object
        GSheetDAO output = new GSheetDAO();
        // Build a new authorized API client service.
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        //Identify the sheet by Id
        final String spreadsheetId = "1k694Wl8xDpPVSsRwVKCxnM8s4ym7gr3aKgu33MUbb7Q";
        //Get the header first:        
        //      set the range to read the header
        final String rangeHeader = "A1:BZ1"; // put BZ1 to get a good margin for a big header;)
        Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();
        ValueRange response = service.spreadsheets().values()
                .get(spreadsheetId, rangeHeader)
                .execute();
        //read the values of the header
        List<List<Object>> values = response.getValues();

        if (values == null || values.isEmpty()) //If is empty launch an Exception
        {
            System.out.println("No data source found.");
            throw new IOException();
        }
        else
        {
            //set the header
            String read;
            ArrayList<Integer> idColumn = new ArrayList<>();
            for (int i = 0; i < values.get(0).size(); i++)
            {
                read = values.get(0).get(i).toString();
                //Confirm the header positions and name
                if (read.equals("Nom") || read.equals("Email") || read.equals("Sexe")
                        || read.equals("Curs") || read.equals("Conclusió")
                        || read.equals("Data Alta") || read.equals("Data limit")
                        || read.equals("Data de control d'assistencies (llista)"))
                {
                    output.addToHeader(values.get(0).get(i).toString()); //save the column 
                    idColumn.add(i); // save the column position
                }
            }

            //Get the rest now:                 
            //          set the range to read all the source data
            final String rangeRows = "A2:BZ"; // A2 to jump the header and BZ to get a big margin ;)
            ValueRange responseRows = service.spreadsheets().values()
                    .get(spreadsheetId, rangeRows)
                    .execute();
            //Rescue the data        
            List<List<Object>> valuesRows = responseRows.getValues();

            if (values == null || valuesRows.isEmpty())
            {
                System.out.println("No data source found.");
                throw new IOException();
            }
            else
            {
                //Reading the target columns on each row 
                List<List<String>> outputRows = new ArrayList<>();

                for (int i = 0; i < valuesRows.size(); i++)
                {
                    List<String> listColumns = new ArrayList<>();
                    String columnToAdd = "";
                    Boolean isVerified = false;

                    for (int j = 0; j < idColumn.size(); j++) //read and jump no target columns
                    {
                        int posColumn = idColumn.get(j);
                        //add to a List of columns row
                        columnToAdd = valuesRows.get(i).get(posColumn).toString();
                        listColumns.add(columnToAdd); // Create the list of columns
                    }

                    //filter the rows list before add to the List                   
                    isVerified = filterColumns(output, listColumns);
                    if (isVerified == true)
                    {
                        outputRows.add(listColumns);
                    }
                }
                output.setListRows(outputRows);
            }
        }
        return output; // return the header List and the rows List in the DTO object
    }

    private boolean filterColumns(GSheetDAO gSheetDao, List<String> listColumns)
    {
        //Having a list of columns in a line, filter by different cases

        int countFalse = 0; // count to verify the no coincidence cases
        for (int i = 0; i < gSheetDao.getHeader().size(); i++) // Get the targets position header
        {
            String headerTitle = gSheetDao.getHeader().get(i);
            int posColumn = gSheetDao.getHeader().indexOf(headerTitle);
            //For a new condition inser new case 
            switch (headerTitle)
            {
                case "Curs":
                    if (!(listColumns.get(posColumn).equals("DEVELOPER")
                            || listColumns.get(posColumn).equals("FE&BE")))
                    {
                        countFalse++;
                    }
                    break;

                case "Conclusió":
                    if (!(listColumns.get(posColumn).equals("APTE")))
                    {

                        countFalse++;
                    }
                    break;
            }
        }
        if (countFalse != 0)
        {
            //filter not passed
            return false;
        }
        else
        {
            //filter passed
            return true;
        }
    }
}
