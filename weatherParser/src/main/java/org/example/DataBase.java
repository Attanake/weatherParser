package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class DataBase {
    Connection conn = null;
    public Connection DBConnect(String DBName, String UserName){
        try{
            Class.forName("org.postgresql.Driver");
            System.out.println("Enter the password for DataBase connection");
            Scanner scanner = new Scanner(System.in);
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+DBName, UserName, scanner.nextLine());

            if(conn != null){
                System.out.println("Connected");
            }else{
                System.out.println("Not connected");
            }
        }catch (Exception exception){
            System.out.println(exception);
        }
        return conn;
    }

    public PreparedStatement QueryTemperature(){
        try {
            String query = "INSERT INTO temperature VALUES (?, ?, ?)";
            return conn.prepareStatement(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public PreparedStatement QueryExpectedTemperature(){
        try {
            String query = "INSERT INTO expected_temperature VALUES (?, ?, ?, ?)";
            return conn.prepareStatement(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public PreparedStatement QueryShowTemperature(){
        try {
            String query = "SELECT \n" +
                    "    t.date,\n" +
                    "    t.max_temp,\n" +
                    "    t.min_temp,\n" +
                    "    e.max_expected_temp,\n" +
                    "    e.min_expected_temp\n" +
                    "FROM \n" +
                    "    temperature t\n" +
                    "JOIN \n" +
                    "    expected_temperature e ON t.date = e.date\n" +
                    "WHERE e.parse_date = ?";
            return conn.prepareStatement(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
