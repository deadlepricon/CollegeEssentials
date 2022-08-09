package com.example.application.views.Items;

import java.security.Provider.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.example.application.Data.DBAccount;
import com.example.application.Data.DBAdmin;
import com.example.application.Data.DBHome;
import com.example.application.views.MainLayout;
import com.example.application.views.Account.AccountView;
import com.example.application.views.Account.ViewUser;
import com.example.application.views.Footer.FooterView;
import com.example.application.views.home.HomeView;
import com.example.application.views.login.LoginView;
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
import com.vaadin.flow.server.VaadinSession;

@CssImport("./themes/myapp/ItemView.css")
@PageTitle("Item")
@Route(value = "itemView", layout = MainLayout.class)
public class ItemView extends VerticalLayout {
  private String itemName;
  private String url;
  private String body;
  private String price;
  private String quant;
  private String product_id;
  private String seller_id;
  private HashMap<String, ArrayList<String>> items = new HashMap<>();
  private ArrayList<String> array;
  private static String userName;
  // private ItemDetails itemDetail;
  // HomeView hv = new HomeView();

  public ItemView() {

    VerticalLayout vl = new VerticalLayout();
    vl.setClassName("verticLayout");
    HorizontalLayout hl = new HorizontalLayout();
    hl.setClassName("horLayout");
    setSizeFull();
    itemName = HomeView.getTitle();
    items = HomeView.getMap();
    H3 h3 = new H3(itemName);
    vl.add(h3);
    array = items.get(itemName);
    url = array.get(0);
    body = array.get(1);
    price = array.get(2);
    quant = array.get(3);
    product_id = array.get(4);
    seller_id = array.get(5);
    String seller = "By user";
    String condition = "New";
    if (url != null) {
      Image image = new Image(url, "");

      image.setHeight("500px");
      image.setWidth("450px");
      image.setClassName("itemPostImage");
      hl.add(image);
    }

    if (price == null) {
      price = "No price was set";

    }
    if (body == null) {
      body = "No description was given";
    }

    TextArea ta = new TextArea("Description");// maybe remove title because its not center
    ta.setValue(body);
    ta.setReadOnly(true);
    ta.setClassName("DescripBox");
    vl.add(ta);
    TextField con = new TextField();
    con.setValue(condition);
    con.setReadOnly(true);
    vl.add(con);
    Div sellerLink = new Div();
    userName = DBAccount.getUserNameFromId(seller_id);
    sellerLink.add(new RouterLink("By " + userName, ViewUser.class));
    vl.add(sellerLink);
    TextField priceField = new TextField("Price");

    String temp = "$" + price;
    priceField.setValue(temp);
    priceField.setReadOnly(true);
    TextField amount = new TextField("Quantity");
    amount.setValue(quant);
    amount.setReadOnly(true);
    vl.add(amount);
    vl.add(temp);

    // initalize session
    VaadinSession currentSession = VaadinSession.getCurrent();
    Button adminDelete = new Button("Delete", eve -> {
      // delete Post;
      this.getUI().ifPresent(ui -> ui.navigate("/home"));
      DBAdmin.deletePost(itemName);
      showDeleteSuccess();

    });

    Button addToCart = new Button("Add to Cart", event -> {

      if (currentSession.getAttribute("cart") == null) {
        currentSession.setAttribute("cart", new ArrayList<HashMap<String, String>>());
      }

      HashMap<String, String> item = new HashMap<String, String>();
      item.put("title", itemName);
      item.put("image", url);
      item.put("price", price);
      item.put("quantity", "1");
      item.put("amount", quant);
      item.put("product_id", product_id);
      System.out.println(seller_id);
      item.put("seller_id", seller_id);

      ArrayList<HashMap<String, String>> cart = (ArrayList<HashMap<String, String>>) currentSession
          .getAttribute("cart");

      cart.add(item);
      currentSession.setAttribute("cart", cart);

      // add to db of cart
      this.getUI().ifPresent(ui -> ui.navigate("/home"));
      showSuccess();

    });
    // System.out.println(currentSession.getAttribute("cart"));

    addToCart.setClassName("addToCartButton");
    addToCart.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
    vl.add(addToCart);

    // checks if admin is logged in and gives the abbility to delete
    if (LoginView.logStatus()) {
      if (LoginView.isAdmin()) {
        adminDelete.setVisible(true);
        vl.add(adminDelete);
      }
      String temp2 = LoginView.getUser();
      if(LoginView.getUser().equalsIgnoreCase(userName) ){
        adminDelete.setVisible(true);
        vl.add(adminDelete);
      }

    } else {
      adminDelete.setVisible(false);
    }

    vl.setAlignItems(Alignment.CENTER);
    hl.add(vl);
    hl.setWidthFull();
    hl.setWidth("70%");
    hl.setJustifyContentMode(JustifyContentMode.AROUND);
    add(hl);

    FooterView footer = new FooterView();
    HorizontalLayout footerLay = new HorizontalLayout();
    footerLay.setClassName("FooterLayout");
    footerLay = footer.getFooter();
    add(footerLay);

    // itemName = hv.getTitle();
    // System.out.println("Item is ="+itemName);

  }

  private void showSuccess() {

    Notification notification = Notification.show("Item Was Success Fully added to Shopping cart");
    notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);

  }

  private void showDeleteSuccess() {

    Notification notification = Notification.show("Item Was Success deleted");
    notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);

  }

  public static String getUser() {
    return userName;
  }

public static void resetUser() {
  userName = null;
}

}
