package edu.neumont.csc150.controllers;

import edu.neumont.csc150.models.Transaction;
import edu.neumont.csc150.models.TransactionLog;
import edu.neumont.csc150.models.enums.TransactionCategory;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class SaveManager {
    private static final String DATA_FOLDER = "data";
    private static final String SAVE_FOLDER = "save";
    private static final String FILE_EXTENSION = "csv";

    private static String getFullSaveGamePath() {
        return DATA_FOLDER + '/' + SAVE_FOLDER + '/';
    }

    public static void save(String saveName, TransactionLog txnLog) {
        if (createSaveFolder()) {
            // OK TO SAVE
            StringBuilder contents = new StringBuilder();
            contents.append("name,amount,date,category,description\n");
            for (Transaction txn : txnLog) {
                contents.append(txn.getName()).append(',');
                contents.append(txn.getAmount()).append(',');
                contents.append(txn.getDate()).append(',');
                contents.append(txn.getCategory()).append(',');
                contents.append(txn.getDescription()).append('\n');
            }
            writeSave(saveName, contents.toString());
        }
    }

    public static TransactionLog load(String saveName) {
        List<String> saveData = readSave(saveName);
        TransactionLog txnLog = new TransactionLog();
        // parse if there is any data, otherwise skip
        if (saveData != null ) {
            for (String line : saveData) {
                // parse from csv
                String[] properties = line.split(",");

                // create transaction
                Transaction txn = new Transaction();
                String name;
                int amount;
                LocalDate date;
                TransactionCategory category;
                String description;

                // try to parse all properties, otherwise continue to next line
                try {
                    name = properties[0];
                    amount = Integer.parseInt(properties[1]);
                    date = LocalDate.parse(properties[2]);
                    category = TransactionCategory.valueOf(properties[3].toUpperCase());
                    description = properties[4].trim();
                } catch (DateTimeParseException | IllegalArgumentException e) {
                    continue;
                }

                txn.setProperties(name, amount, date, category, description);
                txnLog.add(txn);
            }
        }
        return txnLog;
    }

    public static String[] getAllSaveFiles() {
        File saveFolder = new File(getFullSaveGamePath());
        if (saveFolder.exists()) {
            return saveFolder.list();
        }
        return null;
    }

    private static void writeSave(String saveName, String contents) {
        try {
            String path = getFullSaveGamePath() + saveName + '.' + FILE_EXTENSION;
            OutputStream fileOut = new FileOutputStream(path, false);
            Writer writer = new OutputStreamWriter(fileOut);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            try {
                bufferedWriter.write(contents);
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

    private static List<String> readSave(String saveName) {
        try {
            // create buffered reader based on save path
            String path = getFullSaveGamePath() + saveName + '.' + FILE_EXTENSION;
            InputStream fileIn = new FileInputStream(path);
            Reader reader = new InputStreamReader(fileIn);
            BufferedReader bufferedReader = new BufferedReader(reader);
            try {
                // appened all the info from file to an array then return
                List<String> saveInfo = new ArrayList<>();
                while (bufferedReader.ready()) {
                    saveInfo.add(bufferedReader.readLine());
                }
                return saveInfo;
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                // close reader and catch possible exception
                try{
                    bufferedReader.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (FileNotFoundException fnf) {
            fnf.printStackTrace();
        }
        return null;
    }

    private static boolean createSaveFolder() {
        File saveFolder = new File(DATA_FOLDER + '/' + SAVE_FOLDER);
        if (!saveFolder.exists()) {
            return saveFolder.mkdirs();
        } else {
            return true;
        }
    }
}
