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

@PageTitle("Meet Darren")
@Route(value = "aboutdarren", layout = MainLayout.class)
public class DarrenView extends VerticalLayout {

    public DarrenView() {
        add(new H2("I see you have found this page, nice! Here's a a cookie c:"));
        add(new H2("Note: Unfortunately we didn't have the time to style and format the about pages. Will be done in the future"));
        
        add(new H2("Hello, my name is Darren Wong, and I'm a CS major at SFSU graduating in Fall 2022 "));
        add(new H2("My main role for this project was being the database master, as well as working as an assistant on both"));
        add(new H2("the backend and some frontend on this project. Thank you! "));
    }
}

