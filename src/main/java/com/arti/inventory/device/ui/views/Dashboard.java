package com.arti.inventory.device.ui.views;

import com.arti.inventory.MainAppLayout;
import com.arti.inventory.device.backend.model.DeviceStats;
import com.arti.inventory.device.backend.service.ComputerService;
import com.arti.inventory.device.backend.service.PhoneService;
import com.arti.inventory.device.backend.service.PrinterService;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;

import jakarta.annotation.security.PermitAll;

@Route(value = "dashboard", layout = MainAppLayout.class)
@PageTitle("INV-ARTI | Dasboard")
@PermitAll
public class Dashboard extends VerticalLayout{

    H2 title;

    public Dashboard(ComputerService computerService, 
                        PrinterService printerService, 
                        PhoneService phoneService){

        title = new H2("Dashboard");
        add(title);
        add(new Paragraph("Vue d'ensembe des équipements techniques et réseaux de l'ARTI"));

        HorizontalLayout cardContainer = new HorizontalLayout();
        
        DeviceStats printerStats = printerService.countOnlinePrinters();
        DeviceStats phoneStats = phoneService.getStats();

        Card card = new Card("Ordinateurs", computerService.getDeviceCount().toString(), "computers");
        Card card2 = new Card("Imprimantes", printerStats.getTotalDevices().toString(), printerStats.getOnlineCounts().toString(), printerStats.getOfflineCounts().toString(), "printers");
        Card card3 = new Card("Téléphones IP", phoneStats.getTotalDevices().toString(), phoneStats.getOnlineCounts().toString(), phoneStats.getOfflineCounts().toString(), "phones");

        String totalDevices = String.valueOf(computerService.getDeviceCount() + printerService.getDeviceCount() + phoneService.getDeviceCount());
        Card card4 = new Card("Adresses IP utilisées", totalDevices, "ip");
        
        cardContainer.add(card, card2, card3, card4);
        //cardContainer.add(card2);
        //cardContainer.add(card3);
        cardContainer.setFlexGrow(1, card, card2, card3, card4);
        //cardContainer.setFlexGrow(1, card2);
        //cardContainer.setFlexGrow(1, card3);
        //add(card);
        cardContainer.getStyle().set("width", "100%");
        cardContainer.addClassName(LumoUtility.FlexWrap.WRAP);
        add(cardContainer);
    }

    public class Card extends VerticalLayout {

        Div cardContent = new Div();
        HorizontalLayout statLayout = new HorizontalLayout();
        H1 statNumber;
        H1 statNumber2;
        H1 statNumber3;
        Anchor tableLink;
        String label;
    
        public Card(String label, String stat, String link){
            this.label = label;
            statNumber = new H1(stat);
            tableLink = new Anchor(link, "Voir le tableau");
            
            cardStructure();
            setCustomStyle();
            add(cardContent);
        }

        public Card(String label, String stat, String online, String link){
            this(label, stat, link);
            statNumber2 = new H1(online);
            statNumber2.getElement().getThemeList().add("badge success");
            statNumber2.getStyle().set("font-size", "30px");
            statLayout.add(statNumber2);
        }

        public Card(String label, String stat, String online, String offline, String link){
            this(label, stat, online, link);
            statNumber3 = new H1(offline);
            statLayout.add(statNumber3);
            statNumber3.getElement().getThemeList().add("badge error");
            statNumber3.getStyle().set("font-size", "30px");
        }

        private void cardStructure(){
            // Card Strucutre
            cardContent.add(new Paragraph(label));
            cardContent.add(new Hr());
            cardContent.add(statLayout);
            statLayout.add(statNumber);
            cardContent.add(new Hr());
            cardContent.add(tableLink);
        }

        private void setCustomStyle(){

            statLayout.setWidthFull();
            statLayout.setAlignItems(Alignment.CENTER);
            statLayout.setJustifyContentMode(JustifyContentMode.CENTER);
            
            // Card Style
            statNumber.getStyle().set("font-size", "80px");
            statNumber.getStyle().set("text-align", "center");
            statNumber.getStyle().set("padding", "20px 0px 20px 0px");
            cardContent.setSizeFull();
            
            getStyle().set("box-shadow", "0 1px 2px 0 rgba(0, 0, 0, 0.2), 0 1px 5px 0 rgba(0, 0, 0, 0.19)");
            getStyle().set("border-radius", "2px");
            getStyle().set("min-width", "300px");
            getStyle().set("width", "25%");
        }
    
    }


}
