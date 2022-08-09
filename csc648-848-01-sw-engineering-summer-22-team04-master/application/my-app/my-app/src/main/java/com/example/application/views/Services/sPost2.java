package com.example.application.views.Services;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.ClickNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextAreaVariant;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@CssImport("./themes/myapp/forumpost.css")
@PageTitle("Service Listing")
@Route(value = "sPost2", layout = MainLayout.class)
public class sPost2 extends VerticalLayout{
    public sPost2(){
        getElement().getClassList().add("forum-grid");
        setWidth("100%");
        this.addClassName("forum-background");

        HashMap<String, String> post2 = new HashMap<String, String>();
        post2.put("title", "CSC 510 TA");
        post2.put("user", "Brendan2");
        post2.put("description", "I am in need of a teacher assistant for my class.");

        //Grid area for image
        Div imageholder = new Div();
        imageholder.addClassName("fp-image");
        Image img1 = new Image("images/icon.jpg", "placeholder icon");
        img1.addClassName("fp-image");

        //Grid area for title
        Div titleholder = new Div();
        titleholder.addClassName("fp-title");
        H2 title = new H2(post2.get("title"));
        title.addClassName("fp-title");

        //Grid area for user name
        Div nameholder = new Div();
        nameholder.addClassName("fp-user");
        TextField user = new TextField("");
        user.addClassName("fp-user");
        user.setValue(post2.get("user"));
        user.addThemeVariants(TextFieldVariant.LUMO_ALIGN_CENTER);
        user.setReadOnly(true);

        //Grid area for the post
        Div postholder = new Div();
        postholder.addClassName("fp-description");
        TextArea description = new TextArea("");
        description.addThemeVariants(TextAreaVariant.LUMO_ALIGN_CENTER);
        description.setWidth("800px");
        description.setMinHeight("200px");
        description.setValue(post2.get("description"));
        description.setReadOnly(true);
        description.addClassName("fp-description");

        //Grid area for contact
        Div contactHolder = new Div();
        contactHolder.addClassName("fp-contact");
        H3 aboutMe = new H3("About Me");
        aboutMe.addClassName("fp-about-me");
        
        H6 aboutText1 = new H6("Hi, I'm " + post2.get("user") + "! Nice to meet you!");
        H6 aboutText2 = new H6("CS @ San Francisco State University");
        
        VerticalLayout aboutText = new VerticalLayout(aboutText1, aboutText2);
        aboutText.addClassName("fp-aboutText");
        
        H3 contactMe = new H3("Contact Me");
        contactMe.addClassName("fp-contact-me");
        H6 contactText1 = new H6("For business inquiries only: brendini1@gmail.com");
        VerticalLayout contactText = new VerticalLayout(contactText1);
        contactText.addClassName("fp-contact-text");
        VerticalLayout personalInfo = new VerticalLayout(aboutMe, aboutText, contactMe, contactText);
        personalInfo.addClassName("fp-contact");

        //Comment Grid Area
        Div commentsDiv = new Div();
        H3 comments = new H3("Comments");
        comments.addClassName("fp-comment-title");

        H6 commentAuthor1 = new H6 ("@Reviewer1");
        commentAuthor1.addClassName("fp-comment-author");
        Span datePosted1 = new Span ("12/17/2022");
        datePosted1.addClassName("fp-date-posted");
        H6 commentText1 = new H6("Is this that one algorithm class?");
        commentText1.addClassName("fp-comment-text");
        VerticalLayout comment1 = new VerticalLayout(commentAuthor1, datePosted1, commentText1);
        comment1.addClassName("fp-single-comment");

        H6 commentAuthor2 = new H6 ("@Reviewer2");
        commentAuthor2.addClassName("fp-comment-author");
        Span datePosted2 = new Span ("12/17/2022");
        datePosted2.addClassName("fp-date-posted");
        H6 commentText2 = new H6("Might take this up just to brush up my skills.");
        commentText2.addClassName("fp-comment-text");
        VerticalLayout comment2 = new VerticalLayout(commentAuthor2, datePosted2, commentText2);
        comment2.addClassName("fp-single-comment");

        H6 commentAuthor3 = new H6 ("@Reviewer3");
        commentAuthor3.addClassName("fp-comment-author");
        Span datePosted3 = new Span ("12/17/2022");
        datePosted3.addClassName("fp-date-posted");
        H6 commentText3 = new H6("This class gives me pain.");
        commentText3.addClassName("fp-comment-text");
        VerticalLayout comment3 = new VerticalLayout(commentAuthor3, datePosted3, commentText3);
        comment3.addClassName("fp-single-comment");
        
        VerticalLayout allComments = new VerticalLayout(comment1, comment2, comment3);
        allComments.addClassName("fp-all-comments");
        
        TextArea newCommentField = new TextArea();
        newCommentField.addClassName("new-comment-text");
        newCommentField.setPlaceholder("Add a new comment for this user");

        VerticalLayout h3 = new VerticalLayout(comments, allComments, newCommentField);
        h3.addClassName("fp-comments");
        commentsDiv.addClassName("fp-comments");

        add(
            img1,
            title,
            user,
            description,
            personalInfo,
            h3
        );
    }
}
