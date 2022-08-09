package com.example.application.views.Admin;

import java.util.ArrayList;

import javax.annotation.security.RolesAllowed;

import com.example.application.Data.DBAdmin;
import com.example.application.views.MainLayout;
import com.example.application.views.login.LoginView;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin.Horizontal;

@PageTitle("Admin View")
@Route(value = "Admin", layout = MainLayout.class)
// @RolesAllowed("ADMIN")

public class AdminView extends VerticalLayout{
    private DBAdmin db = new DBAdmin();

    public AdminView() throws ClassNotFoundException{
    if(LoginView.logStatus()){
       if(LoginView.isAdmin()){
        
    
        H1 header = new H1("Admin View");
         add(header);
        TextField userName = new TextField("userName");
            userName.setValue("UserName");
            userName.setReadOnly(true);
        TextField numbOfPosts =new TextField("Number Of Post");
            numbOfPosts.setValue("0");
            numbOfPosts.setReadOnly(true);
        TextField email = new TextField("user@gmail.com");
            email.setValue("Email");
            email.setReadOnly(true);
        TextField FirstName = new TextField("first");
            FirstName.setValue("First");
            FirstName.setReadOnly(true);
        TextField lastName = new TextField("last");
            lastName.setValue("last");
            lastName.setReadOnly(true);
        TextField banned = new TextField("Ban");
        banned.setValue("Ban");
        banned.setReadOnly(true);
        banned.setWidth("7%");

         HorizontalLayout row = new HorizontalLayout();
         row.setSpacing(false);
         VerticalLayout layout = new VerticalLayout();

         row.add(FirstName, lastName, userName, email, banned );
         add(row);

         
         ArrayList<ArrayList<String>> users = db.searchUser();


         for(int i =0; i < users.size(); i++){
            for(int j =0; j <users.get(i).size(); j+=5){
                row = new HorizontalLayout();
          String first =  users.get(i).get(0);
          String last = users.get(i).get(1);
          String username = users.get(i).get(2);
          String userEmail = users.get(i).get(3);
          String isUserBanned = users.get(i).get(4);
        
          TextField firstNam = new TextField();
          firstNam.setValue(first);
          firstNam.setReadOnly(true);
          
          TextField lastNam = new TextField();
          lastNam.setValue(last);
          lastNam.setReadOnly(true);
          TextField userNam = new TextField();
          userNam.setValue(username);
          userNam.setReadOnly(true);
          TextField usernemail = new TextField();
          usernemail.setValue(userEmail);
          usernemail.setReadOnly(true);
          TextField isBannedUser = new TextField();
          isBannedUser.setWidth("5%");
          isBannedUser.setValue(isUserBanned);
          isBannedUser.setReadOnly(true);

          Button delete = new Button("Delete",e->{
            db.deleteUser(username);
            UI.getCurrent().getPage().reload();

          });
          Button ban = new Button("Ban", ev->{
            db.banUser(username);
            UI.getCurrent().getPage().reload();
          });

          Button unBan = new Button("UnBan",eve->{
            db.unBanUser(username);
            UI.getCurrent().getPage().reload();

          });
          HorizontalLayout b = new HorizontalLayout();
          b.setSpacing(true);
          b.add(unBan, ban,delete);
          row.add(firstNam, lastNam, userNam, usernemail,isBannedUser,b);
          row.setSpacing(false);
          add(row);
            }
            

         }
          
        }else{
            H1 header = new H1("Restriced Access, Only Admins can view");
            add(header);
        } 
    }else{
        H1 header = new H1("Restriced Access, Only Admins can view");
        add(header);
    } 
        
    
          

         

         //add(layout);
            
    
    }

}
