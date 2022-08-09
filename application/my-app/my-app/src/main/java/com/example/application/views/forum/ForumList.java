package com.example.application.views.forum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.application.Data.DBForumList;
import com.example.application.views.MainLayout;
import com.example.application.views.Footer.FooterView;
import com.example.application.views.login.LoginView;
import com.vaadin.flow.component.ClickNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.Component;

@CssImport("./themes/myapp/forumlist.css")
@PageTitle("Forum List")
@Route(value = "forumList", layout = MainLayout.class)
public class ForumList extends VerticalLayout {

    // Array list of posts to be added to the horizontal layout. Temporary, and
    // should be deleted later
    static ArrayList<HashMap<String, String>> post = new ArrayList<HashMap<String, String>>();
    //static ArrayList<HashMap<String, String>> itemList = new ArrayList<HashMap<String, String>>();
    // Determines the page to be shown when a post is clicked on
    static String nav;
    private Button search;
    private TextField searchBar;
    private String searchedItem;
    private DBForumList db;
    private int forumPostCounter;
    Select<String> select;
    private static String flUser;
    private List<Component> comps = new ArrayList<>();

    public ForumList() throws ClassNotFoundException {
        this.addClassName("forum-background");

        // New database connection
        db = new DBForumList();
        post = db.getForumListings();

        // Code for the new post button that routes you to a page where you can make a
        // post
        Button newPost = new Button("Make a new post", e -> {

            if (LoginView.logStatus() == false) {

                Notification notification = Notification.show("User not logged in!");
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);

            } else {

                this.getUI().ifPresent(ui -> ui.navigate("/forumPost"));
            }
        });
        newPost.addClassName("forum-search-button");

        // Code for the search button
        search = new Button("Search");
        search.addClassName("forum-search-button");

        // Code for the text field
        searchBar = new TextField();
        searchBar.addClassName("forum-search-bar");
        searchBar.setPlaceholder("Search for a forum post (Title, Description, All)");
        searchBar.setPrefixComponent(VaadinIcon.SEARCH.create());
        searchBar.setClearButtonVisible(true);
        searchBar.setWidth("50em");

        // This is a selection drop down bar. Dropdown is here as placeholder, and will
        // not be changed till later.
        select = new Select<>();
        select.addClassName("forum-dropdown-bar");
        select.setItems("All", "Title", "Description");
        select.setValue("All");

        // The horizontal layout with the text field, search button, and new post button
        HorizontalLayout hv = new HorizontalLayout(searchBar, select, search, newPost);
        hv.addClassName("forum-search-layout");
        setHorizontalComponentAlignment(Alignment.CENTER, hv);
        hv.setWidthFull();

        hv.setPadding(true);

        add(hv);

        forumPostCounter = 1; // Keeps count of the forum posts
        for (HashMap<String, String> i : post) {
            HorizontalLayout hv1 = new HorizontalLayout();
            hv1.addClassName("forum-post");
            hv1.setWidthFull();

            // Should at some point route to an url stored in the database
            Button title = new Button("(" + forumPostCounter + ") Title: " + i.get("title"), e -> {
                nav = i.get("title");
                System.out.println("Title:" + i.get("title"));
                this.getUI().ifPresent(ui -> ui.navigate("/fPost"));
            });
            title.addClassName("forum-post-title");
            title.addClassName("forum-post-title-hov");

            Button user = new Button("User: " + i.get("user"), e -> {
                flUser = i.get("user");
                this.getUI().ifPresent(ui -> ui.navigate("/viewAccount"));
            });
            user.addClassName("forum-post-user");
            user.addClassName("forum-post-user-hov");

            Button description = new Button(i.get("description"), e -> {
                nav = i.get("description");
                this.getUI().ifPresent(ui -> ui.navigate("/fPost"));
            });
            description.addClassName("forum-post-description");
            description.addClassName("forum-post-description-hov");

            hv1.add(title, user, description);
            add(hv1);
            comps.add(hv1);

            forumPostCounter++;
        }

        // final FooterView footer = new FooterView();
        // HorizontalLayout footerLay = new HorizontalLayout();
        // footerLay.setClassName("FooterLayout");
        // footerLay = footer.getFooter();
        // add(footerLay);
        // comps.add(footerLay);

        getSearchButton().addClickListener(event -> {
            // Should show a list of forum posts based on the input from the search bar
            searchedItem = searchBar.getValue();
            String searchValue = select.getValue();
            //System.out.println(searchedItem);

            try {
                post = db.searchForumListings(searchedItem, searchValue);
                // removes components
                if (!comps.isEmpty()) {
                    for (Component c : comps) {
                        remove(c);
                    }
                    forumPostCounter = 1;
                }
                if(post.isEmpty()){
                    H2 error = new H2("Sorry, we couldnt find what you were searching for");
                    error.setClassName("errorMessage");
                    add(error);
                    comps.add(error);
                    
                    // comps.add(footerLay);
                    // add(footerLay);
                } else{
                    loadPage();
                }

            } catch (ClassNotFoundException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

        });
        //post.clear();
    }

    public static String getNav() {
        return nav;
    }

    public static ArrayList<HashMap<String, String>> getPosts() {
        return post;
    }

    public Button getSearchButton() {
        return search;
    }

    public TextField getSearch() {
        return searchBar;
    }
    
    public String getSelector() {
        return select.toString();
    }
    public static String getForumUser(){
        return flUser;
    }

    public void loadPage() {
        for (HashMap<String, String> i : post) {
            HorizontalLayout hv1 = new HorizontalLayout();
            hv1.addClassName("forum-post");
            hv1.setWidthFull();

            // Should at some point route to an url stored in the database
            Button title = new Button("(" + forumPostCounter + ") Title: " + i.get("title"), e -> {
                nav = i.get("title");
                this.getUI().ifPresent(ui -> ui.navigate("/fPost"));
            });
            title.addClassName("forum-post-title");
            title.addClassName("forum-post-title-hov");

            Button user = new Button("User: " + i.get("user"), e -> {
                this.getUI().ifPresent(ui -> ui.navigate(i.get("account_route")));
            });
            user.addClassName("forum-post-user");
            user.addClassName("forum-post-user-hov");

            Button description = new Button(i.get("description"), e -> {
                nav = i.get("description");
                this.getUI().ifPresent(ui -> ui.navigate("/fPost"));
            });
            description.addClassName("forum-post-description");
            description.addClassName("forum-post-description-hov");

            hv1.add(title, user, description);
            add(hv1);
            comps.add(hv1);

            forumPostCounter++;
        }
    }

    public static void resetFormUser() {
        flUser = null;
    }

}