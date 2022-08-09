
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

@PageTitle("Meet Ryan")
@Route(value = "aboutryan", layout = MainLayout.class)
public class RyanView extends VerticalLayout {

    public RyanView() {
        add(
            new H2("Hello My name is Ryan Murphy and i am a student at SFSU, ")            
            );
        add(new H2(" I was team lead and backend lead for this project. My role was "));
        add(new H2("To assignn tasks, Fix bugs, create the UI along with functioning backend."));
        

    }

}
