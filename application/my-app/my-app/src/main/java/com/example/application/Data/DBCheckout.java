package com.example.application.Data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

import com.mysql.cj.Query;


public class DBCheckout {

    private static Connection connection;

    public static void createPayment(String payment_type, String card_number)
    {
        //insert payment into db

        String url = "jdbc:mysql://aa6sm8glmiegl4.cabpjb9qfuhk.us-west-1.rds.amazonaws.com/ebdb";
        String userName = "team42022";
        String password = "team4_2022";

        try{

        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(url, userName, password);
        System.out.println("Connection is Successful to the database");
        
        int payment_id = 0;
        String payment = "INSERT INTO Payment (payment_id, payment_type, card_number) VALUES (?,?,?);";
        PreparedStatement paymentStmt = connection.prepareStatement(payment);
        paymentStmt.setInt(1, payment_id);
        paymentStmt.setString(2, payment_type);
        paymentStmt.setString(3, card_number);
        System.out.println(payment_id + payment_type + card_number);
        
        paymentStmt.execute();
        connection.close();

        }catch (Exception e)

        {
            System.out.println(e);
        } 
        

    }

    public static void createTransaction(int buyer_id, int seller_id){
        
        String url = "jdbc:mysql://aa6sm8glmiegl4.cabpjb9qfuhk.us-west-1.rds.amazonaws.com/ebdb";
        String userName = "team42022";
        String password = "team4_2022";


            try{

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, userName, password);
            System.out.println("Connection is Successful to the database");


            int transaction_id = 0;
            int payment_id = 0;
            LocalDateTime completed_at = LocalDateTime.now();

            String paymentQuery = "SELECT payment_id FROM Payment ORDER BY payment_id DESC LIMIT 1;";
            try (Statement paymentStmt = connection.createStatement()) {
                ResultSet rs = paymentStmt.executeQuery(paymentQuery);
                while (rs.next()) {
                   payment_id = rs.getInt("payment_id");
                }
              } catch (SQLException e) {
                System.out.println(e);
              }

            // insert transaction into db
            String transaction = "INSERT INTO Transaction (transaction_id, completed_at, payment_id, buyer_id, seller_id) VALUES (?,?,?,?,?);";
            PreparedStatement transactionStmt = connection.prepareStatement(transaction);
            transactionStmt.setInt(1, transaction_id);
            transactionStmt.setObject(2, completed_at);
            transactionStmt.setInt(3, payment_id);
            transactionStmt.setInt(4, buyer_id);
            transactionStmt.setInt(5, seller_id);

            transactionStmt.execute();
            connection.close();

            }catch (Exception e)
            {
            System.out.println(e);
            }    
        
    }

    public static void createTransactionItem(int product_id, String title, String image, String price, String quant) {

        String url = "jdbc:mysql://aa6sm8glmiegl4.cabpjb9qfuhk.us-west-1.rds.amazonaws.com/ebdb";
        String userName = "team42022";
        String password = "team4_2022";

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, userName, password);
            System.out.println("Connection is Successful to the database");


            int transaction_id = 0;

            String transactionQuery = "SELECT transaction_id FROM Transaction ORDER BY transaction_id DESC LIMIT 1;";
            try (Statement transactionQStmt = connection.createStatement()) {
                ResultSet rs = transactionQStmt.executeQuery(transactionQuery);
                while (rs.next()) {
                    transaction_id = rs.getInt("transaction_id");
                  //System.out.println(generalUserId);
                }
              } catch (SQLException e) {
                System.out.println(e);
              }

            // insert transactionitems into db
            String transactionItems = "INSERT INTO TransactionItems (transaction_id, product_id, title, image, price, quantity) VALUES (?,?,?,?,?,?);";
            PreparedStatement transactionItemsStmt = connection.prepareStatement(transactionItems);
            transactionItemsStmt.setInt(1, transaction_id);
            transactionItemsStmt.setInt(2, product_id);
            transactionItemsStmt.setString(3, title);
            transactionItemsStmt.setString(4, image);
            transactionItemsStmt.setString(5, price);
            transactionItemsStmt.setString(6, quant);
            
            transactionItemsStmt.execute();
            connection.close();

        }
        catch (Exception e)
        {
            System.out.println(e);
        } 

    }
    
}
