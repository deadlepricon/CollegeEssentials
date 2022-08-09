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
public class DBPostItem {
    


    public static void StorePostItem(String Title, String image, String condition, String general_type, String price, String body, String quant) {
        
        String url = "jdbc:mysql://aa6sm8glmiegl4.cabpjb9qfuhk.us-west-1.rds.amazonaws.com/ebdb";
        String userName = "team42022";
        String password = "team4_2022";
        System.out.println("\n\n\n\n\n" + Title + " " + image +" "+ condition+"\n\n\n\n");

        int productID = 0;
        int marketPlaceID = 0;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, userName, password);
            System.out.println("Connection is Successful to the database");
            
            /* Insert into the product table */
            
            String query = "INSERT INTO Product (product_image, product_condition, general_type) VALUES (?,?,?);";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, image);
            preparedStmt.setString(2, condition);
            preparedStmt.setString(3, general_type);
            
            preparedStmt.execute();

            /* Get the ID of the newly added product */
            query = "SELECT product_id FROM Product ORDER BY product_id DESC LIMIT 1;";
            
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                  productID = rs.getInt("product_id");
                  //System.out.println(generalUserId);
                }
              } catch (SQLException e) {

            }
        /* Insert into the marketplace listing table */
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
             connection = DriverManager.getConnection(url, userName, password);
            System.out.println("Connection is Successful to the database for PostItems");
            
            //TODO - Hardcoded solution
             query = "INSERT MarketplaceListing(title, seller_id, price, body, quantity) values(?, ? , ?, ?, ?)";
            
             PreparedStatement preparedStmt2 = connection.prepareStatement(query);
             preparedStmt2.setString(1, Title);
             preparedStmt2.setInt( 2,DBlogin.getUserId());
             preparedStmt2.setString(3, price);
             preparedStmt2.setString(4, body);
             preparedStmt2.setString(5, quant);
             preparedStmt2.execute();
        

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        /* Get the marketplace listing id of the newly added listing */
        query = "SELECT marketplace_listing_id FROM MarketplaceListing ORDER BY marketplace_listing_id DESC LIMIT 1;";
           
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
              marketPlaceID = rs.getInt("marketplace_listing_id");
              //System.out.println(generalUserId);
            }
          } catch (SQLException e) {

          }
        /* Insert into the associative table ProductListings */
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
             connection = DriverManager.getConnection(url, userName, password);
            System.out.println("Connection is Successful to the database" + url);
            
             query = "INSERT INTO ProductListings (marketplace_listing_id, product_id) VALUES (?, ?);";
             PreparedStatement preparedStmt3 = connection.prepareStatement(query);
             preparedStmt3.setInt(1, marketPlaceID);
             preparedStmt3.setInt(2, productID);

             preparedStmt3.execute();
        
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("DBPostItem postItem connection closed");
        connection.close();
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    } catch (SQLException throwables) {
        throwables.printStackTrace();
    }
    }
}
