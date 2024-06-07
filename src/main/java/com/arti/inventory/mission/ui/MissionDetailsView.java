package com.arti.inventory.mission.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.crudui.crud.impl.GridCrud;

import com.arti.inventory.MainAppLayout;
import com.arti.inventory.mission.backend.model.Employee;
import com.arti.inventory.mission.backend.model.Member;
import com.arti.inventory.mission.backend.model.Mission;
import com.arti.inventory.mission.backend.service.EmployeeService;
import com.arti.inventory.mission.backend.service.MemberService;
import com.arti.inventory.mission.backend.service.MissionService;
import com.arti.inventory.mission.ui.component.RenderMoney;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;

import jakarta.annotation.security.PermitAll;

@Route(value = "missions", layout = MainAppLayout.class)
@PermitAll
public class MissionDetailsView extends VerticalLayout implements HasUrlParameter<Long> {

    private Mission mission;

    @Autowired
    private MissionService missionService;

    @Autowired
    private MemberService memberService;

    @Autowired
    EmployeeService employeeService;

    @Override
    public void setParameter(BeforeEvent event, Long missionId) {
        try {
            this.mission = missionService.findOne(missionId);
            if (mission == null) {
                event.rerouteTo("missions");
                return;
            }
        } catch (Exception e) {
            event.rerouteTo("missions");
            return;
        }

        Paragraph missionSubject = new Paragraph();
        missionSubject.setText(mission.getSubject());
        missionSubject.getStyle().set("color", "var(--lumo-primary-text-color)");
        add(
            new H2("Page de détails"), 
            new HorizontalLayout(new Paragraph("Page de détails de la mission:"), missionSubject)
        );

        HorizontalLayout badgeLayout = new HorizontalLayout();
        Paragraph missionType = new Paragraph(MissionService.getMissionType(mission.getType()));
        missionType.getElement().setAttribute("theme", "badge contrast primary");
        Paragraph missionStatus = new Paragraph(MissionService.getMissionStatus(mission.getStatus()));
        missionStatus.getElement().setAttribute("theme",MissionService.getMissionStatusTheme(mission.getStatus()));
        badgeLayout.add(missionType, missionStatus);

        HorizontalLayout detailsLayout = new HorizontalLayout();
        detailsLayout.setWidth("100%");
        MissionDetailsItem location = new MissionDetailsItem("Lieu", mission.getLocation(), "", VaadinIcon.LOCATION_ARROW_CIRCLE_O);
        MissionDetailsItem numberOfDays = new MissionDetailsItem("Durée", String.valueOf(mission.getNumberOfDays()), "jours", VaadinIcon.CALENDAR_CLOCK);
        MissionDetailsItem dateOfDeparture = new MissionDetailsItem("Départ", mission.getDateOfDeparture().toString().split(" ")[0], "", VaadinIcon.CALENDAR_CLOCK);
        MissionDetailsItem dateOfReturn = new MissionDetailsItem("Retour", mission.getDateOfReturn().toString().split(" ")[0], "", VaadinIcon.CALENDAR_CLOCK);
        MissionDetailsItem members = new MissionDetailsItem("Participants", String.valueOf(mission.getMembers().size()), "personne(s)", VaadinIcon.USERS);
        MissionDetailsItem totalBudget = new MissionDetailsItem("Budget", String.format("%,d", mission.getTotalBudget()), "FCFA", VaadinIcon.MONEY);
        detailsLayout.add(location, numberOfDays, dateOfDeparture, dateOfReturn, members, totalBudget);
        
        // Mission members
        GridCrud<Member> crud = new GridCrud<>(Member.class);
        crud.setCrudListener(memberService);
        crud.setRowCountCaption("%d membre(s) trouvé(s)");
        crud.setFindAllOperation(() -> memberService.findMissionMembers(missionId));
        crud.getGrid().setColumns("employee", "dateOfDeparture", "dateOfReturn", "numberOfDays", "transportation", "mobility", "hotelFees", "ressortExpenses", "mobilityGasFees", "totalBudget");

        crud.getGrid().getColumnByKey("employee").setRenderer(new ComponentRenderer<>(member -> {
            Employee employee = member.getEmployee();
            return new Span(employee.getFirstName() + " " + employee.getLastName());
        
        })).setHeader("Participant");

        crud.getGrid().getColumnByKey("dateOfDeparture").setRenderer(new ComponentRenderer<>(member -> {
            return new Span(member.getDateOfDeparture().toString().split(" ")[0]);
        })).setHeader("Départ");

        crud.getGrid().getColumnByKey("dateOfReturn").setRenderer(new ComponentRenderer<>(member -> {
            return new Span(member.getDateOfReturn().toString().split(" ")[0]);
        })).setHeader("Retour");

        crud.getGrid().getColumnByKey("numberOfDays").setRenderer(new ComponentRenderer<>(member -> {
            return new Span(String.format("%02d",member.getNumberOfDays()));
        })).setHeader("Durée");

        crud.getGrid().getColumnByKey("transportation").setRenderer(new ComponentRenderer<>(member -> {
            return new Span(MissionService.getTransportation(member.getTransportation()));
        })).setHeader("Transport");

        crud.getGrid().getColumnByKey("mobility").setRenderer(new ComponentRenderer<>(member -> {
            return new Span(MissionService.getMobility(member.getMobility()));
        })).setHeader("Mobilité");

        crud.getGrid().getColumnByKey("hotelFees").setRenderer(new ComponentRenderer<RenderMoney, Member>(member -> {
            return new RenderMoney(member.getHotelFees());
        })).setHeader("Frais d'hôtel");

        crud.getGrid().getColumnByKey("ressortExpenses").setRenderer(new ComponentRenderer<RenderMoney, Member>(member -> {
            return new RenderMoney(member.getRessortExpenses());
        })).setHeader("Frais de séjour");

        crud.getGrid().getColumnByKey("mobilityGasFees").setRenderer(new ComponentRenderer<RenderMoney, Member>(member -> {
            return new RenderMoney(member.getMobilityGasFees());
        })).setHeader("Carburant");

        crud.getGrid().getColumnByKey("totalBudget").setRenderer(new ComponentRenderer<RenderMoney, Member>(member -> {
            return new RenderMoney(member.getTotalBudget());
        })).setHeader("Budget");

        crud.getGrid().addColumn(new ComponentRenderer<>(mission -> {
            HorizontalLayout layout = new HorizontalLayout();
            Button download1 = new Button(VaadinIcon.DOWNLOAD_ALT.create());
            download1.setTooltipText("Ordre de mission");
            Button download2 = new Button(VaadinIcon.DOWNLOAD_ALT.create());
            download2.setTooltipText("Fiche de mission");
            layout.add(download1, download2);
            return layout;
        })).setHeader("Télécharger");

        crud.getGrid().getColumns().forEach(column -> column.setAutoWidth(true));

        // Form configuration
        crud.getCrudFormFactory().setVisibleProperties("employee", "dateOfDeparture", "dateOfReturn", "transportation", "mobility");
        crud.getCrudFormFactory().setFieldProvider("employee", member -> {
            ComboBox<Employee> employeeComboBox = new ComboBox<>("Participant");
            employeeComboBox.setItems(employeeService.findAll());
            employeeComboBox.setItemLabelGenerator(employee -> employee.getFirstName() + " " + employee.getLastName());
            return employeeComboBox;
        });
        crud.getCrudFormFactory().setFieldCaptions("Participant", "Départ", "Retour", "Transport", "Mobilité");

        // FORM CRUD OPERATIONS
        crud.setAddOperation(member -> {
            Member m = memberService.add(member, mission);
            mission.setTotalBudget(mission.getTotalBudget() + m.getTotalBudget());
            mission.getMembers().add(m);
            totalBudget.setValue(String.format("%,d", mission.getTotalBudget()), "FCFA");
            members.setValue(String.valueOf(mission.getMembers().size()), "personne(s)");
            return m;
        });

        crud.setDeleteOperation(member -> {
            mission.setTotalBudget(mission.getTotalBudget() - member.getTotalBudget());
            mission.getMembers().remove(member);
            totalBudget.setValue(String.format("%,d", mission.getTotalBudget()), "FCFA");
            members.setValue(String.valueOf(mission.getMembers().size()), "personne(s)");
            memberService.delete(member);
        });

        crud.setUpdateOperation(member -> {
            Member m = memberService.add(member, mission);
            mission.setTotalBudget(mission.getTotalBudget() + m.getTotalBudget());
            mission.getMembers().add(m);
            totalBudget.setValue(String.format("%,d", mission.getTotalBudget()), "FCFA");
            return m;
        });

        setSizeFull();
        add(badgeLayout, detailsLayout, new Span("Liste de membres"), crud);
    }

    private class MissionDetailsItem extends VerticalLayout{
        public MissionDetailsItem(String label, String value, String suffix, VaadinIcon icon) {
            setSpacing(false);
            HorizontalLayout layout = new HorizontalLayout();
            Span p = new Span(label);
            layout.add(icon.create(), p);
            p.getStyle().set("font-weight", "bold");
            add(layout, new Paragraph(value + " " + suffix));
        }

        public void setValue(String value, String suffix) {
            ((Paragraph)getComponentAt(1)).setText(value + " " + suffix);
        }
    }
}
