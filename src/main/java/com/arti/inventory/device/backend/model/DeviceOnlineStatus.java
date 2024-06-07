package com.arti.inventory.device.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DeviceOnlineStatus(Boolean status) {}
