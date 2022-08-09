package com.example.application.Data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import org.apache.poi.ss.formula.functions.Count;

public class DBPurchaseHistory {

    private static String transaction_id;


    public static HashMap<String, ArrayList<String>> createPaymentHistory(int buyer_id){
        HashMap<String, ArrayList<String>> paymentList = new HashMap<String, ArrayList<String>>();

        String url = "jdbc:mysql://aa6sm8glmiegl4.cabpjb9qfuhk.us-west-1.rds.amazonaws.com/ebdb";
        String userName = "team42022";
        String password = "team4_2022";
        

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, userName, password);
            System.out.println("Connection is Successful to the database");


            String paymentQuery = "SELECT Transaction.payment_id, Transaction.transaction_id FROM Transaction WHERE buyer_id = '" + buyer_id + "' ORDER BY payment_id DESC";


            String payment_id;


            try (Statement paymentStmt = connection.createStatement()) 
            {
                ResultSet rs = paymentStmt.executeQuery(paymentQuery);
                String tempKey = "";
                ArrayList<String> paymentArray = new ArrayList<String>();
                while (rs.next()) {

                   
                    payment_id = rs.getString("payment_id");
                    transaction_id = rs.getString("transaction_id");

                    if(tempKey.equals(payment_id))
                    {
                        paymentArray.add(transaction_id);
                        paymentList.put(payment_id, paymentArray);

                    }else
                    {

                        paymentArray = new ArrayList<>();
                        paymentArray.add(transaction_id);
                        paymentList.put(payment_id, paymentArray);
                                          
                    }
                    tempKey = payment_id;

                    paymentList.get(payment_id).get(0);
                   
                    System.out.println(paymentList);

                }
            } 
            catch (SQLException e) 
            {
                System.out.println(e);
            }

            connection.close();

        }
        catch (Exception e)
        {
            System.out.println(e);
        }


        return paymentList;

    }

    public static ArrayList<HashMap<String, String>> createTransactionItemHistory(HashMap<String, ArrayList<String>>paymentList){

        ArrayList<HashMap<String, String>> transactionList = new ArrayList<HashMap<String, String>>();

        String url = "jdbc:mysql://aa6sm8glmiegl4.cabpjb9qfuhk.us-west-1.rds.amazonaws.com/ebdb";
        String userName = "team42022";
        String password = "team4_2022";


        try{

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, userName, password);
            System.out.println("Connection is Successful to the database");


        for(HashMap.Entry<String, ArrayList<String>>i : paymentList.entrySet())
        {
            String payment_id = i.getKey();
            ArrayList<String> value = i.getValue();

            for(String transaction_id: value){
            String transactionQuery = "SELECT * FROM TransactionItems WHERE transaction_id = '" + transaction_id + "' ORDER BY transaction_id DESC";

            String title;
            String imageUrl;
            String price;
            String quantity;


            try (Statement transactionStmt = connection.createStatement()) 
            {
                ResultSet rs = transactionStmt.executeQuery(transactionQuery);
                HashMap<String, String> transactionArray = new HashMap<String, String>();

                while (rs.next()) 
                {

                title = rs.getString("title");
                imageUrl = rs.getString("image");
                price = rs.getString("price");
                quantity = rs.getString("quantity");

                transactionArray.put("payment_id", payment_id);
                transactionArray.put("title", title);
                transactionArray.put("image", imageUrl);
                transactionArray.put("price", price);
                transactionArray.put("quantity", quantity);

                transactionList.add(transactionArray);
                // System.out.println(transactionList);
               
            
                }

            
            }catch (Exception e)
            {
                System.out.println(e);
            }

            }
            
        }
        



        connection.close();
           
        }
        catch (Exception e)
        {
            System.out.println(e);
        }


        return transactionList;

    }
    
}
