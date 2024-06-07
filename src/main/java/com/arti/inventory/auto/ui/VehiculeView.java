package com.arti.inventory.auto.ui;

import org.vaadin.crudui.crud.impl.GridCrud;

import com.arti.inventory.MainAppLayout;
import com.arti.inventory.auto.backend.model.MileageHistory;
import com.arti.inventory.auto.backend.model.Vehicule;
import com.arti.inventory.auto.backend.service.MileageHistoryService;
import com.arti.inventory.auto.backend.service.VehiculeService;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;

import jakarta.annotation.security.PermitAll;

@Route(value = "vehicules", layout = MainAppLayout.class)
@PermitAll
public class VehiculeView extends VerticalLayout {

    public VehiculeView(VehiculeService service, MileageHistoryService mileageService) {
        add(new H2("Véhicules ARTI"),
                new Paragraph("Relevé de kilométrage et de consommation des véhicules de l'ARTI"));

        H3 selectedVehiculeName = new H3();
        selectedVehiculeName.getStyle().set("color", "var(--lumo-primary-text-color)");

        VerticalLayout mileageLayout = new VerticalLayout();
        H3 title = new H3("Relevé de kilométrage");
        title.getStyle().set("color", "var(--lumo-secondary-text-color)");
        GridCrud<MileageHistory> mileageCrud = new GridCrud<>(MileageHistory.class);
        mileageCrud.getGrid().setColumns("mileage", "statementDate");
        mileageCrud.getGrid().getColumnByKey("mileage").setRenderer(new ComponentRenderer<>(mileage -> {
            Paragraph paragraph = new Paragraph();
            paragraph.setText(mileage.getMileage() + " km");
            return paragraph;

        })).setHeader("Kilométrage");
        mileageCrud.getGrid().getColumnByKey("statementDate").setHeader("Date de relevé");
        mileageCrud.setAddOperationVisible(false);
        mileageCrud.setDeleteOperationVisible(false);
        mileageCrud.setUpdateOperationVisible(false);
        mileageCrud.setFindAllOperationVisible(false);
        mileageLayout.add(title, selectedVehiculeName, mileageCrud);

        GridCrud<Vehicule> crud = new GridCrud<>(Vehicule.class);
        crud.setCrudListener(service);
        // crud.setDeleteOperationVisible(false);
        crud.getGrid().setColumns("plate", "type", "brand", "model", "color", "currentMileage", "driverName");
        crud.getGrid().getColumnByKey("plate").setHeader("Immatriculation");
        crud.getGrid().getColumnByKey("type").setHeader("Type");
        crud.getGrid().getColumnByKey("brand").setHeader("Marque");
        crud.getGrid().getColumnByKey("model").setHeader("Modèle");
        crud.getGrid().getColumnByKey("color").setHeader("Couleur");
        crud.getGrid().getColumnByKey("currentMileage").setRenderer(new ComponentRenderer<>(vehicule -> {
            String theme = String.format("badge %s", getColorForNumber(vehicule.getCurrentMileage()));
            Paragraph paragraph = new Paragraph();
            paragraph.setText(vehicule.getCurrentMileage() + " km");
            paragraph.getElement().getThemeList().add(theme);
            paragraph.setWidthFull();
            return paragraph;
        })).setHeader("Kilométrage");
        crud.getGrid().getColumnByKey("driverName").setHeader("Conducteur");

        crud.getGrid().getColumns().forEach(column -> column.setAutoWidth(true));
        // Add a value change listener to the grid to display the mileage history of the selected vehicule
        crud.getGrid().asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() != null) {
                selectedVehiculeName.setText(event.getValue().getPlate());
                mileageCrud.getGrid().setItems(mileageService.getVehiculeMileages(event.getValue().getId()));
            }
        });

        crud.getCrudFormFactory().setVisibleProperties("type","plate","brand", "model", "color","fuelType","driverName");
        crud.getCrudFormFactory().setFieldCaptions("Type","Immatriculation","Marque", "Modèle", "Couleur","Type de carburant","Conducteur");
        crud.getCrudFormFactory().setUseBeanValidation(true);

        SplitLayout splitLayout = new SplitLayout();
        splitLayout.setSizeFull();
        splitLayout.addToPrimary(crud);
        splitLayout.addToSecondary(mileageLayout);
        splitLayout.setSplitterPosition(80);

        setSizeFull();
        add(splitLayout);
    }

    public String getColorForNumber(Long number) {
        long remainder = number % 10000;
        long distanceToNextMultiple = 10000 - remainder;

        if (distanceToNextMultiple <= 1500) {
            return "error";
        } else if (distanceToNextMultiple <= 2000) {
            return "warning";
        } else {
            return " ";
        }
    }
}
