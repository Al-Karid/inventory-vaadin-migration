package com.arti.inventory.device.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PrinterDetail(Boolean status, String url, InkColor color) {}