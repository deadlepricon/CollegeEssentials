package com.example.application.views.Marketplace;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.application.views.MainLayout;
import com.example.application.views.Account.AccountView;
import com.example.application.views.Marketplace.Marketplace;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin.Horizontal;

@CssImport("./themes/myapp/ItemView.css")
@PageTitle("Item")
@Route(value = "marketItemView", layout = MainLayout.class)
public class marketPlaceItemView extends VerticalLayout{
    private String itemName;
    private HashMap<String,ArrayList<String>> items = new HashMap<>();
    private ArrayList<String> array;
   // private ItemDetails itemDetail;
   // HomeView hv = new HomeView();

  
    public marketPlaceItemView(){
      System.out.println("1");
        VerticalLayout vl = new VerticalLayout();
        vl.setClassName("verticLayout");
        HorizontalLayout hl = new HorizontalLayout();
        hl.setClassName("horLayout");
        setSizeFull();
       itemName = Marketplace.getTitle();
       System.out.println(itemName);
       items = Marketplace.getMap();
       H3 h3 = new H3(itemName);
       vl.add(h3);
       array = items.get(itemName);
       String url = array.get(0);
       String body = array.get(1);
       String price = array.get(2);
       String seller = "By user";
       String condition = "New";
       if(url != null){
        Image image = new Image(url,"");

        image.setHeight("500px");
        image.setWidth("450px");
        image.setClassName("itemPostImage");
        hl.add(image);
       }
       if(price == null || body == null){
        price = "No price was set";
        body = "No description was given";
       }
       TextArea ta = new TextArea("Description");//maybe remove title because its not center
       ta.setValue(body);
       ta.setReadOnly(true);
       ta.setClassName("DescripBox");
       vl.add(ta);
       TextField con = new TextField();
       con.setValue(condition);
       con.setReadOnly(true);
       vl.add(con);
       Div sellerLink = new Div();
      sellerLink.add(new RouterLink(seller, AccountView.class));
       vl.add(sellerLink);
       TextField priceField = new TextField("Price");
       priceField.setValue(price);
       priceField.setReadOnly(true);
       vl.add(price);
       
       Button addToCart = new Button("Add to Cart",event ->{
        //add to db of cart
        this.getUI().ifPresent(ui -> ui.navigate("/home"));
        showSuccess();

       });
       addToCart.setClassName("addToCartButton");
       addToCart.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
       vl.add(addToCart);
        vl.setAlignItems(Alignment.CENTER);
        hl.add(vl);
        hl.setWidthFull();
        hl.setWidth("70%");
        hl.setJustifyContentMode(JustifyContentMode.AROUND);
        add(hl);
       
       
      //  itemName = hv.getTitle();
        // System.out.println("Item is ="+itemName);
        
    }

    
    private void showSuccess() {
      Notification notification =
              Notification.show("Item Was Success Fully added to Shopping cart");
      notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
      

      // Here you'd typically redirect the user to another view
  }

    
    
}
