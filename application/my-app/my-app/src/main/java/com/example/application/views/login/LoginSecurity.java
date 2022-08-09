package com.example.application.views.login;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.example.application.Data.DBlogin;
import com.vaadin.flow.server.VaadinSession;

public class LoginSecurity {
    private Boolean flag;
    DBlogin db = new DBlogin();
    private String userName;
    private String passWord;
    private Boolean isAdmin;
    private Boolean isBanned;


    public LoginSecurity(String userName, String passWord){
        
        this.userName = userName;
        this.passWord = passWord;
        passWord = HashPass(passWord);

        if( userName.contains("@")){
            //is email
         flag = db.getEmailCred(userName,passWord);
         isAdmin = db.getAdmin();
         isBanned = db.isBanned();

        }else{
            //is username
         flag = db.getUserNameCred(userName,passWord);
         isAdmin = db.getAdmin();
         isBanned = db.isBanned();


        }
        
        
    }
    // This is hashing the password 
    private String HashPass (String password)
    {
        String hashPass;
      try {
         MessageDigest md = MessageDigest.getInstance("MD5");
     md.update(password.getBytes());
     byte[] resultByteArray = md.digest();
     StringBuilder sd = new StringBuilder();
     for (byte b : resultByteArray)
     {
         System.out.println(sd.toString());
         sd.append(String.format("%02x",b));
     }
     System.out.println(sd.toString());
           hashPass =  sd.toString();
           return hashPass;
     } catch (NoSuchAlgorithmException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
     }
      return ""; 
    }

    public Boolean getFlag(){
        return flag;
    }

    public Boolean isAdmin(){
        return isAdmin;
    }

    public Boolean isBanned(){
        return isBanned;
    }

    public int getUserId(){
        return DBlogin.getUserId();
    }

    
}
