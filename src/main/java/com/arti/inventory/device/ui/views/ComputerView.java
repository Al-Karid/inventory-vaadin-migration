package com.arti.inventory.device.ui.views;

import org.vaadin.crudui.crud.impl.GridCrud;

import com.arti.inventory.MainAppLayout;
import com.arti.inventory.device.backend.model.Computer;
import com.arti.inventory.device.backend.service.ComputerService;
import com.arti.inventory.device.ui.component.DeviceStatusRenderer;
import com.arti.inventory.device.ui.render.SqlDateTimeRenderer;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import jakarta.annotation.security.PermitAll;

@Route(value = "computers", layout = MainAppLayout.class)
@PageTitle("INV-ARTI | Ordinateurs")
@PermitAll
public class ComputerView extends VerticalLayout{

    H2 title = new H2("Ordinateurs");
    Paragraph paragraph = new Paragraph("Inventaire des ordinateurs de l'ARTI");

    public ComputerView(ComputerService computerService, 
                        DeviceStatusRenderer<Computer> deviceStatusRenderer,
                        SqlDateTimeRenderer<Computer> sqlDateTimeRenderer){
        
        add(title, paragraph);

        // Crud
        GridCrud<Computer> crud = new GridCrud<>(Computer.class);
        crud.getGrid().setColumns("name","brand","serie","ip","connexionMode","direction","assignedTo");
        renameComputerDeviceTableHeader(crud);

        // Additional columns
        //crud.getGrid().addColumn(sqlDateTimeRenderer.createSqlDateTimeComponentRenderer()).setHeader("Achat");
        //crud.getGrid().addColumn(deviceStatusRenderer.createDeviceStatusComponentRenderer()).setHeader("Statut");
        crud.getGrid().getColumnByKey("ip").setComparator(Computer::getIpToInteger);
        
        crud.getGrid().getColumns().forEach(column -> column.setAutoWidth(true));
        crud.setCrudListener(computerService);
        
        // Crud form
        crud.getCrudFormFactory().setVisibleProperties("name","brand","serie","label","ip","connexionMode","direction","assignedTo");
        crud.getCrudFormFactory().setFieldCaptions("Nom/Hostname","Marque","Numéro de série","Nom commercial","Addresse IP","Mode de connexion","Direction","Bénéficiaire");
        
        setSizeFull();
        add(crud);
    }

    private void renameComputerDeviceTableHeader(GridCrud<Computer> crud){
        crud.getGrid().getColumnByKey("name").setHeader("Nom de l'ordinateur");
        crud.getGrid().getColumnByKey("ip").setHeader("Adresse IP");
        crud.getGrid().getColumnByKey("brand").setHeader("Marque");
        crud.getGrid().getColumnByKey("connexionMode").setHeader("Connexion");
        crud.getGrid().getColumnByKey("serie").setHeader("Numéro de série");
        crud.getGrid().getColumnByKey("direction").setHeader("Direction");
        crud.getGrid().getColumnByKey("assignedTo").setHeader("Bénéficiaire");
    }   
}
