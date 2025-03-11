package edu.neumont.csc150.controllers.save;

import edu.neumont.csc150.models.Goal;
import edu.neumont.csc150.models.TransactionLog;

import java.util.List;

public class SaveController {
    public static void run() {
        listSaves();
    }

    private static void listSaves() {
        String[] saves = SaveManager.getAllSaveFiles();
        if (saves != null) {
            System.out.println(String.join(" ,", saves));
        }
    }

    public static void saveState(String username, TransactionLog txnLog, List<Goal> goalLog) {
        SaveManager.saveTransactions(username, txnLog);
        SaveManager.saveGoals(username, goalLog);
    }

    public static boolean doesSaveExist(String username) {
        String[] saves = SaveManager.getAllSaveFiles();
        if (saves != null) {
            for (String save : saves) {
                if (save.split("\\.")[0].equals(username)) {
                    return true;
                }
            }
        }

        return false;
    }
}
