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

@PageTitle("Meet Justin")
@Route(value = "aboutjustin", layout = MainLayout.class)
public class JustinView extends VerticalLayout {

    public JustinView() {

        add(new H2("Hello, my name is Justin Wong, and I'm a CS major at SFSU graduating in Fall 2022."));
        add(new H2("My jobs for this project was being the Git Master as well as working on some front end and backend."));
        add(new H2("Thank you for chekcing out our project!"));
    }

}


