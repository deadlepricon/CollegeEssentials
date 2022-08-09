package com.example.application.Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import java.sql.Statement;

public class DBAccount {
    public static ArrayList<String> searchEmail(String email) throws ClassNotFoundException {
        ArrayList<String> user = new ArrayList<String>();
        String url = "jdbc:mysql://aa6sm8glmiegl4.cabpjb9qfuhk.us-west-1.rds.amazonaws.com/ebdb";
        String userName = "team42022";
        String password = "team4_2022";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, userName, password);
            System.out.println("Connection is Successful to the database" + url);

            String first_name;
            String last_name;
            String username;
            String emailDB;
            String about;
            String contact;
            String pfp;

            String q = "SELECT first_name, last_name, username, email, about, contact, pfp FROM RegisteredUser WHERE email LIKE '%"
                    + email + "%'";
            try {
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(q);
                while (rs.next()) {
                    first_name = rs.getString("first_name");
                    last_name = rs.getString("last_name");
                    username = rs.getString("username");
                    emailDB = rs.getString("email");
                    about = rs.getString("about");
                    contact = rs.getString("contact");
                    pfp = rs.getString("pfp");

                    user.add(first_name);
                    user.add(last_name);
                    user.add(username);
                    user.add(emailDB);
                    user.add(about);
                    user.add(contact);
                    user.add(pfp);

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("DBAccount searchEmail connection closed");
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    public static ArrayList<String> searchUser(String userLogin) throws ClassNotFoundException {
        ArrayList<String> user = new ArrayList<String>();
        String url = "jdbc:mysql://aa6sm8glmiegl4.cabpjb9qfuhk.us-west-1.rds.amazonaws.com/ebdb";
        String userName = "team42022";
        String password = "team4_2022";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, userName, password);
            System.out.println("Connection is Successful to the database" + url);

            String first_name;
            String last_name;
            String username;
            String emailDB;
            String about;
            String contact;
            String pfp;

            // add for rating
            String q = "SELECT first_name, last_name, username, email, about, contact, pfp FROM RegisteredUser WHERE username LIKE '%"
                    + userLogin + "%'";
            try {
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(q);
                while (rs.next()) {
                    first_name = rs.getString("first_name");
                    last_name = rs.getString("last_name");
                    username = rs.getString("username");
                    emailDB = rs.getString("email");
                    about = rs.getString("about");
                    contact = rs.getString("contact");
                    pfp = rs.getString("pfp");
                    // rating = rs.getString("rating");

                    user.add(first_name);
                    user.add(last_name);
                    user.add(username);
                    user.add(emailDB);
                    user.add(about);
                    user.add(contact);
                    user.add(pfp);

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("DBAccount searchUsername connection closed");
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    public static String getUserNameFromId(String string) {
        String url = "jdbc:mysql://aa6sm8glmiegl4.cabpjb9qfuhk.us-west-1.rds.amazonaws.com/ebdb";
        String userName = "team42022";
        String password = "team4_2022";
        String name = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, userName, password);
            System.out.println("Connection is Successful to the database" + url);

            // add for rating
            String q = "SELECT RegisteredUser.username FROM RegisteredUser"
                    + " WHERE registered_user_id = '" + string + "';";
            try {
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(q);
                while (rs.next()) {

                    name = rs.getString("username");

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("DBAccount getUserNameFromId connection closed");
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return name;
    }

    public static int getUserID(String loginCred) throws ClassNotFoundException {
        ArrayList<String> user = new ArrayList<String>();
        String url = "jdbc:mysql://aa6sm8glmiegl4.cabpjb9qfuhk.us-west-1.rds.amazonaws.com/ebdb";
        String userName = "team42022";
        String password = "team4_2022";
        int reg_user_id = 0;
        String q;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, userName, password);
            System.out.println("Connection is Successful to the database" + url);

            if (!loginCred.contains("@")) {
                q = "SELECT registered_user_id FROM RegisteredUser WHERE username LIKE '%" + loginCred + "%'";
            } else {
                q = "SELECT registered_user_id FROM RegisteredUser WHERE email LIKE '%" + loginCred + "%'";
            }
            try {
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(q);
                while (rs.next()) {
                    reg_user_id = rs.getInt("registered_user_id");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("DBAccount getUserId connection closed");
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return reg_user_id;
    }

    public static void addRating(int rating, String comment, int currUserId, String accountPostedOnId) {
        String url = "jdbc:mysql://aa6sm8glmiegl4.cabpjb9qfuhk.us-west-1.rds.amazonaws.com/ebdb";
        String userName = "team42022";
        String password = "team4_2022";
        int reg_user_id = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, userName, password);
            System.out.println("Connection is Successful to the database" + url);

            String q = "INSERT INTO Ratings (rating, registered_user_sender_id, registered_user_receiver_id,  description, posted_at)"
                    + " VALUES (?, ?, ?, ?, NOW());";
            try {
                PreparedStatement preparedStmt = connection.prepareStatement(q);
                preparedStmt.setInt(1, rating);
                preparedStmt.setInt(2, currUserId);
                preparedStmt.setString(3, accountPostedOnId);
                preparedStmt.setString(4, comment);

                preparedStmt.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("DBAccount addRating connection closed");
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public static ArrayList<ArrayList<String>> getRating(String AccountId) {
        ArrayList<ArrayList<String>> ratings = new ArrayList<>();
        ArrayList<String> details = new ArrayList<>();
        String url = "jdbc:mysql://aa6sm8glmiegl4.cabpjb9qfuhk.us-west-1.rds.amazonaws.com/ebdb";
        String userName = "team42022";
        String password = "team4_2022";
        int reg_user_id = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, userName, password);
            System.out.println("Connection is Successful to the database" + url);

            String q = "SELECT rating, registered_user_sender_id, description, posted_at"
                    + " FROM Ratings"
                    + " WHERE registered_user_receiver_id = " + AccountId + ";";
            String rating, description, postedAt, name;
            name = " ";
            int user_id;
            try {
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(q);
                int count = 0;
                while (rs.next()) {
                    rating = rs.getString("rating");
                    postedAt = rs.getString("posted_at");
                    description = rs.getString("description");
                    user_id = rs.getInt("registered_user_sender_id");
                    // name = rs.getString("username");

                    q = "SELECT username FROM RegisteredUser WHERE registered_user_id =" + user_id + "";
                    try {
                        Statement stmt2 = connection.createStatement();
                        ResultSet rs2 = stmt2.executeQuery(q);
                        while (rs2.next()) {
                            name = rs2.getString("username");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    details.add(rating);
                    details.add(postedAt);
                    details.add(description);
                    details.add(name);
                    // details.add(name);
                    ratings.add(details);
                    details = new ArrayList<>();

                }
                System.out.println("DBAccount getRating connection closed");
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return ratings;
    }

}
