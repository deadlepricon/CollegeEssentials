package com.example.application.Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.validation.constraints.Pattern.Flag;

import com.example.application.views.login.LoginSecurity;

public class DBlogin {
    private final String url = "jdbc:mysql://aa6sm8glmiegl4.cabpjb9qfuhk.us-west-1.rds.amazonaws.com/ebdb";
    private final String userName = "team42022";
    private final String password = "team4_2022";
    private int adminId = 0;
    private static int userID;
    private int isBanned;

    public DBlogin() {
        System.out.println("hi");

    }

    public Boolean getEmailCred(String email, String pass) {
        boolean flag = false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, userName, password);
            System.out.println("Connection is Successful to the database for Login" + url);

            final String sqlEmail = "SELECT RegisteredUser.email,RegisteredUser.registered_user_id, RegisteredUser.is_banned FROM RegisteredUser"
                    + " WHERE RegisteredUser.email LIKE '%" + email + "%';";

            final String sqlPassWord = "SELECT RegisteredUser.password FROM RegisteredUser"
                    + " WHERE RegisteredUser.email LIKE '%" + email + "%';";
            String tempEmail = "";
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(sqlEmail);
                while (rs.next()) {

                    tempEmail = rs.getString("email");
                    userID = rs.getInt("registered_user_id");
                    isBanned = rs.getInt("is_banned");

                    // isAdmin = rs.getBoolean("admin");

                }
            } catch (SQLException e) {

            }

            final String admincheck = "SELECT RegisteredUser.email, RegisteredUser.registered_user_id FROM RegisteredUser"
                    + " JOIN Admin ON Admin.registered_user_id = RegisteredUser.registered_user_id"
                    + " WHERE RegisteredUser.registered_user_id = '" + userID + "';";

            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(admincheck);
                while (rs.next()) {
                    adminId = rs.getInt("registered_user_id");

                }
            } catch (SQLException e) {

            }

            if (tempEmail.length() > 0) {
                String tempPass = "";
                try (Statement stmt = connection.createStatement()) {
                    ResultSet rs = stmt.executeQuery(sqlPassWord);
                    while (rs.next()) {

                        tempPass = rs.getString("password");

                    }
                } catch (SQLException e) {

                }
                if (!tempPass.isEmpty()) {
                    if (pass.equals(tempPass)) {
                        flag = true;
                    }

                }
            }
            System.out.println("DBlogin getEmailCred connection closed");
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return flag;
    }

    public Boolean getUserNameCred(String uName, String pass) {

        // possible bug where password is alwyas the same

        boolean flag = false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, userName, password);
            System.out.println("Connection is Successful to the database for Login" + url);

            final String sqlUserName = "SELECT RegisteredUser.username, RegisteredUser.registered_user_id, RegisteredUser.is_banned FROM RegisteredUser"
                    + " WHERE RegisteredUser.username LIKE '%" + uName + "%';";

            final String sqlPassWord = "SELECT RegisteredUser.password FROM RegisteredUser"
                    + " WHERE RegisteredUser.username LIKE '%" + uName + "%';";
            String tempEmail = "";
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(sqlUserName);
                while (rs.next()) {

                    tempEmail = rs.getString("username");
                    userID = rs.getInt("registered_user_id");
                    isBanned = rs.getInt("is_banned");
                    // isAdmin = rs.getBoolean("admin");

                }
            } catch (SQLException e) {

            }

            final String admincheck = "SELECT RegisteredUser.username, RegisteredUser.registered_user_id FROM RegisteredUser"
                    + " JOIN Admin ON Admin.registered_user_id = RegisteredUser.registered_user_id"
                    + " WHERE RegisteredUser.registered_user_id = '" + userID + "';";

            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(admincheck);
                while (rs.next()) {
                    adminId = rs.getInt("registered_user_id");

                    // isAdmin = rs.getBoolean("admin");

                }
            } catch (SQLException e) {

            }

            if (tempEmail.length() > 0) {
                String tempPass = "";
                try (Statement stmt = connection.createStatement()) {
                    ResultSet rs = stmt.executeQuery(sqlPassWord);
                    while (rs.next()) {

                        tempPass = rs.getString("password");

                    }
                } catch (SQLException e) {

                }
                if (!tempPass.isEmpty()) {
                    if (pass.equals(tempPass)) {
                        flag = true;
                    }

                }
            }
            System.out.println("DBlogin getUsernameCred connection closed");
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return flag;

    }

    public Boolean getAdmin() {
        if (adminId == 0) {
            return false;
        } else {
            return true;
        }

    }

    public static int getUserId() {
        return userID;
    }

    public Boolean isBanned() {
        if (isBanned == 0) {
            return false;
        } else {
            return true;
        }
    }

}
