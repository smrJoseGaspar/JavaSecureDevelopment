/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.gva.edu.sanchezgarcia.jg.javasecuresw;

import java.util.Scanner;
import java.lang.System.*;
import java.sql.*;
import java.util.logging.Level;

/**
 *
 * @author josegaspar
 */
public class SQLInjectionExample {
    
    private static Connection con=null;
    private static final String driver="com.mysql.cj.jdbc.Driver";
    private static final String user="root";
    private static final String passwd="1234";
    private static final String url="jdbc:mysql://localhost:4208/mydb?zeroDateTimeBehavior=CONVERT_TO_NULL";
    
    
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your username: ");
        String username = scanner.nextLine();
        
        
        try {
            con=DriverManager.getConnection(url, user, passwd);
            Statement statement = con.createStatement();
            String query = "SELECT * FROM users WHERE username = '" + username + "';";
            ResultSet resultSet;
            resultSet = statement.executeQuery(query);
            
            if(resultSet.next()) {
                System.out.println("Welcome, " + resultSet.getString("username"));
            } else {
                System.out.println("User not found.");
            }
            con.close();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(SQLInjectionExample.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
