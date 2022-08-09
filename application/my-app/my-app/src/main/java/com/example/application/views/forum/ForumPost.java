package com.example.application.views.forum;

import java.util.stream.Stream;

import com.example.application.Data.DBAccount;
import com.example.application.Data.DBPostForum;
import com.example.application.Data.PostForumDetail;
import com.example.application.views.MainLayout;
import com.example.application.views.Footer.FooterView;
import com.example.application.views.login.LoginView;
import com.vaadin.flow.component.ClickNotifier;
import com.vaadin.flow.component.HasValueAndElement;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@CssImport("./themes/myapp/makeforumpost.css")
@PageTitle("Forum Post")
@Route(value = "forumPost", layout = MainLayout.class)
public class ForumPost extends VerticalLayout{
    private Button submit;
    TextField title;
    TextArea description;
    private PostForumDetail userBean = new PostForumDetail();

    public ForumPost(){
        getElement().getClassList().add("make-forum-grid");
        setWidth("100%");

        //The title of the make a forum post page
        this.addClassName("forum-background");

        Div headerholder = new Div();
        headerholder.addClassName("header");
        H1 header = new H1("Make a new Post!");
        header.addClassName("header");


        Div titleholder = new Div();
        titleholder.addClassName("make-title");
        title= new TextField("Title");
        title.addClassName("make-title");


        Div descriptionholder = new Div();
        descriptionholder.addClassName("make-description");
        description = new TextArea("Description");
        description.addClassName("make-description");
        description.setWidth("800px");
        description.setHeight("200px");

        Div submitholder = new Div();
        submitholder.addClassName("make-submit-button");
        submit = new Button ("Submit");



        submit.addClassName("make-submit-button");

        //This should create a new post object which will later be stored on the database to be accessed later
        //Binder will make it so all inputs into the fields will alter the object properties
        // var binder = new Binder<>(fPost.class);
        // binder.bindInstanceFields(this);

        add(
            header,
            title,
            description,
            submit
        );
        submit.addClickListener(ev ->{
            DBPostForum db = new DBPostForum();
            storeUserInfo();
            String userName;
            int userId = 0;
            DBAccount dbAccount = new DBAccount();
            if (LoginView.getUser() != null) {
                userName = LoginView.getUser();

                System.out.println(userName);
                
                try {
                    userId = dbAccount.getUserID(userName);
                    System.out.println("userId:" + userId);

                } catch (ClassNotFoundException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }
            System.out.println(userBean.getTitle());
            db.StorePostForum(userId, userBean.getTitle(), userBean.getBody());
            showSuccess(userBean);
            this.getUI().ifPresent(ui ->
                ui.navigate("/home"));
        });
        
        FooterView footer = new FooterView();
           HorizontalLayout footerLay = new HorizontalLayout();
           footerLay.setClassName("FooterLayout");
           footerLay = footer.getFooter();
           add(footerLay);
    }

    public Button getPostButton() { return submit; }

    private void showSuccess(PostForumDetail userBean) {
        Notification notification =
                Notification.show("Forum Post was successfully created");
        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
        
 
        // Here you'd typically redirect the user to another view
    }
    private void storeUserInfo(){
        userBean.setTitle(title.getValue());
        userBean.setBody(description.getValue());

   }
}
