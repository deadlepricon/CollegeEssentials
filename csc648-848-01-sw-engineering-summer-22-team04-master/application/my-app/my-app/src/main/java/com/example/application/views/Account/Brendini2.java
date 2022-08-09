
package com.example.application.views.Account;

import com.example.application.views.MainLayout;
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
@Route(value = "Brendini2", layout = MainLayout.class)
public class Brendini2 extends Div{
    public Brendini2(){

        getElement().getClassList().add("hello-grid");
        setWidth("99%");

        /* Name Profile Grid Area */
        Div nametag = new Div();
        nametag.addClassName("label-one");

        Image img1 = new Image("images/icon.jpg", "placeholder icon");
        img1.setWidth("180px");
        img1.addClassName("pfp");
        H1 name = new H1("Brendan2");
        name.addClassName("name");
        
        Button purchaseHistory = new Button("Recent Orders");
        purchaseHistory.addClassName("purchases");

        Button editAccount = new Button("Edit Account");
        editAccount.addClassName("editAcc");
        HorizontalLayout h1 = new HorizontalLayout(img1, name, purchaseHistory, editAccount);
        h1.addClassName("label-one");

        
        /* Contact Grid Area */
        Div personalDiv = new Div();
        H3 aboutMe = new H3("About Me");
        aboutMe.addClassName("about-me");
        
        H6 aboutText1 = new H6("Hi, I'm Brendan2! Nice to meet you!");
        H6 aboutText2 = new H6("CS @ San Francisco State University");
        
        VerticalLayout aboutText = new VerticalLayout(aboutText1, aboutText2);
        aboutText.addClassName("aboutText");
        
        H3 contactMe = new H3("Contact Me");
        contactMe.addClassName("contact-me");
        H6 contactText1 = new H6("For business inquiries only: brendini2@gmail.com");
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
        H6 commentText1 = new H6("Brendan2 does a great job at teaching.");
        commentText1.addClassName("comment-text");
        VerticalLayout rating1 = new VerticalLayout(ratingAuthor1, datePosted1, commentText1);
        rating1.addClassName("single-rating");

        H6 ratingAuthor2 = new H6 ("@Reviewer2");
        ratingAuthor2.addClassName("rating-author");
        Span datePosted2 = new Span ("12/17/2022");
        datePosted2.addClassName("date-posted");
        H6 commentText2 = new H6("Brendan2 is a reliable seller, and teacher.");
        commentText2.addClassName("comment-text");
        VerticalLayout rating2 = new VerticalLayout(ratingAuthor2, datePosted2, commentText2);
        rating2.addClassName("single-rating");

        H6 ratingAuthor3 = new H6 ("@Reviewer3");
        ratingAuthor3.addClassName("rating-author");
        Span datePosted3 = new Span ("12/17/2022");
        datePosted3.addClassName("date-posted");
        H6 commentText3 = new H6("Brendan2 product are top quality for no good reason!");
        commentText3.addClassName("comment-text");
        VerticalLayout rating3 = new VerticalLayout(ratingAuthor3, datePosted3, commentText3);
        rating3.addClassName("single-rating");
        
        VerticalLayout allRatings = new VerticalLayout(rating1, rating2, rating3);
        allRatings.addClassName("all-ratings");
        
        TextArea newRatingField = new TextArea();
        newRatingField.addClassName("new-rating-text");
        newRatingField.setPlaceholder("Add a new rating for this user");

        VerticalLayout h3 = new VerticalLayout(ratings, allRatings, newRatingField);
        h3.addClassName("label-three");
        ratingsDiv.addClassName("label-three");
        add(nametag);
        add(personalDiv);
        add(ratingsDiv);

        add(h1);
        add(personalInfo);
        add(h3);
    }
}