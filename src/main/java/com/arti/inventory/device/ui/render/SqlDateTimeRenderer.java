package com.arti.inventory.device.ui.render;

import org.springframework.stereotype.Component;

import com.arti.inventory.device.backend.model.Device;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.function.SerializableBiConsumer;

@Component
public class SqlDateTimeRenderer<T extends Device> {

    private final SerializableBiConsumer<Span, T> sqlDateTimeComponentUpdater;

    public SqlDateTimeRenderer() {
        sqlDateTimeComponentUpdater = (span, device) -> {
            String sqlDateTime = device.getPurchaseDate().toString().split(" ")[0];
            span.setText(sqlDateTime);
        };
    }

    public ComponentRenderer<Span, T> createSqlDateTimeComponentRenderer() {
        return new ComponentRenderer<>(Span::new, sqlDateTimeComponentUpdater);
    }
}
