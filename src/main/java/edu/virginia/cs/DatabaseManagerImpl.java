package edu.virginia.cs;

import java.sql.*;
import java.util.*;

public class DatabaseManagerImpl {
    static DatabaseManagerImpl single_instance;
    String databaseUrl = "Reviews.sqlite3";
    Connection connection;
    Statement statement;
    //https://drive.google.com/drive/folders/1A7bzgE8RJYdYMRCX7cA9vRu6I9IYHtli
    //Referred to SDE Lecture Content 11/17/22 throughout to find out about how connections work and about creating/using queries
    //https://www.sqlitetutorial.net/
    //Referred to the tutorial throughout to find out about specific sqlite keywords

    private DatabaseManagerImpl(){
        connect();
        createTables();
    }

    public static DatabaseManagerImpl getDatabaseManager(){
        if(single_instance == null){
            single_instance = new DatabaseManagerImpl();
        }
        return single_instance;
    }

    //@Override
    public void connect(){
        String url = "jdbc:sqlite:" + databaseUrl;
        try{
            Class.forName("org.sqlite.JDBC");
            if(connection == null) connection = DriverManager.getConnection(url);
        } catch(ClassNotFoundException e){
            throw new RuntimeException("Database class not found in root.");
        } catch(SQLException e){
            throw new IllegalStateException("Connection has already been established with database.");
        }
    }
    //@Override
    public void createTables(){
        try{
            if (connection == null || connection.isClosed()) {
                throw new IllegalStateException("Connection is closed right now.");
            }
            //First table: Students
            statement = connection.createStatement();
            String sqlStudents = "CREATE TABLE Students "+
                    " (id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    " name VARCHAR(255) not NULL, "+
                    " password VARCHAR(255) not NULL);";
            statement.executeUpdate(sqlStudents);
            statement.close();

            //Second table: Courses
            statement = connection.createStatement();
            String sqlCourses = "CREATE TABLE Courses "+
                    " (id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    " Department VARCHAR(255) not NULL, "+
                    " Catalog_Number INTEGER not NULL);";
            statement.executeUpdate(sqlCourses);
            statement.close();

            //Third table: Reviews
            statement = connection.createStatement();
            String sqlReviews = "CREATE TABLE Reviews "+
                    " (id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    " StudentName VARCHAR(255) not NULL, "+
                    " CourseDepartment VARCHAR(255) not NULL, "+
                    " CourseCatalogNumber INTEGER not NULL, "+
                    " textMessage VARCHAR(255) not NULL, "+
                    " rating INTEGER not NULL, "+
                    "FOREIGN KEY (StudentName) REFERENCES Students (name) ON DELETE CASCADE, "+
                    "FOREIGN KEY (CourseDepartment) REFERENCES Courses (Department) ON DELETE CASCADE, "+
                    "FOREIGN KEY (CourseCatalogNumber) REFERENCES Courses (Catalog_Number) ON DELETE CASCADE);";
            statement.executeUpdate(sqlReviews);
            statement.close();

        } catch (SQLException e) {
            throw new IllegalStateException(e+" At least one of the tables (Stops, BusLines, or Routes) already exists");
        }
    }
    //@Override
    public void clear() {
        try {
            if (connection == null || connection.isClosed()) {
                throw new IllegalStateException("Connection is closed right now.");
            }
            statement = connection.createStatement();

            statement.execute("DELETE FROM Students;");
            statement.execute("DELETE FROM Courses;");
            statement.execute("DELETE FROM Reviews;");

            statement.close();
        } catch(SQLException e){
            throw new IllegalStateException("Unable to clear the tables (Stops, BusLines, and Routes) as one of them may not exist");
        }
    }

    //@Override
    public void deleteTables() {
        try {
            if (connection == null || connection.isClosed()) {
                throw new IllegalStateException("Connection is closed right now.");
            }
            statement = connection.createStatement();

            String sql = "DROP TABLE Students;" + "DROP TABLE Courses;" + "DROP TABLE Reviews;";
            statement.executeUpdate(sql);
            statement.close();
        } catch(SQLException e){
            throw new IllegalStateException("Unable to delete the tables (Stops, BusLines, and Routes) as one of them may not exist");
        }

    }
    public boolean checkIfLoginExists(String username, String password) {
        try{
            if (connection == null || connection.isClosed()) {
                throw new IllegalStateException("Sorry, you connection is closed right now.");
            }
            statement = connection.createStatement();
            String sql = String.format("""
                    SELECT * FROM Students WHERE name = "%s" AND password = "%s" """, username, password);
            ResultSet rs = statement.executeQuery(sql);
            if(rs.next()){
                //Login exists
                System.out.println(rs.getString("name"));
                statement.close();
                rs.close();
                return true;
            }
            statement.close();
            rs.close();
            return false;

        } catch(SQLException e){
            throw new IllegalStateException("Unable to return user with name "+username+" because Students table does not exist");
        }
    }

