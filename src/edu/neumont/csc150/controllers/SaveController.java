package edu.neumont.csc150.controllers;

import edu.neumont.csc150.models.TransactionLog;

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

    public static void saveState(String username, TransactionLog txnLog) {
        SaveManager.save(username, txnLog);
    }

    public static TransactionLog loadState(String username) {
        return SaveManager.load(username);
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
