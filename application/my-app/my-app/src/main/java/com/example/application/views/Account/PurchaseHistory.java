package com.example.application.views.Account;

import com.example.application.Data.DBPurchaseHistory;
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
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin.Horizontal;
import com.vaadin.flow.component.dependency.StyleSheet;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;



@CssImport("./themes/myapp/shopping-cart.css")

@PageTitle("Purchase History")//maybe make to name of web
@Route(value = "PurchaseHistory", layout = MainLayout.class)

public class PurchaseHistory extends HorizontalLayout{

    String title;
    String image;
    String price;
    String quantity;
    String user;

    static HashMap<String, ArrayList<String>> paymentList = new HashMap<>();
    static ArrayList<HashMap<String, String>> transactionList = new ArrayList();

    public PurchaseHistory(){
        
        VaadinSession currentSession = VaadinSession.getCurrent();

        int buyer_id = (Integer)currentSession.getAttribute("user_id");

        DBPurchaseHistory db = new DBPurchaseHistory();

        paymentList = db.createPaymentHistory(buyer_id);
        transactionList = db.createTransactionItemHistory(paymentList);
        // System.out.println(transactionList);
        // System.out.println(paymentList);

        // // mock data delete later
        // HashMap<String, String> item1 = new HashMap<String, String>();
        // item1.put("title", "really long string of text");
        // item1.put("image", "url");
        // item1.put("price", "10");
        // item1.put("quantity", "1");
        // item1.put("user", "@Darren");


        // cart.add(item1);

        VerticalLayout purchaseHistory = new VerticalLayout();

        Button account = new Button("Return to Account", e ->{
            this.getUI().ifPresent(ui -> ui.navigate("/Account"));
        });
        account.setClassName("button");
        
        purchaseHistory.add(account);
        H3 header = new H3("Purchase History");
        purchaseHistory.add(header);

        HashSet<String> payment_set = new HashSet<String>();

        for (HashMap<String, String> i: transactionList){

            HorizontalLayout horizontalSC = new HorizontalLayout();
            horizontalSC.setWidthFull();
            H4 payment_header = new H4("Payment ID: " + i.get("payment_id"));

            String cartUrl = i.get("image");
            
            TextArea title = new TextArea("Name");

            Image image = new Image(cartUrl, "shopping cart image");
            image.setHeight("150px");
            image.setWidth("150px");
            // TextField image = new TextField();
            TextField quantity = new TextField("Quantity");
            quantity.setClassName("textField");
            quantity.setWidth("50%");
            quantity.setHeight("50%");

            TextField price = new TextField("Price");
            price.setWidth("50%");
            price.setHeight("50%");

            title.setValue(i.get("title"));
            title.setReadOnly(true);
            image.setTitle(i.get("image"));
            quantity.setValue(i.get("quantity"));
            quantity.setReadOnly(true);
            price.setValue(i.get("price"));
            price.setReadOnly(true);

            horizontalSC.addClassName("shoppingCartLayout");
            if(!payment_set.contains(i.get("payment_id")))
            {
                purchaseHistory.add(payment_header);
                payment_set.add(i.get("payment_id")); 
                
            }
            horizontalSC.add(title);
            horizontalSC.add(image);
            horizontalSC.add(price);
            horizontalSC.add(quantity);
            horizontalSC.setJustifyContentMode(JustifyContentMode.START);
            horizontalSC.setAlignItems(Alignment.CENTER);


            purchaseHistory.add(horizontalSC);
            


        }

        add(purchaseHistory);


        }


    
       
        
        // for (HashMap<String, String> i: cart){
        //     HorizontalLayout horizontalSC = new HorizontalLayout();
           


        //     horizontalSC.setWidthFull();

        //     String cartUrl = i.get("image");
            
        //     TextArea title = new TextArea("Name");

        //     Image image = new Image(cartUrl,"");
        //     image.setHeight("150px");
        //     image.setWidth("150px");

        //     TextField quantity = new TextField("Quantity");
        //     quantity.setWidth("50%");
        //     quantity.setHeight("50%");

        //     TextField price = new TextField("Price");
        //     price.setWidth("50%");
        //     price.setHeight("50%");

        //     TextField user = new TextField("User");
        //     user.setWidth("50%");
        //     user.setHeight("50%");



        //     title.setValue(i.get("title"));
        //     title.setReadOnly(true);
        //     image.setTitle(i.get("image"));
        //     quantity.setValue(i.get("quantity"));
        //     quantity.setReadOnly(true);
        //     price.setValue(i.get("price"));
        //     price.setReadOnly(true);
        //     user.setValue(i.get("user"));
        //     user.setReadOnly(true);
            
        
        //     HorizontalLayout priceLayout = new HorizontalLayout();
        //     priceLayout.add(price);
        //     priceLayout.setSpacing(true);
        //     priceLayout.setAlignItems(Alignment.BASELINE);

        //     horizontalSC.addClassName("shoppingCartLayout");
        //     horizontalSC.add(image, title, quantity, price, user);
        //     horizontalSC.setJustifyContentMode(JustifyContentMode.START);
        //     horizontalSC.setAlignItems(Alignment.CENTER);

        //     purchaseHistory.add(horizontalSC);

        // }

        // add(purchaseHistory);
        

        } 


    
