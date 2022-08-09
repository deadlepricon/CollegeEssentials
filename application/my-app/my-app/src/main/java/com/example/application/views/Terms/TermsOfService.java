
package com.example.application.views.Terms;

import com.example.application.views.MainLayout;
import com.microsoft.schemas.office.office.impl.HrpctAttributeImpl;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.grid.Grid;

import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import java.util.ArrayList;
import java.util.HashMap;



@PageTitle("Terms Of Service")
@Route(value = "tos", layout = MainLayout.class)
public class TermsOfService extends VerticalLayout{

    public TermsOfService(){
        H2 header = new H2("Terms of Service");

        TextArea tos = new TextArea("");
        tos.setValue("Terms of Service.");
        tos.setReadOnly(true);
        tos.setWidth("500px");
        tos.setHeight("500px");
    

        VerticalLayout layout = new VerticalLayout();
        layout.add(header, tos);
        layout.setAlignItems(Alignment.CENTER);
        add(layout);



    }



}

