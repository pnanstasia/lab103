package ua.edu.ucu.apps;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        Document sd = new SmartDocument("gs://cv-examples/wiki.png");
        System.out.println(sd.parse());
        TimeDocument timedDocument = new TimeDocument("gs://cv-examples/wiki.png");
        System.out.println(timedDocument.parse());
    }
}
