
package com.example.application.views.Account;

import com.example.application.Data.DBAccount;
import com.example.application.Data.DBlogin;
import com.example.application.views.MainLayout;
import com.example.application.views.login.LoginView;
import com.microsoft.schemas.office.office.impl.HrpctAttributeImpl;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.grid.Grid;

import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import java.util.ArrayList;
import java.util.HashMap;

@CssImport("./themes/myapp/Account.css")
@PageTitle("My Account")
@Route(value = "Account", layout = MainLayout.class)
public class AccountView extends Div{


    static String clickedUser;
    private float RatingScore = 0;
    private int numRatings = 0;

    public AccountView() throws ClassNotFoundException{
        if(LoginView.logStatus()){

        
        DBAccount db = new DBAccount();

        ArrayList<String> user = new ArrayList<String>();
        String first_name = "Kelly";
        String last_name = "Smith";
        String username = "ksmith99";
        String emailDB = "kellysmith@gmail.com";
        String about = "Hi, I'm Kelly! Nice to meet you!\nCS @ SFSU";
        String contact = "For business inquiries only: kellysmith@gmail.com";
        String pfp = "Filler pfp";
        
        String userName;
        /* Comment out this code if you want to solely work on testing AccountView */
        /* Connect to DB and store the user info of the person who logged in */
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
            about = user.get(4);
            contact = user.get(5);
            pfp = user.get(6);

            if(pfp == null){
                pfp = "images/icon.jpg";
            }


            /* Test the values from the DB */
            // for(int i = 0; i < user.size(); i++){
            //     System.out.println(user.get(i));
            // }
        }

        /* CSS Grid for Formatting */
        getElement().getClassList().add("hello-grid");
        setWidth("99%");

        /* Name Profile Grid Area */
        Div nametag = new Div();
        nametag.addClassName("label-one");

        /* Profile Banner */
        Image img1 = new Image(pfp, "placeholder icon");
        img1.setWidth("180px");
        img1.addClassName("pfp");
        H1 name = new H1(first_name + " "+ last_name);
        name.addClassName("name");

        H2 usernameProfile = new H2("@"+username);
        usernameProfile.addClassName("username-profile");
        VerticalLayout names = new VerticalLayout(name, usernameProfile);
        names.addClassName("names");
        
        /* Order History View */
        Button purchaseHistory = new Button("Recent Orders", e ->{
            this.getUI().ifPresent(ui -> ui.navigate("/PurchaseHistory"));
        });
        purchaseHistory.addClassName("purchases");

        /* Selling History View */
        // Button sellingHistory = new Button("Selling History", e ->{
        //     this.getUI().ifPresent(ui -> ui.navigate("/SellingHistory"));
        // });
        // sellingHistory.addClassName("selling-history");

        /* Edit Account View */
        Button editAccount = new Button("Edit Account", ev ->{
            this.getUI().ifPresent(ui -> ui.navigate("/editAccount"));
        });
        editAccount.addClassName("editAcc");

        /* Add the Buttons */
        HorizontalLayout buttons = new HorizontalLayout(purchaseHistory, editAccount);
        buttons.addClassName("profile-buttons");
        HorizontalLayout h1 = new HorizontalLayout(img1, names, buttons);
        h1.addClassName("label-one");

        /* About Grid Area */
        Div personalDiv = new Div();
        H3 aboutMe = new H3("About Me");
        aboutMe.addClassName("about-me");
        
        H6 aboutText1 = new H6(about);
        H6 aboutText2 = new H6();
        
        VerticalLayout aboutText = new VerticalLayout(aboutText1, aboutText2);
        aboutText.addClassName("aboutText");
        
        /* Contact Me Area */
        H3 contactMe = new H3("Contact Me");
        contactMe.addClassName("contact-me");
        H6 contactText1 = new H6(contact);
        VerticalLayout contactText = new VerticalLayout(contactText1);
        contactText.addClassName("contact-text");
        VerticalLayout personalInfo = new VerticalLayout(aboutMe, aboutText, contactMe, contactText);
        personalInfo.addClassName("label-two");
        
        /* Ratings Grid Area */
        Div ratingsDiv = new Div();
        // H3 ratings = new H3("Ratings");
        // ratings.addClassName("ratings");

        ArrayList<ArrayList<String>> details = DBAccount.getRating(""+DBlogin.getUserId());
        VerticalLayout allRatings = new VerticalLayout();
        allRatings.addClassName("all-ratings");
        numRatings = details.size();
        for(int i = 0; i<details.size(); i++){
            //rating, at, descr,name
            Button ratingAuthor1 = new Button();
            String usern = details.get(i).get(3);
            ratingAuthor1.setText("@" + usern);
            ratingAuthor1.addClickListener(event -> {
                clickedUser = usern;
                this.getUI().ifPresent(ui -> ui.navigate("/viewAccount"));
            });
    
            ratingAuthor1.addClassName("rating-author");
            Span datePosted1 = new Span (details.get(i).get(1));
            datePosted1.addClassName("date-posted");
            String curRating = details.get(i).get(0);
            H6 commentText1 = new H6(curRating + ": " + details.get(i).get(2));
            RatingScore += Integer.parseInt(curRating);

            commentText1.addClassName("comment-text");
            VerticalLayout rating1 = new VerticalLayout(ratingAuthor1, datePosted1, commentText1);
            rating1.addClassName("single-rating");
             //allRatings = new VerticalLayout(rating1);
            allRatings.add(rating1);
            add(allRatings);
        }

        RatingScore = RatingScore/numRatings;
        String temp = "";
        if(details.size() == 0){
            temp = "No Rating Yet";
        }else{
            temp = ""+RatingScore;
        }

        H3 ratings = new H3("Ratings " + temp);
        ratings.addClassName("ratings");

        if(details.isEmpty()){
            H4 noRating = new H4("No ratings for user yet");
            allRatings.add(noRating);
        }
       

        
        TextField newRatingField = new TextField();
        newRatingField.addClassName("new-rating-text");
        newRatingField.setPlaceholder("Add a new rating for this user");

        Select <Integer> select = new Select<>();
        select.setItems(1, 2, 3, 4, 5);
        select.setValue(1);
        select.addClassName("rating-selector");



        

        VerticalLayout h3 = new VerticalLayout(ratings, allRatings);
        h3.addClassName("label-three");
        ratingsDiv.addClassName("label-three");
        add(nametag);
        add(personalDiv);
        add(ratingsDiv);

        add(h1);
        add(personalInfo);
        add(h3);
    }else{
        H1 header = new H1("You must be logged in to view");
        add(header);
    }
    
}
    


    public static String getClickedUser(){
        return clickedUser;
    }



    public static void resetUser() {
        clickedUser = null;
    }

}
