package com.arti.inventory;

import org.springframework.beans.factory.annotation.Autowired;

import com.arti.inventory.security.SecurityService;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.dom.Style.AlignSelf;

public class MainAppLayout extends AppLayout {

    H1 title;
    Button logout;
    Scroller scroller;
    DrawerToggle toggle;
    AppNavigation nav;
    HorizontalLayout navLayout;
    HorizontalLayout navLayoutLeftContainer;

    @Autowired
    SecurityService securityService;

    public MainAppLayout(){
        toggle = new DrawerToggle();
        title = createAppTitle();

        scroller = new Scroller(new AppNavigation());
        logout = createLogoutButton();

        navLayout = createNavLayout();
        navLayoutLeftContainer = createNavLayoutLeftContainer();
        navLayout.add(navLayoutLeftContainer, logout);

        addToDrawer(scroller);
        addToNavbar(navLayout);
    }

    private Button createLogoutButton() {
        logout = new Button("Déconnexion");
        logout.getStyle().setAlignSelf(AlignSelf.END);
        logout.getStyle().setMarginRight("1em");
        logout.addClickListener(e -> {
            securityService.logout();
        });
        return logout;
    }

    private H1 createAppTitle() {
        title = new H1("SGI ARTI");
        title.getStyle().set("font-size", "var(--lumo-font-size-l)").set("margin", "0");
        return title;
    }

    private HorizontalLayout createNavLayoutLeftContainer() {
        var navLayoutItem1 = new HorizontalLayout(toggle, title);
        navLayoutItem1.setAlignItems(Alignment.CENTER);
        navLayoutItem1.setSpacing(false);
        return navLayoutItem1;
    }

    private HorizontalLayout createNavLayout() {
        navLayout = new HorizontalLayout();
        navLayout.setSizeFull();
        navLayout.setSpacing(false);
        navLayout.setJustifyContentMode(JustifyContentMode.BETWEEN);
        navLayout.setAlignItems(Alignment.CENTER);
        return navLayout;
    }
}
