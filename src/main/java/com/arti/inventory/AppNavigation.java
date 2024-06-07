package com.arti.inventory;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;

public class AppNavigation extends Div{
    
    public AppNavigation(){

        SideNav portal = new SideNav();
        portal.setLabel("Portail");
        portal.setWidthFull();
        portal.addItem(
            new SideNavItem("Accueil", "", VaadinIcon.HOME.create()));

        SideNav deviceNav = new SideNav();
        deviceNav.setLabel("Equipements");
        deviceNav.addItem(
            new SideNavItem("Dashboard", "/dashboard", VaadinIcon.DASHBOARD.create()),
            new SideNavItem("Ordinateurs", "/computers", VaadinIcon.RECORDS.create()),
            new SideNavItem("Imprimantes", "/printers", VaadinIcon.RECORDS.create()),
            new SideNavItem("Téléphones IP", "/phones", VaadinIcon.RECORDS.create()));
                
        SideNav adminNav = new SideNav();
        adminNav.setLabel("Administrateur");
        adminNav.setCollapsible(true);
        adminNav.setExpanded(false);
        adminNav.addItem(
            new SideNavItem("Adresses IP", "/ip", VaadinIcon.RECORDS.create()));

        SideNav vehiculeNav = new SideNav();
        vehiculeNav.setLabel("Véhicules ARTI");
        vehiculeNav.setSizeFull();
        vehiculeNav.addItem(
            new SideNavItem("Véhicules", "/vehicules", VaadinIcon.CAR.create()),
            new SideNavItem("Kilométrages", "/mileages", VaadinIcon.ROAD.create()));

        SideNav missionNav = new SideNav();
        missionNav.setLabel("Missions ARTI");
        missionNav.setSizeFull();
        missionNav.setCollapsible(true);
        missionNav.addItem(
            new SideNavItem("Missions", "/missions", VaadinIcon.AIRPLANE.create())
            // new SideNavItem("Déplacements", "/mobilities", VaadinIcon.ROAD.create()),
            // new SideNavItem("Transports", "/transports", VaadinIcon.BUG.create())
            );

        VerticalLayout navWrapper = new VerticalLayout(portal, deviceNav, missionNav, vehiculeNav, adminNav);
        navWrapper.setSpacing(true);
        navWrapper.setSizeUndefined();
        deviceNav.setWidthFull();
        adminNav.setWidthFull();

        add(navWrapper);
    }
}
