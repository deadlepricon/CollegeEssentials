package com.example.application.views.registration;
import com.example.application.Data.DBConnection;
import com.example.application.Data.UserDetails;
import com.vaadin.flow.component.HasValueAndElement;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.stream.Stream;

import javax.swing.event.HyperlinkEvent;

import org.apache.poi.common.usermodel.Hyperlink;
import org.apache.poi.hslf.record.InteractiveInfoAtom.Link;

@PageTitle("Registration")
@Route(value = "registration")
@CssImport("./themes/myapp/Registration.css")
public class RegistrationForm extends FormLayout {

   private H2 title;

   private TextField firstName;
   private TextField lastName;
   private TextField school;

   private EmailField email;
   private TextField userName;
   private PasswordField password;
   private PasswordField passwordConfirm;
   private PasswordField passwordHashed;
   private Span passwordStrengthText;
   private Boolean isPassLenght;
   private Boolean isPassMatch;
   private Icon checkIcon;
   private Icon checkIcon2;
   private Checkbox allowMarketing;

   private Span errorMessageField;

   private Button submitButton;
   private UserDetails userBean = new UserDetails();


   public RegistrationForm() {
       this.getStyle().set("margin", "auto");
       this.getStyle().set("margin-top", "100px");
       this.getStyle().set("border", "2px solid black");
       this.getStyle().set("padding-left", "150px");
       this.getStyle().set("padding-right", "150px");
       this.getStyle().set("padding-top", "50px");
       this.getStyle().set("padding-bottom", "80px");
       this.getStyle().set("background-color", "whitesmoke");

       title = new H2("Signup for College Essentials");
       title.addClassName("title-header");
       firstName = new TextField("First name");
       firstName.addClassName("first-name");
       lastName = new TextField("Last name");
       lastName.addClassName("last-name");
       email = new EmailField("Email");
       email.addClassName("email");
       school = new TextField("University");
       school.addClassName("school");
       userName = new TextField("Username");
       userName.addClassName("username");
       userName.setWidth("20em");
       
        Button terms = new Button("Terms & Services",e ->{
                this.getUI().ifPresent(ui -> ui.navigate("/termView"));
        });
        terms.addClassName("terms");

        

       allowMarketing = new Checkbox("Agree To Terms & Services?");
       
       allowMarketing.getStyle().set("margin-top", "10px");

       //password checker
       password = new PasswordField("Password");
       Div passwordStrength = new Div();
        passwordStrengthText = new Span();
        passwordStrength.add(new Text(""), passwordStrengthText);
        password.setHelperComponent(passwordStrength);
        checkIcon = VaadinIcon.CHECK.create();
         checkIcon.setVisible(false);
         checkIcon.getStyle().set("color", "var(--lumo-success-color)");
        password.setSuffixComponent(checkIcon);

        password.setValueChangeMode(ValueChangeMode.EAGER);
        password.addValueChangeListener(e -> {
        String passwordValue = e.getValue();
        updatePasswordHelper(passwordValue);
        });
        
        //confirm password
       passwordConfirm = new PasswordField("Confirm password");
       Div passwordMatch = new Div();
        passwordStrengthText = new Span();
        passwordStrength.add(new Text(""), passwordStrengthText);
        passwordConfirm.setHelperComponent(passwordMatch);
        checkIcon2 = VaadinIcon.CHECK.create();
         checkIcon2.setVisible(false);
         checkIcon2.getStyle().set("color", "var(--lumo-success-color)");
        passwordConfirm.setSuffixComponent(checkIcon2);

        passwordConfirm.setValueChangeMode(ValueChangeMode.EAGER);
        passwordConfirm.addValueChangeListener(ev -> {
        String conPassword = ev.getValue();
        updatePasswordConfirmHelper(conPassword,password.getValue());
        });


       setRequiredIndicatorVisible(firstName, lastName, email, userName,password,
               passwordConfirm);

       errorMessageField = new Span();

       submitButton = new Button("Create Account");
       submitButton.addClassName("submit-reg");

       add(title, firstName, lastName, userName,school, email, password,
               passwordConfirm, allowMarketing,terms, errorMessageField,
               submitButton);

       // Max width of the Form
       setMaxWidth("500px");


       // Allow the form layout to be responsive.
       // On device widths 0-490px we have one column.
       // Otherwise, we have two columns.
       setResponsiveSteps(
               new ResponsiveStep("0", 1, ResponsiveStep.LabelsPosition.TOP),
               new ResponsiveStep("490px", 2, ResponsiveStep.LabelsPosition.TOP));

       // These components always take full width
       setColspan(title, 2);
       setColspan(email, 2);
       setColspan(errorMessageField, 2);
       setColspan(submitButton, 2);

//        RegistrationFormBinder registrationFormBinder = new RegistrationFormBinder(this);
//        registrationFormBinder.addBindingAndValidation();

       submitButton.addClickListener(ev ->{
        

        
        if(firstName.getValue() != "" && lastName.getValue() != "" && userName.getValue() != "" && password.getValue() != "" && passwordConfirm.getValue() != "" && school.getValue() != "" && email.getValue() != ""
        && isPassLenght && isPassMatch){
           DBConnection db = new DBConnection();
        storeUserInfo();
        

        db.StoreRegUser(userBean.getFirstName(), userBean.getLastName(), userBean.getEmail(), userBean.getPassword(),userBean.getSchool(),userBean.getUserName());
        showSuccess(userBean);
        this.getUI().ifPresent(ui ->
               ui.navigate("/login"));     
        }else{
                showFail(userBean);
        }
        

       });
       

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



public void updatePasswordHelper(String pass){
        if(pass.length() >= 8){
                passwordStrengthText.setText("Password passes");
                passwordStrengthText.getStyle().set("color", "var(--lumo-success-color)");
                checkIcon.setVisible(true);
                isPassLenght = true;
        }else{
                passwordStrengthText.setText("Password Must be at least 8 chars");
                passwordStrengthText.getStyle().set("color", "var(--lumo-error-color)");
                checkIcon.setVisible(false);
                isPassLenght = false;
        }
   }
 
   public PasswordField getPasswordField() { return password; }

   public PasswordField getPasswordConfirmField() { return passwordConfirm; }

   public Span getErrorMessageField() { return errorMessageField; }

   public Button getSubmitButton() { return submitButton; }

   private void setRequiredIndicatorVisible(HasValueAndElement<?, ?>... components) {
       Stream.of(components).forEach(comp -> comp.setRequiredIndicatorVisible(true));
   }

   private void storeUserInfo(){
        userBean.setFirstName(firstName.getValue());
        userBean.setLastName(lastName.getValue());
        userBean.setEmail(email.getValue());
        userBean.setPassword(password.getValue());
        userBean.setUserName(userName.getValue());
        userBean.setSchool(school.getValue());
   }

   private void showSuccess(UserDetails userBean) {
        Notification notification =
                Notification.show("Account Created, welcome " + userBean.getFirstName());
        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
      
    }

    private void showFail(UserDetails userBean) {
        Notification notification =
                Notification.show("There is some missing info");
        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
      
    }

}