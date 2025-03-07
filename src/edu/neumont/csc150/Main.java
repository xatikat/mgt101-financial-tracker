package edu.neumont.csc150;

import edu.neumont.csc150.controllers.AppController;
import edu.neumont.csc150.controllers.UserController;

public class Main {
    public static void main(String[] args) {
        new AppController().run();
        //new TestController().run();
        //new UserController().run();
    }
}
