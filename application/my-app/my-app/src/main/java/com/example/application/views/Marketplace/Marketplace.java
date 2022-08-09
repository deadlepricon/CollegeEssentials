package com.example.application.views.Marketplace;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.example.application.Data.DBHome;
import com.example.application.views.MainLayout;
import com.example.application.views.Footer.FooterView;
import com.example.application.views.Items.ItemView;
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


@PageTitle("Marketplace")
@Route(value = "marketplace", layout = MainLayout.class)
public class Marketplace extends VerticalLayout {

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
    private static String currentTitle;
    public Marketplace() {
        // searchbar
        searchBar = new TextField();
        searchBar.setClassName("searchBar");
        searchBar.setPlaceholder("Search For Books, Furniture and More....");
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

        Button newItem = new Button("Sell an item", e ->{
            this.getUI().ifPresent(ui -> ui.navigate("/postItem"));
        });
        newItem.addClassName("searchButton-2");

        HorizontalLayout hv = new HorizontalLayout(searchBar, select, search, newItem);
        hv.addClassName("search-bar");
        hv.setSpacing(false);
        setHorizontalComponentAlignment(Alignment.CENTER, hv);
        add(hv);


        FooterView footer = new FooterView();
        HorizontalLayout footerLay = footer.getFooter();


        ArrayList<Button> buttonList = new ArrayList<>();
        hor.setClassName("horItemLayout");
        vl.setClassName("vertItemLayout");
       
        DBHome db = new DBHome();
        itemList = db.searchHomeItem("", "All Categories");
        popPage();
        getSearchButton().addClickListener(event -> {
            searchedItem = searchBar.getValue();
            String category = select.getValue();
            
         

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

            }else{
                popPage();
            }

            

        });

        itemList.clear();
    }

    public void popPage(){

        int count = 0;
            hor = new HorizontalLayout();
           
            
            vl = new VerticalLayout();
        for (String key : itemList.keySet()) {
                ArrayList<String> array = new ArrayList<>();
            if (count < 4) {
                array = itemList.get(key);
                Image image = new Image(array.get(0), key);
                image.setHeight("200px");
                image.setWidth("150px");
                vl.add(image);
                Button b = new Button(key, e -> {
                    currentTitle = key;
                    System.out.println(currentTitle);
                    this.getUI().ifPresent(ui -> ui.navigate("/marketItemView"));
                });
        
                b.setClassName("buttonItem");
                //b.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
                vl.add(b);
                hor.add(vl);
                comps.add(hor);
                vl.setAlignItems(Alignment.CENTER);
                vl = new VerticalLayout();
            } else {
                hor.setPadding(true);
                hor.setSpacing(true);
                hor.setWidthFull();
                hor.setJustifyContentMode(JustifyContentMode.AROUND);
                add(hor);
                comps.add(hor);

                hor = new HorizontalLayout();

                count = 0;
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
        return currentTitle;
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

