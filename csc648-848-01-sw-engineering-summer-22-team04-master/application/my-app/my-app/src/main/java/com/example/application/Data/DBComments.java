package com.example.application.Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;

public class DBComments {

    private JdbcTemplate jdbc;
    private static String url = "jdbc:mysql://aa6sm8glmiegl4.cabpjb9qfuhk.us-west-1.rds.amazonaws.com/ebdb";
    private static String userName = "team42022";
    private static String password = "team4_2022";
    private static String idVal;
    private static int forum_reply_id, service_reply_id;

    //A method to store a newly created ForumReply into the remote DB
    public static void StoreForumReply(String description, int forum_post_id, int registered_user_id) {
        //Insert into ForumReply table + get ID of recently added ForumReply
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, userName, password);
            System.out.println("DBComment: Connection is Successful to the database" + url);

            String query = "INSERT INTO ForumReply (description, posted_at) VALUES (?, NOW());";
            PreparedStatement preparedStmt1 = connection.prepareStatement(query);
            preparedStmt1.setString(1, description);

            preparedStmt1.execute();

            idVal = "SELECT forum_reply_id FROM ForumReply ORDER BY forum_reply_id DESC LIMIT 1;";

            forum_reply_id = 0;

            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(idVal);
                while (rs.next()) {
                    forum_reply_id = rs.getInt("forum_reply_id");
                    System.out.println(forum_reply_id);
                }
            } catch (SQLException e) {

            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        // finally{
        //     connection.close();
        // }
        //Insert into associative table ForumTies
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, userName, password);
            System.out.println("DBComment: Connection is Successful to the database" + url);

            String query = "INSERT INTO ForumTies (forum_post_id, forum_reply_id) VALUES (?, ?);";
            PreparedStatement preparedStmt2 = connection.prepareStatement(query);
            preparedStmt2.setInt(1, forum_post_id);
            preparedStmt2.setInt(2, forum_reply_id);

            preparedStmt2.execute();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //Insert into associative table Posts
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, userName, password);
            System.out.println("DBComment: Connection is Successful to the database" + url);

            String query = "INSERT INTO Posts (forum_reply_id, registered_user_id) VALUES (?, ?);";
            PreparedStatement preparedStmt3 = connection.prepareStatement(query);
            preparedStmt3.setInt(1, forum_reply_id);
            preparedStmt3.setInt(2, registered_user_id);

            preparedStmt3.execute();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //A method to store a newly created ForumReply into the remote DB
    public static void StoreServiceReply(String description, int service_listing_id, int registered_user_id) {
        //Insert into ServiceReply table + get ID of recently added ServiceReply
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, userName, password);
            System.out.println("DBComment: Connection is Successful to the database" + url);

            String query = "INSERT INTO ServiceReply (description, posted_at) VALUES (?, NOW());";
            PreparedStatement preparedStmt4 = connection.prepareStatement(query);
            preparedStmt4.setString(1, description);

            preparedStmt4.execute();

            idVal = "SELECT service_reply_id FROM ServiceReply ORDER BY service_reply_id DESC LIMIT 1;";

            service_reply_id = 0;

            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(idVal);
                while (rs.next()) {
                    service_reply_id = rs.getInt("service_reply_id");
                    System.out.println(service_reply_id);
                }
            } catch (SQLException e) {

            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //Insert into associative table ServiceTies
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, userName, password);
            System.out.println("DBComment: Connection is Successful to the database" + url);

            String query = "INSERT INTO ServiceTies (service_listing_id, service_reply_id) VALUES (?, ?);";
            PreparedStatement preparedStmt2 = connection.prepareStatement(query);
            preparedStmt2.setInt(1, service_listing_id);
            preparedStmt2.setInt(2, service_reply_id);

            preparedStmt2.execute();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //Insert into associative table Posts
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, userName, password);
            System.out.println("DBComment: Connection is Successful to the database" + url);

            String query = "INSERT INTO Posts (service_reply_id, registered_user_id) VALUES (?, ?);";
            PreparedStatement preparedStmt2 = connection.prepareStatement(query);
            preparedStmt2.setInt(1, service_reply_id);
            preparedStmt2.setInt(2, registered_user_id);

            preparedStmt2.execute();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static ArrayList<ArrayList<String>> getForumReply(int forumPostID){
        ArrayList<ArrayList<String>> comments = new ArrayList<>();
        String description;
        String postedAt;
        String username;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, userName, password);
            System.out.println("DBComment: Connection is Successful to the database" + url);

            String query = "SELECT ForumReply.description, ForumReply.posted_at, RegisteredUser.username, ForumPost.forum_post_id"
            +" FROM ForumReply"
            +" JOIN ForumTies ON ForumTies.forum_reply_id = ForumReply.forum_reply_id"
            +" JOIN Posts ON Posts.forum_reply_id = ForumReply.forum_reply_id"
            +" JOIN RegisteredUser ON RegisteredUser.registered_user_id = Posts.registered_user_id"
            +" JOIN ForumPost ON ForumPost.forum_post_id = ForumTies.forum_post_id"
            +" WHERE ForumPost.forum_post_id ="+forumPostID+""
            +" ORDER BY ForumReply.posted_at DESC;";

            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    ArrayList array = new ArrayList<>();
                    description = rs.getString("description");
                    postedAt = rs.getString("posted_at");
                    username = rs.getString("username");
                    
                    array.add(description);
                    array.add(postedAt);
                    array.add(username);
                    System.out.println(description+ " "+postedAt+" "+username);
                    comments.add(array);
                }
            } catch (SQLException e) {

            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return comments;
    }
    public static ArrayList<ArrayList<String>> getServiceReply(int serviceListingID){
        ArrayList<ArrayList<String>> comments = new ArrayList<>();
        String description;
        String postedAt;
        String username;

        //Insert into ForumReply table + get ID of recently added ForumReply
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, userName, password);
            System.out.println("DBComment: Connection is Successful to the database" + url);

            String query ="SELECT ServiceReply.description, ServiceReply.posted_at, RegisteredUser.username, ServiceListing.service_listing_id"
            +" FROM ServiceReply"
            +" JOIN ServiceTies ON ServiceTies.service_reply_id = ServiceReply.service_reply_id"
            +" JOIN Posts ON Posts.service_reply_id = ServiceReply.service_reply_id"
            +" JOIN RegisteredUser ON RegisteredUser.registered_user_id = Posts.registered_user_id"
            +" JOIN ServiceListing ON ServiceListing.service_listing_id = ServiceTies.service_listing_id"
            +" WHERE ServiceListing.service_listing_id ="+serviceListingID+""
            +" ORDER BY ServiceReply.posted_at DESC;";

            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    ArrayList<String> indivComment = new ArrayList<>();
                    description = rs.getString("description");
                    postedAt = rs.getString("posted_at");
                    username = rs.getString("username");
                    
                    indivComment.add(description);
                    indivComment.add(postedAt);
                    indivComment.add(username);
                    
                    comments.add(indivComment);
                }
            } catch (SQLException e) {

            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return comments;
    }
}
