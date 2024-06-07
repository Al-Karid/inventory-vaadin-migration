package com.arti.inventory;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.arti.inventory.device.backend.model.Computer;
import com.arti.inventory.device.backend.model.Phone;
import com.arti.inventory.device.backend.model.Printer;
import com.arti.inventory.device.backend.model.PrinterDetail;
import com.arti.inventory.device.backend.repository.ComputerRepository;
import com.arti.inventory.device.backend.repository.PhoneRepository;
import com.arti.inventory.device.backend.repository.PrinterRepository;
import com.arti.inventory.mission.backend.model.Employee;
import com.arti.inventory.mission.backend.model.EmployeeCategory;
import com.arti.inventory.mission.backend.model.Member;
import com.arti.inventory.mission.backend.model.Mission;
import com.arti.inventory.mission.backend.model.MissionType;
import com.arti.inventory.mission.backend.model.Mobility;
import com.arti.inventory.mission.backend.model.Status;
import com.arti.inventory.mission.backend.model.Transportation;
import com.arti.inventory.mission.backend.repository.EmployeeRepository;
import com.arti.inventory.mission.backend.repository.MemberRepository;
import com.arti.inventory.mission.backend.repository.MissionRepository;
import com.github.javafaker.Faker;

@Component
@Profile("dev")
public class AppCmdRunner implements CommandLineRunner{

    private Logger logger = org.slf4j.LoggerFactory.getLogger(AppCmdRunner.class);

    @Autowired
    ComputerRepository repository;

    @Autowired
    PhoneRepository phoneRepository;

    @Autowired
    PrinterRepository printerRepository;

    @Override
    public void run(String... args) throws Exception {
        Computer computer = repository.findAll().get(1);
        System.out.println(computer);

        Phone phone = phoneRepository.findAll().get(1);
        System.out.println(phone);

        testApiCall();
        addMissionDataToDB();
    }

    private void testApiCall(){
        // Call the API here
        List<Printer> printers = printerRepository.findAll().subList(10, 11);
        RestTemplate restTemplate = new RestTemplateBuilder().build();
        //TODO fix the url
        String url = "http://aapi:3005/printers/";

        printers.forEach(printer -> {
            PrinterDetail detail = restTemplate.getForObject(url+printer.getId(), PrinterDetail.class);
            printer.setDetails(detail);
            logger.warn("Printer: {} details {}", printer, printer.getDetails());
        });
    }

    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    MissionRepository missionRepository;
    @Autowired
    MemberRepository memberRepository;
    
    private void addMissionDataToDB(){
        Faker faker = new Faker();
        // Add 10 employees
        for(int i = 0; i < 10; i++){
            Employee employee = new Employee();
            employee.setFirstName(faker.name().firstName());
            employee.setLastName(faker.name().lastName());
            employee.setDepartment(faker.company().profession());
            employee.setPosition(faker.company().profession());
            employee.setEmail(faker.internet().emailAddress());
            employee.setPhotoUrl(faker.internet().avatar());
            employee.setCategory(EmployeeCategory.values()[faker.number().numberBetween(0, EmployeeCategory.values().length)]);
            employeeRepository.save(employee);
        }
        // Add 25 missions
        for(int i = 0; i < 30; i++){
            Mission mission = new Mission();
            mission.setSubject(faker.lorem().sentence(6));
            mission.setType(MissionType.values()[faker.number().numberBetween(0, MissionType.values().length)]);
            mission.setLocation(faker.address().city());
            mission.setDateOfDeparture(faker.date().birthday());
            mission.setDateOfReturn(faker.date().birthday());
            mission.setNumberOfDays(faker.number().numberBetween(1, 30) * 1L);
            mission.setStatus(Status.values()[faker.number().numberBetween(0, Status.values().length)]);
            mission.setTotalBudget(0L);
            missionRepository.save(mission);
        }
        // Add 200 members
        for(int i = 0; i < 200; i++){
            Member member = new Member();
            member.setEmployee(employeeRepository.findAll().get(faker.number().numberBetween(0, 10)));
            member.setMission(missionRepository.findAll().get(faker.number().numberBetween(0, 25)));
            member.setHotelFees(faker.number().numberBetween(10000, 200000) * 1L);
            member.setRessortExpenses(faker.number().numberBetween(10000, 200000) * 1L);
            member.setDateOfDeparture(faker.date().birthday());
            member.setDateOfReturn(faker.date().birthday());
            member.setNumberOfDays(faker.number().numberBetween(1, 30) * 1L);
            member.setTransportation(faker.options().option(Transportation.values()));
            member.setMobility(faker.options().option(Mobility.values()));
            member.setMobilityGasFees(faker.number().numberBetween(10000, 150000) * 1L);
            member.setTotalBudget(member.getHotelFees() + member.getRessortExpenses() + member.getMobilityGasFees() + 0L);
            memberRepository.save(member);
        }

    }

}
