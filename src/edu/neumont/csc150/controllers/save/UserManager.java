package edu.neumont.csc150.controllers.save;

import java.util.*;
import java.io.*;

public class UserManager {
    private static final String DATA_FOLDER = "data";
    private static final String FILENAME = "credentials";
    private static final String FILE_EXT = "csv";

    private static String getFullCredsPath() {
        return DATA_FOLDER + '/' + FILENAME + '.' + FILE_EXT;
    }

    /**
     * Write contents to creds file
     * @param credentialsStr Contents to be written to creds file
     */
    private static void appendToFile(String credentialsStr) {
        try {
            String path = getFullCredsPath();
            OutputStream fileOut = new FileOutputStream(path, true);
            Writer writer = new OutputStreamWriter(fileOut);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            try {
                bufferedWriter.write(credentialsStr);
                bufferedWriter.flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    bufferedWriter.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (FileNotFoundException fnf) {
            fnf.printStackTrace();
        }
    }

    /**
     * Parses username and password into a valid string to be appended to file
     * @param username Username of user to be added
     * @param password Password of user to be added
     */
    public static void addCredsToFile(String username, String password) {
        String credentialsStr = username + ':' + password + '\n';
        appendToFile(credentialsStr);
    }

    /**
     * Reads credentials from file into HashMap
     * @return Hashmap List of credentials
     */
    public static List<Map<String, String>> readFromFile() {
        List<Map<String, String>> credentialsList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(getFullCredsPath()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Parsing username:password
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    Map<String, String> credentials = new HashMap<>();
                    credentials.put("username", parts[0].trim());
                    credentials.put("password", parts[1].trim());
                    credentialsList.add(credentials);
                }
            }
            //System.out.println("Data read from file successfully.");
        } catch (IOException e) {
            //System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
        return credentialsList;
    }

    /**
     * Checks credentials against creds database
     * @param username Username to be checked
     * @param password Password to be checked
     * @return True if creds exist in database together, false if not
     */
    public static boolean checkCredentials(String username, String password) {
        List<Map<String, String>> parsedCredentials = readFromFile();
        for (Map<String, String> credential : parsedCredentials) {
            if (credential.get("username").equals(username) && credential.get("password").equals(password)) {
                //System.out.println("Username and Password are correct");
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if username exists in database
     * @param username Username to be checked
     * @return True if username is found, false if not
     */
    public static boolean checkUsername(String username) {
        List<Map<String, String>> parsedCredentials = readFromFile();
        for (Map<String, String> credential : parsedCredentials) {
            if (credential.get("username").equals(username)) {
                return true;
            }
        }
        return false;
    }
}
