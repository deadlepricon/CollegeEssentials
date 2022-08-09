package com.example.application.views.SellingPage;

import org.apache.xmlbeans.impl.xb.xsdschema.Attribute.Use;

import com.example.application.Data.UserDetails;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.function.ValueProvider;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;



@PageTitle("Buy")
@Route(value = "Buy", layout = MainLayout.class)
public class SellingPageView extends VerticalLayout{

    public SellingPageView(){
        //searchbar
        TextField searchBar = new TextField();
        searchBar.setPlaceholder("Search");
        searchBar.setPrefixComponent(VaadinIcon.SEARCH.create());
        searchBar.setClearButtonVisible(true);
        searchBar.setWidth("50em");
        //searchBar.CENTER;
        //searchBar.set

        //zipcode searchbar
        TextField zipCode = new TextField();
        zipCode.setMinLength(5);
        zipCode.setMaxLength(5);
        zipCode.setPlaceholder("ZipCode");
        zipCode.getStyle().set("width", "6em");
       // zipCode.setLabel("Zip code");
        zipCode.setClearButtonVisible(true);
        
        HorizontalLayout hv = new HorizontalLayout(zipCode,searchBar);
        setHorizontalComponentAlignment(Alignment.CENTER, hv);
        add(hv);

        //Grid
        Grid<UserDetails> grid = new Grid<>(UserDetails.class, false);
        grid.addColumn(createImageRenderer()).setHeader("Image");
               // .setAutoWidth(true).setFlexGrow(0);
        grid.addColumn(UserDetails::getFirstName).setHeader("Image");
        grid.addColumn(UserDetails::getLastName).setHeader("image");
        grid.addColumn(UserDetails::getEmail).setHeader("image");

        grid.addThemeVariants(GridVariant.LUMO_COLUMN_BORDERS);

       // grid.setItems(UserDetails);
        add(grid);



    }

    private ValueProvider<UserDetails, ?> createImageRenderer() {
        /*return LitRenderer.<Person>of(
                "<vaadin-avatar img=\"${item.pictureUrl}\" name=\"${item.fullName}\" alt=\"User avatar\"></vaadin-avatar>")
                .withProperty("pictureUrl", Person::getPictureUrl); */
        return null;
    }
    
}
