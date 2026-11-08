package pt.unips.tapoo;

import javafx.application.Application;

public class Main {
    // Doesn't extend Application so it also launches from the plain classpath
    public static void main(String[] args) {
        Application.launch(GraphApp.class, args);
    }
}
