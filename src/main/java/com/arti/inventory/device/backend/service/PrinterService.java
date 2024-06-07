package com.arti.inventory.device.backend.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.vaadin.crudui.crud.CrudListener;

import com.arti.inventory.device.backend.model.DeviceStats;
import com.arti.inventory.device.backend.model.Printer;
import com.arti.inventory.device.backend.model.PrinterDetail;
import com.arti.inventory.device.backend.repository.PrinterRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrinterService implements CrudListener<Printer>, DeviceService {

    @Autowired
    private PrinterRepository repository;

    public PrinterService(PrinterRepository printerRepository) {
        this.repository = printerRepository;
    }

    @Override
    public Collection<Printer> findAll() {
        List<Printer> printers = repository.findAll();
        printers.forEach(printer -> setDetails(printer));
        return printers;
    }

    @Override
    public Printer add(Printer printer) {
        return repository.save(printer);
    }

    @Override
    public Printer update(Printer printer) {
        return repository.save(printer);
    }

    @Override
    public void delete(Printer printer) {
        repository.delete(printer);
    }

    public Printer findOne(Long id) {
        Printer printer = repository.findById(id).orElse(null);
        return setDetails(printer);
    }

    private Printer setDetails(Printer printer) {
        if (printer == null) {
            System.err.println("Printer is null");
            return null;
        }
        RestTemplate restTemplate = new RestTemplateBuilder().build();
        //TODO Fix the URL
        int category = printer.getCategory() != null ? printer.getCategory().getValue() : 0;
        PrinterDetail details = restTemplate.getForObject("http://api:3000/printers?cat="+category+"&url="+printer.getDetailsPage(), PrinterDetail.class);
        printer.setOnline(details.status());
        printer.setDetails(details);
        return printer;
    }

    @Override
    public Integer getDeviceCount() {
        return repository.findAll().size();
    }

    public DeviceStats countOnlinePrinters() {
        List<Printer> printers = repository.findAll();
        printers.forEach(this::setDetails);
        List<Printer> onlinePrinters = printers.stream()
                        .map(this::setDetails)
                        .filter(printer -> printer.getDetails().status())
                        .collect(Collectors.toList());
        DeviceStats stats = new DeviceStats(printers.size(), onlinePrinters.size(), printers.size() - onlinePrinters.size());
        return stats;
    }
}
