package com.arti.inventory.mission.ui.component;

import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class RenderMoney extends HorizontalLayout {
        
        private static final long serialVersionUID = 1L;
    
        public RenderMoney(Long amount) {
            Span value = new Span();
            value.setText(String.format("%,d", amount));
            add(VaadinIcon.MONEY.create(), value);
        }
}
