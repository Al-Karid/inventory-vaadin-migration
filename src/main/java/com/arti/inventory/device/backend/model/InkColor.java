package com.arti.inventory.device.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record InkColor(Long black, Long red, Long yellow, Long cyan) {}
