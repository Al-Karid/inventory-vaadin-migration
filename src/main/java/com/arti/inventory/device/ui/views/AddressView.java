package com.arti.inventory.device.ui.views;

import org.vaadin.crudui.crud.impl.GridCrud;

import com.arti.inventory.MainAppLayout;
import com.arti.inventory.device.backend.model.Address;
import com.arti.inventory.device.backend.service.AddressService;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.function.SerializableBiConsumer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import jakarta.annotation.security.PermitAll;

@Route(value = "/ip", layout = MainAppLayout.class)
@PageTitle("INV-ARTI | Adresses IP")
@PermitAll
public class AddressView extends VerticalLayout {

    H2 title = new H2("Adresses IP");
    Paragraph paragraph = new Paragraph("Repertoire des Adresse IP du réseau informatique de l'ARTI");

    public AddressView(AddressService addressService) {
        add(title, paragraph);

        GridCrud<Address> crud = new GridCrud<>(Address.class);
        crud.getGrid().setColumns("ipv4", "hostname", "deviceType", "connexionMode", "assignedTo", "direction");
        renameComputerDeviceTableHeader(crud);

        // Additional columns
        crud.getGrid().addColumn(createDeviceStatusComponentRenderer()).setHeader("Statut");
        
        crud.getGrid().getColumns().forEach(column -> column.setAutoWidth(true));
        crud.setCrudListener(addressService);

        // Crud form
        crud.getCrudFormFactory().setVisibleProperties("ipv4", "hostname", "deviceType", "connexionMode", "assignedTo", "direction");
        crud.getCrudFormFactory().setFieldCaptions("Adresse IP", "Nom/Hostname", "Type de périphérique", "Connexion", "Bénéficiaire", "Direction");

        setSizeFull();
        add(crud);
    }

    private static final SerializableBiConsumer<Span, Address> deviceStatusComponentUpdater = (span, device) -> {
        Boolean isOnline = device.getOnline();
        String theme = String.format("badge %s", isOnline ? "success" : "error");
        span.getElement().setAttribute("theme", theme);
        span.setText(isOnline ? "En service" : "Désalloué");
    };

    private static ComponentRenderer<Span, Address> createDeviceStatusComponentRenderer() {
        return new ComponentRenderer<>(Span::new, deviceStatusComponentUpdater);
    }

    private void renameComputerDeviceTableHeader(GridCrud<Address> crud){
        crud.getGrid().getColumnByKey("ipv4").setHeader("Adresse IP");
        crud.getGrid().getColumnByKey("deviceType").setHeader("Type de périphérique");
        crud.getGrid().getColumnByKey("connexionMode").setHeader("Connexion");
        crud.getGrid().getColumnByKey("assignedTo").setHeader("Bénéficiaire");
        crud.getGrid().getColumnByKey("direction").setHeader("Direction");
    }

}
