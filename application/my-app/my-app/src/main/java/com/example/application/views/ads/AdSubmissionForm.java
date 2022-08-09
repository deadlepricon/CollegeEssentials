package com.example.application.views.ads;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.example.application.views.Footer.FooterView;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin.Horizontal;
import com.vaadin.flow.component.dependency.StyleSheet;


import java.util.ArrayList;
import java.util.HashMap;



@CssImport("./themes/myapp/ad.css")
@PageTitle("Ad Submission Form")//maybe make to name of web
@Route(value = "AdSubmissionForm", layout = MainLayout.class)
public class AdSubmissionForm extends HorizontalLayout{

    public AdSubmissionForm(){

        VerticalLayout submission = new VerticalLayout();

        VerticalLayout submissionForm = new VerticalLayout();
        submissionForm.addClassName("verticalLayout");
        
        H3 submissionHeader = new H3("Ad Submission Form");
        submissionHeader.addClassName("h3");

        TextArea title = new TextArea("Title");
        title.setWidth("500px");
        title.setMinHeight("100px");

        TextArea what = new TextArea("What are you advertising");
        what.setWidthFull();
        what.setMinHeight("200px");

        TextArea date = new TextArea("Time/Date");
        date.setWidthFull();
        date.setMinHeight("100px");

        TextArea reach = new TextArea("Reach");
        reach.setWidthFull();
        reach.setMinHeight("100px");

        Button submitForm = new Button("Submit Form");
        submitForm.setClassName("button");


        title.setValue("This is my Ad Name");
        what.setValue("This is what i'm going to be advertising. This section will be longer than others.");
        date.setValue("7/18/2022");
        reach.setValue("This is the audience that we are trying to reach");


        submissionForm.add(title, what, date, reach, submitForm);
        submission.add(submissionHeader, submissionForm);



        VerticalLayout adInfo = new VerticalLayout();
        VerticalLayout rules = new VerticalLayout();
        rules.addClassName("verticalLayout");
        
    

        H3 infoHeader = new H3("Ad Info");
        infoHeader.addClassName("h3");

        TextArea info = new TextArea("Ad Info");
        info.setValue("This is the rules for the ad. \n 1. Rule \n 2. Rule \n 3. Rule");
        info.setReadOnly(true);
        info.setWidth("500px");
        info.setMinHeight("400px");


        rules.add(info);
        adInfo.add(infoHeader, rules);

        
        FooterView footer = new FooterView();
        HorizontalLayout footerLayout = footer.getFooter();
        


        HorizontalLayout topLayout = new HorizontalLayout();
        topLayout.add(submission, adInfo);




        VerticalLayout pageLayout = new VerticalLayout();
        pageLayout.add(topLayout, footerLayout);
        pageLayout.setAlignItems(Alignment.CENTER);

        add(pageLayout);



    }

    
}
