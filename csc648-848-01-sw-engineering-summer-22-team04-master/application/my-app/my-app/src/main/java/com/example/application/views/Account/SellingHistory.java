package com.example.application.views.Account;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin.Horizontal;
import com.vaadin.flow.component.dependency.StyleSheet;


import java.util.ArrayList;
import java.util.HashMap;



@CssImport("./themes/myapp/shopping-cart.css")

@PageTitle("SellingHistory")//maybe make to name of web
@Route(value = "SellingHistory", layout = MainLayout.class)

public class SellingHistory extends HorizontalLayout{

    String title;
    String image;
    String price;
    String quantity;
    String link;

    public SellingHistory(){


        ArrayList<HashMap<String, String>> cart = new ArrayList<HashMap<String, String>>(); 

        // mock data delete later
        HashMap<String, String> item1 = new HashMap<String, String>();
        item1.put("title", "really long string of text");
        item1.put("image", "url");
        item1.put("price", "10");
        item1.put("quantity", "1");
        item1.put("link", "link to item");

        HashMap<String, String> item2 = new HashMap<String, String>();
        item2.put("title", "paper");
        item2.put("image", "url");
        item2.put("price", "5");
        item2.put("quantity", "3");
        item2.put("link", "link to item");

        cart.add(item1);
        cart.add(item2);

        VerticalLayout purchaseHistory = new VerticalLayout();

        Button account = new Button("Return to Account", e ->{
            this.getUI().ifPresent(ui -> ui.navigate("/Account"));
        });
        account.setClassName("button");
        
        purchaseHistory.add(account);

       
        for (HashMap<String, String> i: cart){
            HorizontalLayout horizontalSC = new HorizontalLayout();
           


            horizontalSC.setWidthFull();

            String cartUrl = i.get("image");
            
            TextArea title = new TextArea("Name");

            Image image = new Image(cartUrl,"");
            image.setHeight("150px");
            image.setWidth("150px");

            TextField quantity = new TextField("Quantity");
            quantity.setWidth("50%");
            quantity.setHeight("50%");

            TextField price = new TextField("Price");
            price.setWidth("50%");
            price.setHeight("50%");

            TextField user = new TextField("Link to Item");
            user.setWidth("50%");
            user.setHeight("50%");



            title.setValue(i.get("title"));
            title.setReadOnly(true);
            image.setTitle(i.get("image"));
            quantity.setValue(i.get("quantity"));
            quantity.setReadOnly(true);
            price.setValue(i.get("price"));
            price.setReadOnly(true);
            user.setValue(i.get("link"));
            user.setReadOnly(true);
            
        
            HorizontalLayout priceLayout = new HorizontalLayout();
            priceLayout.add(price);
            priceLayout.setSpacing(true);
            priceLayout.setAlignItems(Alignment.BASELINE);

            horizontalSC.addClassName("shoppingCartLayout");
            horizontalSC.add(image, title, quantity, price, user);
            horizontalSC.setJustifyContentMode(JustifyContentMode.START);
            horizontalSC.setAlignItems(Alignment.CENTER);

            purchaseHistory.add(horizontalSC);

        }

        add(purchaseHistory);
        

        } 


    }
    
