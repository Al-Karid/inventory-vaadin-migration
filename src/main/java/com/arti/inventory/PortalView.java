package com.arti.inventory;

import java.util.ArrayList;
import java.util.Collection;

import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.theme.lumo.LumoUtility;


@Route(value = "")
@PageTitle("SGI ARTI | Portail")
@AnonymousAllowed
public class PortalView extends VerticalLayout {

    VerticalLayout itemContainer;
    VerticalLayout externalAppsContainer;

    public PortalView() {
        addClassName("portal-view");

        HorizontalLayout logoLayout = new HorizontalLayout();
        Image logo = new Image("images/logo.png", "SGI ARTI");
        logoLayout.setPadding(true);
        logoLayout.add(logo);

        externalAppsContainer = getInitializedContainer();
        Collection<PortalItemList> externalApps = new ArrayList<>();
        // externalApps.add(new PortalItemList("SGI ARTI", "http://localhost:8080/dashboard", VaadinIcon.DASHBOARD.create()));
        externalApps.add(new PortalItemList("SYGFP", "http://finances.arti.ci", VaadinIcon.MONEY.create(), "_blank"));
        externalApps.add(new PortalItemList("Emeraude", "http://courrier.arti.ci", VaadinIcon.ENVELOPES.create(), "_blank"));
        externalApps.add(new PortalItemList("Gestion des Congés et Absences", "https://tinyurl.com/arti-conges", VaadinIcon.COMPILE.create(), "_blank"));
        externalApps.add(new PortalItemList("Gestion des Equipements", "/dashboard", VaadinIcon.RECORDS.create()));
        externalApps.add(new PortalItemList("Gestion des Véhicules", "https://tinyurl.com/arti-vehicules", VaadinIcon.CAR.create(), "_blank"));
        externalApps.add(new PortalItemList("Gestion des Missions", "/missions", VaadinIcon.AIRPLANE.create()));
        externalApps.add(new PortalItemList("Gestion des Immobilisations", "http://srv-immo.arti.local/arti/public/", VaadinIcon.BUILDING.create(), "_blank"));
        externalApps.add(new PortalItemList("Logiciel de Santé MCI", "http://isanet.mcicareci.com:8036/", VaadinIcon.HEALTH_CARD.create(), "_blank"));
        externalApps.forEach(item -> addToExternalAppsContainer(item));
        
        itemContainer = getInitializedContainer();
        Collection<PortalItemList> items = new ArrayList<>();
        items.add(new PortalItemList("Accueil", "/", VaadinIcon.HOME.create()));
        items.add(new PortalItemList("Dashboard", "/dashboard", VaadinIcon.DASHBOARD.create()));
        items.add(new PortalItemList("Ordinateurs", "/computers", VaadinIcon.RECORDS.create()));
        items.add(new PortalItemList("Imprimantes", "/printers", VaadinIcon.RECORDS.create()));
        items.add(new PortalItemList("Téléphones", "/phones", VaadinIcon.RECORDS.create()));
        items.add(new PortalItemList("Véhicules", "/vehicules", VaadinIcon.CAR.create()));
        items.add(new PortalItemList("Kilométrages", "/mileages", VaadinIcon.ROAD.create()));
        items.add(new PortalItemList("Missions", "/missions", VaadinIcon.AIRPLANE.create()));
        items.forEach(item -> addToItemsContainer(item));

        MasterContent masterContent = new MasterContent();
        DetailsContent detailsContent = new DetailsContent();
        detailsContent.add(logoLayout, externalAppsContainer);
        SplitLayout splitLayout = new SplitLayout(masterContent, detailsContent);
        splitLayout.setSplitterPosition(70);
        splitLayout.setHeightFull();

        setSizeFull();
        addClassName(LumoUtility.JustifyContent.CENTER);
        add(splitLayout);
    }

    private VerticalLayout getInitializedContainer() {
        VerticalLayout container = new VerticalLayout();
        container.setSpacing(true);
        container.setPadding(true);
        container.getStyle().set("border-radius", "5px");
        // container.setClassName(LumoUtility.FlexWrap.WRAP);
        return container;
    }

    private void addToItemsContainer(PortalItemList item) {
        itemContainer.add(item);
        itemContainer.setFlexGrow(1, item);
    }

    private void addToExternalAppsContainer(PortalItemList item) {
        externalAppsContainer.add(item);
        externalAppsContainer.setFlexGrow(1, item);
    }

    private class MasterContent extends VerticalLayout{
        public MasterContent(){
            H1 title = new H1("PORTAIL ARTI");
            title.getStyle().set("color", "white");
            title.getStyle().setFont("bold 50px oswald");
            Span subtitle1 = createTexte("Bienvenue sur le portail de l'Autorité de Régulation du Transport Intérieur.");
            subtitle1.getStyle().setFont("bold 40px Arial, sans-serif");
            subtitle1.setWidth("80%");
            addClassName(LumoUtility.JustifyContent.CENTER);
            addClassName(LumoUtility.AlignItems.CENTER);
            add(title, subtitle1);
        }
        
        private Span createTexte(String text){
            Span span = new Span(text);
            span.getStyle().set("color", "white");
            span.getStyle().set("font-size", "35px");
            span.getStyle().set("text-align", "center");
            return span;
        }
    }

    private class DetailsContent extends VerticalLayout{
        public DetailsContent(){
            setSizeFull();
            addClassName(LumoUtility.JustifyContent.CENTER);
            getStyle().set("background-color", "white");
            getStyle().set("border-radius", "5px");
        }
        
    }

    private class PortalItemList extends Anchor{

        Icon icon;
        Span label;
        String link;
        
        public PortalItemList(String label, String link, Icon icon){
            this.icon = icon;
            this.link = link;
            this.label = new Span(label);
            var item = configureItem();
            add(item);
        }

        public PortalItemList(String label, String link, Icon icon, String target){
            this(label, link, icon);
            setTarget(target);
        }

        private HorizontalLayout configureItem() {
            icon.setSize("20px");
            this.label = new Span(label);

            getStyle().set("border-radius", "5px");
            setClassName("portal-item");
            
            VerticalLayout containerLeft = new VerticalLayout();
            containerLeft.add(icon);
            containerLeft.setWidth("35px");
            VerticalLayout containerRight = new VerticalLayout();
            containerRight.add(label);
            containerRight.addClassName("portal-item-label");

            HorizontalLayout container = new HorizontalLayout();
            container.setMargin(false);
            container.setAlignItems(Alignment.CENTER);
            
            container.add(containerLeft, containerRight);
            container.setFlexGrow(1, containerRight);

            setHref(link);
            setSizeFull();
            return container;
        }  
    }
}
