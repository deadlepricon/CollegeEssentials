package com.example.application.views.Account;

import java.util.ArrayList;

import com.example.application.Data.DBViewUser;
import com.example.application.views.Items.ItemView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;

public class ItemUserView extends VerticalLayout{
    public static String clickedUser;

    public ItemUserView(){

        
        
        /* Store the user info from the DB to an array */
        ArrayList<String> user = new ArrayList<String>();
        String first_name = "Kelly";
        String last_name = "Smith";
        String username = "ksmith99";
        String emailDB = "kellysmith@gmail.com";
        String about = "Hi, I'm Kelly! Nice to meet you!\nCS @ SFSU";
        String contact = "For business inquiries only: kellysmith@gmail.com";
        
        String userName;

       
            userName = ItemView.getUser();
            

            if(userName.contains("@")){
                try {
                    user = DBViewUser.searchEmail(userName);
                } catch (ClassNotFoundException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
            else{
                try {
                    user = DBViewUser.searchUser(userName);
                } catch (ClassNotFoundException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
            if(!user.isEmpty()){
                first_name = user.get(0);
                last_name = user.get(1);
                username = user.get(2);
                emailDB = user.get(3);
                about = user.get(4);
                contact = user.get(5); 
            }
        
        

        getElement().getClassList().add("hello-grid");
        setWidth("99%");

        /* Name Profile Grid Area */
        Div nametag = new Div();
        nametag.addClassName("label-one");

        Image img1 = new Image("images/icon.jpg", "placeholder icon");
        img1.setWidth("180px");
        img1.addClassName("pfp");
        H1 name = new H1(first_name + " "+ last_name);
        name.addClassName("name");

        H2 usernameProfile = new H2("@"+username);
        usernameProfile.addClassName("username-profile");
        VerticalLayout names = new VerticalLayout(name, usernameProfile);
        names.addClassName("names");
        


        HorizontalLayout h1 = new HorizontalLayout(img1, names);
        h1.addClassName("label-one");

        
        /* Contact Grid Area */
        Div personalDiv = new Div();
        H3 aboutMe = new H3("About Me");
        aboutMe.addClassName("about-me");
        
        H6 aboutText1 = new H6(about);
        H6 aboutText2 = new H6();
        
        VerticalLayout aboutText = new VerticalLayout(aboutText1, aboutText2);
        aboutText.addClassName("aboutText");
        
        H3 contactMe = new H3("Contact Me");
        contactMe.addClassName("contact-me");
        H6 contactText1 = new H6(contact);
        VerticalLayout contactText = new VerticalLayout(contactText1);
        contactText.addClassName("contact-text");
        VerticalLayout personalInfo = new VerticalLayout(aboutMe, aboutText, contactMe, contactText);
        personalInfo.addClassName("label-two");
        
        /* Ratings Grid Area */
        Div ratingsDiv = new Div();
        H3 ratings = new H3("Ratings");
        ratings.addClassName("ratings");

        
        H6 ratingAuthor1 = new H6 ("@Reviewer1");
        ratingAuthor1.addClassName("rating-author");
        Span datePosted1 = new Span ("12/17/2022");
        datePosted1.addClassName("date-posted");
        H6 commentText1 = new H6("Kelly sold me a great oak table at a great price! Would buy from her again");
        commentText1.addClassName("comment-text");
        VerticalLayout rating1 = new VerticalLayout(ratingAuthor1, datePosted1, commentText1);
        rating1.addClassName("single-rating");

        H6 ratingAuthor2 = new H6 ("@Reviewer2");
        ratingAuthor2.addClassName("rating-author");
        Span datePosted2 = new Span ("12/17/2022");
        datePosted2.addClassName("date-posted");
        H6 commentText2 = new H6("Kelly sold me a pair of Sony Headphones");
        commentText2.addClassName("comment-text");
        VerticalLayout rating2 = new VerticalLayout(ratingAuthor2, datePosted2, commentText2);
        rating2.addClassName("single-rating");

        H6 ratingAuthor3 = new H6 ("@Reviewer3");
        ratingAuthor3.addClassName("rating-author");
        Span datePosted3 = new Span ("12/17/2022");
        datePosted3.addClassName("date-posted");
        H6 commentText3 = new H6("Kelly sold me a nice couch from IKEA");
        commentText3.addClassName("comment-text");
        VerticalLayout rating3 = new VerticalLayout(ratingAuthor3, datePosted3, commentText3);
        rating3.addClassName("single-rating");
        
        VerticalLayout allRatings = new VerticalLayout(rating1, rating2, rating3);
        allRatings.addClassName("all-ratings");
        
        TextField newRatingField = new TextField();
        newRatingField.addClassName("new-rating-text");
        newRatingField.setPlaceholder("Add a new rating for this user");

        Select <Integer> select = new Select<>();
        select.setItems(1, 2, 3, 4, 5);
        select.setValue(1);
        select.addClassName("rating-selector");

        Button submitRating = new Button("Submit", e ->{
            
        });
        submitRating.addClassName("submit-rating");

        HorizontalLayout newRating = new HorizontalLayout(newRatingField, select, submitRating);
        newRating.addClassName("new-rating-field");

        VerticalLayout h3 = new VerticalLayout(ratings, allRatings, newRating);
        h3.addClassName("label-three");
        ratingsDiv.addClassName("label-three");
        add(nametag);
        add(personalDiv);
        add(ratingsDiv);

        add(h1);
        add(personalInfo);
        add(h3);
    
    }


    public static String getClickedUser(){
        return clickedUser;
    }
    
}

    

