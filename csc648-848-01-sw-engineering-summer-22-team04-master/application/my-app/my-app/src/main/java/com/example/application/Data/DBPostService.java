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
public class DBPostService {
    
    public static void StorePostService(int userId, String Title, String body) {
        
        String url = "jdbc:mysql://aa6sm8glmiegl4.cabpjb9qfuhk.us-west-1.rds.amazonaws.com/ebdb";
        String userName = "team42022";
        String password = "team4_2022";
        System.out.println("\n\n\n\n\n" + Title + " "+ body+"\n\n\n\n");
        Statement statement;
        int serviceListingID = 0;
        

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, userName, password);
            System.out.println("Connection is Successful to the database" + url);
            
            /* Insert into the ForumPost table */
            String query = "INSERT INTO ServiceListing (title, description, created_at) VALUES (?,?, NOW());";
            
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, Title);
            preparedStmt.setString(2, body);
            
            preparedStmt.execute();

            /* Get the ID of the newly added product */
            query = "SELECT service_listing_id FROM ServiceListing ORDER BY service_listing_id DESC LIMIT 1;";
            
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                  serviceListingID = rs.getInt("service_listing_id");
                  //System.out.println(generalUserId);
                }
              } catch (SQLException e) {

            }
        /* Insert into the marketplace listing table */
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
             connection = DriverManager.getConnection(url, userName, password);
            System.out.println("Connection is Successful to the database" + url);
            
             query = "INSERT INTO Posts(service_listing_id, registered_user_id) values(?, ?)";
             PreparedStatement preparedStmt2 = connection.prepareStatement(query);
             preparedStmt2.setInt(1, serviceListingID);
             preparedStmt2.setInt(2, userId);
             preparedStmt2.execute();
        

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        System.out.println("DBPostItem storePostService connection closed");
        connection.close();
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    } catch (SQLException throwables) {
        throwables.printStackTrace();
    }
    }
}