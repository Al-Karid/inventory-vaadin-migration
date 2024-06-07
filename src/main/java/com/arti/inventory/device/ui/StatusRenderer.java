package com.arti.inventory.device.ui;

import com.arti.inventory.device.backend.model.Device;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.function.SerializableBiConsumer;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class StatusRenderer<T extends Device> {

    private final SerializableBiConsumer<Span, T> deviceStatusComponentUpdater = (span, device) -> {
        Boolean isOnline = device.getOnline();
        String theme = String.format("badge %s", isOnline ? "success" : "error");
        span.getElement().setAttribute("theme", theme);
        span.setText(isOnline ? "En ligne" : "Hors ligne");
    };

    public ComponentRenderer<Span, T> createDeviceStatusComponentRenderer() {
        return new ComponentRenderer<>(Span::new, deviceStatusComponentUpdater);
    }

}
