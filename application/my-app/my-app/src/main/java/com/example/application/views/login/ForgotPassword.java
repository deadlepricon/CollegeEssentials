package com.example.application.views.login;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;


@Route(value = "forgotPassword")
public class ForgotPassword extends VerticalLayout{

    private Span passwordStrengthText;
    private Icon checkIcon;
    private Icon checkIcon2;
    private Boolean isLength;
   private Boolean isPassMatch;

    public ForgotPassword(){
        VerticalLayout layout = new VerticalLayout();
        layout.setClassName("loginVertLayout");
        

        H1 header = new H1("Forgot Password");
        header.addClassName("title-header");
        layout.add(header);

        TextField newPassword = new TextField("New Password");
        newPassword.setWidth("60%");
        newPassword.setPlaceholder("Enter new password here...");
        Div passwordStrength = new Div();
        passwordStrengthText = new Span();
        passwordStrength.add(new Text(""), passwordStrengthText);
        newPassword.setHelperComponent(passwordStrength);
        checkIcon = VaadinIcon.CHECK.create();
         checkIcon.setVisible(false);
         checkIcon.getStyle().set("color", "var(--lumo-success-color)");
        newPassword.setSuffixComponent(checkIcon);
        layout.add(newPassword);

        newPassword.setValueChangeMode(ValueChangeMode.EAGER);
        newPassword.addValueChangeListener(e -> {
        String passwordValue = e.getValue();
        updatePasswordHelper(passwordValue);
         });



        TextField reTypePassword = new TextField("Re enter password");
        reTypePassword.setWidth("60%");
        reTypePassword.setPlaceholder("re type password here...");
        Div passwordMatch = new Div();
        passwordStrengthText = new Span();
        passwordStrength.add(new Text(""), passwordStrengthText);
        reTypePassword.setHelperComponent(passwordMatch);
        checkIcon2 = VaadinIcon.CHECK.create();
         checkIcon2.setVisible(false);
         checkIcon2.getStyle().set("color", "var(--lumo-success-color)");
        reTypePassword.setSuffixComponent(checkIcon2);
        
        
        layout.add(reTypePassword);
        
        
        reTypePassword.setValueChangeMode(ValueChangeMode.EAGER);
        reTypePassword.addValueChangeListener(ev -> {
        String conPassword = ev.getValue();
        updatePasswordConfirmHelper(conPassword,newPassword.getValue());
        });

        Button submitButton = new Button("Submit", event->{
            if(isLength && isPassMatch){
                this.getUI().ifPresent(ui ->
                ui.navigate("/home"));  
                showSuccess();
            }else{

            }
        });
        submitButton.setClassName("loginButton");

        layout.add(submitButton);

        layout.setAlignItems(Alignment.CENTER);

        add(layout);

        





    }



    public void updatePasswordHelper(String pass){
        if(pass.length() >= 8){
                passwordStrengthText.setText("Password passes");
                passwordStrengthText.getStyle().set("color", "var(--lumo-success-color)");
                checkIcon.setVisible(true);
                isLength = true;
                
        }else{
                passwordStrengthText.setText("Password Must be at least 8 chars");
                passwordStrengthText.getStyle().set("color", "var(--lumo-error-color)");
                checkIcon.setVisible(false);
                isLength = false;
                
        }
   }


   private void updatePasswordConfirmHelper(String conPassword, String pass) {

    if(conPassword.equals(pass)){
            passwordStrengthText.setText("Passwords Match");
            passwordStrengthText.getStyle().set("color", "var(--lumo-success-color)");
            checkIcon2.setVisible(true);
            isPassMatch = true;
    }else{
            passwordStrengthText.setText("Passwords Must Match");
            passwordStrengthText.getStyle().set("color", "var(--lumo-error-color)");
            checkIcon2.setVisible(false);
            isPassMatch = false;
    }

}

private void showSuccess() {
    Notification notification =
            Notification.show("Password Was successfully changed!");
    notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);

}
    
}
