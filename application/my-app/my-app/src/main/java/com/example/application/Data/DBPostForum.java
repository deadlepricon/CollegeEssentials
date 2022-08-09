package com.example.application.Data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;

import com.vaadin.flow.component.charts.model.Title;
import com.vaadin.flow.component.html.Image;
public class DBPostForum {
    
    public static void StorePostForum(int userId, String Title, String body) {
        
        String url = "jdbc:mysql://aa6sm8glmiegl4.cabpjb9qfuhk.us-west-1.rds.amazonaws.com/ebdb";
        String userName = "team42022";
        String password = "team4_2022";
        System.out.println("\n\n\n\n\n" + Title + " "+ body+"\n\n\n\n");
        Statement statement;
        int forumPostID = 0;
        

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, userName, password);
            System.out.println("Connection is Successful to the database" + url);
            
            /* Insert into the ForumPost table */
            String query = "INSERT INTO ForumPost (title, description, created_at) VALUES (?,?, NOW());";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, Title);
            preparedStmt.setString(2, body);
            
            
            preparedStmt.execute();

            /* Get the ID of the newly added product */
            query = "SELECT forum_post_id FROM ForumPost ORDER BY forum_post_id DESC LIMIT 1;";
            
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                  forumPostID = rs.getInt("forum_post_id");
                  //System.out.println(generalUserId);
                }
              } catch (SQLException e) {

            }
        /* Insert into the marketplace listing table */
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
             connection = DriverManager.getConnection(url, userName, password);
            System.out.println("Connection is Successful to the database" + url);
            
            //TODO - Hardcoded solution for registered user
            query = "INSERT INTO Posts(forum_post_id, registered_user_id) values(?, ?)";
            PreparedStatement preparedStmt2 = connection.prepareStatement(query);
            preparedStmt2.setInt(1, forumPostID);
            preparedStmt2.setInt(2, userId);
            preparedStmt2.execute();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("DBPostForum storePostForum connection closed");
        connection.close();
    
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    } catch (SQLException throwables) {
        throwables.printStackTrace();
    }
    }
}