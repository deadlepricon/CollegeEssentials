package com.example.application.Data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import java.sql.Statement;

import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;

public class DBConnection implements CommandLineRunner{

    private JdbcTemplate jdbc;

    public static void StoreRegUser(String FirstName, String LastName, String Email, String Pass, String school, String uName) {
        
        String url = "jdbc:mysql://aa6sm8glmiegl4.cabpjb9qfuhk.us-west-1.rds.amazonaws.com/ebdb";
        String userName = "team42022";
        String password = "team4_2022";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, userName, password);
            System.out.println("Connection is Successful to the database" + url);
            
            //creates general user first
                //ip getter, need more
            //String ipAddress = request.getRemoteAddr();

            String GeneralQ = "INSERT INTO GeneralUser(ip_address) VALUES (1234567)";
            
            
            Statement statement = connection.createStatement();
            statement.execute(GeneralQ);

            String idVal = "SELECT general_user_id FROM GeneralUser ORDER BY general_user_id DESC LIMIT 1;";


            int generalUserId =0;

            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(idVal);
                while (rs.next()) {
                  generalUserId = rs.getInt("general_user_id");
                  System.out.println(generalUserId);
                }
              } catch (SQLException e) {

              }
              
            
            String query = "Insert into RegisteredUser(first_name, last_name, email, password, general_user_id,username) values('"+FirstName+"','"+LastName+"','"+Email+"','"+Pass+"', '"+generalUserId+"', '"+uName+"')";
            
            statement = connection.createStatement();
            statement.execute(query);
        
            System.out.println("DBConnection storeRegUser connection closed");
            connection.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void run(String... args) throws Exception {
        
        
    }

}