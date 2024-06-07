package com.arti.inventory.device.ui.views;

import org.springframework.beans.factory.annotation.Autowired;

import com.arti.inventory.MainAppLayout;
import com.arti.inventory.device.backend.model.Printer;
import com.arti.inventory.device.backend.service.PrinterService;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;

import jakarta.annotation.security.PermitAll;

@Route(value = "printers", layout = MainAppLayout.class)
@PermitAll
public class PrinterDetailPage extends VerticalLayout implements HasUrlParameter<Long> {

    private Long printerId;
    private Printer printer;

    @Autowired
    PrinterService printerService;
    
    @Override
    public void setParameter(BeforeEvent arg0, Long id) {
        this.printerId = id;
        this.printer = printerService.findOne(printerId);
        
        add(new H1("Détails de l'imprimante"), new H5(printer.getName()));

        HorizontalLayout grid = new HorizontalLayout();
        VerticalLayout leftGrid = new VerticalLayout();
        VerticalLayout rightGrid = new VerticalLayout();
        HorizontalLayout detailsGrid = new HorizontalLayout();

        leftGrid.setWidth("20%");
        rightGrid.setWidth("20%");

        leftGrid.setPadding(false);
        leftGrid.setSpacing(false);
        rightGrid.setPadding(false);
        rightGrid.setSpacing(false);
        detailsGrid.setPadding(false);
        detailsGrid.setSpacing(false);
        
        grid.setWidthFull();
        grid.expand(detailsGrid);
        grid.setSpacing(false);
        grid.add(leftGrid, rightGrid, detailsGrid);

        leftGrid.add(new DetailPane("Adresse IP", printer.getIp()));
        leftGrid.add(new DetailPane("Hostname", printer.getName()));
        leftGrid.add(new DetailPane("Mode de Connexion", printer.getConnexionMode().toString()));
        leftGrid.add(new DetailPane("Fabriacant", printer.getBrand()));
        leftGrid.add(new DetailPane("Numéro de série", printer.getSerie()));
        leftGrid.add(new DetailPane("Bénéficiaire", printer.getAssignedTo()));
        leftGrid.add(new DetailPane("Direction", printer.getDirection()));
        leftGrid.add(new DetailPane("Date d'achat", printer.getPurchaseDate().toString()));

        rightGrid.add(new DetailPane("Cartouche Noire", printer.getInk1()));
        rightGrid.add(new DetailPane("Cartouche Cyan", printer.getInk2()));
        rightGrid.add(new DetailPane("Cartouche Magenta", printer.getInk3()));
        rightGrid.add(new DetailPane("Cartouche Jaune", printer.getInk4()));
        rightGrid.add(new DetailPane("Plus d'info", printer.getDetailsPage()));
        rightGrid.add(new DetailPane("Catégorie", printer.getCategory().toString()));

        if (printer.getDetails().status() == false) {
            detailsGrid.add(new DetailPane("Statut", "Hors ligne", "error"));
        } else if (printer.getDetails().status() == true) {
            detailsGrid.add(new DetailPane("Statut", "En ligne", "success"));
        }
        detailsGrid.add(new DetailPane("Ancre Noire", printer.getDetails().color().black().toString()+"%"));
        detailsGrid.add(new DetailPane("Ancre Cyan", printer.getDetails().color().cyan().toString()+"%"));
        detailsGrid.add(new DetailPane("Ancre Magenta", printer.getDetails().color().red().toString()+"%"));
        detailsGrid.add(new DetailPane("Ancre Jaune", printer.getDetails().color().yellow().toString()+"%"));

        add(grid);

    }

    private class DetailPane extends VerticalLayout {
        
        Span labelSpan;
        Paragraph valueParagraph;
        
        public DetailPane(String label, String value){
            // Setting the values
            labelSpan = new Span(label);
            valueParagraph = new Paragraph(value);
            setCustomStyle();
            add(labelSpan, valueParagraph);
        }

        public DetailPane(String label, String value, String badge){
            // Setting the values
            labelSpan = new Span(label);
            valueParagraph = new Paragraph(value.toString());
            valueParagraph.getElement().getThemeList().add("badge " + badge);
            setCustomStyle();
            add(labelSpan, valueParagraph);
        }

        private void setCustomStyle(){
            // Setting the styles
            this.getThemeList().remove("spacing");
            valueParagraph.getStyle().set("margin", "0px");
            labelSpan.getStyle().set("font-weight", "bold");
            labelSpan.getStyle().set("font-size", "12px");
            labelSpan.setClassName(LumoUtility.TextColor.PRIMARY);
        }
        
    }

}
