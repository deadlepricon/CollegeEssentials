package com.example.application.views.home;

import com.example.application.Data.DBHome;
import com.example.application.Data.HomeDetail;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;

public class HomeBinder {
    HomeView homeView;
    public HomeBinder(HomeView home){
        homeView = home;
    }

    public void addHomeBinder(){
        BeanValidationBinder<HomeDetail> binder = new BeanValidationBinder<>(HomeDetail.class);
        binder.bindInstanceFields(homeView);

        homeView.getSearchButton().addClickListener(event ->{
            try {
                // Create empty bean to store the details into

                
                DBHome db = new DBHome();
                
                HomeDetail userBean = new HomeDetail();
 
                // Run validators and write the values to the bean
                binder.writeBean(userBean);
                //db.StorePostItem(userBean.getTitle(), userBean.getUrl(), userBean.getCondition());
                homeView.getUI().ifPresent(ui ->
                ui.navigate("/login"));
                // Typically, you would here call backend to store the bean
 
                // Show success message if everything went well
                //showSuccess(userBean);
            } catch (ValidationException exception) {
                // validation errors are already visible for each field,
                // and bean-level errors are shown in the status label.
                // We could show additional messages here if we want, do logging, etc.
            }
        });
    }
    
}
