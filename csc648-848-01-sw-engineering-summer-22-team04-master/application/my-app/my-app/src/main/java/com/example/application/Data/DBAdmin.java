package com.example.application.Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBAdmin {

    public  ArrayList<ArrayList<String>> searchUser() throws ClassNotFoundException {
        ArrayList<String> user = new ArrayList<String>();
        ArrayList<ArrayList<String>> userNumList = new ArrayList<>();
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
            String school;
            String contact;
            int isBanned;
            
                
            String q = "SELECT first_name, last_name, username, email, is_banned FROM RegisteredUser";
            try {
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(q);
                int count = 0;
                while (rs.next()) {
                    first_name = rs.getString("first_name");
                    last_name = rs.getString("last_name");
                    username = rs.getString("username");
                    emailDB = rs.getString("email");
                    isBanned = rs.getInt("is_banned");
                    
                    
                    user.add(first_name);
                    user.add(last_name);
                    user.add(username);
                    user.add(emailDB);
                    user.add(""+isBanned);
        

                    userNumList.add(user);
                    user = new ArrayList<>();
                    count++;
                         
                }
                System.out.println("DBAdmin searchUser connection closed");
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }  

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }  
        return userNumList;
    }


    public  void deleteUser(String deletedName){

        String url = "jdbc:mysql://aa6sm8glmiegl4.cabpjb9qfuhk.us-west-1.rds.amazonaws.com/ebdb";
        String userName = "team42022";
        String password = "team4_2022";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, userName, password);
            System.out.println("Connection is Successful to the database" + url);
            
            
                
            String q = "DELETE FROM RegisteredUser WHERE username = '"+deletedName+"';";
            try {
                Statement stmt = connection.createStatement();
                stmt.execute(q);
                System.out.println("\n\n\nHERE\n\n\n");
              
                
            } catch (SQLException e) {
                e.printStackTrace();
            }  
            System.out.println("DBAdmin deleteUser connection closed");
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }  


    }


    public void banUser(String username) {
         String url = "jdbc:mysql://aa6sm8glmiegl4.cabpjb9qfuhk.us-west-1.rds.amazonaws.com/ebdb";
        String userName = "team42022";
        String password = "team4_2022";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, userName, password);
            System.out.println("Connection is Successful to the database" + url);
            
            
                
            String q = "UPDATE RegisteredUser"
           +" SET is_banned = 1"
           +" WHERE username = '"+username+"';";
            try {
                Statement stmt = connection.createStatement();
                stmt.execute(q);
                System.out.println("\n\n\nHERE\n\n\n");
              
                
            } catch (SQLException e) {
                e.printStackTrace();
            }  
            System.out.println("DBAdmin banUser connection closed");
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }  



    }


    public void unBanUser(String username) {
        String url = "jdbc:mysql://aa6sm8glmiegl4.cabpjb9qfuhk.us-west-1.rds.amazonaws.com/ebdb";
        String userName = "team42022";
        String password = "team4_2022";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, userName, password);
            System.out.println("Connection is Successful to the database" + url);
            
            
                
            String q = "UPDATE RegisteredUser"
           +" SET is_banned = 0"
           +" WHERE username = '"+username+"';";
            try {
                Statement stmt = connection.createStatement();
                stmt.execute(q);
                System.out.println("\n\n\nHERE\n\n\n");
              
                
            } catch (SQLException e) {
                e.printStackTrace();
            }  
            System.out.println("DBAdmin unbanUser connection closed");
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }  

    }


    public static void deletePost(String title){

        String url = "jdbc:mysql://aa6sm8glmiegl4.cabpjb9qfuhk.us-west-1.rds.amazonaws.com/ebdb";
        String userName = "team42022";
        String password = "team4_2022";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, userName, password);
            System.out.println("Connection is Successful to the database" + url);
            
            
                
            String q = "DELETE FROM MarketplaceListing WHERE title = '"+title+"';";
            try {
                Statement stmt = connection.createStatement();
                stmt.execute(q);
              
                
            } catch (SQLException e) {
                e.printStackTrace();
            }  
            System.out.println("DBAdmin deletePost connection closed");
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }  

    }


    public static void deleteFormPost(String title){
        String url = "jdbc:mysql://aa6sm8glmiegl4.cabpjb9qfuhk.us-west-1.rds.amazonaws.com/ebdb";
        String userName = "team42022";
        String password = "team4_2022";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, userName, password);
            System.out.println("Connection is Successful to the database" + url);
            
            
                
            String q = "DELETE FROM ForumPost WHERE title = '"+title+"';";
            try {
                Statement stmt = connection.createStatement();
                stmt.execute(q);
              
                
            } catch (SQLException e) {
                e.printStackTrace();
            }  
            System.out.println("DBAdmin deletePost connection closed");
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }  


    }
    
    
    public static void deleteServicePost(String title){
        String url = "jdbc:mysql://aa6sm8glmiegl4.cabpjb9qfuhk.us-west-1.rds.amazonaws.com/ebdb";
        String userName = "team42022";
        String password = "team4_2022";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, userName, password);
            System.out.println("Connection is Successful to the database" + url);
            
            
                
            String q = "DELETE FROM ServiceListing WHERE title = '"+title+"';";
            try {
                Statement stmt = connection.createStatement();
                stmt.execute(q);
              
                
            } catch (SQLException e) {
                e.printStackTrace();
            }  
            System.out.println("DBAdmin deletePost connection closed");
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }  
        
    }
    
}