    public void addStudents(List<Student> studentList){
        try{
            if (connection == null || connection.isClosed()) {
                throw new IllegalStateException("Sorry, you connection is closed right now.");
            }
            for(Student currentStudent : studentList) {
                String insertQuery = String.format("""
                                insert into Students (name, password)
                                    values ("%s", "%s");
                                    """, currentStudent.getUsername(),
                        currentStudent.getPassword());
                try {
                    statement = connection.createStatement();
                    statement.executeUpdate(insertQuery);
                    statement.close();
                }  catch(SQLException e) {
                    throw new IllegalArgumentException("The student with name: " + currentStudent.getUsername() + " is already in the students table.");
                }

            }
        } catch(SQLException e){
            throw new IllegalStateException("Student table likely does not exist.");
        }
    }

    public boolean addStudent(String name, String password){
        try{
            if (connection == null || connection.isClosed()) {
                throw new IllegalStateException("Sorry, your connection is closed right now.");
            }
            if(!checkIfLoginExists(name, password)){
                String sql = String.format("""
                        INSERT INTO Students(name, password)
                        VALUES ("%s", "%s");""", name, password);
                statement.executeUpdate(sql);
                statement.close();
                return true;
            }
            else{
                return false;
            }

        } catch(SQLException e){
            throw new IllegalStateException("Student table likely does not exist.");
        }
    }
    public boolean doesCourseExist(String department, int catalog_number){
        try{
            if (connection == null || connection.isClosed()) {
                throw new IllegalStateException("Sorry, your connection is closed right now.");
            }
            String sql = String.format("""
                    SELECT * FROM Courses WHERE Department = "%s" AND Catalog_Number = "%s" """, department, catalog_number);
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            statement.close();
            if(rs.next()) {
                return true;
            }
            else{
                return false;
            }
        } catch(SQLException e){
            throw new IllegalStateException("Student table likely does not exist.");
        }
    }

    public void addCourses(List<Course> courseList) {
        try {
            if (connection == null || connection.isClosed()) {
                throw new IllegalStateException("Sorry, your connection is closed right now.");
            }
            for(Course currentCourse : courseList) {
                String insertQueryToCourses = String.format("""
                                insert into Courses (Department, Catalog_Number)
                                    values ("%s", %d);
                                    """, currentCourse.getDepartment(),
                        currentCourse.getCatalogNumber());

                Map<Student, Review> allCourseReviews = currentCourse.getAllReviews();

                for(Student currentStudent : allCourseReviews.keySet()) {
                    String insertQueryToReviews = String.format("""
                                insert into Reviews (StudentName, CourseDepartment, CourseCatalogNumber, textMessage, rating)
                                    values ("%s", "%s", %d, "%s", %d);
                                    """, currentStudent.getUsername(),
                            currentCourse.getDepartment(),
                            currentCourse.getCatalogNumber(),
                            allCourseReviews.get(currentStudent).getReviewText(),
                            allCourseReviews.get(currentStudent).getRating());

                    try {
                        statement = connection.createStatement();
                        statement.executeUpdate(insertQueryToReviews);
                        statement.close();
                    } catch(SQLException e) {
                        throw new IllegalArgumentException("There are no reviews for this course.");
                    }
                }
                try {
                    statement = connection.createStatement();
                    statement.executeUpdate(insertQueryToCourses);
                    statement.close();
                } catch(SQLException e) {
                    throw new IllegalArgumentException("This course had invalid data, please check it.");
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException("The courses or reviews table likely doesn't exist.");
        }
    }
    public void addReview(Review review) {
        try {
            if (connection == null || connection.isClosed()) {
                throw new IllegalStateException("Sorry, your connection is closed right now.");
            }
            String insertQuery = String.format("""
                                insert into Reviews (StudentName, CourseDepartment, CourseCatalogNumber, textMessage, rating)
                                    values ("%s", "%s", %d, "%s", %d);
                                    """, review.getStudent().getUsername(),
                    review.getCourse().getDepartment(),
                    review.getCourse().getCatalogNumber(),
                    review.getReviewText(),
                    review.getRating());
            try {
                statement = connection.createStatement();
                statement.executeUpdate(insertQuery);
                statement.close();
;            } catch(SQLException ex) {
                throw new IllegalArgumentException("The given review has invalid data.");
            }
        } catch(SQLException e) {
            throw new IllegalStateException("The reviews table likely doesn't exist.");
        }
    }
    public List<ReviewMessage> getCourseReviews(String department, int catalog_number) {
        try {
            if (connection == null || connection.isClosed()) {
                throw new IllegalStateException("Sorry, your connection is closed right now.");
            }
            List<ReviewMessage> reviewList = new ArrayList<>();
            statement = connection.createStatement();
            String selectReviewsQuery = "select * from Reviews where CourseDepartment = " + department +
                    " and CourseCatalogNumber = " + catalog_number + ";";
            ResultSet rs = statement.executeQuery(selectReviewsQuery);
            while(rs.next()) {
                String reviewMessage = rs.getString("textMessage");
                int reviewScore = rs.getInt("rating");
                reviewList.add(new ReviewMessage(reviewMessage, reviewScore));
            }

            return reviewList;
            } catch (SQLException e) {
            throw new IllegalStateException("The courses or reviews table likely doesn't exist.");
        }

    }
    //used this link - https://www.baeldung.com/jdbc-check-table-exists
    boolean tableExists(Connection connection, String tableName) throws SQLException {
        DatabaseMetaData meta = connection.getMetaData();
        ResultSet resultSet = meta.getTables(null, null, tableName, new String[] {"TABLE"});
        return resultSet.next();
    }



}
