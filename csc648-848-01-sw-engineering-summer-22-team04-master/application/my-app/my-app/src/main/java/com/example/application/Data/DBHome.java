package com.example.application.Data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import com.vaadin.flow.component.charts.model.Items;
public class DBHome {
    private static String userId;

    
    public static HashMap<String,ArrayList<String>> searchHomeItem(String Title, String selector) {
        HashMap<String, ArrayList<String>> itemList = new HashMap<String,ArrayList<String>>();
        // ArrayList array = new ArrayList<>();
        String url = "jdbc:mysql://aa6sm8glmiegl4.cabpjb9qfuhk.us-west-1.rds.amazonaws.com/ebdb";
        String userName = "team42022";
        String password = "team4_2022";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, userName, password);
            System.out.println("Connection is Successful to the database" + url);

            if(!selector.contains("All Categories")){
                
                String q = "SELECT MarketplaceListing.marketplace_listing_id, MarketplaceListing.title, MarketplaceListing.price,"
                +" MarketplaceListing.created_at, MarketplaceListing.body, MarketplaceListing.quantity, MarketplaceListing.seller_id, Product.product_condition,"
                +" Product.product_image, Product.product_id FROM ProductListings"
                +" JOIN MarketplaceListing ON ProductListings.marketplace_listing_id = MarketplaceListing.marketplace_listing_id"
                +" JOIN Product ON ProductListings.product_id = Product.product_id"
                +" WHERE MarketplaceListing.title LIKE '%" +Title+"%' AND Product.general_type LIKE '%" +selector+ "%';";
            
                String nameItem;
                String imageURL;
                String price;
                String body;
                String quant;
                String product_id;
                String seller_id;
                try (Statement stmt = connection.createStatement()) {
                    ResultSet rs = stmt.executeQuery(q);
                    while (rs.next()) {
                        ArrayList array = new ArrayList<>();
                      nameItem = rs.getString("title");
                      imageURL = rs.getString("product_image");
                      price = rs.getString("price");
                      body = rs.getString("body");
                      quant = rs.getString("quantity");
                      product_id = rs.getString("product_id");
                      seller_id = rs.getString("seller_id");
                      array.add(imageURL);
                      array.add(body);
                      array.add(price);
                      array.add(quant);
                      array.add(product_id);
                      array.add(seller_id);
                      userId = seller_id;
                      System.out.println(nameItem + " " +imageURL+ " "+selector);
                        itemList.put(nameItem,array);
                      
                    }
                  } catch (SQLException e) {
    
                  }  

            }else{
            String q = "SELECT MarketplaceListing.marketplace_listing_id, MarketplaceListing.title, MarketplaceListing.price,"
            +" MarketplaceListing.created_at, MarketplaceListing.body, MarketplaceListing.quantity, MarketplaceListing.seller_id, Product.product_condition,"
            +" Product.product_image, Product.product_id FROM ProductListings"
            +" JOIN MarketplaceListing ON ProductListings.marketplace_listing_id = MarketplaceListing.marketplace_listing_id"
            +" JOIN Product ON ProductListings.product_id = Product.product_id"
            +" WHERE MarketplaceListing.title LIKE '%" +Title + "%';";
            String nameItem;
            String imageURL;
            String price;
            String body;
            String quant;
            String product_id;
            String seller_id;
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(q);
               
                while (rs.next()) {
                ArrayList array = new ArrayList<>();
                  nameItem = rs.getString("title");
                  imageURL = rs.getString("product_image");
                  price = rs.getString("price");
                      body = rs.getString("body");
                      quant = rs.getString("quantity");
                      product_id = rs.getString("product_id");
                      seller_id = rs.getString("seller_id");
                      array.add(imageURL);
                      array.add(body);
                      array.add(price);
                    array.add(quant);
                      array.add(product_id);
                      array.add(seller_id);
                      userId = seller_id;
                  
                    itemList.put(nameItem,array);
                  
                }
              } catch (SQLException e) {

              }  
            }
            System.out.println("DBHome searchHomeItem connection closed");
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return itemList;
    }

    public static String getUserId(){
        return userId;
    }
}
