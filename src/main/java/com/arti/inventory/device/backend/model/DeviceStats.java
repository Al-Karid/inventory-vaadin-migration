package com.arti.inventory.device.backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DeviceStats {
    private Integer totalDevices;
    private Integer onlineCounts;
    private Integer offlineCounts;
}