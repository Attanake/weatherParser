package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class DataBaseConnection {
    public Connection DBConnect(String DBName, String UserName){
        Connection conn = null;
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
}
