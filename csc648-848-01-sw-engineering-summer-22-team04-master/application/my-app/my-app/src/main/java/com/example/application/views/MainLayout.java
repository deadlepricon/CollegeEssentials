package com.example.application.views;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties.Lettuce.Cluster.Refresh;

import com.example.application.views.Items.ItemView;
import com.example.application.views.Marketplace.Marketplace;
import com.example.application.views.PostItem.PostItemView;
import com.example.application.views.SellingPage.SellingPageView;
import com.example.application.views.Services.ServiceList;
import com.example.application.views.Terms.TermsOfService;
import com.example.application.views.aboutus.AboutUsView;
import com.example.application.views.forum.ForumList;
import com.example.application.views.home.HomeView;
import com.example.application.views.login.*;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.charts.model.Title;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.html.ListItem;
import com.vaadin.flow.component.html.Nav;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.html.UnorderedList;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.lumo.LumoUtility.JustifyContent;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.*;
//mvn spring-boot:run

/**
 * The main view is a top-level placeholder for other views.
 */
public class MainLayout extends AppLayout {

    /**
     * A simple navigation item component, based on ListItem element.
     */
    public static class MenuItemInfo extends ListItem {

        private final Class<? extends Component> view;

        public MenuItemInfo(String menuTitle, String iconClass, Class<? extends Component> view) {
            this.view = view;
            RouterLink link = new RouterLink();
            link.addClassNames("menu-item-link");
            link.setRoute(view);

            Span text = new Span(menuTitle);
            text.addClassNames("menu-item-text");

            link.add(new LineAwesomeIcon(iconClass), text);
            add(link);
        }

        public Class<?> getView() {
            return view;
        }

        /**
         * Simple wrapper to create icons using LineAwesome iconset. See
         * https://icons8.com/line-awesome
         */
        @NpmPackage(value = "line-awesome", version = "1.3.0")
        public static class LineAwesomeIcon extends Span {
            public LineAwesomeIcon(String lineawesomeClassnames) {
                addClassNames("menu-item-icon");
                if (!lineawesomeClassnames.isEmpty()) {
                    addClassNames(lineawesomeClassnames);
                }
            }
        }

    }

    private H1 viewTitle;

    public MainLayout() {
        setPrimarySection(Section.DRAWER);

        // H1 Title = new H1("Test");
        //Image appName = new Image("https://cdn.discordapp.com/attachments/998116033850249267/1000655208122171403/unknown.png", "Logo");
        HorizontalLayout hv = new HorizontalLayout();
        Button cart = new Button("Cart");
        cart.addClassName("cartButton");
        cart.addClickListener(e -> cart.getUI().ifPresent(ui -> ui.navigate("/ShoppingCart")));
        Button log = new Button("Log in");
        log.addClassName("logInButton");
        log.addClickListener(e -> log.getUI().ifPresent(ui -> ui.navigate("/login")));
        Button reg = new Button("Register");
        reg.addClassName("regButton");
        reg.addClickListener(e -> reg.getUI().ifPresent(ui -> ui.navigate("/registration")));
        Button account = new Button("Account",e->{
            this.getUI().ifPresent(ui -> ui.navigate("/Account"));
        });
        account.setClassName("accountButton");

        Button logout = new Button("Log Out");
        logout.addClassName("logOutButton");

        Button admin = new Button("Admin",even ->{
            this.getUI().ifPresent(ui -> ui.navigate("/Admin"));
        });
        admin.addClassName("logOutButton");


        // change login and logout buttons
        if (LoginView.logStatus() == false) {
            // hv.add(log, reg);
            logout.setVisible(false);
            account.setVisible(false);
            admin.setVisible(false);
            hv.add(cart);

        } else {
            log.setVisible(false);
            reg.setVisible(false);
            if(!LoginView.isAdmin()){
                admin.setVisible(false);
            }
           
            logout.addClickListener(e -> {
                LoginView.logOut();
                 logout.setVisible(false);
                 account.setVisible(false);
                 admin.setVisible(false);
                 log.setVisible(true);
                 reg.setVisible(true);
                 
               this.getUI().ifPresent(ui -> ui.navigate(
                HomeView.class));
           
            }); 
           
        }
        hv = new HorizontalLayout(log,reg,admin,account,logout, cart);

        hv.addClassNames("hv");
        hv.setHeight("3.5em");
        hv.setAlignItems(Alignment.CENTER);
        hv.setPadding(true);
        addToNavbar(true, createHeaderContent(), hv);
        addToDrawer(createDrawerContent());
        
    }

