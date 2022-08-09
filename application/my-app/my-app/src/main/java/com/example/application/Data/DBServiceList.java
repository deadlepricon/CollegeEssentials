package com.example.application.Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.mysql.cj.xdevapi.Statement;

public class DBServiceList {
    public ArrayList<HashMap<String, String>> getServiceListings() throws ClassNotFoundException {
        //This function will return an array of hashmaps.
        //Each hashmap contains unique information for individual forum posts.
        ArrayList<HashMap<String, String>> posts = new ArrayList<HashMap<String, String>>();
        String url = "jdbc:mysql://aa6sm8glmiegl4.cabpjb9qfuhk.us-west-1.rds.amazonaws.com/ebdb";
        String userName = "team42022";
        String password = "team4_2022";

        try{
            //Variables needed to hold information from database to be stored in hashmaps/posts
            String forumTitle;
            String username;
            String description;

            //Establishes database connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, userName, password);
            System.out.println("Connection to the database" + url + "didn't go kaboom");
            java.sql.Statement stmt = connection.createStatement();

            //An SQL query that relates and joins 3 tables together.
            //"Forum Post" joins to a general "Posts" table, and "Posts" joins and relates to "Registered User" table 
            String q = "SELECT ServiceListing.title, ServiceListing.description, ServiceListing.created_at, RegisteredUser.username"
            +" FROM Posts"
            +" JOIN ServiceListing ON ServiceListing.service_listing_id = Posts.service_listing_id"
            +" JOIN RegisteredUser ON RegisteredUser.registered_user_id = Posts.registered_user_id"
            +" ORDER BY ServiceListing.created_at DESC;";

            ResultSet rs = stmt.executeQuery(q);
            while (rs.next()) {
                HashMap<String, String> post = new HashMap<String, String>();
                forumTitle = rs.getString("title");
                username = rs.getString("username");
                description = rs.getString("description");
                post.put("title",forumTitle);
                post.put("user",username);
                post.put("description",description);
                System.out.println(forumTitle + " " +username+ " "+description);
                posts.add(post);
            }
            System.out.println("DBServiceList getServiceListings connection closed");
            connection.close();
        }catch(SQLException e){
            
        }

        return posts;
    }
    public ArrayList<HashMap<String, String>> searchServiceListings(String searchValue, String searchBy) throws ClassNotFoundException {
        //This function will return an array of hashmaps.
        //Each hashmap contains unique information for individual forum posts.
        ArrayList<HashMap<String, String>> posts = new ArrayList<HashMap<String, String>>();
        String url = "jdbc:mysql://aa6sm8glmiegl4.cabpjb9qfuhk.us-west-1.rds.amazonaws.com/ebdb";
        String userName = "team42022";
        String password = "team4_2022";

        try{
            //Variables needed to hold information from database to be stored in hashmaps/posts
            String forumTitle;
            String username;
            String description;
            String q = "Filler";

            //Establishes database connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, userName, password);
            System.out.println("Connection to the database" + url + "didn't go kaboom");
            java.sql.Statement stmt = connection.createStatement();

            //An SQL query that relates and joins 3 tables together.
            //"Forum Post" joins to a general "Posts" table, and "Posts" joins and relates to "Registered User" table 
            if(searchBy.equals("Title")){
                q = "SELECT ServiceListing.title, ServiceListing.description, ServiceListing.created_at, RegisteredUser.username"
                +" FROM Posts"
                +" JOIN ServiceListing ON ServiceListing.service_listing_id = Posts.service_listing_id"
                +" JOIN RegisteredUser ON RegisteredUser.registered_user_id = Posts.registered_user_id"
                +" WHERE ServiceListing.title LIKE '%" +searchValue+"%'"
                +" ORDER BY ServiceListing.created_at DESC";
            }
            else if(searchBy.equals("Description")){
                q = "SELECT ServiceListing.title, ServiceListing.description, ServiceListing.created_at, RegisteredUser.username"
                +" FROM Posts"
                +" JOIN ServiceListing ON ServiceListing.service_listing_id = Posts.service_listing_id"
                +" JOIN RegisteredUser ON RegisteredUser.registered_user_id = Posts.registered_user_id"
                +" WHERE ServiceListing.description LIKE '%" +searchValue+"%'"
                +" ORDER BY ServiceListing.created_at DESC";
            }
            else{
                q = "SELECT ServiceListing.title, ServiceListing.description, ServiceListing.created_at, RegisteredUser.username"
                +" FROM Posts"
                +" JOIN ServiceListing ON ServiceListing.service_listing_id = Posts.service_listing_id"
                +" JOIN RegisteredUser ON RegisteredUser.registered_user_id = Posts.registered_user_id"
                +" WHERE ServiceListing.description LIKE '%" +searchValue+"%' OR ServiceListing.title LIKE '%" +searchValue+"%'"
                +" ORDER BY ServiceListing.created_at DESC";
            }
            
            ResultSet rs = stmt.executeQuery(q);
            while (rs.next()) {
                HashMap<String, String> post = new HashMap<String, String>();
                forumTitle = rs.getString("title");
                username = rs.getString("username");
                description = rs.getString("description");
                post.put("title",forumTitle);
                post.put("user",username);
                post.put("description",description);
                System.out.println(forumTitle + " " +username+ " "+description);
                posts.add(post);
            }
            System.out.println("DBServiceList searchServiceListings connection closed");
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }  

        return posts;
    }
    public static int getServiceListingID(String title) throws ClassNotFoundException {    
        String url = "jdbc:mysql://aa6sm8glmiegl4.cabpjb9qfuhk.us-west-1.rds.amazonaws.com/ebdb";
        String userName = "team42022";
        String password = "team4_2022";
        int service_listing_id = 0;
        try{
            //Establishes database connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, userName, password);
            System.out.println("Connection to the database" + url + "didn't go kaboom");
            java.sql.Statement stmt = connection.createStatement();

            //An SQL query that relates and joins 3 tables together.
            //"Forum Post" joins to a general "Posts" table, and "Posts" joins and relates to "Registered User" table 
            String q = "SELECT ServiceListing.service_listing_id FROM ServiceListing WHERE ServiceListing.title ='"+title+"';";

            ResultSet rs = stmt.executeQuery(q);
            while (rs.next()) {
                service_listing_id = rs.getInt("service_listing_id");
            }
            System.out.println("DBServiceList getServiceListingId connection closed");
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }  
        return service_listing_id;
    }
}


