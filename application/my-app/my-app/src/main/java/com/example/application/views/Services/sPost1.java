package com.example.application.views.Services;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.application.Data.DBAccount;
import com.example.application.Data.DBAdmin;
import com.example.application.Data.DBComments;
import com.example.application.Data.DBForumList;
import com.example.application.Data.DBServiceList;
import com.example.application.views.MainLayout;
import com.example.application.views.Account.ViewUser;
import com.example.application.views.login.LoginView;
import com.vaadin.flow.component.ClickNotifier;
import com.vaadin.flow.component.UI;
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
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextAreaVariant;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@CssImport("./themes/myapp/forumpost.css")
@PageTitle("Service Listing")
@Route(value = "sPost", layout = MainLayout.class)
public class sPost1 extends VerticalLayout{
    private static String slUser;
    private static String slCommentUser;
    HashMap<String, String> post1 = new HashMap<String, String>();

    public sPost1() throws ClassNotFoundException{
        getElement().getClassList().add("forum-grid");
        setWidth("100%");
        this.addClassName("forum-background");

        ArrayList<HashMap<String, String>> posts = ServiceList.getPosts();
        String nav = ServiceList.getNav();
        System.out.println(nav);
        for(HashMap<String, String> i: posts){
            if(nav == i.get("title")){
                post1 = i;
            }
        }

        //Grid area for image
        Div imageholder = new Div();
        imageholder.addClassName("fp-image");
        Image img1 = new Image("images/icon.jpg", "placeholder icon");
        img1.addClassName("fp-image");

        //Grid area for title
        Div titleholder = new Div();
        titleholder.addClassName("fp-title");
        String titleString = post1.get("title");
        H2 title = new H2(post1.get("title"));
        title.addClassName("fp-title");

        //Grid area for user name
        Div nameholder = new Div();
        nameholder.addClassName("fp-user");
        Button user = new Button(post1.get("user"));
        user.addClassName("fp-user");
        // user.setValue(post1.get("user"));
        // user.addThemeVariants(TextFieldVariant.LUMO_ALIGN_CENTER);
        // user.setReadOnly(true);
        user.addClickListener(e ->{
            slUser = post1.get("user");
            user.getUI().ifPresent(ui ->ui.navigate("viewAccount"));
        });

        //Grid area for the post
        Div postholder = new Div();
        postholder.addClassName("fp-description");
        TextArea description = new TextArea("");
        description.addThemeVariants(TextAreaVariant.LUMO_ALIGN_CENTER);
        description.setWidth("800px");
        description.setMinHeight("200px");
        description.setValue(post1.get("description"));
        description.setReadOnly(true);
        description.addClassName("fp-description");

        //Grid area for contact
        Div contactHolder = new Div();
        contactHolder.addClassName("fp-contact");
        H3 aboutMe = new H3("About Me");
        aboutMe.addClassName("fp-about-me");
        
        H6 aboutText1 = new H6("Hi, I'm " + post1.get("user") + "! Nice to meet you!");
        H6 aboutText2 = new H6("CS @ San Francisco State University");
        
        VerticalLayout aboutText = new VerticalLayout(aboutText1, aboutText2);
        aboutText.addClassName("fp-aboutText");
        
        H3 contactMe = new H3("Contact Me");
        contactMe.addClassName("fp-contact-me");
        H6 contactText1 = new H6("For business inquiries only: loremipsum@gmail.com");
        VerticalLayout contactText = new VerticalLayout(contactText1);
        contactText.addClassName("fp-contact-text");
        VerticalLayout personalInfo = new VerticalLayout(aboutMe, aboutText, contactMe, contactText);
        personalInfo.addClassName("fp-contact");

        // Comment Grid Area
        DBComments db = new DBComments();
        int service_listing_id = DBServiceList.getServiceListingID(nav);
        System.out.println("spost:"+service_listing_id);
        VerticalLayout allComments = new VerticalLayout();
        ArrayList<ArrayList<String>> comments = DBComments.getServiceReply(service_listing_id);
        System.out.println(comments.size());
        for (int i = 0; i < comments.size(); i++) {
            String desc = comments.get(i).get(0);
            String postedAt = comments.get(i).get(1);
            String username = comments.get(i).get(2);

            VerticalLayout indivComment = new VerticalLayout();
            Div commentAuthor = new Div();
            commentAuthor.addClickListener(e ->{
                slUser = username;
            });
            commentAuthor.add(new RouterLink(username, ViewUser.class));
            commentAuthor.addClassName("fp-comment-author");
            Span datePosted = new Span(postedAt);
            datePosted.addClassName("fp-date-posted");
            H6 commentText = new H6(desc);
            commentText.addClassName("fp-comment-text");
            indivComment = new VerticalLayout(commentAuthor, datePosted, commentText);
            indivComment.addClassName("fp-single-comment");
            allComments.add(indivComment);
        }
        allComments.addClassName("fp-all-comments");

        TextArea newCommentField = new TextArea();
        newCommentField.addClassName("new-comment-text");
        newCommentField.setPlaceholder("Add a new comment for this user");

        Button submitComment = new Button("Submit");
        submitComment.addClassName("fp-submit-button");
        submitComment.addClickListener(e -> {
            String comment = newCommentField.getValue();
            String userName;
            DBServiceList dbServiceList = new DBServiceList();
            DBAccount dbAccount = new DBAccount();
            if(LoginView.logStatus()){
                if(comment != ""){
                   if (LoginView.getUser() != null) {
                userName = LoginView.getUser();

                System.out.println(userName);
                int userId;
                try {
                    userId = dbAccount.getUserID(userName);
                    System.out.println("userId:" + userId);
                    int service_listing_id1 = DBServiceList.getServiceListingID(nav);
                    DBComments.StoreServiceReply(comment, service_listing_id1, userId);
                } catch (ClassNotFoundException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

                UI.getCurrent().getPage().reload();

            } 
                }else{
                    showEmpty();
                }
                
            }else{
                showFailer();
            }
            

        });

        Button adminDelete = new Button("Delete",evnt ->{
            //TODO add delete
            this.getUI().ifPresent(ui -> ui.navigate("/serviceList"));
            DBAdmin.deleteServicePost(titleString);
            showDeleteSuccess();

        });

        if(LoginView.logStatus()){
           if(LoginView.isAdmin()){
            adminDelete.setVisible(true);
        }else{
            adminDelete.setVisible(false);
        } 
        }else{
            adminDelete.setVisible(false);
        }

        HorizontalLayout commentSubmission = new HorizontalLayout(newCommentField, submitComment,adminDelete);
        commentSubmission.addClassName("comment-submission");
        VerticalLayout h3 = new VerticalLayout(allComments, commentSubmission);
        h3.addClassName("fp-comments");
        
        // TextArea newCommentField = new TextArea();
        // newCommentField.addClassName("new-comment-text");
        // newCommentField.setPlaceholder("Add a new comment for this user");

        // VerticalLayout h3 = new VerticalLayout(comments, allComments, newCommentField);
        // h3.addClassName("fp-comments");

        add(
            img1,
            title,
            user,
            description,
            personalInfo,
            h3
        );
    }
    public static String getListingUser(){
        return slUser;
    }

    private void showFailer() {
        Notification notification =
                Notification.show("Must be logged in to Post Comments");
        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
      
    }

    private void showEmpty() {
        Notification notification = Notification.show("Comments cant be empty");
        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);

    }
    public static void resetUser() {
        slUser = null;
    }
    private void showDeleteSuccess() {

        Notification notification = Notification.show("Item Was Success deleted");
        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
    
      }
}
