package com.example.application.Data;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.UniqueElements;

public class UserDetails {

   @NotBlank
   private String firstName;

   @NotBlank
   private String lastName;

   @NotBlank
   private String userName;

   @NotBlank
   @UniqueElements
   @Email
   private String email;

   @NotBlank
   private String school;

   private boolean allowsMarketing;

   // FIXME Passwords should never be stored in plain text!
   @Size(min = 8, max = 64, message = "Password must be 8-64 char long")
   private String password;

   public String getSchool(){return school;}
   public void setSchool(String school){this.school = school; }

   public String getUserName(){return userName;}
   public void setUserName(String userName){this.userName = userName;}

   public String getFirstName() {return firstName;}

   public void setFirstName(String firstName) {this.firstName = firstName;}

   public String getLastName() {return lastName;}

   public void setLastName(String lastName) {this.lastName = lastName;}

   public String getEmail() {return email;}

   public void setEmail(String email) {this.email = email;}

   public String getPassword() {
      String hashPass;
      try {
         MessageDigest md = MessageDigest.getInstance("MD5");
     md.update(password.getBytes());
     byte[] resultByteArray = md.digest();
     StringBuilder sd = new StringBuilder();
     for (byte b : resultByteArray)
     {
         
         sd.append(String.format("%02x",b));
     }

           hashPass =  sd.toString();
           return hashPass;
     } catch (NoSuchAlgorithmException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
     }
      return ""; }

   public void setPassword(String password) {this.password = password;}

   public boolean isAllowsMarketing() {return allowsMarketing;}

   public void setAllowsMarketing(boolean allowsMarketing) {this.allowsMarketing = allowsMarketing;}

}