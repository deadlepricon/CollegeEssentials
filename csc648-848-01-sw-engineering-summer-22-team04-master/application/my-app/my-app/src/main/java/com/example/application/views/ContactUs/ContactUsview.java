package com.example.application.views.ContactUs;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@PageTitle("Contact Us")
@Route(value = "contactUs", layout = MainLayout.class)
public class ContactUsview extends VerticalLayout {
    

    public ContactUsview(){
        H2 header = new H2("Contact Us");

        TextArea notes = new TextArea("");
        notes.setValue("Here are some things about the team that created this site");
        notes.setReadOnly(true);
        notes.setWidth("500px");
        notes.setHeight("500px");
    
        TextField email = new TextField("Our Email");
        email.setValue("CollegeEssentialsHelp@CollegeEssentials.com");
        email.setReadOnly(true);
        email.setWidth("500px");
        email.setHeight("200px");

        VerticalLayout layout = new VerticalLayout();
        layout.add(header,notes,email);
        layout.setAlignItems(Alignment.CENTER);
        add(layout);



    }



}
