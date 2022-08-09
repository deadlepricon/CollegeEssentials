package com.example.application.views.home;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.example.application.Data.DBHome;
import com.example.application.views.MainLayout;
import com.example.application.views.Footer.FooterView;
import com.example.application.views.Items.ItemView;
import com.example.application.views.login.LoginView;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.function.ValueProvider;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.*;

@CssImport("./themes/myapp/Home.css")
@PageTitle("College Essentials")
@Route(value = "home", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class HomeView extends VerticalLayout {

    private Button search;
    private Button itemButton;
    TextField searchBar;
    Select<String> select;
    static HashMap<String, ArrayList<String>> itemList = new HashMap<>();
    String searchedItem;
    List<Component> comps = new ArrayList<>();
    //ItemView itemView = new ItemView();
    HorizontalLayout hor = new HorizontalLayout();
    VerticalLayout vl = new VerticalLayout();
    private static String currTitle;
    private static String userId;
    
    // Liveshare test - Darren Wong
    public HomeView() {
        
        
        // searchbar
        // adds background image
        //this.getElement().getStyle().set( "background-image" , "url('https://cdn.discordapp.com/attachments/982032674078216210/997652262996430868/unknown.png')" );
        this.addClassName("background-photo");
        searchBar = new TextField();
        searchBar.setClassName("searchBar");
        searchBar.setPlaceholder("Search For Products, and change the category section....");
        searchBar.setPrefixComponent(VaadinIcon.SEARCH.create());
        searchBar.setClearButtonVisible(true);
        searchBar.setWidth("40em");
        searchBar.addClassName("home-search-bar");

        // dropdown menu
        select = new Select<>();
        select.setItems("All Categories","Textbook", "School Supplies", "Furniture", "Lifestyle", "Miscellaneous");
        select.setValue("All Categories");
        select.setClassName("selector");
        select.addClassName("home-dropdown-bar");


        search = new Button("Search");
        search.setClassName("searchButton");
        search.addClassName("home-search-button");


        HorizontalLayout hv = new HorizontalLayout(searchBar, select, search);
        hv.addClassName("search-bar");
        hv.setSpacing(false);
        setHorizontalComponentAlignment(Alignment.CENTER, hv);
        add(hv);

        Paragraph college = new Paragraph("Connect students and faculty from");
        college.addClassName("title-p1");
        Paragraph essentials = new Paragraph("colleges all over the world.");
        essentials.addClassName("title-p2");
        
        add(college);
        add(essentials);

        H1 descrip1 = new H1("Get started today by browsing our marketplace, ");
        descrip1.setClassName("description-1");
        H1 descrip2 = new H1("ask a question in our forums,");
        descrip2.setClassName("description-2");
        H1 descrip3 = new H1("or look for different service listings.");
        descrip3.setClassName("description-3");

        
        add(descrip1);
        add(descrip2);
        add(descrip3);
        comps.add(descrip1);
        comps.add(descrip2);
        comps.add(descrip3);
        comps.add(college);
        comps.add(essentials);
        Button marketPlaceButton = new Button("Marketplace", e->{
            //clears page and shows grid of everything
            if (!comps.isEmpty()) {
                for (Component c : comps) {
                    remove(c);
                }
            }
            DBHome db = new DBHome();
            
            itemList = db.searchHomeItem("", "All Categories");
            popPage();

        });
        marketPlaceButton.setClassName("marketPlaceButton");
        marketPlaceButton.addThemeVariants(ButtonVariant.LUMO_LARGE);

        Button formButton = new Button("Forum Place", ev ->{
            this.getUI().ifPresent(ui -> ui.navigate("/forumList"));
        });
        formButton.setClassName("forumButton");
        formButton.addThemeVariants(ButtonVariant.LUMO_LARGE);

        Button serviceButton = new Button("Service Listings" ,event ->{
            //add for service listing serviceList
            this.getUI().ifPresent(ui -> ui.navigate("/serviceList"));
        });
        serviceButton.setClassName("serviceButton");
        serviceButton.addThemeVariants(ButtonVariant.LUMO_LARGE);

        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.add(marketPlaceButton,formButton,serviceButton);
        buttonLayout.setJustifyContentMode(JustifyContentMode.AROUND);
        buttonLayout.setClassName("buttonLayout");
        add(buttonLayout);

        //code for button images for home page
        Button marketImage = new Button("", e->{
            //clears page and shows grid of everything
            if (!comps.isEmpty()) {
                for (Component c : comps) {
                    remove(c);
                }
            }
            DBHome db = new DBHome();
            
            itemList = db.searchHomeItem("", "All Categories");
            popPage();

        });
        marketImage.setClassName("homePageMarketImage");
        Button forumImage = new Button("", ev ->{
            this.getUI().ifPresent(ui -> ui.navigate("/forumList"));
        });
        forumImage.setClassName("homePageForumImage");
        Button serviceImage = new Button("", ev ->{
            this.getUI().ifPresent(ui -> ui.navigate("/serviceList"));
        });
        serviceImage.setClassName("homePageServiceImage");
        HorizontalLayout imageLayout = new HorizontalLayout();
        imageLayout.add(marketImage,forumImage,serviceImage);
        imageLayout.setJustifyContentMode(JustifyContentMode.AROUND);
        imageLayout.setClassName("homePageImageLayout");
        add(imageLayout);
        comps.add(imageLayout);

        //add(descrip);
        comps.add(buttonLayout);
        FooterView footer = new FooterView();
        HorizontalLayout footerLay = footer.getFooter();
        
        comps.add(footerLay);
        add(footerLay);


        ArrayList<Button> buttonList = new ArrayList<>();
        hor.setClassName("horItemLayout");
        vl.setClassName("vertItemLayout");
       
        getSearchButton().addClickListener(event -> {
            searchedItem = searchBar.getValue();
            String category = select.getValue();

            // key is title, val is url
            DBHome db = new DBHome();
            itemList = db.searchHomeItem(searchedItem, category);
         

            // removes components
            if (!comps.isEmpty()) {
                for (Component c : comps) {
                    remove(c);
                }
            }

            if(itemList.isEmpty()){
                H2 error = new H2("Sorry, we couldnt find what you were searching for");
                error.setClassName("errorMessage");
                add(error);
                comps.add(error);
                add(footerLay);
                comps.add(footerLay);

            } else{
                popPage();
            }

            

        });

        itemList.clear();
    }

    public void popPage(){

        int count = 0;
            hor = new HorizontalLayout();
            vl = new VerticalLayout();
            vl.setSizeFull();
        for (String key : itemList.keySet()) {
                ArrayList<String> array = new ArrayList<>();
                vl.setClassName("vlHome");
                vl.setWidth("25%");
            if (count < 4) {
                array = itemList.get(key);
                Image image = new Image(array.get(0), key);
                image.setHeight("200px");
                image.setWidth("150px");
                vl.add(image);

                //price
                TextField price = new TextField();
                String cost ="$"+ array.get(2);
                if(array.get(2) == null){
                    cost = "No price was Given";
                }
                price.setValue(cost);
                price.setReadOnly(true);
                vl.add(price);
                String Id = array.get(4);
                Button b = new Button(key, e -> {
                    currTitle = key;
                    userId = Id;
                    this.getUI().ifPresent(ui -> ui.navigate("/itemView"));
                });
                b.setClassName("buttonItem");
                vl.add(b);
                hor.add(vl);
                comps.add(hor);
                vl.setAlignItems(Alignment.CENTER);
                vl = new VerticalLayout();
            }  if (count == 3){
                hor.setPadding(true);
                hor.setSpacing(true);
                hor.setWidthFull();
                hor.setJustifyContentMode(JustifyContentMode.AROUND);
                add(hor);
                comps.add(hor);

                hor = new HorizontalLayout();

                count = -1;
            }
            count++;
        }

        hor.setWidthFull();
        hor.setJustifyContentMode(JustifyContentMode.AROUND);
        add(hor);
        HorizontalLayout footer = new HorizontalLayout();
        footer = new FooterView().getFooter();
        add(footer);
        comps.add(footer);

    }

    public static String getTitle() {
        return currTitle;
    }

    public static String getUserId(){
        return userId;
    }

    public static HashMap<String, ArrayList<String>> getMap() {
        return itemList;
    }

    public TextField getSearch() {
        return searchBar;
    }

    public void setButton(Button title) {
        itemButton = title;
    }

    public Button getButton() {
        return itemButton;
    }

    public String getSelector() {
        return select.toString();
    }

    public Button getSearchButton() {
        return search;
    }

}
