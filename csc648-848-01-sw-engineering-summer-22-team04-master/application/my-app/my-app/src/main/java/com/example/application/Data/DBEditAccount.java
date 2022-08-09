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


public class DBEditAccount {

    public static void EditAccount(int user_id, String about, String contact, String pic){

        String url = "jdbc:mysql://aa6sm8glmiegl4.cabpjb9qfuhk.us-west-1.rds.amazonaws.com/ebdb";
        String userName = "team42022";
        String password = "team4_2022";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, userName, password);
            System.out.println("Connection is Successful to the database");


            String editAccountInfo = "UPDATE RegisteredUser SET about ='" + about + "', contact = '" + contact 
            + "', pfp = '" + pic + "' WHERE registered_user_id = '" + user_id + "'";

            PreparedStatement editAccountStmt = connection.prepareStatement(editAccountInfo);

            editAccountStmt.execute();
            connection.close();


        }catch(Exception e){
            
            System.out.println(e);
        }

    }

    
}
