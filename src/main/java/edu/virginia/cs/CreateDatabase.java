package edu.virginia.cs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CreateDatabase {
    static String databaseUrl = "Reviews.sqlite3";
    public static void initialize(){
        String url = "jdbc:sqlite:" + databaseUrl;
        try{
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(url);
        } catch(SQLException | ClassNotFoundException e){
            throw new RuntimeException(e+"Could not create database Reviews.sqlite3");
        }
    }

    public static void main(String[] args){
        initialize();
    }
}
