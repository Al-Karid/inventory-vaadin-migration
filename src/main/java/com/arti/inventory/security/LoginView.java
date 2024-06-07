package com.arti.inventory.security;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.theme.lumo.LumoUtility;

@Route("login")
@PageTitle("SGI ARTI | Login")
@AnonymousAllowed
public class LoginView extends VerticalLayout implements BeforeEnterObserver {

    private LoginForm login = new LoginForm();
    private VerticalLayout layout = new VerticalLayout();
    private VerticalLayout formHeader = new VerticalLayout();

    public LoginView() {
        addClassName("login-view");
        addClassName("login-page");
        setSizeFull();

        setJustifyContentMode(JustifyContentMode.CENTER);
        // setAlignItems(Alignment.CENTER);

        login.setAction("login");
        login.setForgotPasswordButtonVisible(false);

        //FORM HEADER
        Image logo = new Image("images/logo.png", "SGI ARTI");
        formHeader.add(logo, new H2("SGI ARTI"));
        
        //FORM LAYOUT
        LoginI18n i18n = LoginI18n.createDefault();
        i18n.getForm().setTitle("Connexion");
        i18n.getForm().setUsername("Nom d'utilisateur");
        i18n.getForm().setPassword("Mot de passe");
        i18n.getForm().setSubmit("Se connecter");
        login.setI18n(i18n);

        layout.add(formHeader, login);
        layout.addClassName(LumoUtility.AlignSelf.CENTER);
        layout.getStyle().setBackgroundColor("white");
        layout.getStyle().setBorderRadius("5px");
        layout.setSizeUndefined();
        
        add(layout);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        if(beforeEnterEvent.getLocation()
            .getQueryParameters()
            .getParameters()
            .containsKey("error")) {
            login.setError(true);
        }
    }
}