package edu.neumont.csc150.controllers.save;

import edu.neumont.csc150.models.*;
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

    private static String getFullSaveDataPath() {
        return DATA_FOLDER + '/' + SAVE_FOLDER + '/';
    }

    private static String getFullSaveUserPath(String username) {
        return getFullSaveDataPath() + username + '/';
    }

    public static void saveTransactions(String username, TransactionLog txnLog) {
        if (createSaveFolder()) {
            // OK TO SAVE
            StringBuilder contents = new StringBuilder();
            contents.append("type,name,amount,date,category,description\n");
            for (Transaction txn : txnLog) {
                if (txn instanceof Income){
                    contents.append("income,");
                } else {
                    contents.append("expense,");
                }
                contents.append(txn.getName()).append(',');
                contents.append(txn.getAmount()).append(',');
                contents.append(txn.getDate()).append(',');
                contents.append(txn.getCategory()).append(',');
                contents.append(txn.getDescription()).append('\n');
            }
            writeSave(username, "transactions", contents.toString());
        }
    }

    public static TransactionLog loadTransactions(String username) {
        List<String> saveData = readSave(username, "transactions");
        TransactionLog txnLog = new TransactionLog();
        // parse if there is any data, otherwise skip
        if (saveData != null) {
            for (String line : saveData) {
                // parse from csv
                String[] properties = line.split(",");

                // declare variables
                Transaction txn;
                String type;
                String name;
                float amount;
                LocalDate date;
                TransactionCategory category;
                String description;

                // try to parse all properties, otherwise continue to next line
                try {
                    type = properties[0];
                    name = properties[1];
                    amount = Float.parseFloat(properties[2]);
                    date = LocalDate.parse(properties[3]);
                    category = TransactionCategory.valueOf(properties[4].toUpperCase());
                    description = properties[5].trim();
                } catch (DateTimeParseException | IllegalArgumentException e) {
                    continue;
                }

                // determine type
                if (type.equals("income")) {
                    txn = new Income();
                } else if (type.equals("expense")) {
                    txn = new Expense();
                } else {
                    continue;
                }

                txn.setProperties(name, amount, date, category, description);
                txnLog.add(txn);
            }
        }
        return txnLog;
    }

    public static void saveGoals(String username, List<Goal> goalLog) {
        if (createSaveFolder()) {
            // OK TO SAVE
            StringBuilder contents = new StringBuilder();
            contents.append("name,amount,category\n");
            for (Goal goal : goalLog) {
                contents.append(goal.getGoalName()).append(',');
                contents.append(goal.getGoalAmount()).append(',');
                contents.append(goal.getGoalCategory()).append('\n');
            }
            writeSave(username, "goals", contents.toString());
        }
    }

    public static List<Goal> loadGoals(String username) {
        List<String> saveData = readSave(username, "goals");
        List<Goal> goalLog = new ArrayList<>();
        if (saveData != null) {
            for (String line: saveData) {
                String[] properties = line.split(",");

                Goal goal;
                String goalName;
                float goalAmount;
                TransactionCategory goalCategory;
                try {
                    goalName = properties[0];
                    goalAmount = Float.parseFloat(properties[1]);
                    goalCategory = TransactionCategory.valueOf(properties[2].toUpperCase());
                } catch (IllegalArgumentException ex) {
                    continue;
                }
                goal = new Goal(goalName, goalAmount, goalCategory);
                goalLog.add(goal);
            }
        }
        return goalLog;
    }

    public static String[] getAllSaveFiles() {
        File saveFolder = new File(getFullSaveDataPath());
        if (saveFolder.exists()) {
            return saveFolder.list();
        }
        return null;
    }

    private static void writeSave(String username, String filename, String contents) {
        try {
            String path = getFullSaveUserPath(username) + filename + '.' + FILE_EXTENSION;
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

    private static List<String> readSave(String username, String filename) {
        try {
            // create buffered reader based on save path
            String path = getFullSaveUserPath(username) + filename + '.' + FILE_EXTENSION;
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
