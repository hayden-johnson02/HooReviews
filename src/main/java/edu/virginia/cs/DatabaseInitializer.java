package edu.virginia.cs;

public class DatabaseInitializer {
    public static void main(String[] args) {
        DatabaseManagerImpl database = DatabaseManagerImpl.getDatabaseManager();
        database.connect();
        database.clear();
        database.disconnect();
    }
}