    public HorizontalLayout setUnLogin() {
        Button log = new Button("Log in");
        log.addClassName("logInButton");
        log.addClickListener(e -> log.getUI().ifPresent(ui -> ui.navigate("/login")));
        Button reg = new Button("Register");
        reg.addClassName("regButton");
        reg.addClickListener(e -> reg.getUI().ifPresent(ui -> ui.navigate("/registration")));
        return new HorizontalLayout(log, reg);
    }

    private Component createHeaderContent() {
        DrawerToggle toggle = new DrawerToggle();
        toggle.addClassNames("view-toggle");
        toggle.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
        toggle.getElement().setAttribute("aria-label", "Menu toggle");

        viewTitle = new H1();// posible add name
        viewTitle.setHeight("1.8em");
        viewTitle.addClassNames("view-title");

        Header header = new Header(toggle, viewTitle);
        header.addClassNames("view-header");
        return header;
    }

    private Component createDrawerContent() {
        // H2 appName = new H2("College Essentials");
        
        Image appName = new Image("https://cdn.discordapp.com/attachments/998116033850249267/1000655208122171403/unknown.png", "Logo");
        appName.addClickListener(e -> appName.getUI().ifPresent(ui -> ui.navigate("/home")));
        appName.addClassNames("app-name");

        com.vaadin.flow.component.html.Section section = new com.vaadin.flow.component.html.Section(appName,
                createNavigation(), createFooter());
        section.addClassNames("drawer-section");
        return section;
    }

    private Nav createNavigation() {
        Nav nav = new Nav();
        nav.addClassNames("menu-item-container");
        nav.getElement().setAttribute("aria-labelledby", "views");

        // Wrap the links in a list; improves accessibility
        UnorderedList list = new UnorderedList();
        list.addClassNames("navigation-list");
        nav.add(list);

        for (MenuItemInfo menuItem : createMenuItems()) {
            list.add(menuItem);

        }
        return nav;
    }

    private MenuItemInfo[] createMenuItems() {
        return new MenuItemInfo[] { //
                new MenuItemInfo("Home", "la la-home", HomeView.class),
                // added for registration
                // new MenuItemInfo("Marketplace", "la la-adjust", Marketplace.class),
                new MenuItemInfo("Sell an Item", "la la-shopping-cart", PostItemView.class),
               // new MenuItemInfo("Buy", "la la-adjust", SellingPageView.class),
                new MenuItemInfo("Forum Listings", "la la-list", ForumList.class),
                new MenuItemInfo("Services Listings", "la la-paste", ServiceList.class),
                
        };
    }

    private Footer createFooter() {
        Footer layout = new Footer();
        layout.addClassNames("footer");
        VerticalLayout vLayout = new VerticalLayout();
        Div aboutus = new Div();
        aboutus.add(new RouterLink("About Us", AboutUsView.class));
        Div terms = new Div();
        terms.add(new RouterLink("Terms of Service", TermsOfService.class));
        vLayout.add(aboutus, terms);
        layout.add(vLayout);

        return layout;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        viewTitle.setText(getCurrentPageTitle());
    }

    private String getCurrentPageTitle() {
        PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
        return title == null ? "" : title.value();
    }
}
