package com.example.application.views.aboutus;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin.Vertical;

@PageTitle("About Us")
@Route(value = "aboutus", layout = MainLayout.class)
public class AboutUsView extends VerticalLayout {

    public AboutUsView() {
        setSpacing(true);

        add(new H2("Meet the Software Engineering SFSU!\nSummer 2022\nSection 1\nTeam 4 "));
        // add(new Paragraph("It’s a place where you can grow your own UI 🤗"));

        Image img1 = new Image("images/icon.jpg", "placeholder icon");
        img1.setWidth("200px");
        
        NativeButton button1 = new NativeButton(
            "Ryan Murphy");
        button1.addClickListener(e ->
        button1.getUI().ifPresent(ui ->
            ui.navigate("aboutryan"))
        );
        VerticalLayout v1 = new VerticalLayout(img1, button1);

        Image img2 = new Image("images/icon.jpg", "placeholder icon");
        img2.setWidth("200px");
        NativeButton button2 = new NativeButton(
            "Duncan Forniss");
        button2.addClickListener(e ->
        button2.getUI().ifPresent(ui ->
            ui.navigate("aboutduncan"))
        );
        VerticalLayout v2 = new VerticalLayout(img2, button2);

        Image img3 = new Image("images/icon.jpg", "placeholder icon");
        img3.setWidth("200px");
        NativeButton button3 = new NativeButton(
            "Darren Wong");
        button3.addClickListener(e ->
        button3.getUI().ifPresent(ui ->
            ui.navigate("aboutdarren"))
        );
        VerticalLayout v3 = new VerticalLayout(img3, button3);

        Image img4 = new Image("images/icon.jpg", "placeholder icon");
        img4.setWidth("200px");
        NativeButton button4 = new NativeButton(
            "Brendan Leong");
        button4.addClickListener(e ->
        button4.getUI().ifPresent(ui ->
            ui.navigate("aboutbrendan"))
        );
        VerticalLayout v4 = new VerticalLayout(img4, button4);

        Image img5 = new Image("images/icon.jpg", "placeholder icon");
        img5.setWidth("200px");
        NativeButton button5 = new NativeButton(
            "Justin Wong");
        button5.addClickListener(e ->
        button5.getUI().ifPresent(ui ->
            ui.navigate("aboutjustin"))
        );
        VerticalLayout v5 = new VerticalLayout(img5, button5);
        
        var layout = new HorizontalLayout(v1, v2, v3, v4, v5);
        
        add(layout);

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
    }

}
