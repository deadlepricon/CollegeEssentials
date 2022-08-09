package com.example.application.views.Account;

import java.util.ArrayList;

import com.example.application.Data.DBAccount;
import com.example.application.Data.DBEditAccount;
import com.example.application.views.MainLayout;
import com.example.application.views.login.LoginView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.dependency.CssImport;

@CssImport("./themes/myapp/EditAccount.css")
@PageTitle("Edit Account")
@Route(value = "editAccount", layout = MainLayout.class)
public class editAccountView extends Div{

    private String aboutString;
    private String contactString;
    private String picString;
    

    public editAccountView() throws ClassNotFoundException{

        getElement().getClassList().add("hello-grid-2");
        setWidth("99%");
        DBAccount db = new DBAccount();

        ArrayList<String> user = new ArrayList<String>();
        String first_name = "Kelly";
        String last_name = "Smith";
        String username = "ksmith99";
        String emailDB = "kellysmith@gmail.com";
        String aboutDB = "Hi, I'm Kelly! Nice to meet you!\nCS @ SFSU";
        String contactDB = "For business inquiries only: kellysmith@gmail.com";
        String userName;

        
        /* Comment out this code if you want to solely work on testing AccountView */
        /* Connect to DB and store the user info of the person who logged in */
        if(LoginView.logStatus())
        if(LoginView.getUser() != null){
            userName = LoginView.getUser();
            System.out.println(userName);

            if(userName.contains("@")){
                user = DBAccount.searchEmail(userName);
            }
            else{
                user = DBAccount.searchUser(userName);
            }
            first_name = user.get(0);
            last_name = user.get(1);
            username = user.get(2);
            emailDB = user.get(3);
            aboutDB = user.get(4);
            contactDB = user.get(5);

            /* Test the values from the DB */
            // for(int i = 0; i < user.size(); i++){
            //     System.out.println(user.get(i));
            // }
        }
         /* Name Profile Grid Area */
         Div nametag = new Div();
         nametag.addClassName("label-one");
 
         Image img1 = new Image("images/icon.jpg", "placeholder icon");
         img1.setWidth("180px");
         img1.addClassName("pfp");
         H1 name = new H1(first_name + " " + last_name);
         name.addClassName("name");

        HorizontalLayout h1 = new HorizontalLayout(img1, name);
        h1.addClassName("label-one");

        H1 editTitle = new H1("Edit Profile");
        editTitle.addClassName("edit-title");

        VaadinSession currentSession = VaadinSession.getCurrent();
        int user_id = (Integer)currentSession.getAttribute("user_id");

        DBEditAccount dbEditAccount = new DBEditAccount();

        TextField pic = new TextField("Change Profile Pic");
        pic.setValue("Enter new image url...");
        pic.addValueChangeListener(event ->{
            picString = event.getValue();


        });

        TextArea about = new TextArea("Change About Me");
        about.addClassName("about-me");
        about.getValue();
        about.addValueChangeListener(event ->{
            aboutString = event.getValue();


        });

        TextArea contact = new TextArea("Change Contact Info");
        contact.addClassName("contact-me");
        contact.addValueChangeListener(event ->{
            contactString = event.getValue();


        });

        TextField college = new TextField("Change College");

        Button cancel = new Button("Cancel", ev ->{
            this.getUI().ifPresent(ui -> ui.navigate("/Account"));
        });

        Button submit = new Button("Submit", ev ->{

            dbEditAccount.EditAccount(user_id, aboutString, contactString, picString);
            this.getUI().ifPresent(ui -> ui.navigate("/Account"));

        });

        HorizontalLayout buttons = new HorizontalLayout(cancel, submit);
        VerticalLayout v1 = new VerticalLayout(editTitle, pic,about, contact, college, buttons);
        v1.addClassName("edit-label-two");
        v1.setAlignItems(Alignment.CENTER);

        // vMain.add(hl,vl);
        // add(vMain);

        add(h1);
        add(v1);


    }


}