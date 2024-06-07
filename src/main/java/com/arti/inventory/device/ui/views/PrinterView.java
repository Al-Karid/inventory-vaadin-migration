package com.arti.inventory.device.ui.views;

import org.vaadin.crudui.crud.impl.GridCrud;

import com.arti.inventory.MainAppLayout;
import com.arti.inventory.device.backend.model.Printer;
import com.arti.inventory.device.backend.service.PrinterService;
import com.arti.inventory.device.ui.component.DeviceStatusRenderer;
import com.arti.inventory.device.ui.render.SqlDateTimeRenderer;
import com.vaadin.flow.component.grid.contextmenu.GridContextMenu;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import jakarta.annotation.security.PermitAll;

@Route(value = "printers", layout = MainAppLayout.class)
@PageTitle("INV-ARTI | Imprimantes")
@PermitAll
public class PrinterView extends VerticalLayout {

    H2 title = new H2("Imprimantes");
    Paragraph subtitle = new Paragraph("Liste des imprimantes de l'ARTI");

    public PrinterView(PrinterService printerService,
                       DeviceStatusRenderer<Printer> deviceStatusRenderer,
                       SqlDateTimeRenderer<Printer> sqlDateTimeRenderer) {

        add(title, subtitle);

        GridCrud<Printer> crud = new GridCrud<>(Printer.class);
        crud.getGrid().setColumns("name","brand","serie","ip","connexionMode","direction","assignedTo");
        renameComputerDeviceTableHeader(crud);

        // Additional columns
        //crud.getGrid().addColumn(sqlDateTimeRenderer.createSqlDateTimeComponentRenderer()).setHeader("Date d'achat");
        crud.getGrid().addColumn(deviceStatusRenderer.createDeviceStatusComponentRenderer()).setHeader("Statut").setSortable(true);

        crud.getGrid().getColumns().forEach(column -> column.setAutoWidth(true));
        crud.setCrudListener(printerService);

        // Crud form
        crud.getCrudFormFactory().setVisibleProperties("name","brand","serie","label","ip","connexionMode","direction","assignedTo","ink1","ink2","ink3","ink4","detailsPage","category");
        crud.getCrudFormFactory().setFieldCaptions("Nom/Hostname","Marque","Numéro de série","Nom commercial","Addresse IP","Mode de connexion","Direction","Bénéficiaire","Cartouche Noire","Cartouche Cyan","Cartouche Magenta","Cartouche Jaune","Page de détails","Catégorie");

        // Add Context Menu
        GridContextMenu<Printer> contextMenu = new GridContextMenu<>(crud.getGrid());
        contextMenu.addItem("Voir les détails", event -> crud.getGrid().getSelectionModel().getFirstSelectedItem().ifPresent(printer -> {
            getUI().ifPresent(ui -> ui.navigate("printers/" + printer.getId()));
        }));

        setSizeFull();
        add(crud);
    }

    private void renameComputerDeviceTableHeader(GridCrud<Printer> crud){
        crud.getGrid().getColumnByKey("name").setHeader("Nom de l'imprimante");
        crud.getGrid().getColumnByKey("ip").setHeader("Adresse IP");
        crud.getGrid().getColumnByKey("brand").setHeader("Marque");
        crud.getGrid().getColumnByKey("connexionMode").setHeader("Connexion");
        crud.getGrid().getColumnByKey("serie").setHeader("Numéro de série");
        crud.getGrid().getColumnByKey("direction").setHeader("Direction");
        crud.getGrid().getColumnByKey("assignedTo").setHeader("Bénéficiaire");
    }  
}
