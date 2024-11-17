package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class DataBaseConnection {
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
        }catch (Exception e){
            System.out.println(e);
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
}
