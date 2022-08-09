package com.example.application.Data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import java.sql.Statement; 

public class DBViewUser {

    public static ArrayList<String> searchEmail(String email) throws ClassNotFoundException {
        ArrayList<String> user = new ArrayList<String>();
        String url = "jdbc:mysql://aa6sm8glmiegl4.cabpjb9qfuhk.us-west-1.rds.amazonaws.com/ebdb";
        String userName = "team42022";
        String password = "team4_2022";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, userName, password);
            System.out.println("Connection is Successful to the database" + url);
            
            String first_name;
            String last_name;
            String username;
            String emailDB;
            String about;
            String contact;
            String id;
            
                
            String q = "SELECT first_name, last_name, username, email, registered_user_id ,about, contact FROM RegisteredUser WHERE email LIKE '%"+email+"%'";
            try {
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(q);
                while (rs.next()) {
                    first_name = rs.getString("first_name");
                    last_name = rs.getString("last_name");
                    username = rs.getString("username");
                    emailDB = rs.getString("email");
                    about = rs.getString("about");
                    contact = rs.getString("contact");
                    id = rs.getString("registered_user_id");
                    
                    user.add(first_name);
                    user.add(last_name);
                    user.add(username);
                    user.add(emailDB);
                    user.add(about);
                    user.add(contact);
                    user.add(id);
                      
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }  
            System.out.println("DBViewUser searchEmail connection closed");
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }  
        return user;
    }

    public static ArrayList<String> searchUser(String userLogin) throws ClassNotFoundException {
        ArrayList<String> user = new ArrayList<String>();
        String url = "jdbc:mysql://aa6sm8glmiegl4.cabpjb9qfuhk.us-west-1.rds.amazonaws.com/ebdb";
        String userName = "team42022";
        String password = "team4_2022";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, userName, password);
            System.out.println("Connection is Successful to the database" + url);
            
            String first_name;
            String last_name;
            String username;
            String emailDB;
            String about;
            String contact;
            String id;
            String pfp;
                
            String q = "SELECT first_name, last_name, username, registered_user_id,email, about, contact, pfp  FROM RegisteredUser WHERE username LIKE '%"+userLogin+"%'";
            try {
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(q);
                while (rs.next()) {
                    first_name = rs.getString("first_name");
                    last_name = rs.getString("last_name");
                    username = rs.getString("username");
                    emailDB = rs.getString("email");
                    about = rs.getString("about");
                    contact = rs.getString("contact");
                    id = rs.getString("registered_user_id");
                    pfp = rs.getString("pfp");
                    
                    user.add(first_name);
                    user.add(last_name);
                    user.add(username);
                    user.add(emailDB);
                    user.add(about);
                    user.add(contact);
                    user.add(id);
                    user.add(pfp);
                      
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }  
            System.out.println("DBViewUser searchUser connection closed");
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }  
        return user;
    }
}
