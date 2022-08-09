package com.example.application.views.checkout;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin.Horizontal;
import com.vaadin.flow.component.dependency.StyleSheet;


import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;



@CssImport("./themes/myapp/checkout.css")

@PageTitle("Checkout")//maybe make to name of web
@Route(value = "checkout", layout = MainLayout.class)

public class checkout extends HorizontalLayout{

    String title;
    String image;
    String price;
    String quantity;

    public checkout(){

        //intialize session
        VaadinSession currentSession = VaadinSession.getCurrent();

        if(currentSession.getAttribute("cart") == null){
            currentSession.setAttribute("cart", new ArrayList<HashMap<String, String>>());
          }

        ArrayList<HashMap<String, String>> cart = (ArrayList<HashMap<String, String>>) currentSession.getAttribute("cart");

        VerticalLayout shoppingCartItems = new VerticalLayout();
        H3 h1 = new H3("Shopping Cart");
        h1.addClassName("h3");

        Button checkout = new Button("checkout", e ->{
            this.getUI().ifPresent(ui -> ui.navigate("/checkout"));
        });
        checkout.setClassName("button");


       // Horizontal layout for shopping cart items
        shoppingCartItems.add(h1);
        for (HashMap<String, String> i: cart){
            HorizontalLayout horizontalSC = new HorizontalLayout();
           

            horizontalSC.setWidthFull();

            String cartUrl = i.get("image");
            
            TextArea title = new TextArea("Name");

            Image image = new Image(cartUrl,"This is the cart image");
            image.setHeight("150px");
            image.setWidth("150px");

            TextArea quantity = new TextArea("Quantity");

            TextArea price = new TextArea("Price");

            Button removeFromCart = new Button("Remove from Cart");
            removeFromCart.setClassName("button");


            title.setValue(i.get("title"));
            title.setReadOnly(true);
            image.setTitle(i.get("image"));
            quantity.setValue(i.get("quantity"));
            quantity.setReadOnly(true);
            price.setValue("$" + i.get("price"));
            price.setReadOnly(true);
            

            horizontalSC.addClassName("shoppingCartLayout");
            horizontalSC.add(image, title, quantity, price);
            horizontalSC.setJustifyContentMode(JustifyContentMode.START);
            horizontalSC.setAlignItems(Alignment.CENTER);

            shoppingCartItems.add(horizontalSC);
            shoppingCartItems.setSizeUndefined();

        }


        //return to cart button
        Button returnToCart = new Button("Return To Cart", e ->{
            this.getUI().ifPresent(ui -> ui.navigate("/ShoppingCart"));
        });
        returnToCart.setClassName("button");

        shoppingCartItems.add(returnToCart);
        add(shoppingCartItems);


        //Vertical layout for payment items
        VerticalLayout payment = new VerticalLayout();

        H3 paymentHeader = new H3("Payment");
        paymentHeader.setClassName("h3");
        payment.add(paymentHeader);

        VerticalLayout paymentBox = new VerticalLayout();
        paymentBox.setClassName("verticalLayout");

        float priceTotal = 0; 

        for (HashMap<String,String> i:cart){

            TextArea title = new TextArea("Name");
            title.setValue(i.get("title"));
            title.setReadOnly(true);

            TextArea price = new TextArea("Final Price");
            // Float finalPrice = Float.parseFloat(i.get("quantity"))  * Float.parseFloat(i.get("price"));

            // price.setValue(String.valueOf(finalPrice));
            // price.setReadOnly(true);

            if (StringUtils.isNumeric(i.get("price")))
            {
            Float finalPrice = Float.parseFloat(i.get("quantity"))  * Float.parseFloat(i.get("price"));

            price.setValue("$" + String.valueOf(finalPrice));
            priceTotal += finalPrice; 
            }else{
                price.setValue("No price was set.");
            }
            
            price.setReadOnly(true);

            HorizontalLayout priceList = new HorizontalLayout();
            priceList.add(title, price);

            paymentBox.add(priceList);

        }

        TextArea totalPrice = new TextArea("Total Price");
        totalPrice.setValue("$" + String.valueOf(priceTotal));
        totalPrice.setReadOnly(true);

        paymentBox.add(totalPrice);
        payment.add(paymentBox);


        add(payment);
        payment.addClassName("box");


        //Vertical layout for user information
        VerticalLayout userInfo = new VerticalLayout();
        H3 h3 = new H3("Reveiw your order");
        h3.addClassName("h3");


        VerticalLayout reviewBox = new VerticalLayout();
        reviewBox.addClassName("verticalLayout");

        //shipping address layout 
        HorizontalLayout shippingAddressLayout = new HorizontalLayout();
        TextArea shippingAddress = new TextArea("Shipping Address:");
        shippingAddress.setValue("Enter your shipping address with 'change'.");

        // Button changeShippingAddress = new Button("change");
        // changeShippingAddress.setClassName("button");
        shippingAddressLayout.add(shippingAddress);
        shippingAddressLayout.setAlignItems(Alignment.BASELINE);





        //paymentMethodLayout horizontal layout
        HorizontalLayout paymentMethodLayout = new HorizontalLayout();
        TextArea paymentMethod = new TextArea("Payment Method:");
        paymentMethod.setValue("Enter your payment method with 'change'.");

        // Button changePayment = new Button("change");
        // changePayment.setClassName("button");
        paymentMethodLayout.add(paymentMethod);
        paymentMethodLayout.setAlignItems(Alignment.BASELINE);





        //billingAddressLayout
        HorizontalLayout billingAddressLayout = new HorizontalLayout();
        TextArea billingAddress = new TextArea("Billing address:");
        // if billing address == shipping address
        // setValue = "same as shipping address"
        
        billingAddress.setValue("Enter your billing address with 'change'.");

        // Button changeBillingAddress = new Button("change");
        // changeBillingAddress.setClassName("button");
        
        billingAddressLayout.add(billingAddress);
        billingAddressLayout.setAlignItems(Alignment.BASELINE);

        //Place your order button
        Button placeYourOrder = new Button("Place Your Order", e->{
            currentSession.setAttribute("cart", new ArrayList<HashMap<String, String>>());
            this.getUI().ifPresent(ui -> ui.navigate("/home"));
            Notification notification =
            Notification.show("Order has been placed!");
            notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
        });
        placeYourOrder.setClassName("button");

        reviewBox.add(shippingAddressLayout, paymentMethodLayout, billingAddressLayout, placeYourOrder);


        //add all to vertical layout
        userInfo.add(h3, reviewBox);
        add(userInfo);
        userInfo.addClassName("box");
        userInfo.setSizeUndefined();
        

        } 


    }