package com.example.application.views.Account;

import java.util.ArrayList;

import com.example.application.Data.DBAccount;
import com.example.application.Data.DBViewUser;
import com.example.application.Data.DBlogin;
import com.example.application.views.MainLayout;
import com.example.application.views.Items.ItemView;
import com.example.application.views.Services.ServiceList;
import com.example.application.views.Services.sPost1;
import com.example.application.views.forum.ForumList;
import com.example.application.views.forum.fPost;
import com.example.application.views.login.LoginView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@CssImport("./themes/myapp/Account.css")
@PageTitle("User Account")
@Route(value = "viewAccountNew", layout = MainLayout.class)
public class NewViewAccount extends VerticalLayout {

    public static String clickedUser;
    private ArrayList<String> user = new ArrayList<String>();
    private String first_name = "Kelly";
    private String last_name = "Smith";
    private String username = "ksmith99";
    private String emailDB = "kellysmith@gmail.com";
    private String about = "Hi, I'm Kelly! Nice to meet you!\nCS @ SFSU";
    private String contact = "For business inquiries only: kellysmith@gmail.com";
    private String accountId = "1";
    private String pfp = "Filler pfp";
    private float ratingScore = 0;
    private int ratingCount = 0;

    private String userName;
    private static String viewClickedUser;

    //temp variable to test router link
    private static String userRatingAuthor;

    public NewViewAccount() {
        DBViewUser db = new DBViewUser();
        /* Store the user info from the DB to an array */

        // recursive view
        fillRecersive();
        

        // add for clicked user

        getElement().getClassList().add("hello-grid");
        setWidth("99%");

        /* Name Profile Grid Area */
        Div nametag = new Div();
        nametag.addClassName("label-one");

        Image img1 = new Image(pfp, "placeholder icon");
        img1.setWidth("180px");
        img1.addClassName("pfp");
        H1 name = new H1(first_name + " " + last_name);
        name.addClassName("name");

        H2 usernameProfile = new H2("@" + username);
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
        H6 aboutText2 = new H6(about);

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
        // H3 ratings = new H3("Ratings");
        // ratings.addClassName("ratings");

        ArrayList<ArrayList<String>> ratingDetails = new ArrayList<>();
        ratingDetails = DBAccount.getRating(accountId);
        VerticalLayout allRatings = new VerticalLayout();
        allRatings.addClassName("all-ratings_new");

        // loops the comments
        ratingCount = ratingDetails.size();
        for (int i = 0; i < ratingDetails.size(); i++) {
            // rating, at, descr,name
            String username = ratingDetails.get(i).get(3);
            // fix button name
            Button ratingAuthor1 = new Button("@" + username, evet -> {
                clickedUser = username;
                viewClickedUser = username;
                // UI.getCurrent().getPage().reload();
                this.getUI().ifPresent(ui -> ui.navigate(
                        ViewUser.class));
                        
            });
            ratingAuthor1.addClassName("rating-author");
            Span datePosted1 = new Span(ratingDetails.get(i).get(1));
            datePosted1.addClassName("date-posted");
            String curRating = ratingDetails.get(i).get(0);
            H6 commentText1 = new H6(curRating + ": " + ratingDetails.get(i).get(2));
            ratingScore += Integer.parseInt(curRating);
            commentText1.addClassName("comment-text");
            VerticalLayout rating1 = new VerticalLayout(ratingAuthor1, datePosted1, commentText1);
            rating1.addClassName("single-rating");
            allRatings.add(rating1);
            add(allRatings);
        }

        if (ratingDetails.isEmpty()) {
            H4 noRating = new H4("No ratings for user yet");
            allRatings.add(noRating);
        }

        ratingScore = ratingScore / ratingCount;
        String temp = "";
        if (ratingDetails.size() == 0) {
            temp = "";
        } else {
            temp = "" + ratingScore;
        }
        H3 ratings = new H3("Ratings " + temp);
        ratings.addClassName("ratings");

        TextField newRatingField = new TextField();
        newRatingField.addClassName("new-rating-text");
        newRatingField.setPlaceholder("Add a new rating for this user");

        Select<Integer> select = new Select<>();
        select.setItems(1, 2, 3, 4, 5);
        select.setValue(1);
        select.addClassName("rating-selector");
        // System.out.println(DBAccount.getRating(accountId));

        Button submitRating = new Button("Submit", e -> {
            // String rating,String comment, String currUserId, String accountPostedOnId
            if(LoginView.logStatus()){
               int rating = select.getValue();
            String comment = newRatingField.getValue();
            int userId = DBlogin.getUserId();
            String acId = accountId;
            DBAccount.addRating(rating, comment, userId, acId);
            UI.getCurrent().getPage().reload();
            showSuccess(); 
            }else{
                showFail();
            }
            

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

    private void showSuccess() {
        Notification notification = Notification.show("Rating was posted ");
        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);

    }

    private void showFail() {
        Notification notification =
                Notification.show("Must be logged in to Post Comments");
        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
      
    }

    public void fillRecersive() {
        userName =  ViewUser.getClickedUser();
        System.out.println(userName);

        if (userName.contains("@")) {
            try {
                user = DBViewUser.searchEmail(userName);
            } catch (ClassNotFoundException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        } else {
            try {
                user = DBViewUser.searchUser(userName);
            } catch (ClassNotFoundException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
        first_name = user.get(0);
        last_name = user.get(1);
        username = user.get(2);
        emailDB = user.get(3);
        about = user.get(4);
        contact = user.get(5);
        accountId = user.get(6);
        pfp = user.get(7);
        if(pfp == null){
            pfp = "images/icon.jpg";
        }

    }

    public static String getClickedUser(){
        return viewClickedUser;
    }

    public static String getRatingAuthor(){
        return userRatingAuthor;
    }

    public static void setRatingAuthor(String name){
        userRatingAuthor = name;
    }

    public static void resetUser() {
        userRatingAuthor = null;
    }
}
