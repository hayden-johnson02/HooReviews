package edu.virginia.cs;

import org.junit.jupiter.api.BeforeEach;

public class DatabaseImplTest {
    @BeforeEach
    public void initialize(){
        DatabaseManager dbManager = DatabaseManagerImpl.getDatabaseManager();
    }
}
